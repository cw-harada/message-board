package controllers

import javax.inject.{ Inject, Singleton }

import models.Message
import play.api.i18n.{ I18nSupport, Messages, MessagesApi }
import play.api.mvc.{ Action, AnyContent, Controller }
import scalikejdbc.AutoSession

@Singleton
class DeleteMessageController @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {

  def delete(messageId: Long): Action[AnyContent] = Action {
    implicit val session = AutoSession
    val result           = Message.deleteById(messageId)
    if (result > 0) {
      Redirect(routes.GetMessagesController.index())
    } else {
      InternalServerError(Messages("DeleteMessageError"))
    }
  }

}
