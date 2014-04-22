package akka.actor.worker

import akka.actor.Actor
import akka.actor.Messages._
import akka.dao.mybatis.mapper.ContainerDao
import scala.collection.JavaConverters._


class ContainerWorker extends Actor {

  var containerDao: ContainerDao = _

  def receive = {
    case GetContainers(cargoRef) =>
      sender ! ContainerResult(cargoRef,containerDao.findAllInCargo(cargoRef).asScala)
  }

}
