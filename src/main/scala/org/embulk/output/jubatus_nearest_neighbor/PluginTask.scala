package org.embulk.output.jubatus_nearest_neighbor

import org.embulk.config.{Config, ConfigDefault, Task}

trait PluginTask extends Task {

  @Config("host")
  @ConfigDefault("\"127.0.0.1\"")
  def getHost: String

  @Config("port")
  @ConfigDefault("9199")
  def getPort: Int

  @Config("key")
  def getKeyName: String

  @Config("value_name")
  def getValueName: String

  @Config("value_type")
  def getValueType: String

}

sealed trait ValueType

case object StringType extends ValueType
case object NumberType extends ValueType

object PluginTask {
  def convertValueType(v: String): ValueType = v.toLowerCase match {
    case "string" => StringType
    case "number" => NumberType
    case _        => sys.error("could not support type.")
  }
}
