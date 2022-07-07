package cat.monad.domain

trait ValidationError {
  def message: String
}

case object UsernameContainsForbiddenSymbols extends ValidationError {
  override def message: String = "Username contains forbidden symbols, only alphanumerics characters are allowed"
}

case object UserIsUnderage extends ValidationError {
  override def message: String = "You have not yet reached the required age of 14, play with legos until you are ready"
}

case object PasswordRequirementsNotMet extends ValidationError {
  override def message: String = "A password must contain a minimum of eight characters, at least one letter, one number and one special character"
}

case object InvalidMailFormat extends ValidationError {
  override def message: String = "Invalid mail format xxx@yyy.zz"
}