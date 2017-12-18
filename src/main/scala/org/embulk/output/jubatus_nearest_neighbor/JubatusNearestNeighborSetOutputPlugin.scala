package org.embulk.output.jubatus_nearest_neighbor

import java.util

import org.embulk.config.{ConfigDiff, ConfigSource, TaskReport, TaskSource}
import org.embulk.spi.{Exec, OutputPlugin, Schema, TransactionalPageOutput}

class JubatusNearestNeighborSetOutputPlugin extends OutputPlugin {

  override def transaction(config: ConfigSource,
                           schema: Schema,
                           taskCount: Int,
                           control: OutputPlugin.Control): ConfigDiff = {
    val task = config.loadConfig(classOf[PluginTask])
    control.run(task.dump())
    Exec.newConfigDiff
  }

  override def open(taskSource: TaskSource,
                    schema: Schema,
                    taskIndex: Int): TransactionalPageOutput = {
    null
  }

  override def resume(taskSource: TaskSource,
                      schema: Schema,
                      taskCount: Int,
                      control: OutputPlugin.Control): ConfigDiff =
    throw new UnsupportedOperationException(
      "key to redis output plugin does not support resuming")

  override def cleanup(taskSource: TaskSource,
                       schema: Schema,
                       taskCount: Int,
                       successTaskReports: util.List[TaskReport]): Unit = {}

}
