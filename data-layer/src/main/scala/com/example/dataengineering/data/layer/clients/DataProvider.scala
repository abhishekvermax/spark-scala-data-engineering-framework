package com.example.dataengineering.data.layer.clients

import com.example.dataengineering.data.layer.schemas.LoaderSchema
import org.apache.spark.sql.{Dataset, Encoder}

trait Metadata[T] {
  def sparkWareHouseMetadata(): Unit
}
trait Data[T] {
  def provideData: Dataset[T]
}

trait DataProvider[T] extends Metadata[T] with Data[T] {}
