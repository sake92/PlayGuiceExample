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

/**
 * with OneAppPerTest
 */
class PersonControllerSpec extends PlaySpec with OneAppPerTest {

  override def newAppForTest(testData: TestData): Application = new GuiceApplicationBuilder()
    .overrides(bind(classOf[PersonDAO]).to(classOf[PersonDAOMock]))
    .build

  "Find all route" should {
    "return all persons" in {
      route(app, FakeRequest(GET, "/persons")).map(status(_)) mustBe Some(OK)
      
      // this should return PersonDAOMock, because we've overriden it
      val app2PersonDao = play.api.Application.instanceCache[PersonDAO]
      val personDAO: PersonDAO = app2PersonDao(app)
      val persons = personDAO.findAll
      persons.map(_.name) must contain("Mocked Person")
    }
  }
}

/**
 * with OneAppPerSuite
 */
class PersonControllerSpec2 extends PlaySpec  with OneAppPerSuite {

  implicit override lazy val app = new GuiceApplicationBuilder()
    .overrides(bind(classOf[PersonDAO]).to(classOf[PersonDAOMock]))
    .build

  "Find all route" should {
    "return all persons" in running(app) {
      route(app, FakeRequest(GET, "/persons")).map(status(_)) mustBe Some(OK)
      
      // this should return PersonDAOMock, because we've overriden it
      val app2PersonDao = play.api.Application.instanceCache[PersonDAO]
      val personDAO: PersonDAO = app2PersonDao(app)
      val persons = personDAO.findAll
      persons.map(_.name) must contain("Mocked Person")
    }
  }
}

/**
 * 'manually' creating app...
 */
class PersonControllerSpec3 extends PlaySpec {

  val app = new GuiceApplicationBuilder()
    .overrides(bind(classOf[PersonDAO]).to(classOf[PersonDAOMock]))
    .build

  "Find all route" should {
    "return all persons" in running(app) {
      route(app, FakeRequest(GET, "/persons")).map(status(_)) mustBe Some(OK)
      
      // this should return PersonDAOMock, because we've overriden it
      val app2PersonDao = play.api.Application.instanceCache[PersonDAO]
      val personDAO: PersonDAO = app2PersonDao(app)
      val persons = personDAO.findAll
      persons.map(_.name) must contain("Mocked Person")
    }
  }
}