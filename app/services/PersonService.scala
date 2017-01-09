package services

import javax.inject.Inject
import scala.concurrent.Future
import dao.PersonDAO
import models.Person

class PersonService @Inject() (personDAO: PersonDAO) {
  
  def findAll: Future[Seq[Person]] = personDAO.findAll
  
}