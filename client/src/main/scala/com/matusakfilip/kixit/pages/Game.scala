package com.matusakfilip.kixit.pages

import com.matusakfilip.kixit.Main
import com.matusakfilip.kixit.Main._
import com.matusakfilip.kixit.shared.SharedMessages._
import org.scalajs.dom.raw.Element

import scalatags.JsDom.all._

object Game {
  val yourScoreBox = p().render
  val opponentScoreBox = p().render
  val drawsBox = p().render
  val gameState = p().render

  val content: Element = {
    div(
      yourScoreBox,
      opponentScoreBox,
      drawsBox,

      button(
        "Rock",
        onclick := { () =>
          send(SelectObject(Rock))
        }
      ),
      button(
        "Paper",
        onclick := { () =>
          send(SelectObject(Paper))
        }
      ),
      button(
        "Scissors",
        onclick := { () =>
          send(SelectObject(Scissors))
        }
      ),
      gameState
    )
  }.render

  def turnFinished(result: TurnResult): Unit = {
    yourScoreBox.innerText = "Your score: " + result.yourScore
    opponentScoreBox.innerText = "Opponent score: " + result.opponentScore
    drawsBox.innerText = "Draws: " + result.draws
    gameState.innerText = if(result.draw) "Draw" else if(result.youWon) "You won!" else "You lose!"
  }

  def apply(): Unit = {
    context.set(content)
    yourScoreBox.innerText = "Your score: " + 0
    opponentScoreBox.innerText = "Opponent score: " + 0
    drawsBox.innerText = "Draws: " + 0
  }
}
