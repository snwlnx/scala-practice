package future

case class Context(message: String)

object ImplicitParameters extends App {

  implicit val ctx = Context("1")
  def printContext(s: String)(implicit ctx: Context): Unit =
    println(ctx.message)

  def printContext2(s: String)(implicit ctx: Context): Unit =
    println(ctx.message)

  printContext("sdf") //Hello implicit
  printContext("sdf") //Hello implicit


}
