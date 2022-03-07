package com.spark.publisher;

import com.spark.publisher.util.ConfigUtility;
import com.spark.publisher.util.SnowflakeStreamWriter;
import com.spark.publisher.util.SparkUtil;
import com.typesafe.config.Config;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.*;

import static com.spark.publisher.util.StreamingConstants.*;
import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.from_json;

public class SparkSnowflakeStreaming {

    private static Config config = ConfigUtility.getConfig();

    public static void main(String[] args) {
        try {
                //Create spark session
                SparkSession spark = SparkUtil.getLocalSparkSession();

                //Kafka stream reader
                Dataset<Row> inputStream = spark.readStream()
                        .format(KAFKA_FORMAT)
                        .option(KAFKA_BOOTSTRAP, config.getString(KAFKA_BOOTSTRAP_VALUE))
                        .option(KAFKA_SUBSCRIBE, config.getString(KAFKA_SUBSCRIBE_VALUE))
                        .option(KAFKA_OFFSET, config.getString(KAFKA_OFFSET_VALUE))
                        .option(KAFKA_MAX_OFFSET_TRIGGER, config.getString(KAFKA_MAX_OFFSET_TRIGGER_VALUE))
                        .option(KAFKA_FAIL_ON, config.getString(KAFKA_FAIL_ON_VALUE))
                        .load();

                //Casting kafka value field to string
                inputStream = inputStream.selectExpr(CAST_STRING).as(VALUE);

                //Struct to format source json object
                StructType schema = new StructType()
                        .add(STRUCT_ID, DataTypes.IntegerType)
                        .add(STRUCT_NAME, DataTypes.StringType)
                        .add(STRUCT_FEE,DataTypes.IntegerType)
                        .add(STRUCT_EVENT_TIME, DataTypes.LongType);

            //Converting json to struct type
            inputStream = inputStream.withColumn(JSON_DATA, from_json(col(VALUE), schema))
                    .select(JSON_ALL_COLUMNS);

            //Writing batch dataset's to snowflake stage table
            SnowflakeStreamWriter.WriteDataSet(inputStream);

            //Awaiting termination
            spark.streams().awaitAnyTermination();

        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
