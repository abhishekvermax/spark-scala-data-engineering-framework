package com.example.dataengineering.data.layer.datasources

import org.apache.spark.sql.Dataset

trait data[T] {
  def loadData: Dataset[T]
}
trait metadata[T] {
  def loadSparkWareHouseMetaData(): Unit
}

trait Loader[T] extends metadata[T] with data[T] {}
