package com.hjd.prepaser

import org.apache.spark.sql.{Encoders, SaveMode, SparkSession}

object PreparseETL {
  def main(args: Array[String]): Unit = {
    // 1. 创建spark应用程序
    val spark = SparkSession.builder()
      .appName("PreparseETL")
      .enableHiveSupport()
      .getOrCreate()

    // 2. 设置参数值，读取路径和预设置的分区数
    val rawdataInputPath = spark.conf.get("spark.traffic.analysis.rawdata.input",
      "hdfs://master:9999/user/hadoop-hjd/traffic-analysis/rawlog/20180605")
    val numberPartitions = spark.conf.get("spark.traffic.rawdata.numberPartitions", "2").toInt

    // 3. 读取原始数据，进行预解析，生成dataset类型
    val preParsedLogRDD = spark.sparkContext.textFile(rawdataInputPath)
      .flatMap(line => Option(WebLogPreParser.parse(line)))

    val preParsedLogDS = spark.createDataset(preParsedLogRDD)(Encoders.bean(classOf[PreParsedLog]))

    // 4. 对解析后的数据进行小文件合并分依据年月日进行分区，写入hive的table表中
    preParsedLogDS.coalesce(numberPartitions)
      .write
      .mode(SaveMode.Append)
      .partitionBy("year","month", "day")
      .saveAsTable("rawdata.web")

    // 5. 关闭资源
    spark.stop()
  }
}
