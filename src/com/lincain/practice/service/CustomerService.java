package com.lincain.practice.service;

import com.lincain.practice.domain.Customer;
import com.lincain.practice.domain.PageBean;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    List<Customer> findAllCustomer();
    Integer deleteCustomer(Integer id);
    Integer addCustomer(Customer customer);
    void deleteSelected(String[] ids) throws Exception;
    Integer updateCustomer(Customer customer);
    Customer selectCustomerById(Integer id);
    PageBean selectCustomerByPage(String currentPage, String rows, Map<String, String[]> conditions);
}
