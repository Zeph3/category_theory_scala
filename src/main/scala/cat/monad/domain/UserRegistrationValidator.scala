package cat.monad.domain

import cat.monad.Monad_K
import cat.monad.domain.ValidationError
import cat.monad.Monad_K.eitherMonad_K
import cat.monad.KleisliOps

import scala.language.postfixOps

object UserRegistrationValidator {
  def validateUsername(username: String): Either[ValidationError, String] =
    Either.cond(username.matches("^[a-zA-Z0-9]+$"), username, UsernameContainsForbiddenSymbols)

  def validatePassword(password: String): Either[ValidationError, String] =
    Either.cond(password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$"), password, PasswordRequirementsNotMet)

  def validateAge(age: Int): Either[ValidationError, Int] =
    Either.cond(age > 14, age, UserIsUnderage)

  def validateMail(mail: String): Either[ValidationError, String] =
    Either.cond(mail.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"), mail, InvalidMailFormat)


  def validateRegistration(username: String, password: String, age: Int, mail: String): Either[ValidationError, UserRegistration] = {

    val result = for {
      validatedUsername <- validateUsername(username)
      validatedPassword <- validatePassword(password)
      validMail <- validateMail(mail)
      validatedAge <- validateAge(age)
    } yield UserRegistration(username, password, age, mail)
    if (result.isLeft) {
      println(": " + result.left)
      println(": " + result.left)
      result
    } else {
      // save result to DB
      result.foreach(u => println(s"We welcome you ${u.name}"))
      result
    }
  }

//  def validateRegistration_K(username: String, password: String, age: Int, mail: String): Either[ValidationError, UserRegistration] = {
//    // won't work, types don't connect
//     validateUsername _ >=> validatePassword >=> validateAge >=> validateMail
//  }
}