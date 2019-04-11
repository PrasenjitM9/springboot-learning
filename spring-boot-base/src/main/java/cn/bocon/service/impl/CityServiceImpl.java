package cn.bocon.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.bocon.entity.City;
import cn.bocon.mapper.CityMapper;
import cn.bocon.service.ICityService;

/**
 * 城市业务逻辑实现类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
@Service
public class CityServiceImpl implements ICityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    private CityMapper cityDao;

    /**
     * 获取城市逻辑：
     * 如果缓存存在，从缓存中获取城市信息
     * 如果缓存不存在，从 DB 中获取城市信息，然后插入缓存
     */
    @Cacheable(value = "baseCityInfo",key="'id_'+#id")
    public City findCityById(Long id) {
        // 从 DB 中获取城市信息
        City city = cityDao.findById(id);
        return city;
    }

    public Long saveCity(City city) {
        return cityDao.saveCity(city);
    }

    /**
     * 更新城市逻辑：
     * 如果缓存存在，删除
     * 如果缓存不存在，不操作
     */
    @CachePut(value = "baseCityInfo",key="'id_'+#id")
    public Long updateCity(City city) {
        Long ret = cityDao.updateCity(city);
        return ret;
    }

    @CacheEvict(value="baseCityInfo",key="'id_'+#id")  
    public Long deleteCity(Long id) {
        Long ret = cityDao.deleteCity(id);
        return ret;
    }

}