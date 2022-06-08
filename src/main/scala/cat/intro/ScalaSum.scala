package cat.intro

import scala.util.Either

sealed trait Either[+A, +B] {
  def isLeft: Boolean
  def isRight: Boolean
  def map[C](f: B => C): Either[A, C]
  def flatMap[A1 >: A, C](f: B => Either[A1,C]): Either[A1, C]
}

final case class Right[+A, +B](value: B) extends Either[A, B] {
  override def isLeft: Boolean = false
  override def isRight: Boolean = true
  override def map[C](f: B => C): Either[A, C] = Right(f(value))
  override def flatMap[A1 >: A, C](f: B => Either[A1,C]): Either[A1, C] = f(value)
}

final case class Left[+A, +B](value: A) extends Either[A, B] {
  override def isLeft: Boolean = false
  override def isRight: Boolean = true
  override def map[C](f: B => C): Either[A, C] = this.asInstanceOf[Either[A, C]]
  override def flatMap[A1 >: A, C](f: B => Either[A1,C]): Either[A1, C] = this.asInstanceOf[Either[A, C]]
}



object Scala3SumTypes {
  trait Role {
    def name: String
  }

  case class Wizard(name: String) extends Role

  case class Priest(name: String) extends Role

  case class Assassin(name: String) extends Role


  def intro(role: Wizard | Priest | Assassin): String = {
    role match {
      case Wizard(r) => s"I am a $r, I can use my arcane knowledge to summon fireballs and electric storms"
      case Priest(r) => s"I am a $r, I can tend to your wounds and strengthen your will"
      case Assassin(r) => s"I am a $r, I sneak in the shadows and deal with my foes before they can react"
    }
  }

  type Role2 = Assassin | Priest | Wizard
}
