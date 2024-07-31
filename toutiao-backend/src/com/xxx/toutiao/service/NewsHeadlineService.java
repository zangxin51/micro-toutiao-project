package com.xxx.toutiao.service;

import com.xxx.toutiao.pojo.NewsHeadline;
import com.xxx.toutiao.pojo.vo.HeadlineDetailVo;
import com.xxx.toutiao.pojo.vo.HeadlineQueryVo;

import java.util.Map;

public interface NewsHeadlineService {
    Map<String, Object> findPage(HeadlineQueryVo headlineQueryVo);

    HeadlineDetailVo findDetail(Integer hid);

    int save(HeadlineDetailVo vo);

    NewsHeadline findHeadlineByHid(Integer hid);

    int removeByHid(Integer hid);
}
