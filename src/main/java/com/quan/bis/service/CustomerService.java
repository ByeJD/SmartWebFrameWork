package com.quan.bis.service;

import com.quan.frame.annotation.Service;
import com.quan.frame.common.DatabaseHelper;
import com.quan.frame.common.PropsUtil;
import com.quan.bis.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Service
public class CustomerService {

    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        Properties conf = PropsUtil.loadProps("config.properties");

        DRIVER = PropsUtil.getString(conf, "jdbc.driver");
        URL = PropsUtil.getString(conf, "jdbc.url");
        USERNAME = PropsUtil.getString(conf, "jdbc.username");
        PASSWORD = PropsUtil.getString(conf, "jdbc.password");

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getCustomerListA() {
        Connection connection = null;
        List<Customer> customers = new ArrayList<>();
        try {
            String sql = "select * from demo";
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setName(resultSet.getString("name"));
                customer.setTelephone(resultSet.getString("telephone"));
                System.out.println(customer);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return customers;
    }

    public List<Customer> getCustomerList() {
        String sql = "select * from Customer";
        List<Customer> customers = DatabaseHelper.queryEntityList(Customer.class, sql);
        return customers;
    }


    public Customer getCustomer(long id) {
        String sql = "select * from Customer where id = " + id;
        Customer customer =  DatabaseHelper.queryEntity(Customer.class,sql);
        return customer;
    }

    public boolean createCustomer(Map<String, Object> fieldMap) {
        return DatabaseHelper.insertEntity(Customer.class,fieldMap);
    }

    public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
        return DatabaseHelper.updateEntity(Customer.class,id,fieldMap);
    }

    public boolean deleteCustomer(long id) {
        return DatabaseHelper.deleteEntity(Customer.class,id);
    }

    public static void main(String[] args) {

        CustomerService customerService = new CustomerService();
        System.out.println(customerService.getCustomerList().size());
    }
}
