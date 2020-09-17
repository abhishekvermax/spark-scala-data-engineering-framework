package com.example.dataengineering.data.layer.jobs.filesystemJob

import java.io.File
import com.example.dataengineering.data.layer.SparkSpec
import org.scalatest.{FunSpec, GivenWhenThen, Matchers}

class FileSystemBatchJobSpec
    extends FunSpec
    with SparkSpec
    with GivenWhenThen
    with Matchers {
  describe("It should run batch job as expected") {
    val outputPath: String = "/home/abhi/Desktop/testOutput/batchJobOut/"
    val tableName = "Users"
    it("Running and validating writing of datasets") {
      FileSystemBatchJob.get(
        tableName,
        "/home/abhi/Documents/own_git/spark-scala-data-engineering-framework/" +
          "data-layer/src/test/scala/com/example/dataengineering" +
          "/data/layer/datasources/fileSystemSource/resources/users",
        ss,
        "overwrite",
        outputPath,
        metadata = true
      )
      Option(new File(outputPath + "/" + tableName).list)
        .map(_.count(_.endsWith(".parquet")))
        .getOrElse(0) shouldBe 1
    }
  }
}
