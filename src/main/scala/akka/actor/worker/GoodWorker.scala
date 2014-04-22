package akka.actor.worker

import akka.actor.Actor
import akka.actor.Messages._
import akka.dao.mybatis.mapper.GoodDao
import scala.collection.JavaConverters._


class GoodWorker extends Actor {

  var goodDao : GoodDao = _

  def receive = {
    case GetGoods(containerRef) =>
      sender ! GoodResult(containerRef, goodDao.findAllInContainer(containerRef).asScala)
  }

}