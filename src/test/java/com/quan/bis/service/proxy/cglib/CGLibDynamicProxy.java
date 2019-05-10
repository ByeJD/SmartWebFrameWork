package com.quan.bis.service.proxy.cglib;

import com.quan.bis.service.proxy.HelloImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLibDynamicProxy implements MethodInterceptor {

    public static CGLibDynamicProxy instance = new CGLibDynamicProxy();

    private CGLibDynamicProxy() {
    }

    public static CGLibDynamicProxy getInstance() {
        return instance;
    }

    public <T> T getProxy(Class<?> cls) {
        return (T) Enhancer.create(cls, this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(o, objects);
        after();
        return result;
    }

    private void after() {
        System.out.println("before");
    }

    private void before() {
        System.out.println("after");
    }

    public static void main(String[] args) {
        HelloImpl helloImpl = CGLibDynamicProxy.getInstance().getProxy(HelloImpl.class);
        helloImpl.foo();
        helloImpl.say(" world");
    }
}
