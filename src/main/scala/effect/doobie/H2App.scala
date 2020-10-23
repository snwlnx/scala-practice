package effect.doobie

import cats.effect._
import cats.implicits._
import doobie._
import doobie.implicits._
import doobie.h2._
import shapeless._

object H2App extends IOApp {

  // Resource yielding a transactor configured with a bounded connect EC and an unbounded
  // transaction EC. Everything will be closed and shut down cleanly after use.
  val transactorResource: Resource[IO, H2Transactor[IO]] = for {
    ce <- ExecutionContexts.fixedThreadPool[IO](32) // our connect EC
    be <- Blocker[IO] // our blocking EC
    xa <- H2Transactor.newH2Transactor[IO](
      "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", // connect URL
      "", // username
      "", // password
      ce, // await connection here
      be // execute JDBC operations here
    )
  } yield xa


  val createTable =
    sql"""
         |CREATE TABLE books (
         |   id INT NOT NULL,
         |   title VARCHAR(50) NOT NULL,
         |   author VARCHAR(20) NOT NULL
         |)""".stripMargin

  def insertSql(id: Int, title: String, author: String) =
    sql"INSERT INTO books (id, title, author) values($id, $title, $author)"


  def run(args: List[String]): IO[ExitCode] =
    transactorResource.use { xa =>
      // Construct and run your server here!
      for {
        _ <- createTable.update.run.transact(xa)
        count <- insertSql(1, "War and Peace", "Tolstoy").update.run.transact(xa)
        books <- sql"select * from books".query[Int :: String :: String :: HNil].to[List].transact(xa)
        _ <- IO(println(books))
      } yield ExitCode.Success
    }
}