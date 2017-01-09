package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import services.PersonService
import play.api.libs.concurrent.Execution.Implicits.defaultContext

class PersonController @Inject() (val personService: PersonService) extends Controller {

  def findAll = Action.async {
    personService.findAll.map { allPersons =>
      val allPersonsString = allPersons mkString ","
      Ok(views.html.index(allPersonsString))
    }
  }

}
