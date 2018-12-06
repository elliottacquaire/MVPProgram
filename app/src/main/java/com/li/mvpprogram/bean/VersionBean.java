/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.li.mvpprogram.bean;

/**
 * 给js提供的版本信息
 * Created by lison on 9/7/16.
 */
public class VersionBean {

    private String phoneModel; // 手机型号

    private String systemVersion; // 手机系统版本

    private String appVersion; // app版本号

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    @Override
    public String toString() {
        return "VersionBean{" +
                "phoneModel='" + phoneModel + '\'' +
                ", systemVersion='" + systemVersion + '\'' +
                ", appVersion='" + appVersion + '\'' +
                '}';
    }
}
