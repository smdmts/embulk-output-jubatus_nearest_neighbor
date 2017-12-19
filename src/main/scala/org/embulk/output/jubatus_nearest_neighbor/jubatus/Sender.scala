package org.embulk.output.jubatus_nearest_neighbor.jubatus

import org.embulk.spi.time.Timestamp
import us.jubat.common.Datum

case class Sender(jubatus: Jubatus) {
  def apply(key: String, value: Map[String, Any]): Unit = {
    val datum = new Datum()
    value.foreach {
      case (key: String, value: Any) =>
        value match {
          case v: String => datum.addString(key, v)
          case v: Long =>
            datum.addNumber(key, java.lang.Double.valueOf(v).doubleValue())
          case v: Double =>
            datum.addNumber(key, java.lang.Double.valueOf(v).doubleValue())
          case _: Boolean   => sys.error("not supported Boolean type")
          case _: Timestamp => sys.error("not supported Timestamp type")
          case _            => // pass
        }
    }
    jubatus.send(key, datum)
  }
}
