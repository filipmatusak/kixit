package com.matusakfilip.kixit

import akka.actor.{Actor, ActorRef}
import com.matusakfilip.kixit.Messages._
import com.matusakfilip.kixit.shared.SharedMessages._

import scala.collection.mutable

class GameActor(listener: ActorRef, gameId: Int, player1Id: Int, player1: ActorRef) extends Actor {
  var player2: ActorRef = _
  var player2Id: Int = _
  var turn = 1
  var player1Choice: Option[GameObject] = None
  var player2Choice: Option[GameObject] = None
  var score1 = 0
  var score2 = 0
  var draws = 0
  val leavedPlayers = mutable.Set[ActorRef]()

  override def receive: Receive = starting

  lazy val starting: Receive = {
    case JoinGameId(playerId, playerActor, gameId_) =>
      println("joining")
      if(gameId_ == gameId){
        player2 = playerActor
        player2Id = playerId
        context.become(running)
        player2 ! GameJoined(gameId, self)
        println("joined")
        player1 ! GameStarted
        player2 ! GameStarted
      }
  }

  lazy val running: Receive = {
    case PlayerTurn(_, obj) =>
      if(sender() == player1) player1Choice = Some(obj)
      if(sender() == player2) player2Choice = Some(obj)
      if(player1Choice.nonEmpty && player2Choice.nonEmpty) evaluateTurn()
    case LeaveGame =>
      leavedPlayers.add(sender())
      if(leavedPlayers.size == 2){
        listener ! DeleteGame(gameId)
        context.stop(self)
      }
  }

  def evaluateTurn(): Unit = {
    val winner = (player1Choice.get, player2Choice.get) match {
      case (Scissors, Paper) => 1
      case (Paper, Scissors) => 2
      case (Paper, Rock) => 1
      case (Rock, Paper) => 2
      case (Rock, Scissors) => 1
      case (Scissors, Rock) => 2
      case _ => -1
    }

    if(winner == 1) score1 += 1
    if(winner == 2) score2 += 1
    if(winner == -1) draws += 1

    val winnerId = if(winner == 0) -1 else if(winner == 1) player1Id else player2Id

    player1 ! TurnResult(player1Choice.get, player2Choice.get, score1, score2, draws, winner == 1, winner == -1)
    player2 ! TurnResult(player2Choice.get, player1Choice.get, score2, score1, draws, winner == 2, winner == -1)

    player1Choice = None
    player2Choice = None
    turn += 1
  }
}

object GameActor {
  trait GameState
  case object Starting extends GameState
}
