package controllers

import forms.MessageForm
import play.api.data.Forms._
import play.api.data._
import play.api.mvc.Controller

trait MessageControllerSupport { this: Controller =>

  protected val form = Form(
    mapping(
      "id"   -> optional(longNumber),
      "body" -> nonEmptyText
    )(MessageForm.apply)(MessageForm.unapply)
  )

}
