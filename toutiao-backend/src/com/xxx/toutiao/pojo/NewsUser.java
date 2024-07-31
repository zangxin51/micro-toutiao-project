package com.xxx.toutiao.pojo;

public class NewsUser {
    private Integer uid;
    private String username;

    private String userPwd;
    private String nickName;

    public NewsUser() {
    }

    public NewsUser(Integer uid, String username, String userPwd, String nickName) {
        this.uid = uid;
        this.username = username;
        this.userPwd = userPwd;
        this.nickName = nickName;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "NewsUser{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
