package com.li.mvpprogram.discovery.recommend;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.li.mvpprogram.bean.MsgVo;


public class MsgVoMuti implements MultiItemEntity {

    public static final int SINGLEIMG = 1;//单张图片
    public static final int VIDEO = 2;//视频
    public static final int THREEIMG = 3;//三张图片
    public static final int TEXTTIPS = 4;//刷新提示

    private int itemType;
    private boolean isRead = false;
    private MsgVo msgVo;

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public MsgVo getMsgVo() {
        return msgVo;
    }

    public void setMsgVo(MsgVo msgVo) {
        this.msgVo = msgVo;
    }

    public MsgVoMuti(MsgVo msgVo) {
        this.msgVo = msgVo;
    }

    public MsgVoMuti(int itemType, MsgVo msgVo) {
        this.itemType = itemType;
        this.msgVo = msgVo;
    }

    public MsgVoMuti(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
