package cat.playground

import cat.intro.ScalaProduct
import cat.intro.Monoid._

object Playground extends App {
  
  ScalaProduct("hello", "worldd")
  
  println(intAdder.combine(intAdder.empty, 5))
  
}
