package org.embulk.output.jubatus_nearest_neighbor

import org.embulk.config.{TaskReport, TaskSource}
import org.embulk.spi._

case class PageOutput(taskSource: TaskSource, schema: Schema)
    extends TransactionalPageOutput {

  override def add(page: Page) = {
    val reader: PageReader = new PageReader(schema)
    reader.setPage(page)
    while (reader.nextRecord()) {
      //TBD
    }
  }

  override def finish(): Unit = ()
  override def close(): Unit = ()
  override def commit(): TaskReport = Exec.newTaskReport
  override def abort(): Unit = ()
}
