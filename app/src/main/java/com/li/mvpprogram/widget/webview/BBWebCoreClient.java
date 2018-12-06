/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.li.mvpprogram.widget.webview;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BBWebCoreClient extends WebViewClient {
    public Context mContext;

    public BBWebCoreClient(Context context) {
        super();
        this.mContext = context;
    }

    // 页面中有链接，如果希望点击链接继续在当前browser中响应，
    // 而不是新开的系统browser中响应该链接，必须覆盖 webview的WebViewClient对象。

    /**
     * (1) 当请求的方式是"POST"方式时这个回调是不会通知的。
     * (2) 当我们访问的地址需要我们应用程序自己处理的时候，可以在这里截获，比如我们发现跳转到的是一个market的链接，那么我们可以直接跳转到应用市场，或者其他app。
     *
     * @param view 接收WebViewClient的那个实例，前面看到webView.setWebViewClient(new MyAndroidWebViewClient())，即是这个webview。
     * @param url  即将要被加载的url
     * @return true 当前应用程序要自己处理这个url， 返回false则不处理。
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        //地址过滤
//		if (Uri.parse(url).getHost().equals("labs.mwrinfosecurity.com")){
//			return true;
//		}
//		return false;
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler,
                                   SslError error) {
        //证书校验失败,页面空白
        handler.cancel();
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        int num = url.indexOf("?");
        if (num > 0) {
            url = url.substring(0, num);
        }
        num = url.indexOf("#");
        if (num > 0) {
            url = url.substring(0, num);
        }
        WebResourceResponse result = null;

        return result;
    }

    /**
     * @see WebViewClient#onPageStarted(WebView,
     * String, Bitmap)
     */
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        BBWebCore bbWebCore = (BBWebCore) view;
        bbWebCore.hideErrorPage();
    }

    /**
     * @see WebViewClient#onPageFinished(WebView,
     * String)
     */
    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);

    }

    /**
     * @see WebViewClient#onLoadResource(WebView,
     * String)
     */
    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
    }

    /**
     * @see WebViewClient#onReceivedError(WebView,
     * int, String, String)
     */
    @Override
    public void onReceivedError(WebView view, int errorCode,
                                String description, String failingUrl) {
        // mErrorView.setVisibility(View.VISIBLE);
        BBWebCore bbWebCore = (BBWebCore) view;
        bbWebCore.showErrorPage(null);

        super.onReceivedError(view, errorCode, description, failingUrl);

    }

}