package future

import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

object ExecutionContextsExamples extends App {

  def await[T](f: Future[T]): T = Await.result(f, Duration.Inf)

  /** single thread thread pool */

  implicit val ex = ExecutionContext.fromExecutor(Executors.newSingleThreadExecutor())
  //implicit val ex = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(3))

  def work: Future[Unit] = ???

  val start: Long = System.currentTimeMillis()
  println("start")

  //print(await(resF))
  println("end - " + (System.currentTimeMillis() - start))


  /** Count runnable in for comprehension */

  //private val realEx = scala.concurrent.ExecutionContext.global
  val counter = new AtomicInteger(1)

  def maxCount = 3

  /*  val resF = for {
      s1 <- Future.successful("1")
      s2 <- Future.successful("2")
      ss = s1 + s2
    } yield ss

    print(await(resF))*/

}
