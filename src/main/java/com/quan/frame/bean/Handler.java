package com.quan.frame.bean;

import java.lang.reflect.Method;

public class Handler {

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    private Class<?> controllerClass;

    public Method getActionMethod() {
        return actionMethod;
    }

    private Method actionMethod;


}
