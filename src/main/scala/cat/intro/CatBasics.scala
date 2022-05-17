package cat.intro

object CatBasics {

  // basic functions
  def addTwo(num: Int): Int = num + 2

  def multiplyByTwo(num: Int): Int = num * 2

  def convertToString(num: Int): String = num.toString

  // Composition
  def compose[A, B, C](f: B => C, g: A => B): A => C = {x => f(g(x))}
  
  def andThen[A, B, C](f: A => B, g: B => C): A => C = {x => g(f(x)) }
  
  
  val addAndMultiplyAndToString: Int => String = convertToString compose addTwo compose multiplyByTwo

  // Rule 1 - Associative
  val addAndMultiplyAndToStringAssociative_1: Int => String = convertToString compose (addTwo compose multiplyByTwo)
  val addAndMultiplyAndToStringAssociative_2: Int => String = (convertToString compose addTwo) compose multiplyByTwo

  // Rule 2 - Identity (left and right)
  val identityAndMultiply: Int => Int = multiplyByTwo compose identity[Int]
  val multiplyAndIdentity: Int => Int = identity[Int] compose multiplyByTwo
}
