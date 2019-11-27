package com.matusakfilip.kixit.pages

import org.scalajs.dom
import org.scalajs.dom.html.Paragraph
import org.scalajs.dom.raw.Element
import com.matusakfilip.kixit.Main._

import scalatags.JsDom.all._

object WaitingGame {
  val gameIdBox: Paragraph = p().render

  val content: Element = {
    div(
      gameIdBox
    )
  }.render

  def apply(gameId: Int): Unit = {
    context.set(content)
    gameIdBox.innerText = "Game id: " + gameId.toString
  }
}
