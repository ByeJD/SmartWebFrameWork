package com.quan.common;

import com.quan.annotation.Controller;

import java.util.Arrays;

public class HelperLoader {

    private static void init(){
        Class<?>[] classList = {ClassHelper.class,BeanHelper.class,IocHelper.class,Controller.class};

        Arrays.asList(classList).forEach((cls)->ClassUtil.loadClass(cls.getName(),true));
    }
}
