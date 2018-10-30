package com.quan.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * 类操作工具类
 */
public class CastUtil {

    private static final Logger logger = LoggerFactory.getLogger(CastUtil.class);

    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    public static Class<?> loadClass(String className,boolean isInitialized){
        Class<?> cls;
        try {
            cls = Class.forName(className,isInitialized,getClassLoader());
        }catch (ClassNotFoundException e){
            logger.error("load class failure",e);
            throw new RuntimeException(e);
        }
        return cls;
    }

    public static Set<Class<?>> getClassSet(String packageName){
        return null;
    }
}