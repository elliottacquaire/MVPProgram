/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.li.mvpprogram.utils;

/**
 * app版本更新
 *
 * @author ex-zhangxiang
 * @version v 1.0.0 2016/10/13 15:18 XLXZ Exp $
 * @email ex-zhangxiang@xianglin.cn
 */

public class UpdateAppUtils {/*

    private NotificationManager manager;
    private Notification notif;
    private int NOTIFCATION_ID = 10;
    private int downloadCount = 0;
    private UpdateDialog updateDialog = null;
    private com.xianglin.app.widget.dialog.UpdateDialogDownload updateDialogDownload = null;
    private boolean isError = false;
    private String mTarget = com.xianglin.app.utils.FileUtils.APK_INSTALL_PATH + "XiangLin.apk";
    private InstallNotifier mInstallGreator;

    private UpdateBuilder mUpdateBuilder;

    public static UpdateAppUtils getInstance() {
        return new UpdateAppUtils();
    }

    *//**
     * 对Config进行配置
     *//*
    public void updateInit() {
        UpdateConfig.getConfig()
                // 必填：需尽早进行Application初始化操作。
                //.init(XLApplication.getInstance().getApplicationContext())
                .setUrl(ENVController.URL)
                // 自定义版本检查
                .setUpdateChecker(update -> {
                    // 在此对应用版本进行比对检测。返回true说明该update版本需要被更新。false不需要更新
                    return true;
                })
                // 自定义更新策略，默认WIFI下自动下载更新
                .setUpdateStrategy(new UpdateStrategy() {
                    @Override
                    public boolean isShowUpdateDialog(Update update) {
                        // 是否在检查到有新版本更新时展示Dialog。
                        return true;
                    }

                    @Override
                    public boolean isAutoInstall() {
                        // 下载完成后，是否自动更新。若为false。则创建Dialog显示
                        return false;
                    }

                    @Override
                    public boolean isShowDownloadDialog() {
                        // 在APK下载时。是否显示下载进度的Dialog
                        return false;
                    }
                });
    }

    *//**
     * app版本更新
     *
     * @param result            服务端返回的数据
     * @param context           context
     * @param content           提示更新时dialog显示的内容
     * @param content2          下载完成dialog显示的内容
     * @param isMandatoryUpdate 是否强制版本升级
     * @param tag               用于判断是哪个页面
     *//*
    public void updateApp(AppVersionVo result, Context context, String content, String content2, String versionCode, boolean isMandatoryUpdate, String tag) {
        if (context == null) {
            return;
        }
        String isDownload = com.xianglin.app.utils.PreferencesUtils.getString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS, Constants.DEFAULT_DID);

        *//*
         * 非强制升级&&进入到首页自动检查
         * 一小时提示一次（三次）
         *//*
        if (!isMandatoryUpdate && tag.equals(MineFragment.TAG)) {
            long sixHours = UPDATE_TIP_TIME;
            long timeFirst = com.xianglin.app.utils.PreferencesUtils.getLong(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_TIME_FIRST);
            long timeSecond = com.xianglin.app.utils.PreferencesUtils.getLong(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_TIME_SECOND);
            long timeThird = com.xianglin.app.utils.PreferencesUtils.getLong(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_TIME_THIRD);
            if (timeFirst == -1) {
                long time = System.currentTimeMillis();
                com.xianglin.app.utils.PreferencesUtils.putLong(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_TIME_FIRST, time);
            } else if (timeSecond == -1) {
                long time = System.currentTimeMillis();
                if (time - timeFirst < sixHours) {
                    return;
                }
                com.xianglin.app.utils.PreferencesUtils.putLong(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_TIME_SECOND, time);
            } else if (timeThird == -1) {
                long time = System.currentTimeMillis();
                if (time - timeSecond < sixHours) {
                    return;
                }
                com.xianglin.app.utils.PreferencesUtils.putLong(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_TIME_THIRD, time);
            } else if (timeThird - timeSecond > sixHours) {
                return;
            }
        }

        // 已下载
        if (isDownload.equals(Constants.UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS)) {
            install(content2, isMandatoryUpdate, context, content, versionCode, result);
            return;
        }

        if (mUpdateBuilder == null) {
            mUpdateBuilder = UpdateBuilder.create();
        }

        if (mUpdateBuilder.getCheckWorker() != null && mUpdateBuilder.getCheckWorker().isRunning()) {
            com.xianglin.app.utils.ToastUtils.show(context, "正在检查更新, 请稍后再试");
            return;
        }

        mUpdateBuilder.setUpdateParser(updateParser(result))
                // 自定义检查出更新后显示的Dialog
                .setCheckNotifier(
                        new CheckNotifier() {
                            @Override
                            public Dialog create(Activity activity) {
                                if (updateDialog == null) {
                                    updateDialog = new com.xianglin.app.widget.dialog.UpdateDialog(context, () -> {
                                        updateDialog.dismiss();
                                        if (content == null) return;
                                        if (com.xianglin.app.utils.NetworkUtils.isWifiConnected(context)) {
                                            downloadAPK(result, false, context);
                                        }
//							}
                                    }, () -> {
                                        if (!isMandatoryUpdate) {
                                            updateDialog.dismiss();
                                        }
                                        if (!com.xianglin.app.utils.NetworkUtils.isConnected(context)) {
                                            com.xianglin.app.utils.ToastUtils.show(context, "网络不给力，请检查网络连接");
                                            return;
                                        }
                                        if (com.xianglin.app.utils.PreferencesUtils.getString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_IS_LOWDING).equals(Constants.UPDATE_KEY_EXTRAS_IS_LOWDING)) {
                                            com.xianglin.app.utils.ToastUtils.show(context, "正在下载，请稍后~");
                                            return;
                                        } else if (mUpdateBuilder != null && mUpdateBuilder.getDownloadWorker() != null && mUpdateBuilder.getDownloadWorker().isRunning()) {
                                            com.xianglin.app.utils.ToastUtils.show(context, "正在下载，请稍后~");
                                            return;
                                        } else if (isError) {
                                            errorDownloadAPK(result, content, content2, versionCode, isMandatoryUpdate, context);
                                            return;
                                        }
                                        if (update != null) {
                                            try {
                                                // 立即更新
                                                sendDownloadRequest();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    },
                                            content2, content, isMandatoryUpdate);
                                }

                                if (context != null) {
                                    updateDialog.show();
                                }
                                return updateDialog;
                            }
                        })
                // 自定义下载完成后。显示的Dialog,
                .setInstallNotifier(installCreator(context, result, content, content2, versionCode, isMandatoryUpdate))
                // 新版本APK下载时的回调
                .setDownloadCallback(updateDownloadCB(context))
                // 自定义下载文件缓存,默认参考
                .setFileCreator(versionName -> {
                    // versionName 为解析的Update实例中的update_url数据。在这里可自定义下载文件缓存路径及文件名。放置于File中
                    return new File(com.xianglin.app.utils.FileUtils.APK_INSTALL_PATH + "XiangLin.apk");
                })
                .setDownloadWorker(new com.xianglin.app.utils.update.OkhttpDownloadWorker())
                .check();
    }

    *//**
     * 版本已下载， 是否安装
     *//*
    private void install(String content2, boolean isMandatoryUpdate, Context context, String content, String versionCode, AppVersionVo result) {
        if (updateDialogDownload == null) {
            updateDialogDownload = new com.xianglin.app.widget.dialog.UpdateDialogDownload(context,
                    () -> updateDialogDownload.dismiss(),
                    () -> {
                        if (!isMandatoryUpdate)
                            updateDialogDownload.dismiss();
                        if (com.xianglin.app.utils.PreferencesUtils.getString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_IS_LOWDING).equals(Constants.UPDATE_KEY_EXTRAS_IS_LOWDING)) {
                            com.xianglin.app.utils.ToastUtils.show(context, "正在下载，请稍后~");
                            return;
                        }

                        if (mUpdateBuilder != null && mUpdateBuilder.getDownloadWorker() != null && mUpdateBuilder.getDownloadWorker().isRunning()) {
                            com.xianglin.app.utils.ToastUtils.show(context, "正在下载，请稍后~");
                            return;
                        }

                        judgeInstall(context, result);
                        // 立即安装
//                    if (!AppUtils.installApp(context, FileUtils.APK_INSTALL_PATH + "XiangLin.apk")) {
//                        ToastUtils.show(context, R.string.app_update_download_not_apk);
//                        clearUpdatePreference();
//                        PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS, Constants.DEFAULT_DID);
//                        downloadAPK(result, true, context);
//                    }
                    }, content2, content, versionCode, isMandatoryUpdate);
        }
        updateDialogDownload.show();
    }

    *//**
     * 自动下载
     *//*
    private void downloadAPK(AppVersionVo result, boolean isdown, Context context) {
        if (mUpdateBuilder == null) {
            mUpdateBuilder = UpdateBuilder.create();
        }

        if (mUpdateBuilder.getCheckWorker() != null && mUpdateBuilder.getCheckWorker().isRunning()) {
            com.xianglin.app.utils.ToastUtils.show(context, "正在检查更新，请稍后再试");
            return;
        }

        mUpdateBuilder.setUpdateParser(updateParser(result))
                .setFileCreator(versionName -> {
                    // versionName 为解析的Update实例中的update_url数据。在这里可自定义下载文件缓存路径及文件名。放置于File中
                    return new File(com.xianglin.app.utils.FileUtils.APK_INSTALL_PATH + "XiangLin.apk");
                })
                .setCheckNotifier(new CheckNotifier() {
                    @Override
                    public Dialog create(Activity activity) {
                        sendDownloadRequest();
                        return null;
                    }
                })
                .setInstallNotifier(new InstallNotifier() {
                    @Override
                    public Dialog create(Activity activity) {
                        clearUpdatePreference();
                        com.xianglin.app.utils.PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS, Constants.UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS);
                        return null;
                    }
                })
                .setDownloadCallback(new DownloadCallback() {
                    // 下载开始
                    @Override
                    public void onDownloadStart() {
                        com.xianglin.app.utils.PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_IS_LOWDING, Constants.UPDATE_KEY_EXTRAS_IS_LOWDING);
                    }

                    // 下载完成
                    @Override
                    public void onDownloadComplete(File file) {
                        if (isdown) {
                            judgeInstall(context, result);
//                            if (!AppUtils.installApp(context, FileUtils.APK_INSTALL_PATH + "XiangLin.apk")) {
//                                ToastUtils.show(context, R.string.app_update_download_not_apk);
//                                clearUpdatePreference();
//                                PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS, Constants.DEFAULT_DID);
//                                downloadAPK(result, true, context);
//                            }
                        }
                        com.xianglin.app.utils.PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, XLApplication.getInstance().getApplicationContext(), Constants.UPDATE_KEY_EXTRAS_IS_LOWDING, Constants.DEFAULT_DID);
                    }

                    // 下载进度
                    @Override
                    public void onDownloadProgress(long current, long total) {
                    }

                    // 下载apk错误
                    @Override
                    public void onDownloadError(Throwable var1) {
                        com.xianglin.app.utils.ToastUtils.show(context, R.string.app_update_download_error_tip);
                        com.xianglin.app.utils.PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, XLApplication.getInstance().getApplicationContext(), Constants.UPDATE_KEY_EXTRAS_IS_LOWDING, Constants.DEFAULT_DID);
                    }
                })
                .setDownloadWorker(new com.xianglin.app.utils.update.OkhttpDownloadWorker())
                .check();
    }

    private void errorDownloadAPK(AppVersionVo result, String content, String content2, String versionCode, boolean isMandatoryUpdate, Context context) {
        if (mUpdateBuilder == null) {
            mUpdateBuilder = UpdateBuilder.create();
        }

        if (mUpdateBuilder.getCheckWorker() != null && mUpdateBuilder.getCheckWorker().isRunning()) {
            com.xianglin.app.utils.ToastUtils.show(context, "正在检查更新，请稍后再试");
            return;
        }

        mUpdateBuilder.setUpdateParser(updateParser(result))
                .setFileCreator(versionName -> {
                    // versionName 为解析的Update实例中的update_url数据。在这里可自定义下载文件缓存路径及文件名。放置于File中
                    return new File(com.xianglin.app.utils.FileUtils.APK_INSTALL_PATH + "XiangLin.apk");
                })
                .setCheckNotifier(new CheckNotifier() {
                    @Override
                    public Dialog create(Activity activity) {
                        sendDownloadRequest();
                        return null;
                    }
                })
                .setInstallNotifier(installCreator(context, result, content, content2, versionCode, isMandatoryUpdate))
                .setDownloadCallback(updateDownloadCB(context))
                .setDownloadWorker(new com.xianglin.app.utils.update.OkhttpDownloadWorker())
                .check();
    }

    *//**
     * 自定义下载完成后。显示的Dialog
     *//*
    private InstallNotifier installCreator(Context context, AppVersionVo result, String content, String content2, String versionCode, boolean isMandatoryUpdate) {
        mInstallGreator = new InstallNotifier() {
            @Override
            public Dialog create(Activity activity) {
                clearUpdatePreference();
                com.xianglin.app.utils.PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS, Constants.UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS);
                if (updateDialogDownload == null) {
                    updateDialogDownload = new com.xianglin.app.widget.dialog.UpdateDialogDownload(context, () -> {
                        updateDialogDownload.dismiss();
                        // 取消更新
                        sendUserCancel();
                    }, () -> {
                        if (!isMandatoryUpdate)
                            updateDialogDownload.dismiss();
                        if (com.xianglin.app.utils.PreferencesUtils.getString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_IS_LOWDING).equals(Constants.UPDATE_KEY_EXTRAS_IS_LOWDING)) {
                            com.xianglin.app.utils.ToastUtils.show(context, "正在下载，请稍后~");
                            return;
                        }

                        if (mUpdateBuilder != null && mUpdateBuilder.getDownloadWorker() != null && mUpdateBuilder.getDownloadWorker().isRunning()) {
                            com.xianglin.app.utils.ToastUtils.show(context, "正在下载，请稍后~");
                            return;
                        }

                        if (new File(mTarget).exists()) {
                            sendToInstall();
                        } else {
                            com.xianglin.app.utils.ToastUtils.show(context, R.string.app_update_download_not_apk);
                            clearUpdatePreference();
                            com.xianglin.app.utils.PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS, Constants.DEFAULT_DID);
                            downloadAPK(result, true, context);
                        }
                        // 立即安装
                    *//*if (!AppUtils.installApp(context, FileUtils.APK_INSTALL_PATH + "XiangLin.apk")) {
                        ToastUtils.show(context, R.string.app_update_download_not_apk);
                        clearUpdatePreference();
                        PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS, Constants.DEFAULT_DID);
                        downloadAPK(result, true, context);
                    }*//*
                    }, content2, content, versionCode, isMandatoryUpdate);
                }
                if (activity != null) {
                    updateDialogDownload.show();
                }
                return updateDialogDownload;
            }
        };
        return mInstallGreator;
    }

    private UpdateParser updateParser(AppVersionVo result) {
        return response -> {
            *//*
            * 此处模拟一个Update对象，传入接口返回的原始数据进去保存。
            * 若用户需要自定义的时候。对于有额外参数。可从中获取并定制。
            *//*
            Update update = new Update();
            // 此apk包的下载地址  例:http://apk.hiapk.com/web/api.do?qt=8051&id=721
            update.setUpdateUrl(result.getDownloadURL());
            update.setVersionCode(result.getVersionCode());
            return update;
        };
    }

    *//**
     * 下载apk时的回调
     *//*
    private DownloadCallback updateDownloadCB(Context context) {

        return new DownloadCallback() {
            // 下载开始
            @Override
            public void onDownloadStart() {
                com.xianglin.app.utils.PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_IS_LOWDING, Constants.UPDATE_KEY_EXTRAS_IS_LOWDING);
                com.xianglin.app.utils.ToastUtils.show(context, R.string.app_update_downloading);
                downloadCount = 0;
                manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

                String id = "channel_xianglin";
                Notification.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // 大于等于Android8.0
                    String description = XLApplication.getInstance().getString(R.string.app_name);
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel channel = new NotificationChannel(id, description, importance);
                    manager.createNotificationChannel(channel);

                    builder = new Notification.Builder(context, id);
                } else { // 低于Android8.0
                    builder = new Notification.Builder(context);
                }
                notif = builder
                        .setSmallIcon(R.drawable.ic_notify)
                        .setLargeIcon(BitmapFactory.decodeResource(XLApplication.getInstance().getResources(), R.drawable.ic_notify))
                        .setTicker(context.getResources().getText(R.string.app_update_downloading))
                        //通知栏显示所用到的布局文件
                        .setContent(new RemoteViews(context.getPackageName(), R.layout.progress_notif))
                        .setOnlyAlertOnce(true)
                        .build();

                manager.notify(NOTIFCATION_ID, notif);


            }

            // 下载完成
            @Override
            public void onDownloadComplete(File file) {
                clearUpdatePreference();
                com.xianglin.app.utils.PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, XLApplication.getInstance().getApplicationContext(), Constants.UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS, Constants.UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS);
                com.xianglin.app.utils.PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, XLApplication.getInstance().getApplicationContext(), Constants.UPDATE_KEY_EXTRAS_IS_LOWDING, Constants.DEFAULT_DID);
                // 下载完成  notification 取消
                NotificationManager manger = (NotificationManager) XLApplication.getInstance().getApplicationContext().getSystemService(XLApplication.getInstance().getApplicationContext().NOTIFICATION_SERVICE);
                manger.cancel(NOTIFCATION_ID);
                downloadCount = 0;
            }

            // 下载进度
            @Override
            public void onDownloadProgress(long current, long total) {
                if (current == total) {

                    notif.contentView.setTextViewText(R.id.content_view_text1, String.format(XLApplication.getInstance().getApplicationContext().getString(R.string.app_update_download_progress), String.valueOf(100)));
                    notif.contentView.setProgressBar(R.id.content_view_progress, 100, 100, false);
                    manager.notify(NOTIFCATION_ID, notif);


                }

                int tmp = (int) (current * 100 / total);
                // 过滤不必要的刷新进度
                if (downloadCount < tmp) {
                    downloadCount = tmp;

                    notif.contentView.setTextViewText(R.id.content_view_text1, String.format(XLApplication.getInstance().getApplicationContext().getString(R.string.app_update_download_progress), String.valueOf(tmp)));
                    notif.contentView.setProgressBar(R.id.content_view_progress, 100, tmp, false);
                    manager.notify(NOTIFCATION_ID, notif);
                }
            }

            // 下载apk错误
            @Override
            public void onDownloadError(Throwable var1) {
                if (mUpdateBuilder != null && mUpdateBuilder.getCheckWorker() != null && mUpdateBuilder.getCheckWorker().isRunning()) {
                    com.xianglin.app.utils.ToastUtils.show(context, "正在检查更新, 请稍后再试");
                } else {
                    com.xianglin.app.utils.ToastUtils.show(context, R.string.app_update_download_error_tip);
                }
                com.xianglin.app.utils.PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_IS_LOWDING, Constants.DEFAULT_DID);
            }
        };
    }

    private void clearUpdatePreference() {
        com.xianglin.app.utils.PreferencesUtils.putLong(Constants.UPDATE_PREFERENCE, XLApplication.getInstance(), Constants.UPDATE_KEY_EXTRAS_TIME_FIRST, -1);
        com.xianglin.app.utils.PreferencesUtils.putLong(Constants.UPDATE_PREFERENCE, XLApplication.getInstance(), Constants.UPDATE_KEY_EXTRAS_TIME_SECOND, -1);
        com.xianglin.app.utils.PreferencesUtils.putLong(Constants.UPDATE_PREFERENCE, XLApplication.getInstance(), Constants.UPDATE_KEY_EXTRAS_TIME_THIRD, -1);
    }

    private void judgeInstall(Context context, AppVersionVo result) {
        if (new File(mTarget).exists() && null != mInstallGreator) {
            mInstallGreator.sendToInstall();
        } else if (null != context && !com.xianglin.app.utils.AppUtils.installApp(context, mTarget)) {
            com.xianglin.app.utils.ToastUtils.show(context, R.string.app_update_download_not_apk);
            clearUpdatePreference();
            com.xianglin.app.utils.PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS, Constants.DEFAULT_DID);
            downloadAPK(result, true, context);
        }
    }*/

}
