package cat.intro

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class CustomEitherTest extends AnyFunSpec with Matchers {
  val r: Either[String, Int] = Right(5)
  val l: Either[String, Int] = Left("ERROR_NOT_FOUND")

  r.map(x => x + 1) shouldBe Right(6)
  l.map(x => x + 1) shouldBe Left("ERROR_NOT_FOUND")
}
