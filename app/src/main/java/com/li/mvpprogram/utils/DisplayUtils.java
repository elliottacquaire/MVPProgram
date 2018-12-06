package com.li.mvpprogram.utils;

import android.content.Context;

/**
 * 【类功能说明】
 * dp与px转换
 * File: DisplayUtils.java
 * Author: longfeng
 * Vesion: 3.2.0
 * Create: 2018/7/16
 * Changes (from 2018/7/16)
 * -------------------------------------------------------
 * 2018/7/16:创建DisplayUtils.java(longfeng)
 * -------------------------------------------------------
 */
public class DisplayUtils {
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
