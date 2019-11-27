package com.matusakfilip.kixit

import akka.actor.ActorRef
import com.matusakfilip.kixit.shared.SharedMessages.GameObject

object Messages {
  case class CreateGame(playerId: Int, playerActor: ActorRef)
  case class JoinGameId(playerId: Int, playerActor: ActorRef, gameId: Int)
  case object LeaveGame
  case class DeleteGame(gameId: Int)

  case class GameJoined(gameId: Int, gameActor: ActorRef)
  case class GameCreated(gameId: Int, gameActor: ActorRef)

  case class PlayerTurn(playerId: Int, obj: GameObject)


}
