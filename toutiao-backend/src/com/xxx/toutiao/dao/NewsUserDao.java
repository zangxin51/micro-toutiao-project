package com.xxx.toutiao.dao;

import com.xxx.toutiao.pojo.NewsUser;

public interface NewsUserDao {
    NewsUser queryByUsername(String username);

    NewsUser queryByUid(Integer uid);

    int insert(NewsUser paramUser);
}
