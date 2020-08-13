package future

object MonadOptionExample extends App {

  object Monad {
    def unit[T](x: T): Monad[T] = ???
  }

  trait Monad[T] {
    def flatMap[U](f: T => Monad[U]): Monad[U]
  }

  /** Цепочки вычислений с помощью flatMap */

  def one: Option[Int] = Some(1)
  def two: Option[Int] = Some(2)
  def none: Option[Int] = Some(1)

  /** Проверка монадических законов */

  def squareFunction(x: Int): Option[Int] = Some(x * x) //f
  def incrementFunction(x: Int): Option[Int] = Some(x + 1) //g

  /**
   * Left unit law:
   * (unit(x) flatMap f) == f(x)
   **/
  def leftUnitLaw(): Unit = {
    val monad: Option[Int] = Some(1)
    assert(???)
    println("leftUnitLaw check success")
  }

  leftUnitLaw()

  /**
   * Right unit law:
   * (monad flatMap unit) == monad
   **/
  def rightUnitLaw(): Unit = {
    val monad: Option[Int] = Some(1)
    assert(???)
    println("rightUnitLaw check success")
  }

  rightUnitLaw()

  /**
   * Associativity law:
   * ((monad flatMap f) flatMap g) == (monad flatMap (x => f(x) flatMap g))
   **/
  def associativityLaw(): Unit = {
    val monad: Option[Int] = Some(1)
    val left = ???
    val right = ???
    assert(left == right)
    println("associativityLaw check success")
  }

  associativityLaw()
}