package com.lincain.practice.dao;

import com.lincain.practice.domain.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerDao {

    List<Customer> selectAllCustomer();

    Integer deleteCustomerById(Integer id);

    Integer addCustomer(Customer customer);

    Integer updateCustomer(Customer customer);

    Customer selectCustomerById(Integer id);

    List<Customer> selectCustomerByCondition(Integer startIndex, Integer rows, Map<String, String[]> conditions);

    Integer selectCount(Map<String, String[]> conditions);
}
