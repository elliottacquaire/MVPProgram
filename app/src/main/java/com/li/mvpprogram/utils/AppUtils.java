/**/

package com.li.mvpprogram.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import com.li.mvpprogram.BaseApplication;
import com.li.mvpprogram.BuildConfig;
import com.li.mvpprogram.bean.InstallAppDTO;
import com.li.mvpprogram.config.Constants;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static android.content.pm.PackageManager.GET_SERVICES;

/**
 * APP相关操作
 */
public class AppUtils {

    private AppUtils() {
        throw new UnsupportedOperationException("you can't create AppUtils object");
    }

    /**
     * 安装指定路径下的Apk
     * 根据路径名是否符合和文件是否存在判断是否安装成功
     * 更好的做法应该是startActivityForResult回调判断是否安装成功比较妥当
     * 这里做不了回调，后续自己做处理
     */
    public static boolean installApp(Context context, String filePath) {
        if (filePath != null && filePath.length() > 4
                && filePath.toLowerCase().substring(filePath.length() - 4).equals(".apk")) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            File file = new File(filePath);
            if (file.exists() && file.isFile() && file.length() > 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//解决 Android N 上 安装Apk时报错
                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    Uri contentUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".FileProvider", new File(filePath));
                    intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
                } else {
                    intent.setDataAndType(Uri.fromFile(new File(filePath)), "application/vnd.android.package-archive");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }
                context.startActivity(intent);
                return true;
            }
        }
        return false;
    }

    /**
     * 判断app是否在运行
     */
    public static boolean isAppRuning() {
        List<Activity> activities = ActivityManagerTool.getActivityManager().getActivities();
        if (activities == null || activities.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * 卸载指定包名的App
     * 这里卸载成不成功只判断了packageName是否为空
     * 如果要根据是否卸载成功应该用startActivityForResult回调判断是否还存在比较妥当
     * 这里做不了回调，后续自己做处理
     */
    public boolean uninstallApp(Context context, String packageName) {
        if (!TextUtils.isEmpty(packageName)) {
            Intent intent = new Intent(Intent.ACTION_DELETE);
            intent.setData(Uri.parse("package:" + packageName));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            return true;
        }
        return false;
    }

    /**
     * 封装App信息的Bean类
     */
    public static class AppInfo {

        private String name;
        private Drawable icon;
        private String packagName;
        private String versionName;
        private int versionCode;
        private boolean isSD;
        private boolean isUser;

        public Drawable getIcon() {
            return icon;
        }

        public void setIcon(Drawable icon) {
            this.icon = icon;
        }

        public boolean isSD() {
            return isSD;
        }

        public void setSD(boolean SD) {
            isSD = SD;
        }

        public boolean isUser() {
            return isUser;
        }

        public void setUser(boolean user) {
            isUser = user;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPackagName() {
            return packagName;
        }

        public void setPackagName(String packagName) {
            this.packagName = packagName;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        /**
         * @param name        名称
         * @param icon        图标
         * @param packagName  包名
         * @param versionName 版本号
         * @param versionCode 版本Code
         * @param isSD        是否安装在SD卡
         * @param isUser      是否是用户程序
         */
        public AppInfo(String name, Drawable icon, String packagName,
                       String versionName, int versionCode, boolean isSD, boolean isUser) {
            this.setName(name);
            this.setIcon(icon);
            this.setPackagName(packagName);
            this.setVersionName(versionName);
            this.setVersionCode(versionCode);
            this.setSD(isSD);
            this.setUser(isUser);
        }

        /*@Override
        public String toString() {
            return getName() + "\n"
                    + getIcon() + "\n"
                    + getPackagName() + "\n"
                    + getVersionName() + "\n"
                    + getVersionCode() + "\n"
                    + isSD() + "\n"
                    + isUser() + "\n";
        }*/
    }

    /**
     * 获取当前App信息
     * AppInfo（名称，图标，包名，版本号，版本Code，是否安装在SD卡，是否是用户程序）
     */
    public static AppInfo getAppInfo(Context context) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(context.getApplicationContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return pi != null ? getBean(pm, pi) : null;
    }

    /**
     * 得到AppInfo的Bean
     */
    private static AppInfo getBean(PackageManager pm, PackageInfo pi) {
        ApplicationInfo ai = pi.applicationInfo;
        String name = ai.loadLabel(pm).toString();
        Drawable icon = ai.loadIcon(pm);
        String packageName = pi.packageName;
        String versionName = pi.versionName;
        int versionCode = pi.versionCode;
        boolean isSD = (ApplicationInfo.FLAG_SYSTEM & ai.flags) != ApplicationInfo.FLAG_SYSTEM;
        boolean isUser = (ApplicationInfo.FLAG_SYSTEM & ai.flags) != ApplicationInfo.FLAG_SYSTEM;
        return new AppInfo(name, icon, packageName, versionName, versionCode, isSD, isUser);
    }

    /**
     * 得到AppInfo的Bean
     */
    private static InstallAppDTO getXLAppBean(PackageManager pm, PackageInfo pi, String deveiceId) {

        PackageInfo packageInfo = null;
        try {
            packageInfo = pm.getPackageInfo(pi.packageName, PackageManager.GET_PERMISSIONS);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        //获取每个包的权限
        String[] requestedPermissions = packageInfo.requestedPermissions;


        ApplicationInfo ai = pi.applicationInfo;
        String name = ai.loadLabel(pm).toString();
        Drawable icon = ai.loadIcon(pm);
        String packageName = pi.packageName;
        String versionName = pi.versionName;
        long appInstallDate = pi.firstInstallTime;
        String[] perms = requestedPermissions;
        InstallAppDTO installAppDTO = new InstallAppDTO();
        installAppDTO.setAppName(name);
        installAppDTO.setAppPackage(packageName);
        installAppDTO.setAppVersion(versionName + "");
        installAppDTO.setAppInstallDate(new Date(appInstallDate));
        installAppDTO.setDeviceId(deveiceId);
        // installAppDTO.setAppIcon(StringUtils.drawableToByte(icon));
        // installAppDTO.setAppPrivilege(GsonUtils.GsonString(new ArrayList<>()));

        return installAppDTO;
    }


    /**
     * 获取所有已安装App信息
     * AppInfo（名称，图标，包名，版本号，版本Code，是否安装在SD卡，是否是用户程序）
     * 依赖上面的getBean方法
     */
    public static List<AppInfo> getAllAppsInfo(Context context) {
        List<AppInfo> list = new ArrayList<>();
        PackageManager pm = context.getPackageManager();
        // 获取系统中安装的所有软件信息
        List<PackageInfo> installedPackages = pm.getInstalledPackages(0);
        for (PackageInfo pi : installedPackages) {
            if (pi != null) {
                list.add(getBean(pm, pi));
            }
        }
        return list;
    }

    /**
     * 获取所有已安装App信息,不包括系统app
     * AppInfo（名称，图标，包名，版本号，版本Code，是否安装在SD卡，是否是用户程序）
     * 依赖上面的getBean方法
     */
    public static List<InstallAppDTO> getXLUserAppsInfo(Context context) {
        List<InstallAppDTO> list = new ArrayList<>();
        PackageManager pm = context.getPackageManager();


        String deveiceId = PreferencesUtils.getString(
                Constants.DID_PREFERENCE,
                BaseApplication.getInstance().getApplicationContext(),
                Constants.SP_KEY_DEVICE_ID, Constants.DEFAULT_DID
        );


        // 获取系统中安装的所有软件信息
        List<PackageInfo> installedPackages = pm.getInstalledPackages(0);
        for (PackageInfo pi : installedPackages) {

            // 判断系统/非系统应用
            if ((pi.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0)    // 非系统应用
            {
                list.add(getXLAppBean(pm, pi, deveiceId));
            }

        }
        return list;
    }


    /**
     * 打开指定包名的App
     */

    public static boolean openAppByPackageName(Context context, String packageName) {
        if (!TextUtils.isEmpty(packageName)) {
            PackageManager pm = context.getPackageManager();
            Intent launchIntentForPackage = pm.getLaunchIntentForPackage(packageName);
            if (launchIntentForPackage != null) {
                context.startActivity(launchIntentForPackage);
                return true;
            }
        }
        return false;
    }


    /**
     * 打开指定包名的App应用信息界面
     */
    public static boolean openAppInfo(Context context, String packageName) {
        if (!TextUtils.isEmpty(packageName)) {
            Intent intent = new Intent();
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.parse("package:" + packageName));
            context.startActivity(intent);
            return true;
        }
        return false;
    }

    /**
     * 可用来做App信息分享
     */
    public static void shareAppInfo(Context context, String info) {
        if (!TextUtils.isEmpty(info)) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, info);
            context.startActivity(intent);
        }
    }

    /**
     * 判断当前App处于前台还是后台
     * 需添加<uses-permission android:name="android.permission.GET_TASKS"/>
     * 并且必须是系统应用该方法才有效
     */
    public static boolean isAppBackground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        @SuppressWarnings("deprecation")
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取服务是否开启
     *
     * @param className 完整包名的服务类名
     */
    public static boolean isRunningService(String className, Context context) {
        // 进程的管理者,活动的管理者
        ActivityManager activityManager = (ActivityManager)
                context.getSystemService(Context.ACTIVITY_SERVICE);
        // 获取正在运行的服务，最多获取1000个
        List<ActivityManager.RunningServiceInfo> runningServices = activityManager.getRunningServices(1000);
        // 遍历集合
        for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
            ComponentName service = runningServiceInfo.service;
            Log.i("StepService", service.getClassName());
            if (className.equals(service.getClassName())) {
                return true;
            }
        }
        return false;
    }


    /**
     * 判断登录页是否在栈顶
     */
    public static boolean isLoginActivityRunning(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        @SuppressWarnings("deprecation")
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
//            if (topActivity.getClassName().equals(LoginActivity.class.getName())) {
//                return true;
//            }
        }
        return false;
    }

    /**
     * 获取manifest文件meta值
     *
     * @param context
     * @param keyName
     * @param defValue
     * @return
     */
    public static String getMetaValue(Context context, String keyName, String defValue) {
        Object value = null;
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context
                    .getPackageName(), PackageManager.GET_META_DATA);

            value = applicationInfo.metaData.get(keyName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (value != null) {
            return value.toString();
        } else {
            return defValue;
        }

    }

    public static String getCacheDirPath(Context context) {
        return context.getCacheDir().getAbsolutePath();
    }

    /**
     * 获取当前进程名称
     *
     * @param context
     * @return
     */
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess
                : mActivityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    /**
     * 开启计步服务
     */
    public static void setupService(Context context) {
        if (context == null)return;
//        Intent intent = new Intent(context, StepService.class);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            context.startForegroundService(intent);
//            return;
//        }
//        context.startService(intent);
    }


    /**
     * 重新启动当前app
     *
     * @param context
     */
    public static void restartCurrentApp(Context context) {
        Intent i = context.getPackageManager()
                .getLaunchIntentForPackage(context.getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(i);
    }


    public static boolean isInServiceProcess(Context context, Class<? extends Service> serviceClass) {
        PackageManager packageManager = context.getPackageManager();

        PackageInfo packageInfo;
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), GET_SERVICES);
        } catch (Exception var14) {
            LogUtils.d(var14 + "Could not get package info for %s", new Object[]{context.getPackageName()});
            return false;
        }

        String mainProcess = packageInfo.applicationInfo.processName;
        ComponentName component = new ComponentName(context, serviceClass);

        ServiceInfo serviceInfo;
        try {
            serviceInfo = packageManager.getServiceInfo(component, 0);
        } catch (PackageManager.NameNotFoundException var13) {
            return false;
        }

        if (serviceInfo.processName.equals(mainProcess)) {
           LogUtils.d("Did not expect service %s to run in main process %s" + new Object[]{serviceClass, mainProcess});
            return false;
        } else {
            int myPid = Process.myPid();
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            ActivityManager.RunningAppProcessInfo myProcess = null;
            List runningProcesses = activityManager.getRunningAppProcesses();
            if (runningProcesses != null) {
                Iterator var11 = runningProcesses.iterator();

                while (var11.hasNext()) {
                    ActivityManager.RunningAppProcessInfo process = (ActivityManager.RunningAppProcessInfo) var11.next();
                    if (process.pid == myPid) {
                        myProcess = process;
                        break;
                    }
                }
            }

            if (myProcess == null) {
                LogUtils.d("Could not find running process for %d" + new Object[]{Integer.valueOf(myPid)});
                return false;
            } else {
                return myProcess.processName.equals(serviceInfo.processName);
            }
        }
    }

}
