package akka.actor.worker

import akka.actor.Actor
import akka.actor.Messages._
import scala.xml.PrettyPrinter
import java.io.FileWriter


class XmlWorker extends Actor {

  def receive = {
    case BuildXml(nodeLabel, items) =>
      val node = <root>{items.map(_.toXML)}</root>
      sender ! XmlNode(node.copy(label = nodeLabel))

    case WriteXml(node, fileName) =>
      val pprinter = new PrettyPrinter(256, 2)
      val file = new FileWriter(fileName)
      println(s"write $fileName")
      file.write(pprinter.format(node))
      file.close()
  }

}
