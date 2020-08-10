package collections

object ForComprehension extends App {

  case class Person(name: String, age: Int)

  val persons: List[Person] = List(Person("jhon", 24))

  def filterAge: List[String] = for {
    p <- persons if p.age > 30
  } yield p.name

  println(filterAge)

  /** Рассмотрим задачу: задано положительное целое число n,
   * необходимо найти все возможные пары положительных чисел (i, j) таких что:
   * 1 <= j < i < n и i + j - четное число */

  def generatePairs(n: Int): List[(Int, Int)] = ???

  println(generatePairs(5))

}
