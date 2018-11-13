package com.quan.frame.common;

import com.quan.frame.annotation.Action;
import com.quan.frame.bean.Handler;
import com.quan.frame.bean.Request;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ControllerHelper {

    private static final Map<Request, Handler> ACTION_MAP = new HashMap<>();

    static {
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();

        if (CollectionUtils.isNotEmpty(controllerClassSet)) {
            controllerClassSet.forEach((cls) -> {
                Method[] methods = cls.getDeclaredMethods();

                if (ArrayUtils.isEmpty(methods)) {
                    return;
                }

                for (Method method : methods) {
                    if (method.isAnnotationPresent(Action.class)) {
                        Action action = method.getDeclaredAnnotation(Action.class);
                        String mapping = action.value();

                        if (mapping.matches("\\w+:/\\w*")) {
                            String[] array = mapping.split(":");
                            if (ArrayUtils.isNotEmpty(array) && array.length == 2){
                                String requestMethod = array[0];
                                String requestPath = array[1];

                                Request request = new Request(requestMethod,requestPath);
                                Handler handler = new Handler(cls,method);
                                ACTION_MAP.put(request,handler);
                            }
                        }
                    }
                }
            });
        }
    }

    public static Handler getHandler(String requestMethod,String requestPath){
        Request request = new Request(requestMethod,requestPath);
        return ACTION_MAP.get(request);
    }
}
