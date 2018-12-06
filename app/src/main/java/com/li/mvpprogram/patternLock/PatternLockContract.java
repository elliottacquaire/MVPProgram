package com.li.mvpprogram.patternLock;

import com.li.mvpprogram.base.BasePresenter;
import com.li.mvpprogram.base.BaseView;

public interface PatternLockContract {
    interface View extends BaseView<PatternLockContract.Presenter> {

        //提示消息
        void showMsgTip(String msg);


    }

    interface Presenter extends BasePresenter {


    }
}
