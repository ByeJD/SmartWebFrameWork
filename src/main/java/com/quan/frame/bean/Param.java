package com.quan.frame.bean;

import java.util.LinkedHashMap;
import java.util.Map;

public class Param {

    public Param(LinkedHashMap<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public LinkedHashMap<String, Object> getParamMap() {
        return paramMap;
    }

    private LinkedHashMap<String,Object> paramMap;

    public long getLong(String name){
        return (long)paramMap.get(name);
    }

}
