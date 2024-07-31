package com.xxx.toutiao.service.impl;

import com.xxx.toutiao.dao.NewsTypeDao;
import com.xxx.toutiao.dao.impl.NewsTypeDaoImpl;
import com.xxx.toutiao.pojo.NewsType;
import com.xxx.toutiao.service.NewsTypeService;

import java.util.List;

public class NewsTypeServiceImpl implements NewsTypeService {
    private NewsTypeDao typeDao = new NewsTypeDaoImpl();
    @Override
    public List<NewsType> findAll() {
        return typeDao.queryAll();
    }
}
