package services

import javax.inject.Inject
import models.Person
import dao.PersonDAO

class PersonService @Inject() (personDAO: PersonDAO) {
  
  def findAll: Seq[Person] = personDAO.findAll
  
}