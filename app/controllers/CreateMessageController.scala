package controllers

import java.time.ZonedDateTime
import javax.inject.{ Inject, Singleton }

import models.Message
import play.api.i18n.{ I18nSupport, Messages, MessagesApi }
import play.api.mvc.{ Action, AnyContent, Controller }
import services.MessageService

@Singleton
class CreateMessageController @Inject()(val messagesApi: MessagesApi, messageService: MessageService)
    extends Controller
    with I18nSupport
    with MessageControllerSupport {

  def index: Action[AnyContent] = Action { implicit request =>
    Ok(views.html.create(form))
  }

  def create: Action[AnyContent] = Action { implicit request =>
    form
      .bindFromRequest()
      .fold(
        formWithErrors => BadRequest(views.html.create(formWithErrors)), { model =>
          val now     = ZonedDateTime.now()
          val message = Message(None, Some(model.title), model.body, now, now)
          val result  = messageService.create(message)
          if (result > 0) {
            Redirect(routes.GetMessagesController.index())
          } else {
            InternalServerError(Messages("CreateMessageError"))
          }
        }
      )
  }

}
