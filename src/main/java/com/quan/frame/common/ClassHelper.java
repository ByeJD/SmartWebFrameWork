package com.quan.frame.common;

import com.quan.frame.annotation.Controller;
import com.quan.frame.annotation.Service;

import java.lang.annotation.Annotation;
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
            if (cls.isAnnotationPresent(Service.class)) {
                classSet.add(cls);
            }
        });
        return classSet;
    }

    public static Set<Class<?>> getControllerClassSet() {
        Set<Class<?>> classSet = new HashSet<>();

        CLASS_SET.forEach((cls) -> {
            if (cls.isAnnotationPresent(Controller.class)) {
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


    /**
     * 获取应用包名下某父类(或接口)的所有子类(或实现类)
     * @param superClass
     * @return
     */
    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass){
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> cls : CLASS_SET){
            if (superClass.isAssignableFrom(cls) && !superClass.equals(cls)){
                classSet.add(cls);
            }
        }
        return classSet;
    }

    public static Set<Class<?>> getClassSetByAnnatation(Class<? extends Annotation> annatationClass){
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> cls : CLASS_SET){
            if (cls.isAnnotationPresent(annatationClass)){
                classSet.add(cls);
            }
        }
        return classSet;
    }
}
