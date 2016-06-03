package dao

import models.Person

class PersonDAOImpl extends PersonDAO {

  val persons = Seq(
    Person(
      id = 1,
      name = "Real Person",
      age = 24
    ))

  def findAll: Seq[Person] = {
    println("Hello from real DAO!")
    persons
  }

}