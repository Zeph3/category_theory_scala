package cat.monad

import cat.functor.{CustomNone, CustomOption, CustomSome, Functor}
import cat.monad.Monad_K.EitherM
import cat.monad.domain.ValidationError

trait Monad_K[M[_]] extends Functor[M] {
  def >=>[A,B,C](f:A => M[B], g:B => M[C]): A => M[C]
  def pure[A](a: A): M[A]
}

trait Monad_F[M[_]] extends Functor[M] {
  def flatten[A](F: M[M[A]]): M[A]
  def pure[A](a: A): M[A]
}

trait Monad[M[_]] extends Functor[M] {
  def flatMap[A, B](m: M[A])(f: A => M[B]): M[B]
  def pure[A](a: A): M[A]
}

object Monad_K {
  val customOption_K: Monad_K[CustomOption] = new Monad_K[CustomOption] {
    override def map[A, B](F: CustomOption[A])(f: A => B): CustomOption[B] = F match {
      case CustomSome(value) => CustomSome(f(value))
      case _ => CustomNone
    }


    override def >=>[A, B, C](f: A => CustomOption[B], g: B => CustomOption[C]): A => CustomOption[C] = x => {
      f(x) match {
        case CustomSome(a) => g(a)
        case CustomNone => CustomNone
      }
    }

    override def pure[A](a: A): CustomOption[A] = CustomSome(a);
  }

    type EitherM[A] = Either[ValidationError, A]

    val eitherMonad_K: Monad_K[EitherM] = new Monad_K[EitherM] {
      override def >=>[A, B, C](f: A => EitherM[B], g: B => EitherM[C]): A => EitherM[C] = x => {
        f(x) match {
          case Left(value) => Left(value).asInstanceOf[EitherM[C]]
          case Right(value) => g(value)
        }
      }

      override def pure[A](a: A): EitherM[A] = Right(a)

      override def map[A, B](F: EitherM[A])(f: A => B): EitherM[B] = F.map(f)
    }
}

implicit class KleisliOps[A, B, C](f: A => EitherM[B]) {
  def >=>(g: B => EitherM[C]): A => EitherM[C] = x => {
    f(x) match {
      case Right(a) => g(a)
      case Left(b) => Left(b)
    }
  }
}
