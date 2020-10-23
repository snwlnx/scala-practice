package effect.cats

import cats.effect.Bracket


object DAO1{

  trait Dao[F[_]]

  class DaoImpl[F[_] : Bracket[*[_], Throwable]] extends Dao[F]
}


object DAO2 {

  trait Dao[F[_]]

  class DaoImpl[F[_]](implicit bracket: Bracket[F, Throwable]) extends Dao[F]
}

object DAO3{

  type BracketThrow[F[_]] = Bracket[F, Throwable]

  trait Dao[F[_]]

  class DaoImpl[F[_] : BracketThrow] extends Dao[F]
}
