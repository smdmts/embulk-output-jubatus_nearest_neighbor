package org.embulk.output.jubatus_nearest_neighbor.column

import org.embulk.spi.time.TimestampFormatter
import org.embulk.spi.{Column, PageReader, ColumnVisitor => EmbulkColumnVisitor}

case class SetValueColumnVisitor(reader: PageReader,
                                 timestampFormatter: TimestampFormatter)
    extends EmbulkColumnVisitor {
  import scala.collection.mutable
  private val recordMap = mutable.Map[String, Any]()

  override def timestampColumn(column: Column): Unit =
    value(column, reader.getTimestamp).foreach(v =>
      put(column, timestampFormatter.format(v)))

  override def stringColumn(column: Column): Unit =
    value(column, reader.getString).foreach { v =>
      put(column, v)
    }

  override def longColumn(column: Column): Unit =
    value(column, reader.getLong).foreach(v => put(column, v))

  override def doubleColumn(column: Column): Unit =
    value(column, reader.getDouble).foreach(v => put(column, v))

  override def booleanColumn(column: Column): Unit =
    value(column, reader.getBoolean).foreach(v => put(column, v))

  override def jsonColumn(column: Column): Unit =
    value(column, reader.getJson).foreach { v =>
      put(column, v.toJson)
    }

  def value[A](column: Column, method: => (Column => A)): Option[A] =
    if (reader.isNull(column)) {
      None
    } else {
      Some(method(column))
    }

  def put(column: Column, value: Any): Unit = {
    recordMap.put(column.getName, value)
    ()
  }

  def getValue: Map[String, Any] = recordMap.toMap

}
