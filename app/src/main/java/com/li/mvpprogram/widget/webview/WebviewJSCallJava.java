/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.li.mvpprogram.widget.webview;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.li.mvpprogram.BaseApplication;
import com.li.mvpprogram.base.BaseNativeActivity;
import com.li.mvpprogram.bean.VersionBean;
import com.li.mvpprogram.utils.AppUtils;
import com.li.mvpprogram.utils.StringUtils;
import com.li.mvpprogram.utils.ToastUtils;
import com.li.mvpprogram.utils.json.GsonUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class WebviewJSCallJava {

    private Context context;
    private BBWebCore webView;
    private Handler handler = new Handler();

    public WebviewJSCallJava(Context context, BBWebCore webView) {
        this.context = context;
        this.webView = webView;
    }

    @JavascriptInterface
    public void savingTakingData(String callBack) {

    }


    @JavascriptInterface
    public void nativeCallJavaScript(final String callbackName) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                webView.callJavascript("App.nativeCallbacks." + callbackName,
                        null);
            }
        });
    }


    /**
     * 获取android系统信息
     *
     * @return
     */
    @JavascriptInterface
    public String getSystemInfo() {
        VersionBean versionBean = new VersionBean();
        versionBean.setAppVersion(
                AppUtils.getAppInfo(BaseApplication.getInstance().getApplicationContext()).getVersionName()
        );
        versionBean.setPhoneModel(Build.MODEL);
        versionBean.setSystemVersion(Build.VERSION.RELEASE);
        return GsonUtils.GsonString(versionBean);
    }

    /**
     * 提供给h5页面设置标题栏右端文字
     *
     * @param text
     */
    @JavascriptInterface
    public void setRightText(String text) {
        if (context instanceof WebViewActivity) {


        }
    }

    /**
     * 关闭当前webview activity
     * 发送连连支付成功失败事件
     */
    @JavascriptInterface
    public void closeWebviewPage(boolean state) {

    }

    /**
     * 关闭当前webview activity
     */
    @JavascriptInterface
    public void close() {
        if (context instanceof WebViewActivity) {

        }
    }



    /**
     * 提供给H5，拨打电话，不需要权限
     *
     * @param hotlineNumber
     */
    @JavascriptInterface
    public void callHotline(String hotlineNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL); // 这个不需要电话权限，不用ACTION_CALL，ACTION_CALL需要权限
        Uri data = Uri.parse("tel:" + hotlineNumber);
        intent.setData(data);
        if (context != null) {
            context.startActivity(intent);
        }
    }



    /**
     * 提供给乡邻账房，下载保存二维码
     *
     * @param url
     */
    @JavascriptInterface
    public void downloadImage(String url) {
        if (null == context || !(context instanceof Activity)) {
            return;
        }

        if (null == url || 0 == url.length()) {
            return;
        }
        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                saveImage(url);
            }
        });

    }

    //保存图片到本地
/*
    private void saveImage(String url) {
        new RxPermissions((Activity) context)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .compose(TransformUtils.defaultSchedulers())
                .subscribe(granted -> {
                    if (granted) { // 在android 6.0之前会默认返回true, 已经获取权限
                        RetrofitUtil.createFileService().downloadFiles(url)
                                .compose(TransformUtils.defaultSchedulers())
                                .subscribe(new Observer<ResponseBody>() {
                                    @Override
                                    public void onSubscribe(@NonNull Disposable disposable) {
                                    }

                                    @Override
                                    public void onNext(@NonNull ResponseBody responseBody) {
                                        if (null != responseBody) {
//                                            String path = FileUtils.IMG_SAVE_PATH + "/PayQRCode.jpg";
                                            String path = FileUtils.IMG_SAVE_PATH + System.currentTimeMillis() + ".jpg";
                                            try {
                                                FileUtils.getInstance().saveFile2SDCard(path, responseBody.bytes());
                                                //保存到系统相册
                                                //MediaStore.Images.Media.insertImage(context.getContentResolver(), path, "PayQRCode", "PayQRCode");
                                                //保存图片到相册
                                                File file = new File(path);
                                                try {
                                                    MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), file.getName(), null);
                                                } catch (FileNotFoundException e) {
                                                    e.printStackTrace();
                                                }
                                                //广播通知
                                                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getAbsolutePath())));

                                                //通知相册更新
                                                MediaScannerConnection.scanFile(context, new String[]{path}, new String[]{"image/jpeg"}, (String p, Uri url) -> {
                                                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                                                        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                                                        Uri uri = Uri.fromFile(new File(path));
                                                        intent.setData(uri);
                                                        context.sendBroadcast(intent);
                                                    }
                                                });

                                            } catch (IOException e) {
                                                e.printStackTrace();
                                                ((Activity) context).runOnUiThread(() -> ToastUtils.show(context, "保存失败"));
                                            }
                                            ((Activity) context).runOnUiThread(() -> ToastUtils.show(context, "保存成功，保存在" + path));
                                        } else {
                                            ((Activity) context).runOnUiThread(() -> ToastUtils.show(context, "保存失败"));
                                        }
                                    }

                                    @Override
                                    public void onError(@NonNull Throwable throwable) {
                                        ((Activity) context).runOnUiThread(() -> ToastUtils.show(context, "下载失败"));
                                    }

                                    @Override
                                    public void onComplete() {
                                    }
                                });
                    } else { // 未获取权限
                        DialogManager.showMyAlerDialog(context,
                                "获取权限失败",
                                "请检查并开启应用所需权限", () -> {
                                    DialogManager.hideDialog();
                                },
                                Gravity.CENTER_HORIZONTAL
                        );
                    }
                });
    }
*/



    /**
     * js页面跳转到activity并传数据
     *
     * @param activityName activity类名路径
     * @param paramJson    仅支持五种基本数据类型   boolean int float double String
     */
    @JavascriptInterface
    public void startPage(String activityName, String paramJson) {
        try {
            if (context == null) {
                return;
            }
//            if (activityName.contains("GoldActivity") && UserInfoManager.getInstance().getUserType().equalsIgnoreCase(Constant.UserType.visitor.name())) {
//                login();
//                return;
//            }
            Class aimActivity = Class.forName(activityName);
            Intent intent = new Intent(context, aimActivity);
            Bundle bundle = null;
            if (TextUtils.isEmpty(paramJson)) {
                intent.putExtra(BaseNativeActivity.BUNDLE, bundle);
                context.startActivity(intent);
                return;
            }
            bundle = new Bundle();
            JSONObject jsonObject = new JSONObject(paramJson);
            Iterator<String> it = jsonObject.keys();
            while (it.hasNext()) {
                String json_key = it.next();
                Object json_value = jsonObject.get(json_key);
                if (json_value == null) {
                    continue;
                }
                if (json_value instanceof Boolean) {
                    bundle.putBoolean(json_key, (Boolean) json_value);
                } else if (json_value instanceof Integer) {
                    bundle.putInt(json_key, (Integer) json_value);
                } else if (json_value instanceof Float) {
                    bundle.putFloat(json_key, (Float) json_value);
                } else if (json_value instanceof Double) {
                    bundle.putDouble(json_key, (Double) json_value);
                } else if (json_value instanceof String) {
                    bundle.putString(json_key, (String) json_value);
                }
            }
            intent.putExtra(BaseNativeActivity.BUNDLE, bundle);
            context.startActivity(intent);
        } catch (Exception e) {
            ToastUtils.show(BaseApplication.getInstance(), "跳转页面失败");
            e.printStackTrace();
        }
    }


    /**
     * xxx保存图片到本地xxx
     *
     * @param url          下载图片地址
     * @param isShowDialog 进度条显示文案
     * @param message      是否展示进度条
     * @param toastMessage toast 显示内容
     */

    /*private void saveImage(String url, boolean isShowDialog, String message, String toastMessage) {
        if (context == null || TextUtils.isEmpty(url)) {
            return;
        }
        new RxPermissions((Activity) context)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .compose(TransformUtils.defaultSchedulers())
                .subscribe(granted -> {
                    if (granted) { // 在android 6.0之前会默认返回true, 已经获取权限
                        if (isShowDialog) {//
                            DialogManager.showProgressDialog(context, message);
                        }

                        RetrofitUtil.createFileService().downloadFiles(url)
                                .compose(TransformUtils.defaultSchedulers())
                                .subscribe(new Observer<ResponseBody>() {
                                    @Override
                                    public void onSubscribe(@NonNull Disposable disposable) {
                                    }

                                    @Override
                                    public void onNext(@NonNull ResponseBody responseBody) {
                                        if (null != responseBody) {
//                                            String path = FileUtils.IMG_SAVE_PATH + "/PayQRCode.jpg";
                                            String path = FileUtils.IMG_SAVE_PATH + System.currentTimeMillis() + ".jpg";
                                            try {
                                                FileUtils.getInstance().saveFile2SDCard(path, responseBody.bytes());
                                                //保存到系统相册
                                                //MediaStore.Images.Media.insertImage(context.getContentResolver(), path, "PayQRCode", "PayQRCode");
                                                //保存图片到相册
                                                File file = new File(path);
                                                try {
                                                    MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), file.getName(), null);
                                                } catch (FileNotFoundException e) {
                                                    e.printStackTrace();
                                                }
                                                //广播通知
                                                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getAbsolutePath())));

                                                //通知相册更新
                                                MediaScannerConnection.scanFile(context, new String[]{path}, new String[]{"image/jpeg"}, (String p, Uri url) -> {
                                                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                                                        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                                                        Uri uri = Uri.fromFile(new File(path));
                                                        intent.setData(uri);
                                                        context.sendBroadcast(intent);
                                                    }
                                                });

                                            } catch (IOException e) {
                                                e.printStackTrace();
                                                ((Activity) context).runOnUiThread(() -> ToastUtils.show(context, "保存失败"));
                                            }
//                                            ((Activity) context).runOnUiThread(() -> ToastUtils.show(context, "保存成功，保存在" + path));
                                            if (!TextUtils.isEmpty(toastMessage)) {
                                                ((Activity) context).runOnUiThread(() -> ToastUtils.show(context, toastMessage));
                                            }

                                        } else {
                                            ((Activity) context).runOnUiThread(() -> ToastUtils.show(context, "保存失败"));
                                        }
                                    }

                                    @Override
                                    public void onError(@NonNull Throwable throwable) {
                                        DialogManager.hideDialog();
                                        ((Activity) context).runOnUiThread(() -> ToastUtils.show(context, "下载失败"));
                                    }

                                    @Override
                                    public void onComplete() {
                                        DialogManager.hideDialog();
                                    }
                                });
                    } else { // 未获取权限
                        DialogManager.showMyAlerDialog(context,
                                "获取权限失败",
                                "请检查并开启应用所需权限", () -> {
                                    DialogManager.hideDialog();
                                },
                                Gravity.CENTER_HORIZONTAL
                        );
                    }
                });
    }*/


    /**
     * 查询app是否已在本地安装，给js调用
     *
     * @param packageName
     * @return
     */
    @JavascriptInterface
    public boolean isAppInstalled(String packageName) {
        if (StringUtils.isEmpty(packageName)) {
            return false;
        }
        PackageManager pm = context.getPackageManager();
        //查询到所有已安装的应用
        List<PackageInfo> infos = pm.getInstalledPackages(0);
        for (PackageInfo info : infos) {
            if (packageName.equalsIgnoreCase(info.packageName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 批量查询
     *
     * @param packageNames
     * @return
     */
    @JavascriptInterface
    public String[] isAppInstalled(String[] packageNames) {
        if (packageNames == null || packageNames.length <= 0) {
            return packageNames;
        }
        String[] results = new String[packageNames.length];
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> infos = pm.getInstalledPackages(0);
        List<String> packages = new ArrayList<>();
        for (PackageInfo info : infos) {
            packages.add(info.packageName);
        }
        int index = 0;
        for (int i = 0; i < packageNames.length; i++) {
            if (packages.contains(packageNames[i])) {
                results[index] = packageNames[i];
                index++;
            }
        }
        return results;
    }
}
