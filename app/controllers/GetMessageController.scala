package controllers

import javax.inject.{ Inject, Singleton }

import models.Message
import play.api.i18n.{ I18nSupport, MessagesApi }
import play.api.mvc.{ Action, AnyContent, Controller }

@Singleton
class GetMessageController @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {

  def index(messageId: Long): Action[AnyContent] = Action { implicit request =>
    val message = Message.findById(messageId).get
    Ok(views.html.show(message))
  }

}
