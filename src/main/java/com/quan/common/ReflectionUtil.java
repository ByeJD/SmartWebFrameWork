package com.quan.common;

import com.quan.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

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

    public static Object invokeMethod(Object object, Method method, Object... args){
        Object result;
        try {
            method.setAccessible(true);
            result = method.invoke(object,args);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return result;
    }

    public static void setField(Object object, Field field,Object value){
        try {
            field.setAccessible(true);
            field.set(object,value);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Object o =  ReflectionUtil.newInstance(Customer.class);;
        Method[] methods = Customer.class.getMethods();

//        ReflectionUtil.invokeMethod(o,)
    }


















}
