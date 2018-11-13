package com.quan.bis.controller;


import com.quan.frame.annotation.Action;
import com.quan.frame.annotation.Controller;
import com.quan.frame.bean.Data;

@Controller
public class CusomterController {

    @Action("get:/customer")
    public Data test(){
        Object model = "{\"result\":\"error\"}";
        Data data = new Data(model);
        return data;
    }
}
