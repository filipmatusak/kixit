package com.matusakfilip.kixit

import akka.actor.{Actor, ActorRef, Props}
import com.matusakfilip.kixit.Messages._
import com.matusakfilip.kixit.shared.SharedMessages._

import scala.collection.mutable
import scala.util.Random

class NodeGuardian extends BaseActor {
  val games = mutable.Map[Int, ActorRef]()

  override def receive: Receive = {
    case CreateGame(playerId, playerActor) =>
      val gameId = Random.nextInt(100000)
      val gameActor = context.actorOf(Props(new GameActor(self, gameId, playerId, playerActor)))
      games.put(gameId, gameActor)
      println("creating game")
      sender ! GameCreated(gameId, gameActor)
    case msg@JoinGameId(_, _, gameId) =>
      games.get(gameId) match {
        case Some(gameActor) =>
          println("join")
          gameActor forward msg
        case _ =>
      }
    case DeleteGame(gameId) =>
      games.remove(gameId)
  }

}
