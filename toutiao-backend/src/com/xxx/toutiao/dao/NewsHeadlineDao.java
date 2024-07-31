package com.xxx.toutiao.dao;

import com.xxx.toutiao.pojo.NewsHeadline;
import com.xxx.toutiao.pojo.vo.HeadlineDetailVo;
import com.xxx.toutiao.pojo.vo.HeadlinePageVo;
import com.xxx.toutiao.pojo.vo.HeadlineQueryVo;

import java.util.List;

public interface NewsHeadlineDao {
    Long findCount(HeadlineQueryVo headlineQueryVo);

    List<HeadlinePageVo> findPage(HeadlineQueryVo headlineQueryVo);

    HeadlineDetailVo queryDetail(Integer hid);

    int updatePageViews(Integer hid);

    int insert(HeadlineDetailVo vo);

    int update(HeadlineDetailVo vo);

    NewsHeadline selectByHid(Integer hid);

    int delete(Integer hid);
}
