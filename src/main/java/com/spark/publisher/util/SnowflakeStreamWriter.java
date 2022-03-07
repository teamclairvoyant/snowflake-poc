package com.spark.publisher.util;

import com.typesafe.config.Config;
import net.snowflake.spark.snowflake.SnowflakeConnectorUtils;
import org.apache.spark.api.java.function.VoidFunction2;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.streaming.Trigger;

import java.util.HashMap;
import java.util.Map;

import static com.spark.publisher.util.StreamingConstants.*;

public class SnowflakeStreamWriter {

    private static Config config = ConfigUtility.getConfig();
    public final static String SNOWFLAKE_SOURCE_NAME = SNOWFLAKE_SOURCE;

    public static void WriteDataSet(Dataset dataSet){

        //Enables query push down
        SnowflakeConnectorUtils.enablePushdownSession(SparkUtil.getSparkSession());

        //Snowflake Options
        Map<String, String> sfOptions = new HashMap<>();
        sfOptions.put(SF_URL,config.getString(SPARK_SF_URL));
        sfOptions.put(SF_USER,config.getString(SPARK_SF_USER));
        sfOptions.put(SF_PASSWORD,config.getString(SPARK_SF_PASSWORD));
        sfOptions.put(SF_DB,config.getString(SPARK_SF_DB));
        sfOptions.put(SF_SCHEMA,config.getString(SPARK_SF_SCHEMA));
        sfOptions.put(SF_WH,config.getString(SPARK_SF_WH));

        dataSet.writeStream()
                .trigger(Trigger.ProcessingTime("30 seconds"))
                .foreachBatch(new VoidFunction2<Dataset, Long>() {
                    @Override
                    public void call(Dataset dataset, Long aLong) throws Exception {
                        if(!dataset.isEmpty()){
                            dataset.write()
                                    .format(SNOWFLAKE_SOURCE_NAME)
                                    .options(sfOptions)
                                    .option(SF_TABLE,config.getString(SPARK_SF_TABLE))
                                    .option("insecureMode","true")
                                    .mode(SaveMode.Append)
                                    .save();
                        }
                    }
                }).start();
    }
}
