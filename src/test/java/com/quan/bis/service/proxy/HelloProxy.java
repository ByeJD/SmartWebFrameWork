package com.quan.bis.service.proxy;

public class HelloProxy implements Hello {
    private Hello hello;

    public HelloProxy(Hello hello) {
        before();
        this.hello = hello;
        after();
    }

    private void after() {
        System.out.println("After");
    }

    private void before() {
        System.out.println("Before");
    }

    public HelloProxy() {
        hello = new HelloImpl();
    }

    @Override
    public void say(String name) {
        hello.say(name);
    }
}
