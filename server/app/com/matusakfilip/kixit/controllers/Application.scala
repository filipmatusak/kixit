package com.matusakfilip.kixit.controllers

import javax.inject._

import com.matusakfilip.kixit.shared.SharedMessages
import play.api.mvc._

@Singleton
class Application @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index = Action {
    Ok(views.html.index(SharedMessages.itWorks))
  }

}
