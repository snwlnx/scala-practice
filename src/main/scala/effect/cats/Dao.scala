package effect.cats

import cats.effect.{Bracket, Concurrent, IO, Timer}
import doobie.util.transactor.Transactor
import shapeless.{::, HNil}
import cats.implicits._
import doobie._
import doobie.implicits._
import doobie.h2._
import shapeless._

case class Book(id: Int, title: String, author: String)

object Dao {
  type BracketThrow[F[_]] = Bracket[F, Throwable]
}

trait Dao[F[_]] {
  def getBooks: F[List[Book]]
}


class BookDaoImpl[F[_]: Bracket[*[_], Throwable]: Timer](xa: Transactor[F])
  extends Dao[F] {

  def getBooks: F[List[Book]] = {
    sql"select * from books".query[Int :: String :: String :: HNil]
      .to[List].transact(xa)
      .map(_.map(hlist => Book(hlist.head, hlist.tail.head, hlist.tail.tail.head)))
  }
}
