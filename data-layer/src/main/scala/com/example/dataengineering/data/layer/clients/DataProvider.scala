package com.example.dataengineering.data.layer.clients

import com.example.dataengineering.data.layer.schemas.LoaderSchema
import org.apache.spark.sql.{Dataset, Encoder}


trait DataProvider[T] {
  def provideData: Dataset[T]
}
