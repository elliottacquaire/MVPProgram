package com.li.mvpprogram.bean;

import java.util.Date;

public class MsgVo extends BaseVo {
    private static final long serialVersionUID = -6079155477609505061L;
    private Long id;
    private Long partyId;
    private String msgTitle;
    private String msgType;
    private String msgTag;
    private String titleImg;
    private String loginCheck;
    private Integer expiryTime;
    private String passCheck;
    private String message;
    private Integer readNum;
    private Integer shareNum;
    private String remark;
    private Integer praises;
    private String status;
    private String isDeleted;
    private String creator;
    private String updater;
    private Date createTime;
    private Date updateTime;
    private String msgStatus;
    private String praiseSign;
    private boolean isSave;
    private String url;
    private String msgSource;
    private String msgSourceUrl;
    private String titleImgList;
    private Integer treadNum;
    private String praiseTread;
    private String dateTime;
    private String comments;


    public static MsgVo.MsgVoBuilder builder() {
        return new MsgVo.MsgVoBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public Long getPartyId() {
        return this.partyId;
    }

    public String getMsgTitle() {
        return this.msgTitle;
    }

    public String getMsgType() {
        return this.msgType;
    }

    public String getMsgTag() {
        return this.msgTag;
    }

    public String getTitleImg() {
        return this.titleImg;
    }

    public String getLoginCheck() {
        return this.loginCheck;
    }

    public Integer getExpiryTime() {
        return this.expiryTime;
    }

    public String getPassCheck() {
        return this.passCheck;
    }

    public String getMessage() {
        return this.message;
    }

    public Integer getReadNum() {
        return this.readNum;
    }

    public Integer getShareNum() {
        return this.shareNum;
    }

    public String getRemark() {
        return this.remark;
    }

    public Integer getPraises() {
        return this.praises;
    }

    public String getStatus() {
        return this.status;
    }

    public String getIsDeleted() {
        return this.isDeleted;
    }

    public String getCreator() {
        return this.creator;
    }

    public String getUpdater() {
        return this.updater;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public String getMsgStatus() {
        return this.msgStatus;
    }

    public String getPraiseSign() {
        return this.praiseSign;
    }


    public String getUrl() {
        return this.url;
    }

    public String getMsgSource() {
        return this.msgSource;
    }

    public String getMsgSourceUrl() {
        return this.msgSourceUrl;
    }

    public String getTitleImgList() {
        return this.titleImgList;
    }

    public Integer getTreadNum() {
        return this.treadNum;
    }

    public String getPraiseTread() {
        return this.praiseTread;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public String getComments() {
        return this.comments;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setPartyId(final Long partyId) {
        this.partyId = partyId;
    }

    public void setMsgTitle(final String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public void setMsgType(final String msgType) {
        this.msgType = msgType;
    }

    public void setMsgTag(final String msgTag) {
        this.msgTag = msgTag;
    }

    public void setTitleImg(final String titleImg) {
        this.titleImg = titleImg;
    }

    public void setLoginCheck(final String loginCheck) {
        this.loginCheck = loginCheck;
    }

    public void setExpiryTime(final Integer expiryTime) {
        this.expiryTime = expiryTime;
    }

    public void setPassCheck(final String passCheck) {
        this.passCheck = passCheck;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setReadNum(final Integer readNum) {
        this.readNum = readNum;
    }

    public void setShareNum(final Integer shareNum) {
        this.shareNum = shareNum;
    }

    public void setRemark(final String remark) {
        this.remark = remark;
    }

    public void setPraises(final Integer praises) {
        this.praises = praises;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public void setIsDeleted(final String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void setCreator(final String creator) {
        this.creator = creator;
    }

    public void setUpdater(final String updater) {
        this.updater = updater;
    }

    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setMsgStatus(final String msgStatus) {
        this.msgStatus = msgStatus;
    }

    public void setPraiseSign(final String praiseSign) {
        this.praiseSign = praiseSign;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public void setMsgSource(final String msgSource) {
        this.msgSource = msgSource;
    }

    public void setMsgSourceUrl(final String msgSourceUrl) {
        this.msgSourceUrl = msgSourceUrl;
    }

    public void setTitleImgList(final String titleImgList) {
        this.titleImgList = titleImgList;
    }

    public void setTreadNum(final Integer treadNum) {
        this.treadNum = treadNum;
    }

    public void setPraiseTread(final String praiseTread) {
        this.praiseTread = praiseTread;
    }

    public void setDateTime(final String dateTime) {
        this.dateTime = dateTime;
    }

    public void setComments(final String comments) {
        this.comments = comments;
    }

    public MsgVo(final Long id, final Long partyId, final String msgTitle, final String msgType, final String msgTag, final String titleImg, final String loginCheck, final Integer expiryTime, final String passCheck, final String message, final Integer readNum, final Integer shareNum, final String remark, final Integer praises, final String status, final String isDeleted, final String creator, final String updater, final Date createTime, final Date updateTime, final String msgStatus, final String praiseSign, final String url, final String msgSource, final String msgSourceUrl, final String titleImgList, final Integer treadNum, final String praiseTread, final String dateTime, final String comments) {
        this.id = id;
        this.partyId = partyId;
        this.msgTitle = msgTitle;
        this.msgType = msgType;
        this.msgTag = msgTag;
        this.titleImg = titleImg;
        this.loginCheck = loginCheck;
        this.expiryTime = expiryTime;
        this.passCheck = passCheck;
        this.message = message;
        this.readNum = readNum;
        this.shareNum = shareNum;
        this.remark = remark;
        this.praises = praises;
        this.status = status;
        this.isDeleted = isDeleted;
        this.creator = creator;
        this.updater = updater;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.msgStatus = msgStatus;
        this.praiseSign = praiseSign;
        this.url = url;
        this.msgSource = msgSource;
        this.msgSourceUrl = msgSourceUrl;
        this.titleImgList = titleImgList;
        this.treadNum = treadNum;
        this.praiseTread = praiseTread;
        this.dateTime = dateTime;
        this.comments = comments;
    }

    public MsgVo() {
    }

    public static class MsgVoBuilder {
        private Long id;
        private Long partyId;
        private String msgTitle;
        private String msgType;
        private String msgTag;
        private String titleImg;
        private boolean loginCheck$set;
        private String loginCheck;
        private boolean expiryTime$set;
        private Integer expiryTime;
        private boolean passCheck$set;
        private String passCheck;
        private String message;
        private Integer readNum;
        private Integer shareNum;
        private String remark;
        private Integer praises;
        private String status;
        private String isDeleted;
        private String creator;
        private String updater;
        private Date createTime;
        private Date updateTime;
        private String msgStatus;
        private String praiseSign;
        private boolean isSave$set;
        private String url;
        private String msgSource;
        private String msgSourceUrl;
        private String titleImgList;
        private Integer treadNum;
        private String praiseTread;
        private String dateTime;
        private String comments;

        MsgVoBuilder() {
        }

        public MsgVo.MsgVoBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public MsgVo.MsgVoBuilder partyId(final Long partyId) {
            this.partyId = partyId;
            return this;
        }

        public MsgVo.MsgVoBuilder msgTitle(final String msgTitle) {
            this.msgTitle = msgTitle;
            return this;
        }

        public MsgVo.MsgVoBuilder msgType(final String msgType) {
            this.msgType = msgType;
            return this;
        }

        public MsgVo.MsgVoBuilder msgTag(final String msgTag) {
            this.msgTag = msgTag;
            return this;
        }

        public MsgVo.MsgVoBuilder titleImg(final String titleImg) {
            this.titleImg = titleImg;
            return this;
        }

        public MsgVo.MsgVoBuilder loginCheck(final String loginCheck) {
            this.loginCheck = loginCheck;
            this.loginCheck$set = true;
            return this;
        }

        public MsgVo.MsgVoBuilder expiryTime(final Integer expiryTime) {
            this.expiryTime = expiryTime;
            this.expiryTime$set = true;
            return this;
        }

        public MsgVo.MsgVoBuilder passCheck(final String passCheck) {
            this.passCheck = passCheck;
            this.passCheck$set = true;
            return this;
        }

        public MsgVo.MsgVoBuilder message(final String message) {
            this.message = message;
            return this;
        }

        public MsgVo.MsgVoBuilder readNum(final Integer readNum) {
            this.readNum = readNum;
            return this;
        }

        public MsgVo.MsgVoBuilder shareNum(final Integer shareNum) {
            this.shareNum = shareNum;
            return this;
        }

        public MsgVo.MsgVoBuilder remark(final String remark) {
            this.remark = remark;
            return this;
        }

        public MsgVo.MsgVoBuilder praises(final Integer praises) {
            this.praises = praises;
            return this;
        }

        public MsgVo.MsgVoBuilder status(final String status) {
            this.status = status;
            return this;
        }

        public MsgVo.MsgVoBuilder isDeleted(final String isDeleted) {
            this.isDeleted = isDeleted;
            return this;
        }

        public MsgVo.MsgVoBuilder creator(final String creator) {
            this.creator = creator;
            return this;
        }

        public MsgVo.MsgVoBuilder updater(final String updater) {
            this.updater = updater;
            return this;
        }

        public MsgVo.MsgVoBuilder createTime(final Date createTime) {
            this.createTime = createTime;
            return this;
        }

        public MsgVo.MsgVoBuilder updateTime(final Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        public MsgVo.MsgVoBuilder msgStatus(final String msgStatus) {
            this.msgStatus = msgStatus;
            return this;
        }

        public MsgVo.MsgVoBuilder praiseSign(final String praiseSign) {
            this.praiseSign = praiseSign;
            return this;
        }



        public MsgVo.MsgVoBuilder url(final String url) {
            this.url = url;
            return this;
        }

        public MsgVo.MsgVoBuilder msgSource(final String msgSource) {
            this.msgSource = msgSource;
            return this;
        }

        public MsgVo.MsgVoBuilder msgSourceUrl(final String msgSourceUrl) {
            this.msgSourceUrl = msgSourceUrl;
            return this;
        }

        public MsgVo.MsgVoBuilder titleImgList(final String titleImgList) {
            this.titleImgList = titleImgList;
            return this;
        }

        public MsgVo.MsgVoBuilder treadNum(final Integer treadNum) {
            this.treadNum = treadNum;
            return this;
        }

        public MsgVo.MsgVoBuilder praiseTread(final String praiseTread) {
            this.praiseTread = praiseTread;
            return this;
        }

        public MsgVo.MsgVoBuilder dateTime(final String dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public MsgVo.MsgVoBuilder comments(final String comments) {
            this.comments = comments;
            return this;
        }

    }
}
