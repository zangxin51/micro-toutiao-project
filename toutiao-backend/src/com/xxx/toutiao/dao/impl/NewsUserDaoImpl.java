package com.xxx.toutiao.dao.impl;

import com.xxx.toutiao.dao.BaseDao;
import com.xxx.toutiao.dao.NewsUserDao;
import com.xxx.toutiao.pojo.NewsUser;

import java.util.List;

public class NewsUserDaoImpl extends BaseDao implements NewsUserDao {
    @Override
    public NewsUser queryByUsername(String username) {
        String sql = """
                select uid, username, user_pwd userPwd, nick_name nickName
                from news_user
                where username = ?                
                """;
        List<NewsUser> newsUsers = baseQuery(NewsUser.class, sql, username);
        return newsUsers.isEmpty() ? null : newsUsers.get(0);
    }

    @Override
    public NewsUser queryByUid(Integer uid) {
        String sql = """
                select uid, username, user_pwd userPwd, nick_name nickName
                from news_user
                where uid = ?                
                """;
        List<NewsUser> newsUsers = baseQuery(NewsUser.class, sql, uid);
        return newsUsers.isEmpty() ? null : newsUsers.get(0);
    }

    @Override
    public int insert(NewsUser paramUser) {
        String sql = """
                insert into news_user(uid, username, user_pwd, nick_name)
                values (null, ?, ?, ?)                
                """;
        return baseUpdate(sql, paramUser.getUsername(), paramUser.getUserPwd(), paramUser.getNickName());
    }
}
