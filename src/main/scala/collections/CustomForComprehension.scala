package collections

object CustomForComprehension extends App {

  class Foo[R] {???}

  val someFoo = new Foo[String]
  val someBar = new Foo[String]

/*  for {
    f <- someFoo
    b <- someBar
  } yield f + b
*/

}
