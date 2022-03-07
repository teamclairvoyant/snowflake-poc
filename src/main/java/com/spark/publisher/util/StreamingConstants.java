package com.spark.publisher.util;

public class StreamingConstants {

    //Kafka constants
    public static final String KAFKA_FORMAT = "kafka";
    public static final String KAFKA_BOOTSTRAP = "kafka.bootstrap.servers";
    public static final String KAFKA_SUBSCRIBE = "subscribe";
    public static final String KAFKA_OFFSET = "startingOffsets";
    public static final String KAFKA_MAX_OFFSET_TRIGGER = "maxOffsetsPerTrigger";

    public static final String KAFKA_FAIL_ON = "fail_on_data_loss";
    public static final String KAFKA_BOOTSTRAP_VALUE = "kafka_properties.bootstrap_server";
    public static final String KAFKA_SUBSCRIBE_VALUE = "kafka_properties.source_topic";
    public static final String KAFKA_OFFSET_VALUE = "kafka_properties.offset";
    public static final String KAFKA_MAX_OFFSET_TRIGGER_VALUE = "kafka_properties.rows_trigger";
    public static final String KAFKA_FAIL_ON_VALUE = "kafka_properties.fail_on_data_loss";


    //Snowflake constants

    public static final String SNOWFLAKE_SOURCE = "net.snowflake.spark.snowflake";
    public static final String SF_URL = "sfUrl";
    public static final String SF_USER = "sfUser";
    public static final String SF_DB = "sfDatabase";
    public static final String SF_PASSWORD = "sfPassword";
    public static final String SF_SCHEMA = "sfSchema";
    public static final String SF_WH = "sfWarehouse";
    public static final String SF_TABLE = "dbtable";

    public static final String SPARK_SF_URL = "snowflake_options.sfUrl";
    public static final String SPARK_SF_USER = "snowflake_options.sfUser";
    public static final String SPARK_SF_PASSWORD = "snowflake_options.sfPassword";
    public static final String SPARK_SF_DB = "snowflake_options.sfDatabase";
    public static final String SPARK_SF_SCHEMA = "snowflake_options.sfSchema";
    public static final String SPARK_SF_WH = "snowflake_options.sfWarehouse";
    public static final String SPARK_SF_TABLE = "snowflake_options.dbtable";


    //Struct fields
    public static final String STRUCT_ID = "id";
    public static final String STRUCT_NAME = "name";
    public static final String STRUCT_FEE = "fee";
    public static final String STRUCT_EVENT_TIME = "event_time";

    //Query constants
    public static final String VALUE = "value";
    public static final String CAST_STRING = "CAST(value AS STRING)";
    public static final String JSON_DATA = "jsonData";
    public static final String JSON_ALL_COLUMNS = "jsonData.*";



}
