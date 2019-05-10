package com.quan.frame.proxy;

public interface Proxy {
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
