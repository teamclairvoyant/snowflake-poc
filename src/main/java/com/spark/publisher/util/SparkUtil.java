package com.spark.publisher.util;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;

public class SparkUtil {

    public static SparkSession getLocalSparkSession() {

        SparkConf conf = new SparkConf()
                .setMaster("local[*]")
                .set("spark.driver.bindAddress", "127.0.0.1")
                .setAppName("spark_stream");

        return  SparkSession.builder()
                .config(conf)
                .getOrCreate();
    }

    public static SparkSession getSparkSession() {
        return SparkSession.builder().getOrCreate();
    }
}
