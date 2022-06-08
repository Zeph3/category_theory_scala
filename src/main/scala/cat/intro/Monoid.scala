package cat.intro

trait Semigroup[A] {
  def combine(x: A, y: A): A
}

trait Monoid[A] extends Semigroup [A] {
  def empty: A
}

object Monoid {
  val intAdder: Monoid[Int] = new Monoid[Int] {
    override def combine(x: Int, y: Int): Int = x + y

    override def empty: Int = 0
  }

  def foldAnyList[A](l: List[A], m: Monoid[A]): A = {
    l.foldLeft(m.empty)(m.combine)
  }

  def reduceAnyList[A](l: List[A], m: Semigroup[A]): A = {
    // assumption: size > 1, otherwise: UnsupportedOperationException("empty.reduceLeft")
    l.reduce(m.combine)
  }
}
