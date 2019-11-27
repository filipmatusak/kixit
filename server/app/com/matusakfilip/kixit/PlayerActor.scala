package com.matusakfilip.kixit

import akka.actor.{Actor, ActorRef, Props}
import com.matusakfilip.kixit.Messages._
import com.matusakfilip.kixit.shared.SharedMessages._

import scala.util.Random

class PlayerActor(listener: ActorRef, out: ActorRef) extends Actor {
  val id: Int = Random.nextInt(1000000000)
  var game: ActorRef = _
  var gameId: Int = _

  override def receive: Receive = beforeGame
  /*override def receive: Receive = { case msg =>
    println("coto jest? " + msg)
      out ! "co to jest?" + msg
  }*/

  lazy val beforeGame: Receive = {
    // from web
    case CreateGameRequest =>
      listener ! CreateGame(id, self)
      context.become(waitingForGame)
    case JoinGame(gameId_) =>
      listener ! JoinGameId(id, self, gameId_)
      context.become(waitingForGame)
  }

  lazy val waitingForGame: Receive = {
    // from server
    case GameJoined(gameId_, gameActor) =>
      game = gameActor
      gameId = gameId_
      out ! JoinedGame(gameId)
    case GameCreated(gameId_, gameActor) =>
      game = gameActor
      gameId = gameId_
      out ! GameCreatedId(gameId)
    case GameStarted =>
      out ! GameStarted
      context.become(playing)
  }

  lazy val playing: Receive = {
    // from web
    case SelectObject(obj) =>
      game ! PlayerTurn(id, obj)
    // from server
    case res: TurnResult =>
      out ! res
  }

  override def postStop(): Unit = {
    if(game != null) game ! LeaveGame
  }
}

object PlayerActor {
  def props(listener: ActorRef, out: ActorRef) = Props(new PlayerActor(listener, out))
}
