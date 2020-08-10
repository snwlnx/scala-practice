package collections

object FlatMap extends App {

  /**
   *  Рассмотрим задачу формирования троек элементов:
   *    List(1, 2, 3) => List(1, 1, 1, 2, 2, 2, 3, 3, 3)
   */

  def tripleElements[T](ys: List[T]): List[Any] = ???

  println(tripleElements(List(1,2,3)))


  /**
    Реализуйте метод filter, используя метод flatMap
   */

  def filterFM(xs: List[Int]): List[Int] = ???
}
