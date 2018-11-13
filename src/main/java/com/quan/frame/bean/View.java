package com.quan.frame.bean;

import java.util.HashMap;
import java.util.Map;

public class View {

    private String path;

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    private Map<String,Object> model;


    public View(String path) {
        this.path = path;
        model = new HashMap<>();
    }

    public View addModel(String key,Object value){
        model.put(key,value);
        return this;
    }
}
