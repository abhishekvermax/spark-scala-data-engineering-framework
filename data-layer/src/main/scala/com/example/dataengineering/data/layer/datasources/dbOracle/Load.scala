package com.example.dataengineering.data.layer.datasources.dbOracle

import com.example.dataengineering.data.layer.clients.Oracle
import com.example.dataengineering.data.layer.datasources.Loader
import com.example.dataengineering.data.layer.schemas.LoaderSchema
import org.apache.spark.sql.{Dataset, Encoder, SparkSession}

class Load[T <: LoaderSchema: Encoder](val spark: SparkSession,
                                       val host: String,
                                       val port: Int,
                                       val userName: String,
                                       val password: String,
                                       val serviceName: String,
                                       val databaseName: String,
                                       val tableName: String,
                                       val saveMode: String)
    extends Loader[T] {

  val dbracleInstance: Oracle[T] = new Oracle[T](spark,
                                                 host,
                                                 port,
                                                 userName,
                                                 password,
                                                 serviceName,
                                                 databaseName,
                                                 tableName,
                                                 saveMode)

  override def loadData: Dataset[T] = dbracleInstance.provideData.as[T]

  override def loadSparkWareHouseMetaData(): Unit =
    loadData.write.mode(saveMode).saveAsTable(tableName)
}
