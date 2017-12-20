package org.embulk.output.jubatus_nearest_neighbor.jubatus

import org.scalatest.FlatSpec
import us.jubat.common.Datum

class JubatusSpec extends FlatSpec {
  it should "be" in {
    val jubatus = Jubatus("localhost", 9199, "test", 1000)
    jubatus.send("abc", new Datum().addString("a", "abc"))
  }
}

