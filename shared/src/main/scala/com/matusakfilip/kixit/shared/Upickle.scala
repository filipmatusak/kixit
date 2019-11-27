package com.matusakfilip.kixit.shared

import upickle.default.{ReadWriter => RW, macroRW}
import SharedMessages._

object Upickle {
  implicit val rwError: RW[Error.type] = macroRW
  implicit val rwCreateGameRequest: RW[CreateGameRequest.type] = macroRW
  implicit val rwGameCreatedId: RW[GameCreatedId] = macroRW
  implicit val rwJoinGame: RW[JoinGame] = macroRW
  implicit val rwJoinedGame: RW[JoinedGame] = macroRW
  implicit val rwGameStarted: RW[GameStarted.type ] = macroRW
  implicit val rwRock: RW[Rock.type] = macroRW
  implicit val rwPaper: RW[Paper.type] = macroRW
  implicit val rwScissors: RW[Scissors.type] = macroRW
  implicit val rwGameObject: RW[GameObject] = macroRW
  implicit val rwSelectObject: RW[SelectObject] = macroRW
  implicit val rwTurnResult: RW[TurnResult] = macroRW
  implicit val rwWSMessage: RW[WSMessage] = macroRW

}
