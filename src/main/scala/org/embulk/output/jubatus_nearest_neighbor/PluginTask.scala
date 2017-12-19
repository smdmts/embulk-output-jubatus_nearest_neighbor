package org.embulk.output.jubatus_nearest_neighbor

import org.embulk.config.{Config, ConfigDefault, Task}
import org.embulk.spi.time.TimestampFormatter

trait PluginTask extends Task with TimestampFormatter.Task {

  @Config("host")
  @ConfigDefault("\"127.0.0.1\"")
  def getHost: String

  @Config("port")
  @ConfigDefault("9199")
  def getPort: Int

  @Config("name")
  def getName: String

  @Config("timeout")
  @ConfigDefault("1000")
  def getTimeOut: Int

  @Config("key_column_name")
  def getKeyColumnName: String

}
