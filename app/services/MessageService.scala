package services

import models.Message
import scalikejdbc.{ AutoSession, DBSession }

trait MessageService {

  // DBSessionはImplicit Contextとして暗黙的に渡せるようにする。
  // スコープに存在しない場合は、デフォルト値としてAutoSessionを利用する。
  def create(message: Message)(implicit session: DBSession = AutoSession): Long

  def findAll(implicit s: DBSession = AutoSession): List[Message]

  def findById(id: Long)(implicit s: DBSession = AutoSession): Option[Message]

  def update(id: Long, title: String, body: String)(implicit session: DBSession = AutoSession): Int

  def deleteById(id: Long)(implicit s: DBSession = AutoSession): Int

}
