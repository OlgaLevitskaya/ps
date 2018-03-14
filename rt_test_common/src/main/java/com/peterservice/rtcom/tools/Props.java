package com.peterservice.rtcom.tools;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public final class Props {
    private static final Logger LOG = Logger.getLogger(Props.class);
    private static final String PATH_TO_PROPERTIES = "config.properties";
    private static Props singleInstance = null;
    private Properties properties;

    private Props() {
        properties = readConfig();
    }

    public static Props getProp() {
        if (singleInstance == null) {
            synchronized (Props.class) {
                if (singleInstance == null) {
                    singleInstance = new Props();
                }
            }
        }
        return singleInstance;
    }

    public int port() {
        return Integer.parseInt(properties.getProperty("server_port"));
    }

    public String server() {
        return properties.getProperty("server");
    }

    public String saleUrl() {
        return properties.getProperty("url_sale");
    }

    public String productName() {
        return properties.getProperty("product_name");
    }

    private Properties readConfig() {
        InputStreamReader streamReader = new InputStreamReader(getClass().getResourceAsStream("/" + PATH_TO_PROPERTIES), StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        properties = new Properties();
        try {
            properties.load(bufferedReader);
        } catch (IOException e) {
            LOG.error(e);
        }
        return properties;
    }

}
