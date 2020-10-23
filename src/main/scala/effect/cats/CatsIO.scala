package effect.cats

import java.io.{BufferedReader, File}
import java.nio.file.{Files, Paths}

import cats.effect.{Async, Bracket, Concurrent, ContextShift, Effect, Fiber, IO, LiftIO, Sync, SyncIO, Timer}

import scala.concurrent.duration.{Duration, DurationInt}
import scala.concurrent.{Await, ExecutionContext, Future}

object CatsIO extends App {
  /*

    val f = Future {
      Thread.sleep(1000)
    }

    Sync[Future].suspend(f)


    type LazyFuture = () => Future[Unit]

    lazy val fut = () => Future {
      Thread.sleep(1000)
    }


    Sync[LazyFuture].suspend(fut)

    Sync[IO].delay(println("Hello Sync"))

    Sync[IO].defer(IO(println("Hello Sync")))

    IO(println("test")) //DaoImpl

    Sync[IO].delay(println("test")) // Dao

    trait Dao
    class DaoImpl

    IO

    F[_] : Sync
    F[_] : Bracket

    IO   -> ZIO
    F[_] -> G[_]

    type MyEffect[A] = Future[Either[Throwable, A]]

    implicit def myEffectLiftIO: LiftIO[MyEffect] =
      new LiftIO[MyEffect] {
        override def liftIO[A](ioa: IO[A]): MyEffect[A] = {
          ioa.attempt.unsafeToFuture()
        }
      }
  */

  //cb
  /*Async[IO].async {
    case Right() => cb.o
    case Left() => ??
  }*/


  /*  val s = IO("some value").runAsync {
      case Right(str) => Sync[IO].delay(println("done"))
      case Left(ex) => Sync[IO].raiseError(new Exception("error"))
    }.unsafeRunSync()*/


  val syncIO: Unit = IO("some value").unsafeRunAsync {
    case Right(str) => Sync[IO].delay(println("done"))
    case Left(ex) => Sync[IO].raiseError(new Exception("error"))
  }

  val ex = ExecutionContext.global
  implicit val cs: ContextShift[IO] = IO.contextShift(ex)
  implicit val timer: Timer[IO] = IO.timer(ex)

  val dbWork: IO[Unit] = IO.sleep(1.seconds)
  val timeJob: IO[Unit] = timer.sleep(3.seconds)

  Concurrent[IO].racePair(dbWork, timeJob)
    .map(eth => eth.left.map(pair => pair._2.cancel))
    .timeout(3.seconds)

  val res = Await.result(Concurrent[IO].racePair(dbWork, timeJob).unsafeToFuture(), Duration.Inf)

  println(res)

  // F[Either[(A, Fiber[F, B]), (Fiber[F, A], B)]]

  /*

    implicit val ex = ExecutionContext.global
    implicit val cs: ContextShift[IO] = IO.contextShift(ex)
    Concurrent[IO]
  */


  /*  val ioa = IO {
      println("hey!")
    }

    ioa.runAsync()

    Async[IO].async {

    }

    val program: IO[Unit] =
      for {
        _ <- ioa
        _ <- ioa
      } yield ()

    implicit val ctx: ExecutionContext = ???

    implicit val shift: ContextShift[IO] = CatsIO.contextShift(ctx)

    Concurrent[IO]*/
  //=> hey!
  //=> hey!


  /*
  * timer
  * concurrency
  * mvar
  * tagless final
  * resource
  * logging
  * */

}
