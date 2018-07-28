package models

import java.time.ZonedDateTime

import scalikejdbc._, jsr310._
import skinny.orm._

/**
  * Message
  */
case class Message(id: Option[Long],
                   title: Option[String],
                   body: String,
                   createAt: ZonedDateTime,
                   updateAt: ZonedDateTime)

object Message extends SkinnyCRUDMapper[Message] {

  override def tableName = "messages"

  override def defaultAlias: Alias[Message] = createAlias("m")

  override def extract(rs: WrappedResultSet, n: ResultName[Message]): Message =
    autoConstruct(rs, n)

  private def toNamedValues(record: Message): Seq[(Symbol, Any)] = Seq(
    'title    -> record.title,
    'body     -> record.body,
    'createAt -> record.createAt,
    'updateAt -> record.updateAt
  )

  def create(message: Message)(implicit session: DBSession): Long =
    createWithAttributes(toNamedValues(message): _*)

  def update(message: Message)(implicit session: DBSession): Int =
    updateById(message.id.get).withAttributes(toNamedValues(message): _*)

}
