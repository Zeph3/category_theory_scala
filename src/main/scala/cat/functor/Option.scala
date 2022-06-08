package cat.functor

trait CustomOption[+A] {
  def get: A
  def getOrElse[B >: A](other: => B): B
  def map[B](f: A => B): CustomOption[B]
  def flatMap[B](f: A => CustomOption[B]): CustomOption[B]
}

case class CustomSome[A](a: A) extends CustomOption[A] {
  override def get: A = a

  override def getOrElse[B >: A](other: => B): B = a

  override def map[B](f: A => B): CustomOption[B] = CustomSome(f(a))

  override def flatMap[B](f: A => CustomOption[B]): CustomOption[B] = f(a)
}

object CustomNone extends CustomOption[Nothing] {
  override def get = throw new Exception("Empty")

  override def getOrElse[B >: Nothing](other: => B): B = other

  override def map[B](f: Nothing => B): CustomOption[B] = this

  override def flatMap[B](f: Nothing => CustomOption[B]): CustomOption[B] = this
}
