package com.li.mvpprogram.discovery.Channel;


import com.li.mvpprogram.base.BasePresenter;
import com.li.mvpprogram.base.BaseView;
import com.li.mvpprogram.bean.MapVo;

import java.util.List;

/**
 * 项目名称：gitprogram
 * 类描述：渠道管理
 * 创建人：ex-lixuyang
 * 创建时间：2017/12/14 14:23
 * 修改人：ex-lixuyang
 * 修改时间：2017/12/14 14:23
 * 修改备注：
 */

public interface ChannelManageContract {

    interface View extends BaseView<Presenter> {

        //提示消息
        void showMsgTip(String msg);

        //频道列表显示排序结果
        void displayChannelList(Boolean result);

        //退出保存频道
        void saveChannelListView();

    }

    interface Presenter extends BasePresenter {

        /**
         * 保存渠道列表
         */
        void saveChannelList(List<MapVo> channelList);


    }

}
