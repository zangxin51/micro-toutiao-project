package com.xxx.toutiao.pojo;

public class NewsType {
    private Integer tid;
    private String tname;

    public NewsType() {
    }

    public NewsType(Integer tid, String tname) {
        this.tid = tid;
        this.tname = tname;
    }

    @Override
    public String toString() {
        return "NewsType{" +
                "tid=" + tid +
                ", tname='" + tname + '\'' +
                '}';
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }
}
