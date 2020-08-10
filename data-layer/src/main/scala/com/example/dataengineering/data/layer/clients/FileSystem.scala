package com.example.dataengineering.data.layer.clients

import com.example.dataengineering.data.layer.schemas.LoaderSchema
import org.apache.spark.sql.{DataFrame, Dataset, Encoder, SparkSession}

import scala.reflect.runtime.{universe => runTimeUniverse}
//Don't delete JDBC as this will verify ORACLE Connector is
// present, will not compile if not present.
import oracle.jdbc.OracleDriver

class FileSystem[T<: LoaderSchema: Encoder](val inputPath: String, val spark: SparkSession)
    extends DataProvider[T] {

  override def provideData: Dataset[T] = {
    spark.read.parquet(inputPath).as[T]
  }
}
