package com.li.mvpprogram.discovery.recommend;

import com.li.mvpprogram.base.BasePresenter;
import com.li.mvpprogram.base.BaseView;
import com.li.mvpprogram.bean.MsgVo;

import java.util.List;

public interface RecommendNewsContract {

    interface View extends BaseView<Presenter> {

        /**
         * offline：网络出错的view；
         * zerofans:联网成功，但关注人数为0的view(在serchview下面)；
         * manyfans;联网成功，关注人数大于0的view；
         */
        void showDateEmptyView(boolean offline, boolean zerofans, boolean manyfans);

        //刷新完成
        void loadMoreComplete();

        //所有数据加载完成
        void loadMoreEnd();

        //加载失败
        void loadMoreFail();

        //提示消息
        void showMsg(String msg);

        // 显示网络指示器
        void showLoadingIndicator();

        // 隐藏网络指示器
        void hideLoadningIndicator();

        //悬浮按钮
        void showFloatBtnState(boolean state);

        //展示列表
        void showNewsList(List<MsgVo> result, boolean isLoadMore);

        //刷新后显示提示
        void showRefreshNewsNum(int num);

    }

    interface Presenter extends BasePresenter {

        /**
         * 默认加载视频列表和加载更多视频列表
         *
         * @param title
         */
        void loadingDate(String title, boolean forceUpdate);


        /**
         * 操作新闻，分享或设置已读
         *
         * @param operationType
         * @param msgId
         */
        void operateVideoNews(String operationType, Long msgId);

    }

}
