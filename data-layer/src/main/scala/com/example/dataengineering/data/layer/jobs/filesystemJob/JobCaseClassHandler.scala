package com.example.dataengineering.data.layer.jobs.filesystemJob

import com.example.dataengineering.data.layer.datasources.fileSystemSource.{
  Load,
  UserData,
  Users
}
import org.apache.spark.sql.{Dataset, Encoders, Row, SparkSession}

trait JobCaseClassHandler {
  def get(tableName: String,
          inputPath: String,
          spark: SparkSession,
          saveMode: String,
          outputPath: String,
          metadata: Boolean)
    : Dataset[_ >: Users with UserData with Row <: Serializable] = {

    tableName match {
      case "Users" =>
        new Load[Users](tableName,
                        inputPath,
                        spark,
                        saveMode,
                        outputPath,
                        metadata)(Encoders.product[Users]).Load

      case "UserData" =>
        new Load[UserData](tableName,
                           inputPath,
                           spark,
                           saveMode,
                           outputPath,
                           metadata)(Encoders.product[UserData]).Load

      case _ =>
        spark.emptyDataFrame
    }
  }
}
