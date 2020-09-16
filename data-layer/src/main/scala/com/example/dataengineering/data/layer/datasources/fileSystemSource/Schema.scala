package com.example.dataengineering.data.layer.datasources.fileSystemSource

import java.time.LocalDateTime

import com.example.dataengineering.data.layer.schemas.LoaderSchema

case class SampleParquet1(
    name: String,
    favorite_color: String,
    favorite_numbers: Array[Int]
) extends LoaderSchema {

  override def timestamp: String = LocalDateTime.now().toString
}
case class SampleParquet2() extends LoaderSchema {
  override def timestamp: String = LocalDateTime.now().toString
}
