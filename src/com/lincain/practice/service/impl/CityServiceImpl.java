package com.lincain.practice.service.impl;

import com.lincain.practice.dao.CityDao;
import com.lincain.practice.dao.impl.CityDaoImpl;
import com.lincain.practice.domain.City;
import com.lincain.practice.service.CityService;

import java.util.List;

public class CityServiceImpl implements CityService{

    private CityDao cityDao = new CityDaoImpl();

    @Override
    public List<City> showAllCity() {
        return cityDao.selectAllCity();
    }
}
