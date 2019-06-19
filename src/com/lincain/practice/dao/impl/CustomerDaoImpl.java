package com.lincain.practice.dao.impl;

import com.lincain.practice.dao.CustomerDao;
import com.lincain.practice.domain.Customer;
import com.lincain.practice.utils.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CustomerDaoImpl implements CustomerDao{

    private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());

    /**
     * 查询所有的customer记录
     * @return
     */
    @Override
    public List<Customer> selectAllCustomer() {
        String sql = "select * from customer";
        try {
            List<Customer> list =
                    template.query(sql, new BeanPropertyRowMapper<Customer>(Customer.class));
            return list;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 根据id删除customer表中的记录
     * @param id
     * @return
     */
    @Override
    public Integer deleteCustomerById(Integer id) {
        String sql = "delete from customer where id = ?";
        return template.update(sql,id);
    }

    /**
     * 向customer表中添加一条记录
     * @param customer
     * @return
     */
    @Override
    public Integer addCustomer(Customer customer) {
        String sql = "insert into customer values(null,?,?,?,?,?,?)";
        return template.update(sql,customer.getName(),customer.getGender(),customer.getAge(),
                customer.getAddress(),customer.getQq(),customer.getEmail());
    }

    @Override
    public Integer updateCustomer(Customer customer) {
        String sql = "update customer set name = ?, gender = ?" +
                ", age = ?, address = ?, qq = ?, email = ? where id = ?";
        return template.update(sql,customer.getName(),customer.getGender(),customer.getAge(),
                customer.getAddress(),customer.getQq(),customer.getEmail(),customer.getId());
    }

    @Override
    public Customer selectCustomerById(Integer id) {
        String sql = "select * from customer where id = ?";
        try{
            return template.queryForObject(sql
                    ,new BeanPropertyRowMapper<Customer>(Customer.class),id);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Customer> selectCustomerByCondition(Integer startIndex, Integer rows, Map<String, String[]> conditions) {
        String sql = "select * from customer where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> list = new ArrayList<>();

        Set<String> keySet = conditions.keySet();
        for (String key : keySet) {
            sb.append(" and " + key + " like ? ");
            list.add("%" + conditions.get(key)[0] + "%");
        }

        sb.append(" limit ?,?");
        list.add(startIndex);
        list.add(rows);
        System.out.println(sb.toString());
        System.out.println(list);
        return template.query(sb.toString(),new BeanPropertyRowMapper<Customer>(Customer.class),list.toArray());
    }

    @Override
    public Integer selectCount(Map<String, String[]> conditions) {
        String sql = "select count(*) from customer where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);
        List<Object> list = new ArrayList<>();

        Set<String> keySet = conditions.keySet();
        for (String key : keySet) {
            sb.append(" and " + key + " like ? ");
            list.add("%" + conditions.get(key)[0] + "%");
        }
        return template.queryForObject(sb.toString(),Integer.class,list.toArray());
    }
}
