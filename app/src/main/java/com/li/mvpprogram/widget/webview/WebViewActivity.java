/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.li.mvpprogram.widget.webview;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.li.mvpprogram.R;
import com.li.mvpprogram.base.BaseFragment;
import com.li.mvpprogram.base.ToolbarActivity;

public class WebViewActivity extends ToolbarActivity {

    private Bundle mBundle;
    private String mTitle;// 标题
    public static final int FILECHOOSER_RESULTCODE = 11;
    //视频录制或选择
    public static final int REQUEST_VIDEO_CROP = 2002;

    private WebViewFragment mWebViewFragment;
    // share
    public static final String IMG_URL = "imgUrl";
    public static final String TITLE = "title";
    public static final String URL = "url";
    public static final String CONTENT = "content";
    public static final String NEWS_ID = "newsId"; // 新闻id


    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }

    @Override
    protected void init() {
        super.init();
        mToolbar.setNavigationIcon(R.mipmap.back);
        setRightTv(getString(R.string.close));
        showRightTv(true);
        setTitle(mTitle);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        super.onNewIntent(intent);

        mWebViewFragment.reLoadUrl();

    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_common_layout;
    }

    @Override
    protected BaseFragment getFragment() {
        mWebViewFragment = WebViewFragment.newInstance();
        mWebViewFragment.setArguments(mBundle);
        return mWebViewFragment;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }


    @Override
    public void onBackPressed() {
        if (mWebViewFragment == null || mWebViewFragment.getWebView() == null) {
            return;
        }
        if (mWebViewFragment.getWebView().canGoBack()) {
            mWebViewFragment.getWebView().goBack();
            return;
        }
        //  exit();
        super.onBackPressed();
    }


    private void exit() {
        finish();
    }

    @Override
    protected void onRightIvClick(View view) {
        super.onRightIvClick(view);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String imgUrl = bundle.getString(IMG_URL);
        String title = bundle.getString(TITLE);
        String content = bundle.getString(CONTENT);
        Long newsId = bundle.getLong(NEWS_ID);

    }

    @Override
    protected void onRightTvClick(TextView view) {
        super.onRightTvClick(view);
        if (view.getText().toString().equals("关闭")) { // 关闭页面
            exit();
        } else { // 跳转页面
            String call = "javascript:window.toolBarGoToPage()";
            mWebViewFragment.getWebView().loadUrl(call);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*
         * QQ与新浪不需要添加Activity，但需要在使用QQ分享或者授权的Activity中
         * 注意onActivityResult不可在fragment中实现，如果在fragment中调用登录或分享，
         * 需要在fragment依赖的Activity中实现
         */

        if (requestCode == WebViewActivity.FILECHOOSER_RESULTCODE
                || requestCode == REQUEST_VIDEO_CROP) {
            if (mWebViewFragment != null) {
                mWebViewFragment.onActivityResult(requestCode, resultCode, data);
            }
        }

    }

    @Override
    protected void handleIntent(Intent intent) {
        if (intent == null) {
            return;
        }
        mBundle = intent.getBundleExtra(BUNDLE);
        if (mBundle == null) {
            return;
        }
        mTitle = mBundle.getString(TITLE);
    }
}
