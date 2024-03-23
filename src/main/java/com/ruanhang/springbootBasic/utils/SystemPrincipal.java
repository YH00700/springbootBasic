package com.ruanhang.springbootBasic.utils;

public class SystemPrincipal {

    private static final long serialVersionUID = 1L;

    private String id; //登录人的userid
    private String loginName; // 登录名
    private String name; // 姓名
    private boolean mobileLogin; // 是否手机登录
    private String sessionid;

    public SystemPrincipal() {
    }

    public SystemPrincipal(String id, String loginName, String name, boolean mobileLogin, String sessionid) {
        this.id = id;
        this.loginName = loginName;
        this.name = name;
        this.mobileLogin = mobileLogin;
        this.sessionid = sessionid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMobileLogin() {
        return mobileLogin;
    }

    public void setMobileLogin(boolean mobileLogin) {
        this.mobileLogin = mobileLogin;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    @Override
    public String toString() {
        return id;
    }

}
