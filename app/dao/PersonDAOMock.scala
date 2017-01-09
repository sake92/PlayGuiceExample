package dao

import scala.concurrent.Future
import models.Person

class PersonDAOMock extends PersonDAO {

  val persons = Seq(
    Person(
      id = 1,
      name = "Mocked Person",
      age = 100
    ))

  override def findAll: Future[Seq[Person]] = Future.successful {
    println("Hello from mock DAO!")
    persons
  }
  
  override def insert(p: Person): Future[Int] = Future.successful(1)
  
  override def createTables(): Future[Unit] = Future.successful{()}

}