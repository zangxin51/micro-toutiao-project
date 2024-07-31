package com.xxx.toutiao.pojo.vo;

public class HeadlinePageVo {
    private Integer hid;
    private String title;
    private Integer type;
    private Integer pageViews;
    private Long pastHours;
    private Integer publisher;

    @Override
    public String toString() {
        return "HeadlinePageVo{" +
                "hid=" + hid +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", pageViews=" + pageViews +
                ", pastHours=" + pastHours +
                ", publisher=" + publisher +
                '}';
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPageViews() {
        return pageViews;
    }

    public void setPageViews(Integer pageViews) {
        this.pageViews = pageViews;
    }

    public Long getPastHours() {
        return pastHours;
    }

    public void setPastHours(Long pastHours) {
        this.pastHours = pastHours;
    }

    public Integer getPublisher() {
        return publisher;
    }

    public void setPublisher(Integer publisher) {
        this.publisher = publisher;
    }
}
