package org.embulk.output.jubatus_nearest_neighbor

import com.google.common.base.Optional
import org.embulk.config.{TaskReport, TaskSource}
import org.embulk.output.jubatus_nearest_neighbor.column.SetValueColumnVisitor
import org.embulk.output.jubatus_nearest_neighbor.jubatus.Sender
import org.embulk.spi._
import org.embulk.spi.time.TimestampFormatter

case class PageOutput(taskSource: TaskSource, schema: Schema, keyName: String)
    extends TransactionalPageOutput {
  val task: PluginTask = taskSource.loadTask(classOf[PluginTask])
  val sender = Sender(
    JubatusNearestNeighborSetOutputPlugin.jubatus.getOrElse(
      sys.error("could not find jubatus")))
  def timestampFormatter(): TimestampFormatter =
    new TimestampFormatter(task, Optional.absent())

  override def add(page: Page): Unit = {
    val reader: PageReader = new PageReader(schema)
    reader.setPage(page)
    while (reader.nextRecord()) {
      val setValueVisitor = SetValueColumnVisitor(reader, timestampFormatter())
      schema.visitColumns(setValueVisitor)
      val value = setValueVisitor.getValue
      sender(value(keyName).toString, value.filterKeys(_ != keyName))
    }
    reader.close()
  }

  override def finish(): Unit = ()
  override def close(): Unit = ()
  override def commit(): TaskReport = Exec.newTaskReport
  override def abort(): Unit = ()
}
