package com.baidu.mywork.util;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ConfigUtil {

    private static Config config = ConfigFactory.load("app.conf");

    public static String get(String key) {
        return config.getString(key);
    }

    public static void main(String[] args) {
        System.out.println(get("app.bpm.url"));
    }

}
