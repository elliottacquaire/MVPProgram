/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.li.mvpprogram.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.li.mvpprogram.R;

/**
 * Created by lison on 8/8/16.
 */
public class ToastUtils {

    private static Toast mToast;

    private ToastUtils() {
        throw new AssertionError();
    }

    public static void show(Context context, int resId) {
        show(context, context.getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, int duration) {
        show(context, context.getResources().getText(resId), duration);
    }

    public static void show(Context context, CharSequence text) {
        show(context, text, Toast.LENGTH_SHORT);
    }

    public static void show(Context context, CharSequence text, int duration) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        if (context == null) {
            return;
        }
        if (mToast != null) {
            mToast.setText(text);
        } else {
            //如果这个Context是Activity，而Toast是异步弹出，有可能弹出时Activity已经结束。所以正确使用方法，应该是传入ApplicationContext，避免Toast导致内存泄漏
            mToast = Toast.makeText(context.getApplicationContext(), null, duration);
            mToast.setText(text);
        }
        mToast.show();
    }

    public static void show(Context context, int resId, Object... args) {
        show(context, String.format(context.getResources().getString(resId), args), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, String format, Object... args) {
        show(context, String.format(format, args), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, int duration, Object... args) {
        show(context, String.format(context.getResources().getString(resId), args), duration);
    }

    public static void show(Context context, String format, int duration, Object... args) {
        show(context, String.format(format, args), duration);
    }

    public static void show(Context context, String titles, String messages) {
        if (context == null)return;
         LayoutInflater inflater = LayoutInflater.from(context.getApplicationContext());
         View view = inflater.inflate(R.layout.toast_style, null);
         TextView title = view.findViewById(R.id.tv_title);
         title.setText(titles); //toast的标题
         TextView text = view.findViewById(R.id.tv_award);
         text.setText(messages); //toast内容
         Toast toast = new Toast(context.getApplicationContext());
         toast.setGravity(Gravity.CENTER, 12, 20);//setGravity用来设置Toast显示的位置，相当于xml中的android:gravity或android:layout_gravity
         toast.setDuration(Toast.LENGTH_LONG);//setDuration方法：设置持续时间，以毫秒为单位。该方法是设置补间动画时间长度的主要方法
         toast.setView(view); //添加视图文件
         toast.show();
    }

    public static void show(Context context, Drawable drawable, String titles, String messages) {
        if (context == null)return;
        LayoutInflater inflater = LayoutInflater.from(context.getApplicationContext());
        View view = inflater.inflate(R.layout.toast_style, null);
        ImageView imageView = view.findViewById(R.id.img_toast);
        imageView.setImageDrawable(drawable);
        TextView title = view.findViewById(R.id.tv_title);
        title.setText(titles); //toast的标题
        TextView text = view.findViewById(R.id.tv_award);
        text.setText(messages); //toast内容
        Toast toast = new Toast(context.getApplicationContext());
        toast.setGravity(Gravity.CENTER, 12, 20);//setGravity用来设置Toast显示的位置，相当于xml中的android:gravity或android:layout_gravity
        toast.setDuration(Toast.LENGTH_LONG);//setDuration方法：设置持续时间，以毫秒为单位。该方法是设置补间动画时间长度的主要方法
        toast.setView(view); //添加视图文件
        toast.show();
    }

}
