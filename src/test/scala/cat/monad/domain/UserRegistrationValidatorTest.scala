package cat.monad.domain

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class UserRegistrationValidatorTest extends AnyFunSpec with Matchers  {
  UserRegistrationValidator.
    validateRegistration("Zafer", "Abcdeaafg1?*#", 27, "zafertorun@ing.com").isRight shouldBe true

}
