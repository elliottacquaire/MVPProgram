/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.li.mvpprogram.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.view.ViewConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * 设备相关操作
 */

public class DeviceUtils {

    private DeviceUtils() {
        throw new UnsupportedOperationException("you can't create DeviceUtils object");
    }

    /**
     * 获取设备MAC地址
     * 需添加权限<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
     */
    public static String getMacAddress(Context context) {
        String macAddress;
        WifiManager wifi = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        macAddress = info.getMacAddress();
        if (null == macAddress) {
            return "";
        }
        macAddress = macAddress.replace(":", "");
        return macAddress;
    }

    /**
     * 获取设备厂商，如Xiaomi
     */
    public static String getManufacturer() {
        String MANUFACTURER = Build.MANUFACTURER;
        return MANUFACTURER;
    }

    /**
     * 获取设备型号，如MI2SC
     */
    public static String getModel() {
        String model = Build.MODEL;
        if (model != null) {
            model = model.trim().replaceAll("\\s*", "");
        } else {
            model = "";
        }
        return model;
    }

    /**
     * 获取设备SD卡是否可用
     */
    public static boolean isSDCardEnable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 获取设备SD卡路径
     * 一般是/storage/emulated/0/
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    public static String getImei(Context context) {
        if (context == null) return "";
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return "";
        }
        if (tm == null) return "";
        return tm.getDeviceId() != null ? tm.getDeviceId()
                : Settings.Secure.getString(context.getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

    }

    public static String getImsi(Context context) {
        if (context == null)return "";
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return "";
        }
        return tm == null ? "" : tm.getSubscriberId();
    }


    /**
     * 获取外接SD卡上context.getPackageName()下面的目录，如果不存在则创建
     *
     * @param dir 外接SD卡上context.getPackageName()下面的目录名
     * @return
     */
    public static String getExternalStoragePath(String dir, Context context) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            String path = Environment.getExternalStorageDirectory().getPath()
                    + File.separatorChar + context.getPackageName();
            File file = new File(path);
            if (!file.exists() && !file.mkdir()) {
                LogUtils.e("fail to create " + dir + " dir:" + path);
                return path;
            } else if (!file.isDirectory()) {
                LogUtils.e(dir + " dir exist,but not directory:" + path);
                return null;
            }

            path = path + File.separatorChar + dir;
            file = new File(path);
            if (!file.exists() && !file.mkdir()) {
                LogUtils.e("fail to create " + dir + " dir:" + path);
                return path;
            } else if (!file.isDirectory()) {
                LogUtils.e(dir + " dir exist,but not directory:" + path);
                return null;
            } else {
                return path;
            }
        }
        return null;
    }


    //-----小米系统----
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";

    //-----华为系统-----
    private static final String KEY_HW_API_LEVEL = "ro.build.hw_emui_api_level";
    private static final String KEY_HW_NOTIFICATION = "ro.huawei.mw.notification";
    private static final String KEY_HW__CACHE_SIZE = "o.hwui.layer_cache_size";


    public static boolean isMIUI() {

        // String s= getMIUICode();

        try {
            final BuildProperties prop = BuildProperties.newInstance();
            return prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null
                    || prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null
                    || prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null;
        } catch (final IOException e) {
            return false;
        }
    }


    public static String getMIUICode() {
        try {
            final BuildProperties prop = BuildProperties.newInstance();
            return prop.getProperty(KEY_MIUI_VERSION_NAME, null);

        } catch (final IOException e) {
            return null;
        }
    }

    public static boolean isHW_EM_UI() {

        return false;

   /*     try {
            final BuildProperties prop = BuildProperties.newInstance();
            return prop.getProperty(KEY_HW_API_LEVEL, null) != null
                    || prop.getProperty(KEY_HW_NOTIFICATION, null) != null
                    || prop.getProperty(KEY_HW__CACHE_SIZE, null) != null;
        } catch (final IOException e) {
            return false;
        }*/
    }

    /**
     * 获取判断滑动的最小滑动距离
     * @param context
     * @return
     */
    public static int getMinTouchSlop(Context context){
        return ViewConfiguration.get(context).getScaledTouchSlop();
    }



}
