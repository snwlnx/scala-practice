package future

import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, ExecutionContextExecutor, Future}

object ExecutionContextsExamples extends App {

  def await[T](f: Future[T]): T = Await.result(f, Duration.Inf)

  /** single thread thread pool */

  /*  implicit val ex: ExecutionContext =
      ExecutionContext.fromExecutor(Executors.newSingleThreadExecutor())*/
  //implicit val ex = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(2))
  /*
    def work: Future[Unit] = Future{
      println(Thread.currentThread())
      Thread.sleep(3000)
    }

    println("main-" + Thread.currentThread())
    val start: Long = System.currentTimeMillis()
    println("start")

    val f1F = work
    val f2F = work
    val f3F = work

    await(for {
      f1 <- f1F
      f2 <- f2F
      f3 <- f3F
    } yield ())

    println("end - " + (System.currentTimeMillis() - start))*/


  //print(await(resF))

  /** Count runnable in for comprehension */
  val counter = new AtomicInteger(1)

  def maxCount = 2

  implicit val ex = new ExecutionContext {
    private val realEx = scala.concurrent.ExecutionContext.global

    override def execute(runnable: Runnable): Unit = {
      if (counter.get() <= maxCount) {
        counter.incrementAndGet()
        realEx.execute(runnable)
      }
      else {
        throw new Exception("thread exhausted")
      }
    }

    override def reportFailure(cause: Throwable): Unit = ???
  }

  val resF = for {
    s1 <- Future.successful("1")
    s2 <- {
      val ss = s1 + "2"
      Future.successful("2")
    }
  } yield s1 + "2"

  print(await(resF))

}
