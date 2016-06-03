package dao

import models.Person

class PersonDAOMock extends PersonDAO {

  val persons = Seq(
    Person(
      id = 1,
      name = "Mocked Person",
      age = 100
    ))

  def findAll: Seq[Person] = {
    println("Hello from mock DAO!")
    persons
  }

}