package services

import java.time.ZonedDateTime

import models.Message
import scalikejdbc.DBSession

class MockMessageService extends MessageService {

  override def create(message: Message)(implicit session: DBSession): Long = 1L

  override def findAll(implicit s: DBSession): List[Message] =
    List(Message(Some(1L), Some("a"), "b", ZonedDateTime.now, ZonedDateTime.now))

  override def findById(id: Long)(implicit s: DBSession): Option[Message] =
    Some(Message(Some(1L), Some("a"), "b", ZonedDateTime.now, ZonedDateTime.now))

  override def update(id: Long, title: String, body: String)(implicit session: DBSession): Int = 1

  override def deleteById(id: Long)(implicit s: DBSession): Int = 1
}
