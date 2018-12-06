/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.li.mvpprogram.utils;

import com.li.mvpprogram.BaseApplication;
import com.orhanobut.logger.Logger;

/**
 * 打印日志工具类<br/>
 * Logger.d("hello");<br/>
 * Logger.e("hello");<br/>
 * Logger.w("hello");<br/>
 * Logger.v("hello");<br/>
 * Logger.wtf("hello");<br/>
 * Logger.json(JSON_CONTENT);<br/>
 * Logger.xml(XML_CONTENT);<br/>
 * Logger.log(DEBUG, "tag", "message", throwable);<br/>
 * Created by lison on 8/10/16.
 */
public final class LogUtils {

    /**
     * 设置TAG
     * 仅在Application中设置一次
     *
     * @param tag
     */
    public static void init(String tag) {
        Logger.init(tag);
    }

    public static void log(int priority, String tag, String message, Throwable throwable) {
        if (BaseApplication.getInstance().print) {
            Logger.log(priority, tag, message, throwable);
        }
    }

    public static void d(String message, Object... args) {
        if (BaseApplication.getInstance().print) {
            Logger.d(message, args);
        }
    }

    public static void d(Object object) {
        if (BaseApplication.getInstance().print) {
            Logger.d(object);
        }
    }

    public static void e(String message, Object... args) {
        if (BaseApplication.getInstance().print) {
            Logger.e(message, args);
        }
    }

    public static void e(Throwable throwable, String message, Object... args) {
        if (BaseApplication.getInstance().print) {
            Logger.e(throwable, message, args);
        }
    }

    public static void i(String message, Object... args) {
        if (BaseApplication.getInstance().print) {
            Logger.i(message, args);
        }
    }

    public static void v(String message, Object... args) {
        if (BaseApplication.getInstance().print) {
            Logger.v(message, args);
        }
    }

    public static void w(String message, Object... args) {
        if (BaseApplication.getInstance().print) {
            Logger.w(message, args);
        }
    }

    public static void wtf(String message, Object... args) {
        if (BaseApplication.getInstance().print) {
            Logger.wtf(message, args);
        }
    }

    public static void json(String message) {
        if (BaseApplication.getInstance().print) {
            Logger.json(message);
        }
    }

    public static void xml(String message) {
        if (BaseApplication.getInstance().print) {
            Logger.xml(message);
        }
    }
}
