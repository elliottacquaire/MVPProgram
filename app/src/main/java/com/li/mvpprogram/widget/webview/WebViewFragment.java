/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.li.mvpprogram.widget.webview;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.*;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import butterknife.BindView;
import com.li.mvpprogram.BaseApplication;
import com.li.mvpprogram.BuildConfig;
import com.li.mvpprogram.R;
import com.li.mvpprogram.base.BaseFragment;
import com.li.mvpprogram.config.Constants;
import com.li.mvpprogram.utils.*;
import com.orhanobut.logger.Logger;
import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.li.mvpprogram.base.BaseNativeActivity.BUNDLE;
import static com.li.mvpprogram.widget.webview.WebViewActivity.FILECHOOSER_RESULTCODE;
import static com.li.mvpprogram.widget.webview.WebViewActivity.REQUEST_VIDEO_CROP;


/**
 * Created by songdy on 16/8/10.
 */
public class WebViewFragment extends BaseFragment {

    public static final String TRAIN = "TRAIN";
    //face++ 人脸识别
    public static final String FACEID_URL = "api.megvii.com/faceid/lite/do?";
    //视频选择
    public static final String VIDEO_MIME_TYPE = "video/*";
    //图片选择
    public static final String IMAGE_MIME_TYPE = "image/*";

    @BindView(R.id.earningdetail_network_error)
    LinearLayout networkError;

    private List<String> mListUrl = new ArrayList<String>();
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.webview)
    BBWebCore mBBWebCore;
    private String baseUrl = "";
    private String busiCode = "";

    private boolean isBack = false;

    private boolean videoFlag = false;


    /*是否显示进度*/
    private boolean isShowLoadProgress;

    public ValueCallback<Uri> mUploadMessage;

    public ValueCallback<Uri[]> mUploadMessages;


    private String mCameraFilePath = "";

    private String mTitleFromAct; // Activity传过来的title，如果有值，则不从html header中取title

    public static WebViewFragment newInstance() {
        return new WebViewFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_webview;
    }

    public void setShowLoadProgress(boolean showLoadProgress) {
        isShowLoadProgress = showLoadProgress;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            mTitleFromAct = arguments.getString(WebViewActivity.TITLE);
        }

        isShowLoadProgress = true;

        if (mActivity != null) {
            if (arguments != null) {
                baseUrl = arguments.getString(WebViewActivity.URL);
                busiCode = arguments.getString(TRAIN);
            }
        }
        if (TextUtils.isEmpty(baseUrl)) {
            return;
        }

        showShareBtn(baseUrl);

        networkError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    if (NetworkUtils.isConnected(mActivity)) {
                        loadUrl();
                        networkError.setVisibility(View.GONE);
                    } else {
                        networkError.setVisibility(View.VISIBLE);
                        ToastUtils.show(mActivity, getString(R.string.network_not_connect));
                    }
                }
            }
        });


        if (NetworkUtils.isConnected(mActivity)) {
            loadUrl();
        } else {
            networkError.setVisibility(View.VISIBLE);
            ToastUtils.show(mActivity, getString(R.string.network_not_connect));
        }

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }


    }

    /**
     * 是否显示分享按钮的操作
     *
     * @param url
     */
    private void showShareBtn(String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        if (url.startsWith(Constants.URL_NEWS_DETAIL)) { // 新闻详情页
            if (mActivity instanceof WebViewActivity) {
                ((WebViewActivity) mActivity).setRightIv(R.mipmap.share);
                ((WebViewActivity) mActivity).showRightIv(true);
                ((WebViewActivity) mActivity).showRightIv(true);
                ((WebViewActivity) mActivity).showRightTv(false);
                ((WebViewActivity) mActivity).setTitleColor(mActivity.getResources().getColor(R.color.tip_text_color));
            }
        }
    }


    public void reLoadUrl() {
        Bundle arguments = getActivity().getIntent().getBundleExtra(BUNDLE);
        if (arguments != null) {
            baseUrl = arguments.getString(WebViewActivity.URL);
        }
        if (TextUtils.isEmpty(baseUrl)) {
            return;
        }
        loadUrl();
    }


    /*刷新页面*/
    public void refreshUrl(String url) {
        baseUrl = url;
        loadUrl();
    }


    protected void loadUrl() {
        LogUtils.i(baseUrl);

        mListUrl.add(baseUrl);
        String userAgentString = mBBWebCore.getSettings().getUserAgentString();
        if (userAgentString != null && (!userAgentString.contains("One Account Android;Mozilla"))) { // 电商需要这些特殊字段来区分不同平台
            StringBuilder sb = new StringBuilder();
            String myUserAgent = sb.append(userAgentString).append("One Account Android;Mozilla")
                    .append(";").append(AppUtils.getAppInfo(BaseApplication.getInstance().getApplicationContext()).getVersionName()).toString();
            mBBWebCore.getSettings().setUserAgentString(myUserAgent);
        }
        mBBWebCore.setNetworkAvailable(true);

        // H5canvas黑屏解决方案-幸运大转盘,禁止canvas硬件加速
//        mBBWebCore.setLayerType(View.LAYER_TYPE_SOFTWARE,null);

        // 设置可以支持缩放
//        mBBWebCore.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
//        mBBWebCore.getSettings().setBuiltInZoomControls(true);
        //扩大比例的缩放
//        mBBWebCore.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { // 4.4及以上
            mBBWebCore.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        } else { // 4.4以下
            mBBWebCore.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }
        mBBWebCore.getSettings().setLoadWithOverviewMode(true);

        mBBWebCore.addJavascriptInterface(new WebviewJSCallJava(mActivity, mBBWebCore), "android");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (BuildConfig.DEBUG) {
                mBBWebCore.setWebContentsDebuggingEnabled(true);
            }
        }


        // 兼容5.0以上设置支持http和hptts请求
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBBWebCore.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }


        mBBWebCore.setDownloadListener(new DownloadListener() {

            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
        mBBWebCore.setVerticalScrollBarEnabled(false);
        mBBWebCore.setWebChromeClient(new CustomChromeClient(mActivity));
        mBBWebCore.setWebViewClient(new BBWebCoreClient(mActivity) {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                if (mActivity instanceof WebViewActivity) {
                    ((WebViewActivity) mActivity).setRightTv(getString(R.string.close));
                }

                if (mBBWebCore != null && mBBWebCore.getSettings() != null) {
                    if (!TextUtils.isEmpty(url) && url.startsWith(Constants.URL_LIAN_LIAN)) { // 连连支付页面
                        mBBWebCore.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
                    } else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { // 4.4及以上
                            mBBWebCore.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                        } else { // 4.4以下
                            mBBWebCore.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
                        }
                    }
                }

                if ((mActivity != null) && (!NetworkUtils.isConnected(mActivity))) {
                    networkError.setVisibility(View.VISIBLE);
                    ToastUtils.show(mActivity, getString(R.string.network_not_connect));
                    return;
                }

                super.onPageStarted(view, url, favicon);
                if (mActivity != null) {
                    if (TextUtils.isEmpty(mTitleFromAct) && mActivity instanceof WebViewActivity) {
                        mActivity.setTitle(view.getTitle());
                    }
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (mActivity != null) {
                    if (TextUtils.isEmpty(mTitleFromAct) && mActivity instanceof WebViewActivity) {
                        mActivity.setTitle(view.getTitle());
                    }
                }
                CookieManager cookieManager = CookieManager.getInstance();
                LogUtils.d("cookie with url", cookieManager.getCookie(url));
                super.onPageFinished(view, url);
            }

            @Override
            public void onFormResubmission(WebView view, Message dontResend, Message resend) {
                resend.sendToTarget();
            }


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 处理h5页面调用微信支付
                if (url.startsWith("weixin://wap/pay?")) {
                    //如果return false  就会先提示找不到页面，然后跳转微信
                    try {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                        ToastUtils.show(BaseApplication.getInstance(), "微信支付失败，请检查是否已安装微信");
                    }
                    return true;

                } else if (url.contains("alipays://platformapi")) { // 支付宝
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                } else if (url.startsWith("tel:")) { // 拨打电话
                    try {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                        if (ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                            startActivity(intent);
                        } else {
                            //申请权限
                            ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.CALL_PHONE}, 1);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //这个超连接,java已经处理了，webview不要处理
                    return true;
                }
                //人脸识别 视频录制
                if (!StringUtils.isEmpty(url)) {
                    videoFlag = url.contains(FACEID_URL);
                } else {
                    videoFlag = false;
                }
                return false;
            }


            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }
        });

        String s = PreferencesUtils.getString(
                Constants.COOKIE_PREFERENCE,
                BaseApplication.getInstance().getApplicationContext(),
                Constants.COOKIE);

        String cookie = s + String.format("%s=%s" + ";" + "%s=%s", "domain", ".xianglin.cn", "path", "/");
        LogUtils.i("cookie=" + cookie);


        if (TextUtils.isEmpty(baseUrl)) {
            baseUrl = "";
        }

        clearCookies(baseUrl, BaseApplication.getInstance(), cookie);


        mBBWebCore.loadUrl(baseUrl);

    }


    /**
     * 将cookie同步到WebView
     *
     * @param url    WebView要加载的url
     * @param cookie 要同步的cookie
     * @return true 同步cookie成功，false同步cookie失败
     * @Author JPH
     */


    @SuppressWarnings("deprecation")
    public static void clearCookies(String url, Context context, String cookie) {

        if (TextUtils.isEmpty(url) || context == null || TextUtils.isEmpty(cookie)) {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            LogUtils.d("WebView", "Using clearCookies code for API >=" + String.valueOf(Build.VERSION_CODES.LOLLIPOP_MR1));
            CookieManager.getInstance().removeAllCookies(null);
            CookieManager.getInstance().setAcceptCookie(true);
            CookieManager.getInstance().setCookie(url, cookie);
            CookieManager.getInstance().flush();
            LogUtils.d("cookie2", CookieManager.getInstance().getCookie(url));
        } else {
            LogUtils.d("WebView", "Using clearCookies code for API <" + String.valueOf(Build.VERSION_CODES.LOLLIPOP_MR1));
            CookieSyncManager cookieSyncMngr = CookieSyncManager.createInstance(context);
//            cookieSyncMngr.startSync();
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.removeAllCookie();
//            cookieManager.removeSessionCookie();

//            SystemClock.sleep(800); // 修复低版本4.4以前,某些手机取不到cookie的问题

//            cookieManager.setAcceptCookie(true);
            cookieManager.setCookie(url, cookie);

//            cookieSyncMngr.stopSync();
            cookieSyncMngr.sync();

            LogUtils.d("cookie1", cookieManager.getCookie(url));

        }


    }


    public class CustomChromeClient extends BBViewClient {

        public CustomChromeClient(Context context) {
            super(context);
        }

        public CustomChromeClient(String injectedName, Class injectedCls) {
            super(injectedName, injectedCls);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (mProgressBar != null && isShowLoadProgress) {
                mProgressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
//                    CookieManager cookieManager = CookieManager.getInstance();
//                    String CookieStr = cookieManager.getCookie(view.getUrl());
//                    LogUtils.d("WebViewFragment", "Cookies = " + CookieStr);

                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            }
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (mActivity != null && !TextUtils.isEmpty(title)) {
                if (TextUtils.isEmpty(mTitleFromAct)) {
                    mActivity.setTitle(title);
                }
            }
        }

        // js上传文件的<input type="file" name="fileField" id="fileField" />事件捕获
        // Android > 4.1.1 调用这个方法
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
            openFileChooser(uploadMsg, acceptType);
        }

        // 3.0 + 调用这个方法
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
            if (mUploadMessage != null) {
                return;
            }
            mUploadMessage = uploadMsg;
            Intent targetIntent = videoFlag ? createDefaultReacordVideoIntent() : createDefaultOpenableIntent();
            if (null != acceptType && acceptType.contains(VIDEO_MIME_TYPE)) {
                //只有此种情况进入视频录制
                if (videoFlag) {
                    //支持视频录制 支持活体检测  保证清晰度 使用原生录制
                    targetIntent = createDefaultReacordVideoIntent();
                    mActivity.startActivityForResult(targetIntent, FILECHOOSER_RESULTCODE);
                } else {
                    //调用阿里云进行视频录制
                }
            } else {
                mActivity.startActivityForResult(targetIntent, FILECHOOSER_RESULTCODE);
            }
        }

        // Android < 3.0 调用这个方法
        public void openFileChooser(ValueCallback<Uri> uploadMsg) {
            openFileChooser(uploadMsg, "");

        }


        //Android 5.0+
        @Override
        @SuppressLint("NewApi")
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            if (mUploadMessages != null) {
                return true;
            }
            mUploadMessages = filePathCallback;
            Intent targetIntent = videoFlag ? createDefaultReacordVideoIntent() : createDefaultOpenableIntent();
            if (null != fileChooserParams && fileChooserParams.getAcceptTypes()[0].contains(VIDEO_MIME_TYPE)) {
                //只有此种情况进入视频录制
                if (videoFlag) {
                    //支持视频录制 支持活体检测  保证清晰度 使用原生录制
                    targetIntent = createDefaultReacordVideoIntent();
                    mActivity.startActivityForResult(targetIntent, FILECHOOSER_RESULTCODE);
                } else {
                    //调用阿里云进行视频录制

                }
            } else {
                mActivity.startActivityForResult(targetIntent, FILECHOOSER_RESULTCODE);
            }
            return true;
        }


    }

    /**
     * 录制视频  默认使用原生视频录制
     *
     * @return
     */
    public Intent createDefaultReacordVideoIntent() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        //限制时长
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5);
        return intent;
    }

    protected Intent createDefaultOpenableIntent() {
        // Create and return a chooser with the default OPENABLE
        // actions including the camera, camcorder and sound
        // recorder where available.
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");

        Intent chooser = createChooserIntent(createCameraIntent());
        chooser.putExtra(Intent.EXTRA_INTENT, i);
        return chooser;
    }

    private Intent createChooserIntent(Intent... intents) {
        Intent chooser = new Intent(Intent.ACTION_CHOOSER);
        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, intents);
        chooser.putExtra(Intent.EXTRA_TITLE, "图片选择器");
        return chooser;
    }

    private Intent createCameraIntent() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mCameraFilePath = FileUtils.BROWSER_PHOTO_PATH +
                System.currentTimeMillis() + ".jpg";
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(mCameraFilePath)));
        return cameraIntent;
    }


    protected WebView getWebView() {
        return mBBWebCore;
    }


    @Override
    public void onResume() {
        super.onResume();
        /*if (mBBWebCore != null) {
            // 为了解决先点击其他h5页面，后进入react native页面，js不加载的问题
            // 20171227 为了解决游戏页面空白的问题，重新放开以下代码
            mBBWebCore.resumeTimers(); // 恢复js循环调用
        }*/
    }

    @Override
    public void onPause() {
        super.onPause();
        /*if (mBBWebCore != null) {
            // 为了解决先点击其他h5页面，后进入react native页面，js不加载的问题
            // 20171227 为了解决游戏页面空白的问题，重新放开以下代码
            mBBWebCore.pauseTimers(); // 暂停js循环调用
        }*/
    }


    @Override
    public void onDestroyView() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }

        if (mBBWebCore != null) {
            ViewGroup viewGroup = (ViewGroup) mBBWebCore.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(mBBWebCore);
            }
            mBBWebCore.removeAllViews();
            mBBWebCore.destroy();
        }

        super.onDestroyView();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FILECHOOSER_RESULTCODE) {
            handleOnActivityResult(requestCode, resultCode, data);
        } else if (requestCode == REQUEST_VIDEO_CROP) {

        }

    }

    /**
     * 处理onActivityResult
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    private void handleOnActivityResult(int requestCode, int resultCode, Intent data) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // 5.0以上
            if (null == mUploadMessages) return;
            Uri result = data == null || resultCode != RESULT_OK ? null
                    : data.getData();
            if (result == null && data == null && resultCode == RESULT_OK) {
                File cameraFile = new File(mCameraFilePath);
                if (cameraFile.exists()) {
                    result = Uri.fromFile(cameraFile);
                    // Broadcast to the media scanner that we have a new photo
                    // so it will be added into the gallery for the user.
                    mActivity.sendBroadcast(
                            new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, result));
                }
            }
            if (result == null) {
                mUploadMessages.onReceiveValue(null);
            } else {
                mUploadMessages.onReceiveValue(new Uri[]{result});
            }
            mUploadMessages = null;
        } else { // 5.0以下
            if (null == mUploadMessage) return;
            Uri result = data == null || resultCode != RESULT_OK ? null
                    : data.getData();
            if (result == null && data == null && resultCode == RESULT_OK) {
                File cameraFile = new File(mCameraFilePath);
                if (cameraFile.exists()) {
                    result = Uri.fromFile(cameraFile);
                    // Broadcast to the media scanner that we have a new photo
                    // so it will be added into the gallery for the user.
                    mActivity.sendBroadcast(
                            new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, result));
                }
            }
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        }
    }


    /**
     * 处理拍照/选择的文件
     */
    private File handleFile(File file) {
        DisplayMetrics dMetrics = getResources().getDisplayMetrics();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        int imageWidth = options.outWidth;
        int imageHeight = options.outHeight;
        System.out.println("  imageWidth = " + imageWidth + " imageHeight = " + imageHeight);
        int widthSample = (int) (imageWidth / (dMetrics.density * 90));
        int heightSample = (int) (imageHeight / (dMetrics.density * 90));
        System.out.println("widthSample = " + widthSample + " heightSample = " + heightSample);
        options.inSampleSize = widthSample < heightSample ? heightSample : widthSample;
        options.inJustDecodeBounds = false;
        Bitmap newBitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        System.out.println("newBitmap.size = " + newBitmap.getRowBytes() * newBitmap.getHeight());
        LogUtils.d("file name : " + file.getName());
        File handleFile = new File(file.getParentFile(), file.getName().replace(".jpg", "_upload.jpg"));
        LogUtils.d("file name : " + handleFile.getName());
        try {
            if (newBitmap.compress(Bitmap.CompressFormat.PNG, 50, new FileOutputStream(handleFile))) {
                System.out.println("保存图片成功");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return handleFile;

    }


    /**
     * @param result 扫一扫结果
     *               调用js  scanMachineCode 方法
     */
    public void androidToJsScanMachineCode(final String result) {
        if (getWebView() != null) {
            if (Build.VERSION.SDK_INT < 19) {
                getWebView().post(new Runnable() {
                    @Override
                    public void run() {
                        getWebView().loadUrl("javascript:scanMachineCode('" + result + "')");
                    }
                });
            } else {
                getWebView().evaluateJavascript("Javascript:scanMachineCode('" + result + "')", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        Logger.i("js------" + value);
                    }
                });
            }
        }

    }


    /**
     * 微信支付成功
     */
    public void wxAppCallbackSuccess() {
        if (getWebView() == null) {
            return;
        }
        getWebView().loadUrl("javascript:wxAppCallbackSuccess()");
    }

    /**
     * 微信支付失败
     */
    public void wxAppCallbackError(String msg, int type) {
        if (getWebView() == null) {
            return;
        }
        getWebView().loadUrl("javascript:wxAppCallbackError('" + msg + "','" + type + "')");
    }


}
