/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.li.mvpprogram.utils;

import android.content.Context;


/**
 * @author songdiyuan
 * @version $Id: NativeEncrypt.java, v 1.0.0 2016年8月8日 下午2:39:04 xl Exp
 *          $
 */
public class NativeEncrypt {

    private static NativeEncrypt instance = null;

    private NativeEncrypt() {
    }

    public static NativeEncrypt nativeEncryptInstance(Context context) {
        if (instance == null) {
            // 初始化库
            initEncryptLib(context);
            instance = new NativeEncrypt();
        }
        return instance;
    }

    // 初始化库
    private native static void initEncryptLib(Context context);

    // 传输加密
    // public native String getPublicKEY();
    // 文件加密
    public native String getAesKEY();

    // 手势密码写入即替换
    public native String gestureEncryptEnter(String localFilePath, String userID, String password);

    // 手势密码检查
    public native String gestureEncryptCheck(String localFilePath, String userID, String password);

    // 手势密码检查
    public native String gestureEncryptUserExist(String localFilePath, String userID);

    // 手势密码删除
    public native String gestureEncryptUserDelete(String localFilePath, String userID);

    // 通过key获取url
    public native String getUrlByKey(String key);

    // 通过key获取Rsa公钥
    public native String getRsaByKey(String key);

    static {
        System.loadLibrary("encrypt");
    }
}
