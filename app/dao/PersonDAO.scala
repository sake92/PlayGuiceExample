package dao

import scala.concurrent.Future
import models.Person

trait PersonDAO {
  
  def findAll: Future[Seq[Person]]
  
  def insert(p: Person): Future[Int]
  
  def createTables(): Future[Unit]
}