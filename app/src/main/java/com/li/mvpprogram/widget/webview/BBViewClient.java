/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.li.mvpprogram.widget.webview;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.KeyEvent;
import android.webkit.*;


/**
 * Created by jkxydp on 13-12-26.
 */
public class BBViewClient extends WebChromeClient {

    private BBViewClientCallBack mBBViewClientCallBack;
    private Context mContext;
    private boolean mIsInjectedJS;
    private JsCallJava mJsCallJava;

    public BBViewClient(Context context) {
        super();
        this.mContext = context;
    }

    public BBViewClient(String injectedName, Class injectedCls) {
        mJsCallJava = new JsCallJava(injectedName, injectedCls);
    }

    /**
     * Setter method for property <tt>mBBViewClientCallBack</tt>.
     *
     * @param mBBViewClientCallBack value to be assigned to property mBBViewClientCallBack
     */
    public void setmBBViewClientCallBack(
            BBViewClientCallBack mBBViewClientCallBack) {
        this.mBBViewClientCallBack = mBBViewClientCallBack;
    }

    // 当时数据库存储空间不够时,自动扩容为原来的2倍
    @TargetApi(Build.VERSION_CODES.ECLAIR)
    @Override
    public void onExceededDatabaseQuota(String url, String databaseIdentifier,
                                        long currentQuota, long estimatedSize, long totalUsedQuota,
                                        WebStorage.QuotaUpdater quotaUpdater) {
        quotaUpdater.updateQuota(estimatedSize * 2);
    }

    /**
     * @see WebChromeClient#onProgressChanged(WebView,
     * int)
     */
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if (mBBViewClientCallBack != null) {
            mBBViewClientCallBack.onProgressChanged(newProgress);
        }

        if (newProgress == 100 && mBBViewClientCallBack != null) {
            mBBViewClientCallBack.onProgresscomplete();
        }

        //为什么要在这里注入JS
        //1 OnPageStarted中注入有可能全局注入不成功，导致页面脚本上所有接口任何时候都不可用
        //2 OnPageFinished中注入，虽然最后都会全局注入成功，但是完成时间有可能太晚，当页面在初始化调用接口函数时会等待时间过长
        //3 在进度变化时注入，刚好可以在上面两个问题中得到一个折中处理
        //为什么是进度大于25%才进行注入，因为从测试看来只有进度大于这个数字页面才真正得到框架刷新加载，保证100%注入成功
        if (newProgress <= 25) {
            mIsInjectedJS = false;
        } else if (!mIsInjectedJS) {
            if (view instanceof BBWebCore) {
                BBWebCore bbWebCore = (BBWebCore) view;
                bbWebCore.injectJavascriptInterfaces();
            }
            if (mJsCallJava != null) {
                view.loadUrl(mJsCallJava.getPreloadInterfaceJS());
            }
            mIsInjectedJS = true;
        }

        super.onProgressChanged(view, newProgress);
    }

    /**
     * @see WebChromeClient#onReceivedTitle(WebView,
     * String)
     */
    @Override
    public void onReceivedTitle(WebView view, String title) {
        if (mBBViewClientCallBack != null) {
            mBBViewClientCallBack.onReceivedTitle(title);
        }

        super.onReceivedTitle(view, title);
    }

    /**
     * @see WebChromeClient#onJsConfirm(WebView,
     * String, String, JsResult)
     */
    @Override
    public boolean onJsConfirm(WebView view, String url, String message,
                               JsResult result) {
        return super.onJsConfirm(view, url, message, result);
    }

    /**
     * @see WebChromeClient#onJsPrompt(WebView,
     * String, String, String,
     * JsPromptResult)
     */
    @Override
    public boolean onJsPrompt(WebView view, String url, String message,
                              String defaultValue, JsPromptResult result) {
        if (mJsCallJava != null) {
            result.confirm(mJsCallJava.call(view, message));
        }
        if (view instanceof BBWebCore) {
            BBWebCore bbWebCore = (BBWebCore) view;
            if (bbWebCore.handleJsInterface(view, url, message, defaultValue, result)) {
                return true;
            }
        }

        return super.onJsPrompt(view, url, message, defaultValue, result);
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message,
                             JsResult result) {
        // return super.onJsAlert(view, url, message, result); // To change body of
        // overridden
        // methods use File
        // | Settings | File
        // Templates.
        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("提示")
                .setMessage(message)
                .setPositiveButton("确定", null);

        // 不需要绑定按键事件
        // 屏蔽keycode等于84之类的按键
        builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//                Log.v("onJsAlert", "keyCode==" + keyCode + "event=" + event);
                return true;
            }
        });
        // 禁止响应按back键的事件
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();
//     ToastUtils.toastForLong(mContext, message);
        result.confirm();// 因为没有绑定事件，需要强行confirm,否则页面会变黑显示不了内容。


        return true;

    }

    // 当缓存空间不够时,自动扩容为原来的2倍
    @TargetApi(Build.VERSION_CODES.ECLAIR)
    @Override
    public void onReachedMaxAppCacheSize(long spaceNeeded, long totalUsedQuota,
                                         WebStorage.QuotaUpdater quotaUpdater) {
        quotaUpdater.updateQuota(spaceNeeded * 2);
    }


    public interface BBViewClientCallBack {

        void onProgressChanged(int progress);

        void onProgresscomplete();

        void onReceivedTitle(String title);

    }

}
