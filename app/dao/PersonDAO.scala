package dao

import models.Person

trait PersonDAO {
  
  def findAll: Seq[Person]
  
}