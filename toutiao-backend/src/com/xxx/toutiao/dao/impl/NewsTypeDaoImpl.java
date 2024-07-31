package com.xxx.toutiao.dao.impl;

import com.xxx.toutiao.dao.BaseDao;
import com.xxx.toutiao.dao.NewsTypeDao;
import com.xxx.toutiao.pojo.NewsType;

import java.util.List;

public class NewsTypeDaoImpl extends BaseDao implements NewsTypeDao {
    @Override
    public List<NewsType> queryAll() {
        String sql = "select tid, tname from news_type";
        return baseQuery(NewsType.class, sql);
    }
}
