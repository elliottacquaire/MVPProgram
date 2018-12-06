package com.li.mvpprogram;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.facebook.stetho.Stetho;
import com.li.mvpprogram.config.ENVController;
import com.li.mvpprogram.config.ReleaseSwitch;
import com.li.mvpprogram.exception.CustomCrashHandler;
import com.li.mvpprogram.http.OkHttpUtil;
import com.li.mvpprogram.utils.AppUtils;
import com.li.mvpprogram.utils.ChannelUtil;
import com.li.mvpprogram.utils.LogUtils;
import com.li.mvpprogram.utils.net.NetStateChangeReceiver;
import com.li.mvpprogram.widget.image.AlbumBitmapCacheHelper;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.io.InputStream;

public class BaseApplication extends Application {

    private static BaseApplication instance;

    /**
     * 是否打印log
     */
    public boolean print = true;
    /**
     * code push热更新deployment key
     */
    public String codePushDeployKey;
    /**
     * 是否开启轮询服务
     */
    public boolean pollService = false;


    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public synchronized void onCreate() {
        super.onCreate();

        // 获取热更新patch
        querySophixPatch();

        instance = this;
        initAssistData();
        //解决7.0版本 拍照问题
        handleCamera();

        // 注册广告屏监听，用于处理轮询service前后台切换后停止的问题
//        new SplashAdWrapper(this);
    }

    private void handleCamera() {

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.detectFileUriExposure();
        }

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        // 取消BroadcastReceiver注册
        NetStateChangeReceiver.unregisterReceiver(this);
    }

    /**
     * 获取热更新patch
     */
    private void querySophixPatch() {
        // 如果是debug包，不启用热修复
        if (BuildConfig.DEBUG) {
            return;
        }
        // queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
//        SophixManager.getInstance().queryAndLoadNewPatch();
    }

    /**
     * 初始化辅助工具
     */
    private void initAssistData() {

//        if (!LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            LeakCanary.install(this);
//        }

        /* 初始化全局异常捕获信息 */
        CustomCrashHandler customCrashHandler = CustomCrashHandler.getInstance();
        customCrashHandler.init(this);
        /* 初始化接口环境 */
        String xlEnv = ReleaseSwitch.XL_ENV_DEBUG_VALUE;
        /* 初始化数据库版本 */
        int xlDBVer = ReleaseSwitch.DB_DEBUG_VER;

        String appKey = AppUtils.getMetaValue(this, "UMENG_APPKEY", "");
        String channelName = ReleaseSwitch.UM_CHANNEL_NAME;

        // 非调试模式(自动化打包)下从manifest文件里面读取meta值
        if (!BuildConfig.DEBUG) {
            xlEnv = AppUtils.getMetaValue(this, "XL_ENV", xlEnv);

            channelName = ChannelUtil.getChannel(this, ReleaseSwitch.UM_CHANNEL_NAME_RELEASE);
            try {
                xlDBVer = Integer.parseInt(AppUtils.getMetaValue(this, "XL_DB_VER", String.valueOf(ReleaseSwitch.DB_DEBUG_VER)));
            } catch (Exception e) {
                e.printStackTrace();
                xlDBVer = ReleaseSwitch.DB_DEBUG_VER;
            }
        }

        //图像选择sdk
        AlbumBitmapCacheHelper.init(this);
        /*初始化接口环境地址*/
        ENVController.initEnv(this, xlEnv);

        /*为配合APPInfo init函数 渠道参数获取*/
//        CacheSet.getInstance(this).putString("channels", channelName);


        //初始化推送
//        XLPushManager.initXLPushManager(XLApplication.getInstance());


        initThirdPartyConfig();

        String appID = AppUtils.getMetaValue(this, "BUGLY_APPID", "");

//        codePushDeployKey = getString(R.string.deployment_key_staging);

        // 设置日志开关, release环境关闭log 
        if (!BuildConfig.DEBUG && ENVController.ENV_PRODUCT.equals(xlEnv)) {
//            codePushDeployKey = getString(R.string.deployment_key_production);
            print = false;
            /* 腾讯bugly初始化*/
           /* 第三个参数为SDK调试模式开关，调试模式的行为特性如下：
            ● 输出详细的Bugly SDK的Log；
            ● 每一条Crash都会被立即上报；
            ● 自定义日志将会在Logcat中输出*/
//            CrashReport.initCrashReport(this, appID, false); //发布时第三个参数修改为 "false"
        }

        if (getApplicationInfo().packageName.equals(AppUtils.getCurProcessName(getApplicationContext()))) {
            // 初始化融云
//            RongIM.init(this);
            // 初始化消息监听器
//            ChatAppContext.init(this);
            // 设置消息携带用户信息
            // RongIM.getInstance().setMessageAttachedUserInfo(true);
        }

        // 注册BroadcastReceiver
        NetStateChangeReceiver.registerReceiver(this);
    }


    /**
     * 初始化第三方库的设置
     */
    private void initThirdPartyConfig() {
        // 版本更新
//        UpdateAppUtils.getInstance().updateInit();

        LogUtils.init("XlLogger");
        // 初始化glide
        Glide.get(this)
                .register(
                        GlideUrl.class,
                        InputStream.class,
                        new OkHttpUrlLoader.Factory(OkHttpUtil.createFileClient())
                );

        // 初始化realm
//        Realm.init(this);
//        RealmConfiguration config = new RealmConfiguration.Builder()
//                .name(getPackageName() + ".realm")
//                .schemaVersion(Constants.DB_VERSION)
//                .migration(new DatabaseMigrations())
//                .build();
//        Realm.setDefaultConfiguration(config);
        if (BuildConfig.DEBUG) { // 调试情况下启用stetho
            // 初始化stetho
            Stetho.initialize(
                    Stetho.newInitializerBuilder(this)
                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                            .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                            .build());
        }


    }



}
