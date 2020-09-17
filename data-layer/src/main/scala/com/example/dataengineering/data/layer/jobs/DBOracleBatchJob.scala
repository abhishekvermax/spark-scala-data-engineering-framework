package com.example.dataengineering.data.layer.jobs
import com.example.dataengineering.data.layer.datasources.dbOracle.Load
import com.example.dataengineering.data.layer.schemas.LoaderSchema

import scala.collection.parallel.immutable.ParVector

object DBOracleBatchJob extends SparkSessionForDataLayerJobs {

  private val APP_NAME = getClass.getSimpleName
  private val host = "host"
  private val port = "port"
  private val username = "username"
  private val password = "password"
  private val service_name = "service_name"
  private val output_path = "output_path"

  case class Config(
      host: String = "",
      port: Int = 0,
      username: String = "",
      password: String = "",
      serviceName: String = "",
      outputPath: String = ""
  )

}
