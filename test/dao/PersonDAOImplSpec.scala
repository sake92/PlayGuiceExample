package dao

import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.inject.bind
import org.scalatest.TestData
import play.api.Application
import com.typesafe.config.ConfigFactory
import org.scalatest.concurrent.ScalaFutures
import play.api.Configuration
import play.api.db.slick.DatabaseConfigProvider
import slick.profile.BasicProfile
import modules.ExampleModule

class PersonDAOImplSpec extends PlaySpec with OneAppPerSuite with ScalaFutures {

  implicit override lazy val app = new GuiceApplicationBuilder().
    configure(
      "slick.dbs.mydb.driver" -> "slick.driver.H2Driver$",
      "slick.dbs.mydb.db.driver" -> "org.h2.Driver",
      "slick.dbs.mydb.db.url" -> "jdbc:h2:mem:blah;",
      "slick.dbs.mydb.db.user" -> "test",
      "slick.dbs.mydb.db.password" -> "").build

  def personDAO(implicit app: Application): PersonDAO = Application.instanceCache[PersonDAO].apply(app)
  
  "PersonDAO" should {
    "do whatever" in {
      whenReady(personDAO.findAll) { res =>
        println(res)
      }
    }
  }

}