package com.matusakfilip.kixit

import akka.actor.{Actor, ActorRef, ActorRefFactory, OneForOneStrategy, PoisonPill, Props, Status, SupervisorStrategy, Terminated}
import akka.stream.scaladsl.{Flow, Keep, Sink, Source}
import akka.stream.{Materializer, OverflowStrategy}
import com.matusakfilip.kixit.shared.SharedMessages.WSMessage
import com.matusakfilip.kixit.shared.Upickle._
import upickle.default._


object WebSocketFactory {
  def create(props: ActorRef => Props, bufferSize: Int = 16, overflowStrategy: OverflowStrategy = OverflowStrategy.dropNew)(implicit factory: ActorRefFactory, mat: Materializer): Flow[String, String, _] = {

    val (outActor, publisher) = Source.actorRef[WSMessage](bufferSize, overflowStrategy)
      .map(write(_))
      .toMat(Sink.asPublisher(false))(Keep.both).run()

    Flow.fromSinkAndSource(
      Sink.actorRef(factory.actorOf(Props(new Actor {
        val flowActor = context.watch(context.actorOf(props(outActor), "flowActor"))

        def receive = {
          case Status.Success(_) | Status.Failure(_) => flowActor ! PoisonPill
          case Terminated(_) => context.stop(self)
          case other: String =>
            val parsed = read[WSMessage](other)
            println(parsed)
            flowActor ! parsed
          case other => flowActor ! other
        }

        override def supervisorStrategy = OneForOneStrategy() {
          case _ => SupervisorStrategy.Stop
        }
      })), Status.Success(())),
      Source.fromPublisher(publisher)
    )
  }
}
