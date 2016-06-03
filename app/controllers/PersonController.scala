package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import services.PersonService

class PersonController @Inject() (val personService: PersonService) extends Controller {
  
  def findAll = Action {
    val allPersons = personService.findAll
    val allPersonsString = allPersons mkString ","
    Ok(views.html.index(allPersonsString))
  }

}
