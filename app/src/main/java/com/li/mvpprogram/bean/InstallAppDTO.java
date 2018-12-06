//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.li.mvpprogram.bean;

import java.util.Date;

public class InstallAppDTO {
    private Long id;
    private Long partyId;
    private String deviceId;
    private String appName;
    private String appPackage;
    private String appPrivilege;
    private Date appInstallDate;
    private String isDeleted;
    private String appVersion;
    private String appDev;
    private Date createTime;
    private Date updateTime;
    private String appIcon;

    public InstallAppDTO() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPartyId() {
        return this.partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppPackage() {
        return this.appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getAppPrivilege() {
        return this.appPrivilege;
    }

    public void setAppPrivilege(String appPrivilege) {
        this.appPrivilege = appPrivilege;
    }

    public Date getAppInstallDate() {
        return this.appInstallDate;
    }

    public void setAppInstallDate(Date appInstallDate) {
        this.appInstallDate = appInstallDate;
    }

    public String getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppDev() {
        return this.appDev;
    }

    public void setAppDev(String appDev) {
        this.appDev = appDev;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAppIcon() {
        return this.appIcon;
    }

    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }
}
