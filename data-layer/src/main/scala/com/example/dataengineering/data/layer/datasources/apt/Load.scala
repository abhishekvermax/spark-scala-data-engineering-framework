package com.example.dataengineering.data.layer.datasources.apt

import com.example.dataengineering.data.layer.clients.Oracle
import com.example.dataengineering.data.layer.datasources.Loader
import com.example.dataengineering.data.layer.schemas.LoaderSchema
import org.apache.spark.sql.{Dataset, Encoder, Encoders, SparkSession}


class Load[T<: LoaderSchema: Encoder](val spark: SparkSession,
                                      val host: String,
                                      val port: Int,
                                      val userName: String,
                                      val password: String,
                                      val serviceName: String,
                                      val databaseName: String,val tableName:String)
    extends Loader[T] {

  val aptOracleInstance: Oracle[T] = new Oracle[T](spark,
                                             host,
                                             port,
                                             userName,
                                             password,
                                             serviceName,
                                             databaseName,tableName)

  override def Load: Dataset[T] = {
    aptOracleInstance.provideData.as[T]
  }
}
