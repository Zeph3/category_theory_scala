package cat.playground

import cat.functor.{CustomNone, CustomOption, CustomSome}
import cat.intro.ScalaProduct
import cat.intro.Monoid.*

object Playground extends App {

  def giveOption: CustomOption[Int] = CustomNone
  
  ScalaProduct("hello", "worldd")
  
  println(intAdder.combine(intAdder.empty, 5))

  val some: CustomOption[Int] = CustomSome(5)
  val none: CustomOption[Int] = CustomNone
}
