package com.quan.frame.bean;

import net.sf.cglib.core.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import java.util.Collection;
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

    public boolean isEmpty(){
        return MapUtils.isEmpty(paramMap);
    }

}
