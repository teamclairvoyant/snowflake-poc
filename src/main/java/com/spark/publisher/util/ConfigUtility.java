package com.spark.publisher.util;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ConfigUtility {
    public static Config getConfig(){
        return ConfigFactory.load();
    }
}
