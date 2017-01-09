package controllers

import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.inject.bind
import dao._
import org.scalatest.TestData
import play.api.Application
import dao.PersonDAO
import dao.PersonDAOMock
import play.api.inject.guice.GuiceableModule.fromPlayBinding
import com.typesafe.config.ConfigFactory
import org.scalatest.concurrent.ScalaFutures

/**
 * with OneAppPerTest
 */
class PersonControllerSpec extends PlaySpec with OneAppPerTest with ScalaFutures {

  override def newAppForTest(testData: TestData): Application = new GuiceApplicationBuilder()
    .overrides(bind(classOf[PersonDAO]).to(classOf[PersonDAOMock]))
    .build

  "Find all route" should {
    "return all persons" in {
      route(app, FakeRequest(GET, "/persons")).map(status(_)) mustBe Some(OK)
      
      // this should return PersonDAOMock, because we've overriden it
      val app2PersonDao = play.api.Application.instanceCache[PersonDAO]
      val personDAO: PersonDAO = app2PersonDao(app)
      val personsFuture = personDAO.findAll
      
      whenReady(personsFuture){ persons =>
        persons.map(_.name) must contain("Mocked Person")
      }
      
      // this should be only available in test,
      // from application.test.conf
      val cf = ConfigFactory.load()
      info(cf.getString("my.key"))
    }
  }
}

/**
 * with OneAppPerSuite
 */
class PersonControllerSpec2 extends PlaySpec  with OneAppPerSuite with ScalaFutures{

  implicit override lazy val app = new GuiceApplicationBuilder()
    .overrides(bind(classOf[PersonDAO]).to(classOf[PersonDAOMock]))
    .build

  "Find all route" should {
    "return all persons" in running(app) {
      route(app, FakeRequest(GET, "/persons")).map(status(_)) mustBe Some(OK)
      
      // this should return PersonDAOMock, because we've overriden it
      val app2PersonDao = play.api.Application.instanceCache[PersonDAO]
      val personDAO: PersonDAO = app2PersonDao(app)
      val personsFuture = personDAO.findAll
      
      whenReady(personsFuture){ persons =>
        persons.map(_.name) must contain("Mocked Person")
      }
    }
  }
}

/**
 * 'manually' creating app...
 */
class PersonControllerSpec3 extends PlaySpec with ScalaFutures{

  val app = new GuiceApplicationBuilder()
    .overrides(bind(classOf[PersonDAO]).to(classOf[PersonDAOMock]))
    .build

  "Find all route" should {
    "return all persons" in running(app) {
      route(app, FakeRequest(GET, "/persons")).map(status(_)) mustBe Some(OK)
      
      // this should return PersonDAOMock, because we've overriden it
      val app2PersonDao = play.api.Application.instanceCache[PersonDAO]
      val personDAO: PersonDAO = app2PersonDao(app)
      val personsFuture = personDAO.findAll
      
      whenReady(personsFuture){ persons =>
        persons.map(_.name) must contain("Mocked Person")
      }
    }
  }
}