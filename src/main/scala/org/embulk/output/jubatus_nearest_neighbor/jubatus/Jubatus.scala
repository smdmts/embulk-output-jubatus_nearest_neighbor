package org.embulk.output.jubatus_nearest_neighbor.jubatus

import us.jubat.common.`type`.TBool
import us.jubat.common.{ClientBase, Datum}

case class Jubatus(host: String, port: Int, name: String, timeoutSec: Int) {
  val client = new ClientBase(host, port, name, timeoutSec)
  def send(id: String, row: Datum): Boolean =
    client.call("set_row", TBool.instance, Array[AnyRef](id, row))
}
