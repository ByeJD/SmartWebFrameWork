package com.quan.frame.common;

import com.quan.frame.annotation.Controller;

import java.util.Arrays;

public class HelperLoader {

    public static void init(){
        Class<?>[] classList = {ClassHelper.class,BeanHelper.class,IocHelper.class,ControllerHelper.class};

        Arrays.asList(classList).forEach((cls)->ClassUtil.loadClass(cls.getName(),true));
    }

    public static void main(String[] args) {
        init();
    }
}
