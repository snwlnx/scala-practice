package future

import scala.concurrent.{Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object FutureMapping extends App {
  /*
    val f: Future[Int] = Future.successful(1)
    f.map(_ + 1)

    val f2: Future[Int] = f.flatMap(x => Future.successful(x + 1))

    val f3: Future[Future[Int]] = Future.successful(1).map(x => Future.successful(x + 1))

    f.map(x => Future(Thread.sleep(3000)))
      .flatMap(x => Future.successful(()))*/

  /** for comprehension */
  val xF: Future[Int] = Future.successful(1)
  val yF: Future[Int] = Future.successful(2)

  for {
    y <- yF
    x <- xF
  } yield (x + y)


  /** sequence */

  val lf: List[Future[Int]] = List.fill(5)(Future(1))

  val seqF: Future[List[Int]] = Future.sequence(lf)


  /** traverse */
  val traversF: Future[List[Int]] = Future.traverse(List.range(1, 5)) { i =>
    Future(i)
  }

  def select: Future[String] = ???

  def update(s: String): Future[Unit] = ???

  /** parallel start, combining */
  /*
    for {
      s <- select
      x <- update(s + "cheked")
    } yield (x + y)
  */


  trait CallbackM {
    def sucess(v: String)

    def fail(ex: Throwable)
  }

  /** future eager */
  val ff = Future(1)


  ff.onComplete{
    case Success(v) =>
    case Failure(v) =>
  }


}
