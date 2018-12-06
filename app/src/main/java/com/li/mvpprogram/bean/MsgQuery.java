//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.li.mvpprogram.bean;

public class MsgQuery extends BaseVo {
    private static final long serialVersionUID = 6192868115336253452L;
    private String msgType;
    private Long msgId;
    private String status;
    private String operateType;
    private Integer startPage;
    private Integer pageSize;

    private static Integer $default$startPage() {
        return 1;
    }

    private static Integer $default$pageSize() {
        return 10;
    }

    public static MsgQuery.MsgQueryBuilder builder() {
        return new MsgQuery.MsgQueryBuilder();
    }

    public String getMsgType() {
        return this.msgType;
    }

    public Long getMsgId() {
        return this.msgId;
    }

    public String getStatus() {
        return this.status;
    }

    public String getOperateType() {
        return this.operateType;
    }

    public Integer getStartPage() {
        return this.startPage;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setMsgType(final String msgType) {
        this.msgType = msgType;
    }

    public void setMsgId(final Long msgId) {
        this.msgId = msgId;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public void setOperateType(final String operateType) {
        this.operateType = operateType;
    }

    public void setStartPage(final Integer startPage) {
        this.startPage = startPage;
    }

    public void setPageSize(final Integer pageSize) {
        this.pageSize = pageSize;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof MsgQuery)) {
            return false;
        } else {
            MsgQuery other = (MsgQuery)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$msgType = this.getMsgType();
                Object other$msgType = other.getMsgType();
                if (this$msgType == null) {
                    if (other$msgType != null) {
                        return false;
                    }
                } else if (!this$msgType.equals(other$msgType)) {
                    return false;
                }

                Object this$msgId = this.getMsgId();
                Object other$msgId = other.getMsgId();
                if (this$msgId == null) {
                    if (other$msgId != null) {
                        return false;
                    }
                } else if (!this$msgId.equals(other$msgId)) {
                    return false;
                }

                Object this$status = this.getStatus();
                Object other$status = other.getStatus();
                if (this$status == null) {
                    if (other$status != null) {
                        return false;
                    }
                } else if (!this$status.equals(other$status)) {
                    return false;
                }

                label62: {
                    Object this$operateType = this.getOperateType();
                    Object other$operateType = other.getOperateType();
                    if (this$operateType == null) {
                        if (other$operateType == null) {
                            break label62;
                        }
                    } else if (this$operateType.equals(other$operateType)) {
                        break label62;
                    }

                    return false;
                }

                label55: {
                    Object this$startPage = this.getStartPage();
                    Object other$startPage = other.getStartPage();
                    if (this$startPage == null) {
                        if (other$startPage == null) {
                            break label55;
                        }
                    } else if (this$startPage.equals(other$startPage)) {
                        break label55;
                    }

                    return false;
                }

                Object this$pageSize = this.getPageSize();
                Object other$pageSize = other.getPageSize();
                if (this$pageSize == null) {
                    if (other$pageSize != null) {
                        return false;
                    }
                } else if (!this$pageSize.equals(other$pageSize)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MsgQuery;
    }

    public int hashCode() {
        int result = 1;
        Object $msgType = this.getMsgType();
        Object $msgId = this.getMsgId();
        result = result * 59 + ($msgId == null ? 43 : $msgId.hashCode());
        Object $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        Object $operateType = this.getOperateType();
        result = result * 59 + ($operateType == null ? 43 : $operateType.hashCode());
        Object $startPage = this.getStartPage();
        result = result * 59 + ($startPage == null ? 43 : $startPage.hashCode());
        Object $pageSize = this.getPageSize();
        result = result * 59 + ($pageSize == null ? 43 : $pageSize.hashCode());
        return result;
    }

    public String toString() {
        return "MsgQuery(msgType=" + this.getMsgType() + ", msgId=" + this.getMsgId() + ", status=" + this.getStatus() + ", operateType=" + this.getOperateType() + ", startPage=" + this.getStartPage() + ", pageSize=" + this.getPageSize() + ")";
    }

    public MsgQuery() {
    }

    public MsgQuery(final String msgType, final Long msgId, final String status, final String operateType, final Integer startPage, final Integer pageSize) {
        this.msgType = msgType;
        this.msgId = msgId;
        this.status = status;
        this.operateType = operateType;
        this.startPage = startPage;
        this.pageSize = pageSize;
    }

    public static class MsgQueryBuilder {
        private String msgType;
        private Long msgId;
        private String status;
        private String operateType;
        private boolean startPage$set;
        private Integer startPage;
        private boolean pageSize$set;
        private Integer pageSize;

        MsgQueryBuilder() {
        }

        public MsgQuery.MsgQueryBuilder msgType(final String msgType) {
            this.msgType = msgType;
            return this;
        }

        public MsgQuery.MsgQueryBuilder msgId(final Long msgId) {
            this.msgId = msgId;
            return this;
        }

        public MsgQuery.MsgQueryBuilder status(final String status) {
            this.status = status;
            return this;
        }

        public MsgQuery.MsgQueryBuilder operateType(final String operateType) {
            this.operateType = operateType;
            return this;
        }

        public MsgQuery.MsgQueryBuilder startPage(final Integer startPage) {
            this.startPage = startPage;
            this.startPage$set = true;
            return this;
        }

        public MsgQuery.MsgQueryBuilder pageSize(final Integer pageSize) {
            this.pageSize = pageSize;
            this.pageSize$set = true;
            return this;
        }

        public MsgQuery build() {
            return new MsgQuery(this.msgType, this.msgId, this.status, this.operateType, this.startPage$set ? this.startPage : MsgQuery.$default$startPage(), this.pageSize$set ? this.pageSize : MsgQuery.$default$pageSize());
        }

        public String toString() {
            return "MsgQuery.MsgQueryBuilder(msgType=" + this.msgType + ", msgId=" + this.msgId + ", status=" + this.status + ", operateType=" + this.operateType + ", startPage=" + this.startPage + ", pageSize=" + this.pageSize + ")";
        }
    }
}
