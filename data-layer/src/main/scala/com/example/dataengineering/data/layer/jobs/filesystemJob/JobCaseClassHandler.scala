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

    lazy val users = new Load[Users](tableName,
                                     inputPath,
                                     spark,
                                     saveMode,
                                     outputPath,
                                     metadata)(Encoders.product[Users]).Load

    lazy val userData =
      new Load[UserData](tableName,
                         inputPath,
                         spark,
                         saveMode,
                         outputPath,
                         metadata)(Encoders.product[UserData]).Load

    tableName match {
      case "Users" => users

      case "UserData" => userData

      case "All" =>
        (new Load[Users](tableName,
                         inputPath + "users",
                         spark,
                         saveMode,
                         outputPath,
                         metadata)(Encoders.product[Users]).Load,
         new Load[UserData](tableName,
                            inputPath + "userdata",
                            spark,
                            saveMode,
                            outputPath,
                            metadata)(Encoders.product[UserData]).Load)
        spark.emptyDataFrame

      case _ => spark.emptyDataFrame
    }
  }
}
