package controllers

import javax.inject.{ Inject, Singleton }

import models.Message
import play.api.i18n.{ I18nSupport, MessagesApi }
import play.api.mvc.{ Action, AnyContent, Controller }

@Singleton
class GetMessagesController @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {

  def index: Action[AnyContent] = Action { implicit request =>
    val result = Message.findAll()
    Ok(views.html.index(result))
  }

}
