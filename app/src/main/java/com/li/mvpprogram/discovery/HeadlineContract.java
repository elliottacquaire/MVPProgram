package com.li.mvpprogram.discovery;

import com.li.mvpprogram.base.BasePresenter;
import com.li.mvpprogram.base.BaseView;
import com.li.mvpprogram.bean.BannerVo;
import com.li.mvpprogram.bean.MapVo;

import java.util.List;


/**
 */
public interface HeadlineContract {
    interface View extends BaseView<Presenter> {

        //提示消息
        void showMsgTip(String msg);

        //频道列表
        void displayChannelList(List<MapVo> mapVoList);

        void showIndexBanner(List<BannerVo> result, boolean isNotifyDataSetAdd);

        void startIndexBannerTurning();

        void showFoundH5(String url);

//        void showLoadingIndicator();

//        /*隐藏指示器*/
//        void hideLoadningIndicator();

    }

    interface Presenter extends BasePresenter {

        //查询频道列表
        void queryChannelList();

        void getIndexBanners(boolean flag);

        void getFoundH5(String type); //查询新手必看，精彩活动 h5 地址

    }
}
