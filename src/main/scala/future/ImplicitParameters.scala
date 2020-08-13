package future

object ImplicitParameters extends App {

  case class Context(message: String)

  def printContext(implicit ctx: Context): Unit =
    println(ctx.message)

  //implicit val ctx = Context("Hello implicit")

  //printContext //Hello implicit

}
