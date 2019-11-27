package com.matusakfilip.kixit


import com.matusakfilip.kixit.pages.{Game, Home, WaitingGame}
import org.scalajs.dom.raw.WebSocket
import org.scalajs.dom.window
import upickle.default.{ReadWriter => RW}
import com.matusakfilip.kixit.shared.SharedMessages._
import com.matusakfilip.kixit.shared.Upickle._
import upickle.default._

object Main {
  val context = new Context

  val wsUrl = s"ws://${window.location.host}/ws"
  val ws = new WebSocket(wsUrl)

  def send(wSMessage: WSMessage): Unit =
    ws.send(write(wSMessage))

  ws.onmessage = { msg =>
    val parsed: WSMessage = read[WSMessage](msg.data.toString)
    println(parsed)
    parsed match {
      case GameCreatedId(id) => WaitingGame(id)
      case JoinedGame(_) =>
      case GameStarted => Game()
      case res: TurnResult => Game.turnFinished(res)
    }
  }

  def main(args: Array[String]): Unit = {
    context.set(Home.content)
  }

}
