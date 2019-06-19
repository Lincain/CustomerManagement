package com.lincain.practice.service.impl;

import com.lincain.practice.dao.CustomerDao;
import com.lincain.practice.dao.impl.CustomerDaoImpl;
import com.lincain.practice.domain.Customer;
import com.lincain.practice.domain.PageBean;
import com.lincain.practice.service.CustomerService;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CustomerServiceImpl implements CustomerService{

    private CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public List<Customer> findAllCustomer() {
        return customerDao.selectAllCustomer();
    }

    @Override
    public Integer deleteCustomer(Integer id) {
        return customerDao.deleteCustomerById(id);
    }

    @Override
    public Integer addCustomer(Customer customer) {
        return customerDao.addCustomer(customer);
    }

    @Override
    public void deleteSelected(String[] ids) throws Exception{
        for (String id : ids) {
            try{
                customerDao.deleteCustomerById(Integer.parseInt(id));
            }catch (Exception e){
                throw new Exception();
            }
        }
    }

    @Override
    public Integer updateCustomer(Customer customer) {
        return customerDao.updateCustomer(customer);
    }

    @Override
    public Customer selectCustomerById(Integer id) {
        return customerDao.selectCustomerById(id);
    }

    @Override
    public PageBean selectCustomerByPage(String _currentPage, String _rows, Map<String, String[]> conditions) {
        PageBean pageBean = new PageBean();
        int currentPage,rows;
        try{
            currentPage = Integer.parseInt(_currentPage);
            rows = Integer.parseInt(_rows);
        }catch (Exception e){
            return null;
        }

        Iterator<Map.Entry<String, String[]>> iterator = conditions.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String[]> next = iterator.next();
            if (next.getValue() == null || "".equals(next.getValue()[0])){
                iterator.remove();
            }
        }
        // 获取pageBean的属性
        Integer totalCount = customerDao.selectCount(conditions);
        int totalPage = (totalCount % rows == 0) ? (totalCount / rows) : (totalCount / rows + 1);
        if (currentPage < 1)
            currentPage = 1;
        if (currentPage > totalPage && totalPage > 0)
            currentPage = totalPage;
        List<Customer> list = customerDao.selectCustomerByCondition((currentPage - 1) * rows, rows, conditions);

        // 设置pageBean的属性
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);
        pageBean.setCurrentPage(currentPage);
        pageBean.setRows(rows);
        pageBean.setList(list);

        return pageBean;
    }
}
