package com.quan.frame.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropsUtil {

    private static final Logger logger = LoggerFactory.getLogger(PropsUtil.class);


    public static Properties loadProps(String fileName) {
        Properties properties = null;
        InputStream inputStream = null;

        try {
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if (inputStream == null) {
                throw new FileNotFoundException(fileName + " file is not found");
            }
            properties = new Properties();
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return properties;
    }

    public static String getString(Properties properties, String key) {
        return properties.getProperty(key, "");
    }

    public static void main(String[] args) {
        Properties properties = PropsUtil.loadProps("smart.properties");

        System.out.println(PropsUtil.getString(properties,ConfigConstant.APP_ASSET_PATH));
    }
}
