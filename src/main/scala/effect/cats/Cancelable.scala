package effect.cats

import scala.concurrent.Await
import scala.concurrent.duration.{Duration, DurationInt}

object Cancelable extends App {

  import cats.effect.{ContextShift, IO}

  import scala.concurrent.ExecutionContext

  implicit val timer = IO.timer(ExecutionContext.global)
  implicit val contextShift: ContextShift[IO] = IO.contextShift(ExecutionContext.global)

  IO().timeout(1.seconds)

  val launchMissiles: IO[Unit] = {
    IO.sleep(1.seconds) *> IO(println("work"))
  }

  val runToBunker = IO.raiseError(new Exception("boom!"))
  //val runToBunker = IO.unit

  val f: IO[Unit] = for {
    fiber <- launchMissiles.start
    _ <- runToBunker.handleErrorWith { error =>
      // Retreat failed, cancel launch (maybe we should
      // have retreated to our bunker before the launch?)
      fiber.cancel *> IO(println("failed"))
    }
    aftermath <- fiber.join
  } yield aftermath

  Await.result(f.unsafeToFuture(), Duration.Inf)
  print("finished")


  IO(println("sdfd")).runCancelable(???).unsafeRunSync()
}