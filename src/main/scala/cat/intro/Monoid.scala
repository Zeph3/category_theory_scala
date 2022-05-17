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
}
