package com.quan.frame.common;

import java.util.Properties;

public final class ConfigHelper {

    private static final Properties CONFI_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

    public static String getJdbcDriver(){
        return PropsUtil.getString(CONFI_PROPS,ConfigConstant.JDBC_DRIVER);
    }

    public static String getJdbcUrl(){
        return PropsUtil.getString(CONFI_PROPS,ConfigConstant.JDBC_URL);
    }

    public static String getJdbcUserName(){
        return PropsUtil.getString(CONFI_PROPS,ConfigConstant.JDBC_USERNAME);
    }

    public static String getJdbcPassword(){
        return PropsUtil.getString(CONFI_PROPS,ConfigConstant.JDBC_PASSWORD);
    }

    public static String getAppBasePackage(){
        return PropsUtil.getString(CONFI_PROPS,ConfigConstant.APP_BASE_PACKAGE);
    }

    public static String getAppBaseJspPath(){
        return PropsUtil.getString(CONFI_PROPS,ConfigConstant.APP_JSP_PATH);
    }

    public static String getAppAssetPath(){
        return PropsUtil.getString(CONFI_PROPS,ConfigConstant.APP_ASSET_PATH);
    }


}
