package com.xxx.toutiao.dao.impl;

import com.xxx.toutiao.dao.BaseDao;
import com.xxx.toutiao.dao.NewsHeadlineDao;
import com.xxx.toutiao.pojo.NewsHeadline;
import com.xxx.toutiao.pojo.vo.HeadlineDetailVo;
import com.xxx.toutiao.pojo.vo.HeadlinePageVo;
import com.xxx.toutiao.pojo.vo.HeadlineQueryVo;

import java.util.List;

public class NewsHeadlineDaoImpl extends BaseDao implements NewsHeadlineDao {
    @Override
    public Long findCount(HeadlineQueryVo headlineQueryVo) {
        String sql = """
                  select count(*)
                  from news_headline
                  where type = ?
                    and title like concat('%',?,'%')
                    and is_deleted = 0
                  order by create_time DESC, page_views DESC              
                """;
        return baseQueryOjbect(Long.class, sql, headlineQueryVo.getType(), headlineQueryVo.getKeyWords());
    }

    @Override
    public List<HeadlinePageVo> findPage(HeadlineQueryVo vo) {
        String sql = """
                select hid, title, type, page_views pageViews, TIMESTAMPDIFF(hour, create_time, now()) pastHours, publisher
                from news_headline
                where type = ?
                  and title like concat('%',?,'%')
                  and is_deleted = 0
                order by create_time DESC, page_views DESC limit ?,?            
                """;
        int pageNum = vo.getPageNum();
        int pageSize = vo.getPageSize();
        return baseQuery(HeadlinePageVo.class, sql, vo.getType(), vo.getKeyWords(), (pageNum - 1) * pageSize, pageSize);
    }

    @Override
    public HeadlineDetailVo queryDetail(Integer hid) {
        String sql = """
                select th.hid,
                       th.title,
                       th.article,
                       th.type,
                       ty.tname                                   typeName,
                       th.page_views                              pageViews,
                       TIMESTAMPDIFF(hour, th.create_time, now()) pastHours,
                       th.publisher,
                       tu.nick_name                               author
                from news_headline th
                         join news_type ty
                         join news_user tu
                              on th.type = ty.tid and th.publisher = tu.uid and th.is_deleted = 0
                where th.hid = ?               
                """;
        List<HeadlineDetailVo> vos = baseQuery(HeadlineDetailVo.class, sql, hid);
        return vos.isEmpty() ? null : vos.get(0);
    }

    @Override
    public int updatePageViews(Integer hid) {
        String sql = """
                update news_headline t set t.page_views = t.page_views + 1 where t.hid = ? and is_deleted = 0
                """;
        return baseUpdate(sql, hid);
    }

    @Override
    public int insert(HeadlineDetailVo vo) {
        String sql = """
                insert into news_headline(hid, title, article, type, publisher, page_views, create_time, update_time, is_deleted)
                values (null, ?, ?, ?, ?, 0, now(), now(), 0)                
                """;
        return baseUpdate(sql, vo.getTitle(), vo.getArticle(), vo.getType(), vo.getPublisher());
    }

    @Override
    public int update(HeadlineDetailVo vo) {
        String sql = """
                update news_headline
                set title      = ?,
                    article= ?,
                    type=?,
                    update_time=now()
                where hid = ?
                  and is_deleted = 0                
                """;
        return baseUpdate(sql, vo.getTitle(), vo.getArticle(), vo.getType(), vo.getHid());
    }

    @Override
    public NewsHeadline selectByHid(Integer hid) {
        String sql = """
                select hid, title, article, type
                from news_headline
                where hid = ? and is_deleted = 0               
                """;
        List<NewsHeadline> list = baseQuery(NewsHeadline.class, sql, hid);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public int delete(Integer hid) {
        String sql = """
                update news_headline
                set is_deleted = 1
                where hid = ?
                """;
        return baseUpdate(sql, hid);
    }
}
