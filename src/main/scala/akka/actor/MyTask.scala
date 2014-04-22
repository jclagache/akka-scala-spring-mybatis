package akka.actor

import org.joda.time.Interval
import akka.actor.{Props, Actor, ActorRef}
import akka.actor.worker._
import akka.routing.{SmallestMailboxPool, RoundRobinPool}
import akka.data.{Container, Cargo}
import scala.collection.mutable
import akka.actor.Messages._
import akka.actor.Messages.BuildXml
import akka.actor.Messages.CargoResult
import akka.actor.Messages.ContainerResult
import akka.actor.Messages.GetCargoes
import akka.actor.Messages.XmlNode
import akka.actor.Messages.GetGoods
import akka.actor.Messages.GetContainers
import akka.actor.Messages.WriteXml
import akka.actor.Messages.GoodResult


class MyTask(interval: Interval) extends Actor {

  var cargoWorkerRouter,containerWorkerRouter,goodWorkerRouter, xmlWorker  : ActorRef = _

  private var _cargoes: Seq[Cargo] = _
  val cargoMap = new mutable.HashMap[String, Cargo]
  val containerMap = new mutable.HashMap[String, Container]

  private var counter = 0

  def receive = {
    case StartBatch =>
      cargoWorkerRouter ! GetCargoes(interval)

    case CargoResult(cargoes) =>
      _cargoes = cargoes
      for(cargo <- cargoes) {
        cargoMap += cargo.ref -> cargo
        containerWorkerRouter ! GetContainers(cargo.ref)
      }

    case ContainerResult(cargoRef, containers) =>
      for(container <- containers) {
        containerMap += container.ref -> container
        goodWorkerRouter ! GetGoods(container.ref)
      }
      cargoMap(cargoRef).containers ++= containers
      counter += containers.size

    case GoodResult(containerRef, goods) =>
      containerMap(containerRef).goods ++= goods
      counter -= 1
      if(counter == 0) {
        xmlWorker ! BuildXml("cargoes", _cargoes)
      }

    case XmlNode(node) =>
      xmlWorker ! WriteXml(node, "result.xml")
      context.system.shutdown()
  }
}
