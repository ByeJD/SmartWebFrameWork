package com.quan.frame.common;

import java.util.Arrays;

public class HelperLoader {

    public static void init(){
        Class<?>[] classList = {ClassHelper.class,BeanHelper.class,AopHelper.class,IocHelper.class,ControllerHelper.class};

        Arrays.asList(classList).forEach((cls)->ClassUtil.loadClass(cls.getName(),true));
    }

    public static void main(String[] args) {
        init();
    }
}
