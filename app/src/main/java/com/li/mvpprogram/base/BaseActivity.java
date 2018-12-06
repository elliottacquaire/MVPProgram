package com.li.mvpprogram.base;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.li.mvpprogram.bean.HistoryBean;
import com.li.mvpprogram.utils.ActivityManagerTool;
import com.li.mvpprogram.utils.ToastUtils;
import com.li.mvpprogram.widget.dialog.DialogManager;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 所有页面通用基类(第三方应用页面例外，如融云)
 */

public class BaseActivity extends RxAppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManagerTool.getActivityManager().add(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /**友盟页面统计*/
//        MobclickAgent.onResume(this);

        if (!EventBus.getDefault().isRegistered(BaseActivity.this)) {
            EventBus.getDefault().register(BaseActivity.this);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        /**友盟页面统计*/
//        MobclickAgent.onPause(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DialogManager.hideDialog();
        ActivityManagerTool.getActivityManager().removeActivity(this);
        EventBus.getDefault().unregister(BaseActivity.this);
    }

    // 重写这个方法，避免因为用户手机设置字体大小，而导致的屏幕适配问题
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onNotice(HistoryBean historyBean){
        ToastUtils.show(this,historyBean.getTitle());
    }

    // 推送弹框提醒处理事件
//    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
//    public void onNoticeDialogMessage(NoticeDialogDataEven even) {
//        if (getClass().getName().equals(even.getShowInUI())) {
//            DialogManager.showMyAlerDialog(
//                    BaseActivity.this,
//                    even.getTitle(),
//                    even.getMessage(),
//                    () -> {
//                        DialogManager.hideDialog();
//                    },
//                    Gravity.CENTER_HORIZONTAL
//            );
//        }
//    }


    // 下线提醒操作（如session过期，被挤下线等情况）
//    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
//    public void onLogoutMessage(LogoutDataEven even) {
//
//        //退出融云
//        RongIMClient.getInstance().logout();
//
//        if (TextUtils.isEmpty(even.getTitle()) || TextUtils.isEmpty(even.getMessage())) {
//
//            RpcHelper.sessionExpiredProcess();
//
//        } else {
//
//            DialogManager.showMyAlerDialog(
//                    BaseActivity.this,
//                    even.getTitle(),
//                    even.getMessage(),
//                    () -> {
//                        DialogManager.hideDialog();
//                        RpcHelper.sessionExpiredProcess();
//                    },
//                    Gravity.CENTER_HORIZONTAL
//            );
//        }
//
//        EventBus.getDefault().removeStickyEvent(even);
//    }

}
