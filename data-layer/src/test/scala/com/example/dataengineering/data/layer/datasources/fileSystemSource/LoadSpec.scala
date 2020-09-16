package com.example.dataengineering.data.layer.datasources.fileSystemSource

import com.example.dataengineering.data.layer.SparkSpec
import org.apache.spark.sql.Encoders
import org.scalatest.{FunSpec, GivenWhenThen, Matchers}

class LoadSpec extends FunSpec with SparkSpec with GivenWhenThen with Matchers {

  describe("Column Numbers test for random tables") {

    it("should check all the column numbers of parquet table file") {
      val parquet1Test = new Load[SampleParquet1](
        "/home/abhi/Documents/own_git/spark-scala-data-engineering-framework/data-layer/src/test/scala/com/" +
          "example/dataengineering/data/layer/datasources/fileSystemSource/resources/users",
        ss,
        "overwrite",
        "SampleParquet1"
      )(Encoders.product[SampleParquet1])

      parquet1Test.loadSparkWareHouseMetaData()
      parquet1Test.loadData.columns.length shouldBe 3

    }
  }
}
