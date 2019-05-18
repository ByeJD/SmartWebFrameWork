package com.quan.frame.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.sql.SQLException;

public class Request {

    private String requestMethod;
    private String requestPath;

    public Request(String requestMethod, String requestPath) {
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }


    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        return new EqualsBuilder()
                .append(requestMethod, request.requestMethod)
                .append(requestPath, request.requestPath)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(requestMethod)
                .append(requestPath)
                .toHashCode();
    }

    public static void main(String[] args) {

        BeanInfo beanInfo = null;

        try {
            beanInfo = Introspector.getBeanInfo(Request.class);
        } catch (IntrospectionException var4) {

        }

        System.out.println(beanInfo.getPropertyDescriptors());
    }

}
