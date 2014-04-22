package akka.config

import org.springframework.scala.context.function.FunctionalConfiguration

import org.springframework.beans.factory.config.BeanDefinition
import akka.actor.worker.{XmlWorker, GoodWorker, ContainerWorker, CargoWorker}
import akka.actor.ActorSystem
import akka.config.extension.SpringExtentionImpl
import akka.dao.mybatis.mapper.{CargoDao, GoodDao, ContainerDao}
import org.springframework.context.ApplicationContext

/**
 * Created by j-c.lagache on 18/04/2014.
 */
class ActorConfig extends FunctionalConfiguration {

  implicit val ctx = beanFactory.asInstanceOf[ApplicationContext]

  /**
   * Actor system singleton for this application.
   */
  val actorSystem = bean() {
    val system = ActorSystem("AkkaSample")
    // initialize the application context in the Akka Spring Extension
    SpringExtentionImpl(system)
    system
  }

  val cargoWorker = prototype("cargoWorker") {
    val cgw = new CargoWorker
    cgw.cargoDao = beanFactory.getBean(classOf[CargoDao])
    cgw
  }

  val containerWorker = prototype("containerWorker") {
    val ctw = new ContainerWorker
    ctw.containerDao = beanFactory.getBean(classOf[ContainerDao])
    ctw
  }

  val goodWorker = prototype("goodWorker") {
    val gw = new GoodWorker
    gw.goodDao = beanFactory.getBean(classOf[GoodDao])
    gw
  }

  val xmlWorker = prototype("xmlWorker") {
    new XmlWorker
  }

}
