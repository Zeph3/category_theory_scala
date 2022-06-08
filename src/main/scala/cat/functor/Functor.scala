package cat.functor

import scala.runtime.Nothing$
import cat.intro.Either

trait Functor[F[_]] {
  def map[A, B](F: F[A])(f: A => B): F[B]
}

trait BiFunctor[F[_, _]] {
  def bimap[A, B, C, D](biFunctor: F[A, C])(f1: A => B, f2: C => D): F[B,D]
}

trait ProFunctor[F[-A,+B]] {
  def dimap[A, B, C, D](fab: F[A, B])(f: C ⇒ A)(g: B ⇒ D): F[C, D]
  def lmap[A, B, C](fab: F[A, B])(f: (C) ⇒ A): F[C, B]
  def rmap[A, B, C](fab: F[A, B])(f: (B) ⇒ C): F[A, C]
}

object Functor {
  val customOptionFunctor: Functor[CustomOption] = new Functor[CustomOption] {
    override def map[A, B](customOption: CustomOption[A])(f: A => B): CustomOption[B] = customOption match {
      case CustomSome(a) => CustomSome(f(a))
      case CustomNone => CustomNone
    }
  }
  val customEither: BiFunctor[Either] = new BiFunctor[cat.intro.Either] {
    override def bimap[A, B, C, D](biFunctor: cat.intro.Either[A, C])(f1: A => B, f2: C => D): cat.intro.Either[B, D] = biFunctor match {
      case cat.intro.Left(value) => cat.intro.Left(f1(value))
      case cat.intro.Right(value) => cat.intro.Right(f2(value))
    }
  }

  val customProFunctor: ProFunctor[Function1] = new ProFunctor[Function1] {
    override def dimap[A, B, C, D](fab: A => B)(f: C => A)(g: B => D): C => D = x => {
      (f andThen fab andThen g)(x)
    }

    override def lmap[A, B, C](fab: A => B)(f: C => A): C => B = x => {
      (f andThen fab)(x)
    }

    override def rmap[A, B, C](fab: A => B)(f: B => C): A => C = x => {
      (fab andThen f)(x)
    }
  }
}
