/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.li.mvpprogram.widget.webview;

import android.graphics.Bitmap;
import android.webkit.WebView;

public interface PagerLoaderListener {
    void onPageStarted(WebView view, String url, Bitmap favicon);

    public void onPageFinished(WebView view, String url);
}
