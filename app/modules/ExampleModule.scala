package modules

import com.google.inject.AbstractModule

import dao.PersonDAO
import dao.PersonDAOImpl

class ExampleModule extends AbstractModule {

  override def configure() = {
    bind(classOf[PersonDAO]).to(classOf[PersonDAOImpl])
  }

}
