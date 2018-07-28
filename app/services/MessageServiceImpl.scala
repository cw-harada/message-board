package services

import java.time.ZonedDateTime
import javax.inject.Singleton

import models.Message
import scalikejdbc.DBSession

@Singleton
class MessageServiceImpl extends MessageService {

  override def create(message: Message)(implicit session: DBSession): Long =
    Message.create(message)

  override def findAll(implicit s: DBSession): List[Message] = Message.findAll()

  override def findById(id: Long)(implicit s: DBSession): Option[Message] = Message.findById(id)

  // titleはわざわざNoneで更新することがないので、Option型は採用しない
  override def update(id: Long, title: String, body: String)(implicit session: DBSession): Int =
    Message
      .updateById(id)
      .withAttributes(
        'title    -> title, // Option[String]ではなくStringでも指定可能
        'body     -> body,
        'updateAt -> ZonedDateTime.now()
      )

  override def deleteById(id: Long)(implicit s: DBSession): Int =
    Message.deleteById(id)

}
