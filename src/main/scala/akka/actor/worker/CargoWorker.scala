package akka.actor.worker

import akka.actor.Actor
import akka.actor.Messages._
import akka.dao.mybatis.mapper.CargoDao
import scala.collection.JavaConverters._



class CargoWorker extends Actor {

  var cargoDao : CargoDao = _

  def receive = {
    case GetCargoes(interval) =>
      sender ! CargoResult(cargoDao.findAllBetween(interval.getStart.toDate,interval.getEnd.toDate).asScala)
  }

}
