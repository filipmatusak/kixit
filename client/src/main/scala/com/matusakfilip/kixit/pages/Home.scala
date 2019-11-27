package com.matusakfilip.kixit.pages

import com.matusakfilip.kixit.Main
import com.matusakfilip.kixit.Main._
import com.matusakfilip.kixit.shared.SharedMessages.{CreateGameRequest, JoinGame}
import org.scalajs.dom.raw.Element
import com.matusakfilip.kixit.shared.Upickle._
import org.scalajs.dom
import upickle.default._

import scalatags.JsDom.all._

object Home {
  val gameName: dom.html.Input = input(

  ).render

  val content: Element = {
    div(
      div(
        button(
          "Create game",
          onclick := { () =>
            send(CreateGameRequest)
          }
        )
      ),
      div(
        button(
          "Join game",
          onclick := { () =>
            send(JoinGame(gameName.value.toInt))
          }
        ),
        gameName
      )
    )
  }.render
}
