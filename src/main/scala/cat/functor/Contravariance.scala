package cat.functor

import cat.functor.{Person, Company}

object Contravariance {

  // Company => Person => INT

  implicit val userOrdering: Ordering[Person] = Ordering.by(_.age)

  implicit val companyOrdering: Ordering[Company] = userOrdering.on(_.ceo)

  val addOne: Int => Int = _ + 1

  val addOneToPerson: Person => Int = addOne.contramap(_.age)

  println(addOneToPerson(Person("Zafer", 28)))
}

case class Person(name: String, age: Int)

case class Company(ceo: Person, cfo: Person, cto: Person, employees: List[Person])

implicit class Contravariant[-T1, R](fn1: T1 => R) {
  def contramap[T2](f: T2 => T1): T2 => R = x => {
    fn1(f(x))
  }
}