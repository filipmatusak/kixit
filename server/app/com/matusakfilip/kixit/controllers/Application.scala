package com.matusakfilip.kixit.controllers

import javax.inject._

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.model.ws.TextMessage
import akka.stream.Materializer
import com.matusakfilip.kixit.{NodeGuardian, PlayerActor, Settings, WebSocketFactory}
import com.matusakfilip.kixit.shared.SharedMessages
import com.matusakfilip.kixit.shared.SharedMessages.WSMessage
import com.matusakfilip.kixit.shared.Upickle._
import com.typesafe.config.ConfigFactory
import play.api.libs.streams.ActorFlow
import play.api.mvc._
import upickle.default._


@Singleton
class Application @Inject()(cc: ControllerComponents)(implicit system: ActorSystem, mat: Materializer) extends AbstractController(cc) {
  val settings = new Settings()

  val nodeGuardian = system.actorOf(Props(new NodeGuardian))

  def index = Action {
    Ok(views.html.index())
  }

  def socket = WebSocket.accept[String,String]{ request =>
    WebSocketFactory.create{ out =>
      PlayerActor.props(nodeGuardian, out)
    }
  }

}
