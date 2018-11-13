package com.quan.bis.service;

import com.quan.frame.common.DatabaseHelper;
import com.quan.bis.model.Customer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerServiceTest {

    private final CustomerService customerService = new CustomerService();


    @Before
    public void setUp() throws Exception {
        String file = "customer_init.sql";
        DatabaseHelper.executeSqlFile(file);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getCustomerList() {
        List<Customer> customerList = customerService.getCustomerList();
        Assert.assertEquals(2, customerList.size());
    }

    @Test
    public void getCustomer() {
        long id = 1;
        Customer customer = customerService.getCustomer(id);
        Assert.assertEquals(customer.getId(), id);
    }

    @Test
    public void createCustomer() {
        Map<String,Object> fieldMap = new HashMap<>();
        fieldMap.put("name","customer001");
        fieldMap.put("contact","John");
        fieldMap.put("telephone","159888553248");

        boolean result = customerService.createCustomer(fieldMap);

        Assert.assertTrue(result);
    }


    @Test
    public void updateCustomer() {
        long id = 1;
        Map<String,Object> fieldMap = new HashMap<>();
        fieldMap.put("contact","Eric");
        boolean result = customerService.updateCustomer(id,fieldMap);
        Assert.assertTrue(result);
    }

    @Test
    public void deleteCustomer() {
        long id = 1;
        boolean result = customerService.deleteCustomer(id);
        Assert.assertTrue(result);
    }
}