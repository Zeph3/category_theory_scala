package cat.intro

import CatBasics._
import org.scalatest._
import matchers.should._
import org.scalatest.funspec.AnyFunSpec

class CatBasicsTest extends AnyFunSpec with Matchers {
  // 5 => 6 => 12 => "12"

  addAndMultiplyAndToString(5) shouldBe "12"

  // 5 => 10 => 11 => "11" => "11"
  multiplyAndIdentity(5) shouldBe 10

}
