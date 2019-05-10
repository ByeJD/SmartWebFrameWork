package com.quan.frame.common;

import com.quan.frame.bean.Param;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReflectionUtil {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);


    public static Object newInstance(Class<?> cls) {
        Object instance;

        try {
            instance = cls.newInstance();
        } catch (Exception e) {
            logger.error("new instance failure", e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    public static Object invokeMethod(Object object, Method method, Object... args) {
        Object result;
        try {
            method.setAccessible(true);
            result = method.invoke(object, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

//    public static Object invokeMethod(Object object, Method method, Param param) {
//        Object result;
//        Object[] args = null;
//
//        LinkedHashMap<String, Object> paramMap = param.getParamMap();
//        if (MapUtils.isNotEmpty(paramMap)) {
//            args = new Object[param.getParamMap().size()];
//            int i = 0;
//            for(Map.Entry<String,Object> entry: paramMap.entrySet()){
//                args[i] = entry.getValue();
//                i++;
//            }
//        }
//
//
//        try {
//            method.setAccessible(true);
//            if (args == null){
//                return method.invoke(object);
//            }else {
//                result = method.invoke(object, args);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return result;
//    }

    public static void setField(Object object, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
    }


}
