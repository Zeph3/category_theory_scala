package cat.functor

import org.scalatest._
import matchers.should._
import org.scalatest.funspec.AnyFunSpec
import Functor.customOptionFunctor
import cat.intro.CatBasics._

class CustomOptionTest extends AnyFunSpec with Matchers {
  // Associativity

  customOptionFunctor.map(
    customOptionFunctor.map(
      customOptionFunctor.map(CustomSome(5))
      (multiplyByTwo))(addTwo))(convertToString) shouldBe CustomSome("12")


  // Identity

}
