package akka.actor

import org.joda.time.Interval
import akka.data._
import scala.xml.Node


object Messages {

  case object StartBatch

  case class BuildXml(nodeLabel: String, items: Seq[AnyRef {def toXML: Node}])
  case class WriteXml(node: Node, filename: String)
  case class XmlNode(node: Node)

  case class GetCargoes(interval: Interval)
  case class CargoResult(cargoes: Seq[Cargo])

  case class GetContainers(cargoRef: String)
  case class ContainerResult(cargoRef: String, containers: Seq[Container])

  case class GetGoods(cargoRef: String)
  case class GoodResult(containerRef: String, goods: Seq[Good])
}
