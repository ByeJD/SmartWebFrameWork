package com.quan.frame.common;

import java.util.Arrays;


/**
 * 1. ClassHelper:用来扫描所有带有Service注解和Controller注解的类
 * 2. BeanHelper: 维护类和对象的Map,Map<Classs<?>,Object> hashMap
 * 3. AopHelper: 将BeanHelper中的被代理的Class的Object替换成Object proxy
 *
 */
public class HelperLoader {

    public static void init(){

        Class<?>[] classList = {ClassHelper.class,BeanHelper.class,AopHelper.class,IocHelper.class,ControllerHelper.class};

        Arrays.asList(classList).forEach((cls)->ClassUtil.loadClass(cls.getName(),true));
    }

    public static void main(String[] args) {
        init();
    }
}
