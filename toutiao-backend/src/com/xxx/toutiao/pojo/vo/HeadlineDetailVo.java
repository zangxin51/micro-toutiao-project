package com.xxx.toutiao.pojo.vo;

public class HeadlineDetailVo {
    private Integer hid;
    private String title;
    private String article;
    private Integer type;
    private String typeName;
    private Integer pageViews;
    private Long pastHours;
    private Integer publisher;
    private String author;

    @Override
    public String toString() {
        return "HeadlineDetailVo{" +
                "hid=" + hid +
                ", title='" + title + '\'' +
                ", article='" + article + '\'' +
                ", type=" + type +
                ", typeName='" + typeName + '\'' +
                ", pageViews=" + pageViews +
                ", pastHours=" + pastHours +
                ", publisher=" + publisher +
                ", author='" + author + '\'' +
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
