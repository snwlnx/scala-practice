package collections

object Aggregations extends App {

  val list: List[Int] = List(1, 3, 5, 7, 9)

  /**
   *    List(1, 3, 5, 7, 9).foldLeft(0)(_ + _)
   *    0 + 1 = 1
   *            1 + 3 = 4
   *                    4 + 5 = 9
   *                            9 + 7 = 16
   *                                    16 + 9 = 25 // done
   *
   *    List(1, 3, 5, 7, 9).foldRight(0)(_ + _)
   *    0 + 9 = 9
   *            9 + 7 = 16
   *                    16 + 5 = 21
   *                            21 + 3 = 24
   *                                    24 + 1 = 25 // done
   */

}
