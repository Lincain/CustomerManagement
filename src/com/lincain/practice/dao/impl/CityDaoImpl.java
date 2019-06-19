package com.lincain.practice.dao.impl;

import com.lincain.practice.dao.CityDao;
import com.lincain.practice.domain.City;
import com.lincain.practice.utils.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CityDaoImpl implements CityDao{

    private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());

    @Override
    public List<City> selectAllCity() {
        String sql = "select * from city";
        return template.query(sql,new BeanPropertyRowMapper<City>(City.class));
    }
}
