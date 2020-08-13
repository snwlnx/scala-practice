package future

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global

object FutureRecover extends App {
  def await[T](f: Future[T]): T = Await.result(f, Duration.Inf)

  /** successful vs apply */

  /** не нарушать контракт */

  /** fromTry */

  /** recover */

/*  Future(6 / 2).recover { case e: ArithmeticException => 0 } // result: 3
  Future(6 / 0).recover { case e: ArithmeticException => 0 } // result: 0
  Future(6 / 0).recover { case e: IllegalArgumentException => 0 } // result: throw exception*/

/*
  val resF = for {
    _ <- Future(throw new Exception("Boom"))
    s <- Future.successful("success")
  } yield s

  print(await(resF))*/
}
