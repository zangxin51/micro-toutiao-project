package com.xxx.toutiao.service.impl;

import com.xxx.toutiao.dao.NewsUserDao;
import com.xxx.toutiao.dao.impl.NewsUserDaoImpl;
import com.xxx.toutiao.pojo.NewsUser;
import com.xxx.toutiao.service.NewsUserService;
import com.xxx.toutiao.util.MD5Util;

public class NewsUserServiceImpl implements NewsUserService {
    private NewsUserDao userDao = new NewsUserDaoImpl();

    @Override
    public NewsUser findByUsername(String username) {
        return userDao.queryByUsername(username);
    }

    @Override
    public NewsUser findByUid(Integer uid) {
        return userDao.queryByUid(uid);
    }

    @Override
    public int save(NewsUser paramUser) {
        // 处理注册业务 将密码设为密文
        paramUser.setUserPwd(MD5Util.encrypt(paramUser.getUserPwd()));
        return userDao.insert(paramUser);
    }
}
