package cat.monad

import cat.functor.{CustomOption,CustomNone, CustomSome, Functor}

trait Monad_K[M[_]] {
  def >=>[A,B,C](f:A => M[B], g:B => M[C]): A => M[C]
  def pure[A](a: A): M[A]
}


object Monad_K {
  val customOption_K: Monad_K[CustomOption] = new Monad_K[CustomOption] {
    override def >=>[A, B, C](f: A => CustomOption[B], g: B => CustomOption[C]): A => CustomOption[C] = x => {
      f(x) match {
        case CustomSome(a) => g(a)
        case CustomNone => CustomNone
      }
    }

    override def pure[A](a: A): CustomOption[A] = CustomSome(a);
  }
}
