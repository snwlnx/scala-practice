package future

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object FutureConstructors extends App {

  /** successful ~ unit */
  val f: Future[Int] = Future.successful{
    Thread.sleep(3000)
    1
  }

  /** apply */
  val f2: Future[Int] = Future {
    Thread.sleep(3000)
    1
  }

  /** await */

/*  val f3: Future[String] = Future {
    println(Thread.currentThread())
    Thread.sleep(3000)
    "f3 succcess"
  }

  val res3: String = Await.result(f3, Duration.Inf)
  println(Thread.currentThread())
  println(res3)*/

}
