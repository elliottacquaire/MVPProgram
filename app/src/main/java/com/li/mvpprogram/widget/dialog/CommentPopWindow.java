/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.li.mvpprogram.widget.dialog;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.li.mvpprogram.R;

public class CommentPopWindow extends PopupWindow {

    public int COPY = 1;
    public int DELETE = 2;

    @SuppressLint("InflateParams")
    public CommentPopWindow(final Activity context, boolean isMine, PopOnClickListener popOnClickListener) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popupwindow_comment, null);

        // 设置CommentPopWindow的View
        this.setContentView(view);
        // 设置CommentPopWindow弹出窗体的宽
        this.setWidth(LayoutParams.WRAP_CONTENT);
        // 设置CommentPopWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        // 设置CommentPopWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);

        // 设置CommentPopWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationPreview);

        TextView tv_pop_copy = (TextView) view.findViewById(R.id.tv_pop_copy);
        TextView tv_pop_delete = (TextView) view.findViewById(R.id.tv_pop_delete);

        if (!isMine) {// 不是我的，隐藏删除
            tv_pop_delete.setVisibility(View.GONE);
        }
        ;
//        tv_pop_copy.setOnClickListener(v -> {
//            popOnClickListener.callBack(COPY);
//            CommentPopWindow.this.dismiss();
//        });
//        tv_pop_delete.setOnClickListener(v -> {
//            popOnClickListener.callBack(DELETE);
//            CommentPopWindow.this.dismiss();
//        });
    }

    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAsDropDown(parent, 0, 0);
        } else {
            this.dismiss();
        }
    }

    public interface PopOnClickListener {
        void callBack(int type);
    }
}
