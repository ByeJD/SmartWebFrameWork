package com.quan.bis.aspect;

import com.quan.frame.annotation.Aspect;
import com.quan.frame.annotation.Controller;
import com.quan.frame.proxy.AspectProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    private long begin;


    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        logger.info("------------begin--------------");
        begin = System.currentTimeMillis();
    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {
        logger.info(String.format("time: %dms", System.currentTimeMillis() - begin));
        logger.info("------------end--------------");
    }
}
