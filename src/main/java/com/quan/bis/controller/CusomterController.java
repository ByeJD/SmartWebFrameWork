package com.quan.bis.controller;


import com.quan.bis.model.Customer;
import com.quan.bis.service.CustomerService;
import com.quan.frame.annotation.Action;
import com.quan.frame.annotation.Controller;
import com.quan.frame.annotation.Inject;
import com.quan.frame.bean.Param;
import com.quan.frame.bean.View;

import java.util.List;

@Controller
public class CusomterController {

    @Inject
    private CustomerService customerService;

    @Action("get:/customer")
    public View index(Param param){
       List<Customer> customerList = customerService.getCustomerList();
        return new View("customer.jsp").addModel("customerList", customerList);
    }


}
