package com.xxx.toutiao.pojo;

import java.util.Date;

/**
 * hid         int auto_increment comment '头条id' primary key,
 * title       varchar(50)   not null comment '头条标题',
 * article     varchar(5000) not null comment '头条新闻内容',
 * type        int           not null comment '头条类型id',
 * publisher   int           not null comment '头条发布用户id',
 * page_views  int           not null comment '头条浏览量',
 * create_time datetime      null comment '头条发布时间',
 * update_time datetime      null comment '头条最后的修改时间',
 * is_deleted  int           null comment '头条是否被删除 1 删除  0 未删除'
 */
public class NewsHeadline {
    private Integer hid;
    private String title;
    private String article;
    private Integer type;
    private Integer publisher;
    private Integer pageViews;
    private Date createTime;
    private Date updateTime;
    private Integer isDeleted;

    public NewsHeadline() {
    }

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPublisher() {
        return publisher;
    }

    public void setPublisher(Integer publisher) {
        this.publisher = publisher;
    }

    public Integer getPageViews() {
        return pageViews;
    }

    public void setPageViews(Integer pageViews) {
        this.pageViews = pageViews;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "NewsHeadline{" +
                "hid=" + hid +
                ", title='" + title + '\'' +
                ", article='" + article + '\'' +
                ", type=" + type +
                ", publisher=" + publisher +
                ", pageViews=" + pageViews +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
