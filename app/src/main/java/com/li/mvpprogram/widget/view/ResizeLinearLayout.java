package com.li.mvpprogram.widget.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 监控view大小变化
 * Created by xl on 18/08/2017.
 */

public class ResizeLinearLayout extends LinearLayout {

    public ResizeLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mListener != null) {
            mListener.OnResizeRelative(w, h, oldw, oldh);
        }

    }

    // 监听接口
    private OnResizeRelativeListener mListener;

    public interface OnResizeRelativeListener {
        void OnResizeRelative(int w, int h, int oldw, int oldh);
    }

    public void setOnResizeRelativeListener(OnResizeRelativeListener l) {
        mListener = l;
    }

}
