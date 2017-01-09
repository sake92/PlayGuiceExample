package modules

import javax.inject.Inject
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.Try
import com.google.inject.AbstractModule
import dao.PersonDAO
import dao.PersonDAOImpl
import models.Person
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import dao.Tables

class ExampleModule extends AbstractModule {

  override def configure() = {
    bind(classOf[PersonDAO]).to(classOf[PersonDAOImpl])
    bind(classOf[InitialData]).asEagerSingleton() // fill db with initial data
  }

}

class InitialData @Inject() (personDAO: PersonDAO) {
  val p = Person(1, "Sake", 24)
  val insertInitialDataFuture = for {
     _ <- personDAO.createTables()
    res <- personDAO.insert(p)
  }yield res
  Try(Await.result(insertInitialDataFuture, Duration.Inf))
}