package com.quan.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class BeanHelper {

    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        beanClassSet.forEach((cls) -> BEAN_MAP.put(cls, ReflectionUtil.newInstance(cls)));
    }

    public static <T> T getBean(Class<T> t) {
        if (!BEAN_MAP.containsKey(t)) {
            throw new RuntimeException("can not get the bean by the class: " + t);
        }
        return (T) BEAN_MAP.get(t);
    }


}
