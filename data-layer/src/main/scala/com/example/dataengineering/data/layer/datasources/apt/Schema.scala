package com.example.dataengineering.data.layer.datasources.apt

import java.time.LocalDateTime

import com.example.dataengineering.data.layer.schemas.LoaderSchema

case class SampleDataSourceLayer()
      extends LoaderSchema {
    override def timestamp: String = LocalDateTime.now().toString
  }
