/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.li.mvpprogram.widget.webview;

/**
 * Created by xl on 2016/1/4.
 */
public interface BBViewClientCallBack {
    void onProgressChanged(int progress);

    void onProgresscomplete();

    void onReceivedTitle(String title);
}
