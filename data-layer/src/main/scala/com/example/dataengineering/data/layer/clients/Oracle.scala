package com.example.dataengineering.data.layer.clients

import com.example.dataengineering.data.layer.schemas.LoaderSchema
import org.apache.spark.sql.{DataFrame, Dataset, Encoder, SparkSession}
//Don't delete JDBC as this will verify ORACLE Connector is
// present, will not compile if not present.
import oracle.jdbc.OracleDriver
import scala.reflect.runtime.{universe => runTimeUniverse}
import scala.reflect.runtime.universe._
class Oracle[T <: LoaderSchema: Encoder](val spark: SparkSession,
                                         val host: String,
                                         val port: Int,
                                         val userName: String,
                                         val password: String,
                                         val serviceName: String,
                                         val databaseName: String,
                                         val tableName: String,
                                         val saveMode: String)
    extends DataProvider[T] {

  override def provideData: Dataset[T] = {

    spark.read
      .format("jdbc")
      .option(
        "url",
        "jdbc:oracle:thin:@" + this.host + ":" + this.port + "/" + this.serviceName)
      .option("dbtable", databaseName + "." + tableName)
      .option("user", this.userName)
      .option("password", this.password)
      .option("driver", "oracle.jdbc.OracleDriver")
      .load()
      .as[T]
  }

  override def sparkWareHouseMetadata(): Unit =
    provideData.write.mode(saveMode).saveAsTable(tableName)
}
