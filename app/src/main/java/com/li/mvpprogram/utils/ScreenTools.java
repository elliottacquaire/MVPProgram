package com.li.mvpprogram.utils;

import android.content.Context;

/**
 * [类功能说明]
 * 屏幕工具
 *
 * @author ex-heguogui
 * @version v 2.0.0 2017/2/15 16:55 XLXZ Exp $
 * @email ex-heguoguo@xianglin.cn
 */


public class ScreenTools {

    private static ScreenTools mScreenTools;
    private Context context;

    private ScreenTools(Context context) {
        this.context = context.getApplicationContext();
    }

    public static ScreenTools instance(Context context) {
        if (mScreenTools == null)
            mScreenTools = new ScreenTools(context);
        return mScreenTools;
    }

    public int dip2px(float f) {
        return (int) (0.5D + (double) (f * getDensity(context)));
    }

    public int dip2px(int i) {
        return (int) (0.5D + (double) (getDensity(context) * (float) i));
    }

    public float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public int getScreenDensityDpi() {
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    public int getScreenHeight() {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public int getScreenWidth() {
        return context.getResources().getDisplayMetrics().widthPixels;
    }


    public float getXdpi() {
        return context.getResources().getDisplayMetrics().xdpi;
    }

    public float getYdpi() {
        return context.getResources().getDisplayMetrics().ydpi;
    }

    public int px2dip(float f) {
        float f1 = getDensity(context);
        return (int) (((double) f - 0.5D) / (double) f1);
    }

    public int px2dip(int i) {
        float f = getDensity(context);
        return (int) (((double) i - 0.5D) / (double) f);
    }
}
