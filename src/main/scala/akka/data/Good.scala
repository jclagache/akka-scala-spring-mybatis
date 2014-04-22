package akka.data


class Good(var ref: String,
           var goodType: String,
           var containerRef: String = "",
           var quantity: Int = 1) {

  // Constructor for myBatis-spring
  def this(ref: String, goodType: String, containerRef: String, quantity: Integer) {
    this(ref, goodType, containerRef, quantity.toInt)
  }

  override def toString: String = {
    "Good[" + ref + ", " + quantity + " " + goodType + "]"
  }

  def toXML = {
    <good ref={ref} type={goodType} quantity={quantity.toString} />
  }

}




