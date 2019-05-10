package com.quan.bis.service.proxy;

public class HelloImpl implements Hello {
    @Override
    public void say(String name) {
        System.out.println("Hello !" + name);
    }

    public void foo(){
        System.out.println("foo");
    }
}
