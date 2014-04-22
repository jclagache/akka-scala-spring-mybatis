package akka.data

import scala.collection.mutable.{MutableList => List}


class Container(var ref: String,
                var cargoRef: String = "",
                var goods: List[Good] = List.empty[Good]) {

  // Constructor for myBatis-spring
  def this(ref: String, cargoRef: String) {
    this(ref, cargoRef, List.empty[Good])
  }

  override def toString: String = {
    "Container[" + ref + ", " + goods.size + " Goods, " + cargoRef + "]"
  }

  def toXML = {
    if(goods.isEmpty) {
      <container ref={ref} />
    } else {
      <container ref={ref}>
        <goods>
          {for(good <- goods) yield good.toXML}
        </goods>
      </container>
    }
  }

}


