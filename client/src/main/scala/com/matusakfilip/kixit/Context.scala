package com.matusakfilip.kixit

import org.scalajs.dom
import org.scalajs.dom.document

class Context {
  val content = document.getElementById("content")

  def set(node: dom.Node): Unit = {

    content.innerHTML = ""

    content.appendChild(node)
  }

}
