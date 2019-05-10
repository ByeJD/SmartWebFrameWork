package com.quan.frame.common;

import com.quan.frame.annotation.Inject;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.util.Map;

public final class IocHelper {

    static {
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();

        if (MapUtils.isNotEmpty(beanMap)) {
            beanMap.forEach((cls, obj) -> {
                // 获取一个类的所有字段，getFiled:类和父类public字段
                Field[] fields = cls.getDeclaredFields();
                if (!ArrayUtils.isEmpty(fields)) {
                    for (Field field : fields) {
                        if (field.isAnnotationPresent(Inject.class)) {
                            Class<?> beanFieldClass = field.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if (beanFieldInstance != null) {
                                ReflectionUtil.setField(obj,field,beanFieldInstance);
                            }
                        }
                    }
                }
            });
        }
    }
}
