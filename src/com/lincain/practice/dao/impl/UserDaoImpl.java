package com.lincain.practice.dao.impl;

import com.lincain.practice.dao.UserDao;
import com.lincain.practice.domain.User;
import com.lincain.practice.utils.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao{

    private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());

    /**
     * 根据用户名和密码查询记录
     * @param user
     * @return
     */
    @Override
    public User selectUserByUserNameAndPassword(User user) {
        String sql = "select * from user where username = ? and password = ?";
        try{
            return template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class)
                    ,user.getUsername(),user.getPassword());
        }catch (Exception e){
            return null;
        }
    }
}
