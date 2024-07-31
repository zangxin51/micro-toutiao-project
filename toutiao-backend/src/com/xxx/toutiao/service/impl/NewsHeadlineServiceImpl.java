package com.xxx.toutiao.service.impl;

import com.xxx.toutiao.dao.NewsHeadlineDao;
import com.xxx.toutiao.dao.impl.NewsHeadlineDaoImpl;
import com.xxx.toutiao.pojo.NewsHeadline;
import com.xxx.toutiao.pojo.vo.HeadlineDetailVo;
import com.xxx.toutiao.pojo.vo.HeadlinePageVo;
import com.xxx.toutiao.pojo.vo.HeadlineQueryVo;
import com.xxx.toutiao.service.NewsHeadlineService;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class NewsHeadlineServiceImpl implements NewsHeadlineService {
    private NewsHeadlineDao headlineDao = new NewsHeadlineDaoImpl();

    @Override
    public Map<String, Object> findPage(HeadlineQueryVo vo) {
        Map<String, Object> pageInfo = new TreeMap<>();
        List<HeadlinePageVo> pageData = null;
        // 查询总页数
        int totalSize = headlineDao.findCount(vo).intValue();
        int pageNum = vo.getPageNum();
        int pageSize = vo.getPageSize();
        // 查询分页
        pageData = headlineDao.findPage(vo);
        // 根据分页大小和总记录行数 计算总页数
        int totalPage = 0;
        if (totalSize % pageSize == 0) {
            totalPage = totalSize / pageSize;
        } else {
            totalPage = totalSize / pageSize + 1;
        }
        pageInfo.put("pageData", pageData);
        pageInfo.put("pageNum", pageNum);
        pageInfo.put("pageSize", pageSize);
        pageInfo.put("totalPage", totalPage);
        pageInfo.put("totalSize", totalSize);

        return pageInfo;
    }

    @Override
    public HeadlineDetailVo findDetail(Integer hid) {
        // 1.查询详情
        HeadlineDetailVo vo = headlineDao.queryDetail(hid);
        // 2.更新浏览量+1
        int rows = headlineDao.updatePageViews(hid);
        return vo;
    }

    @Override
    public int save(HeadlineDetailVo vo) {

        // 根据Hid是否存在来区分修改还是新增
        if (vo.getHid() == null) {
            // 新增, 要补全, page_views create_time update_time is_deleted
            vo.setHid(null);
            vo.setPageViews(0);
            return headlineDao.insert(vo);
        } else {
            // 修改,更新时间
            return headlineDao.update(vo);
        }
    }

    /**
     * 处理转义字符 \' 免得插入数据库失败
     */
    private void processEscaping(HeadlineDetailVo vo) {
        String title = vo.getTitle();
        String article = vo.getArticle();
        vo.setTitle(vo.getTitle().replaceAll("\'","\\\'"));
    }

    @Override
    public NewsHeadline findHeadlineByHid(Integer hid) {
        return headlineDao.selectByHid(hid);
    }

    @Override
    public int removeByHid(Integer hid) {
        return headlineDao.delete(hid);
    }
}
