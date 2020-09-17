package com.example.dataengineering.data.layer.datasources

import org.apache.spark.sql.Dataset

trait Loader[T] {
  def Load: Dataset[T]
}
