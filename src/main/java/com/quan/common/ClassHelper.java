package com.quan.common;

import com.quan.annotation.Controller;
import com.quan.annotation.Service;

import java.util.HashSet;
import java.util.Set;

public final class ClassHelper {

    private static final Set<Class<?>> CLASS_SET;

    static {
        String basePackge = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackge);
    }

    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    public static Set<Class<?>> getServiceClassSet() {
        Set<Class<?>> classSet = new HashSet<>();

        CLASS_SET.forEach((cls) -> {
            if (cls.isAssignableFrom(Service.class)) {
                classSet.add(cls);
            }
        });
        return classSet;
    }

    public static Set<Class<?>> getControllerClassSet() {
        Set<Class<?>> classSet = new HashSet<>();

        CLASS_SET.forEach((cls) -> {
            if (cls.isAssignableFrom(Controller.class)) {
                classSet.add(cls);
            }
        });
        return classSet;
    }

    public static Set<Class<?>> getBeanClassSet() {
        Set<Class<?>> classSet = new HashSet<>();
        classSet.addAll(getControllerClassSet());
        classSet.addAll(getServiceClassSet());
        return classSet;
    }


}
