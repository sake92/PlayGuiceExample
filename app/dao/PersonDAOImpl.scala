package dao

import javax.inject.Inject
import javax.inject.Singleton
import scala.concurrent.Future
import play.api.Configuration
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.db.NamedDatabase
import models.Person
import slick.driver.JdbcProfile

@Singleton
class PersonDAOImpl @Inject() (@NamedDatabase("mydb") protected val dbConfigProvider: DatabaseConfigProvider, conf: Configuration) extends PersonDAO with HasDatabaseConfigProvider[JdbcProfile] with Tables {
  import driver.api._

  val loadedDb = conf.getString("slick.dbs.mydb.db.url")//just for demo, should be "blah" in PersonDAOImplSpec
  println("LOADED CONFIG FOR DB: " + loadedDb)
  
  def findAll(): Future[Seq[Person]] =
    db.run(persons.result).map { res => println("Hello from real DAO!"); res }

  def insert(p: Person): Future[Int] =
    db.run(persons += p)

  def createTables(): Future[Unit] =
    db.run(persons.schema.create)
}

trait Tables { self: HasDatabaseConfigProvider[JdbcProfile] =>
  import driver.api._

  class PersonsTable(tag: Tag) extends Table[Person](tag, "PERSON") {
    def id = column[Int]("ID", O.PrimaryKey)
    def name = column[String]("NAME")
    def age = column[Int]("AGE")
    def * = (id, name, age) <> (Person.tupled, Person.unapply _)
  }

  lazy val persons = TableQuery[PersonsTable]
}