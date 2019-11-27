package com.matusakfilip.kixit.shared

object SharedMessages {
  def itWorks = "It works!"

  sealed trait WSMessage

  case object Error extends WSMessage
  case object CreateGameRequest extends WSMessage
  case class GameCreatedId(id: Int) extends WSMessage
  case class JoinGame(id: Int) extends WSMessage
  case class JoinedGame(id: Int) extends WSMessage

  case object GameStarted extends WSMessage

  sealed trait GameObject extends WSMessage
  case object Rock extends GameObject
  case object Paper extends GameObject
  case object Scissors extends GameObject


  case class SelectObject(obj: GameObject) extends WSMessage
  case class TurnResult(yourChoice: GameObject,
                        opponentChoice: GameObject,
                        yourScore: Int,
                        opponentScore: Int,
                        draws: Int,
                        youWon: Boolean,
                        draw: Boolean) extends WSMessage


}
