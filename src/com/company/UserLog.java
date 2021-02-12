package com.company;

import java.util.Date;

public class UserLog {
    private int id;
    private User user;
    private Date loginTime;
    private String loginResult;

    public UserLog(){}

    public UserLog(int id, User user, Date loginTime, String loginResult) {
        this.id = id;
        this.user = user;
        this.loginTime = loginTime;
        this.loginResult = loginResult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginResult() {
        return loginResult;
    }

    public void setLoginResult(String loginResult) {
        this.loginResult = loginResult;
    }
}
