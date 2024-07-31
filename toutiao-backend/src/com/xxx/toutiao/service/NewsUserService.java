package com.xxx.toutiao.service;

import com.xxx.toutiao.pojo.NewsUser;

public interface NewsUserService {
    NewsUser findByUsername(String username);

    NewsUser findByUid(Integer uid);

    int save(NewsUser paramUser);
}
