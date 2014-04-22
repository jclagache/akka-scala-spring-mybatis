package akka.data

import com.github.nscala_time.time.Imports._
import java.text.SimpleDateFormat
import java.util.Date
import scala.collection.mutable.{MutableList => List}


class Cargo(var ref: String,
            var arrival: DateTime,
            var departure: DateTime,
            var containers: List[Container] = List.empty) {

  // Constructor for myBatis-spring
  def this(ref: String, arrival: Date, departure: Date) {
    this(ref, new DateTime(arrival), new DateTime(departure))
  }

  override def toString: String = {
    val f = new SimpleDateFormat("dd/MM/yy HH:mm")
    "Cargo[" + ref + ", (" + f.format(arrival.toDate) + " --> " + f.format(departure.toDate) + "), " + containers.size + " Conteneurs]"
  }

  def toXML = {
    <cargo name={ref}>
      <arrival>{arrival.getMillis}</arrival>
      <departure>{departure.getMillis}</departure>
      {if(containers.nonEmpty) {
      <containers>
        {for (container <- containers) yield container.toXML}
      </containers>}}
    </cargo>
  }

}


