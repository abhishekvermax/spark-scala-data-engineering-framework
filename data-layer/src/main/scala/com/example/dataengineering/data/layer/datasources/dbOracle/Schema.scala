package com.example.dataengineering.data.layer.datasources.dbOracle

import java.time.LocalDateTime

import com.example.dataengineering.data.layer.schemas.LoaderSchema

case class SampleOracleTable1() extends LoaderSchema {
  override def timestamp: String = LocalDateTime.now().toString
}
case class SampleOracleTable2() extends LoaderSchema {
  override def timestamp: String = LocalDateTime.now().toString
}
