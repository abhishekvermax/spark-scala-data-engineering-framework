package com.example.dataengineering.data.layer.clients

import com.example.dataengineering.data.layer.schemas.LoaderSchema
import com.example.dataengineering.data.layer.output.Writer
import org.apache.spark.sql.{Dataset, Encoder, SparkSession}

class FileSystem[T <: LoaderSchema: Encoder](val inputPath: String,
                                             val spark: SparkSession,
                                             val saveMode: String,
                                             val tableName: String)
    extends DataProvider[T]
    with Writer {

  override def provideData(metadata: Boolean,
                           outputPath: String): Dataset[T] = {
    lazy val providedDataDS: Dataset[T] =
      spark.read.parquet(inputPath).as[T].cache()
    writeParquet(providedDataDS, outputPath + "/" + tableName)
    if (metadata) {
      providedDataDS.write.mode("append").saveAsTable(tableName)
      providedDataDS
    } else providedDataDS
  }

}
