package com.example.dataengineering.data.layer.datasources.fileSystemSource

import com.example.dataengineering.data.layer.clients.FileSystem
import com.example.dataengineering.data.layer.datasources.Loader
import com.example.dataengineering.data.layer.schemas.LoaderSchema
import org.apache.spark.sql.{Dataset, Encoder, SparkSession}

class Load[T <: LoaderSchema: Encoder](val inputPath: String,
                                       val spark: SparkSession,
                                       val saveMode: String,
                                       val tableName: String)
    extends Loader[T] {

  val fileSystemSourceInstance: FileSystem[T] =
    new FileSystem[T](inputPath, spark, saveMode, tableName)

  override def loadData: Dataset[T] = fileSystemSourceInstance.provideData.as[T]

  override def loadSparkWareHouseMetaData(): Unit =
    loadData.write.mode(saveMode).saveAsTable(tableName)

}
