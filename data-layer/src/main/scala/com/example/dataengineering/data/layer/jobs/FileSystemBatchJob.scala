package com.example.dataengineering.data.layer.jobs

import scala.collection.parallel.immutable.ParVector

object FileSystemBatchJob extends SparkSessionForDataLayerJobs {

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

//  def parseArgs(args: Array[String]): Config = {
//
//    val parser = new scopt.OptionParser[Config](APP_NAME) {
//      head(APP_NAME)
//
//      opt[String](host)
//        .action((x, c) => c.copy(host = x))
//        .required()
//        .text("Host for database service")
//
//      opt[Int](port)
//        .action((x, c) => c.copy(port = x))
//        .required()
//        .text("port sor database service")
//
//      opt[String](username)
//        .action((x, c) => c.copy(username = x))
//        .required()
//        .text("username for database")
//
//      opt[String](password)
//        .action((x, c) => c.copy(password = x))
//        .text("password for database")
//
//      opt[String](service_name)
//        .action((x, c) => c.copy(serviceName = x))
//        .text("serviceName for database")
//
//      opt[String](output_path)
//        .action((x, c) => c.copy(outputPath = x))
//        .text("OutputPath for bath job")
//
//    }
//
//    parser.parse(args, Config()) match {
//      case Some(config) => config
//      case None =>
//        println("Illegal arguments.")
//        sys.exit(0)
//    }
//  }
//
//  def main(args: Array[String]): Unit = {
//    val config = parseArgs(args)
//
//    val host = config.host
//    val port = config.port
//    val password = config.password
//    val username = config.username
//    val Service_Name = config.serviceName
//    val outputPath = config.outputPath
//
//    batchJobParallelWriter(host,
//                           port,
//                           username,
//                           password,
//                           Service_Name,
//                           outputPath)
//
//  }
//
//  def batchJobParallelWriter(host: String,
//                             port: Int,
//                             username: String,
//                             password: String,
//                             serviceName: String,
//                             outputPath: String): Unit = {
//
//    val parVector = ParVector(
//      (get(newSparkSession, host, port, username, password, serviceName),
//       outputPath))
//
//    parVector.foreach {
//      case (data, out) =>
//        data
//          .coalesce(1)
//          .write
//          .mode("overwrite")
//          .option("header", "true")
//          .json(out)
//    }
//
//  }

}
