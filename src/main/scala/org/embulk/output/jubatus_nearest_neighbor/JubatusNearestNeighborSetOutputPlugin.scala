package org.embulk.output.jubatus_nearest_neighbor

import java.util

import org.embulk.config.{ConfigDiff, ConfigSource, TaskReport, TaskSource}
import org.embulk.output.jubatus_nearest_neighbor.jubatus.Jubatus
import org.embulk.spi.{Exec, OutputPlugin, Schema, TransactionalPageOutput}

class JubatusNearestNeighborSetOutputPlugin extends OutputPlugin {

  override def transaction(config: ConfigSource,
                           schema: Schema,
                           taskCount: Int,
                           control: OutputPlugin.Control): ConfigDiff = {
    val task = config.loadConfig(classOf[PluginTask])
    JubatusNearestNeighborSetOutputPlugin.createJubatusInstance(task)
    control.run(task.dump())
    Exec.newConfigDiff
  }

  override def open(taskSource: TaskSource,
                    schema: Schema,
                    taskIndex: Int): TransactionalPageOutput = {
    val task = taskSource.loadTask(classOf[PluginTask])
    PageOutput(taskSource, schema, task.getKeyColumnName)
  }

  override def resume(taskSource: TaskSource,
                      schema: Schema,
                      taskCount: Int,
                      control: OutputPlugin.Control): ConfigDiff =
    throw new UnsupportedOperationException(
      "this output plugin does not support resuming")

  override def cleanup(taskSource: TaskSource,
                       schema: Schema,
                       taskCount: Int,
                       successTaskReports: util.List[TaskReport]): Unit = {}

}

object JubatusNearestNeighborSetOutputPlugin {
  var jubatus: Option[Jubatus] = None
  def createJubatusInstance(task: PluginTask): Unit = {
    JubatusNearestNeighborSetOutputPlugin.jubatus = Some(
      Jubatus(task.getHost, task.getPort, task.getName, task.getTimeOut)
    )
  }
}
