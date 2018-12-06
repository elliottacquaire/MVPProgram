package com.li.mvpprogram.widget.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.li.mvpprogram.utils.LogUtils;

/**
 * 捕获应为recycle导致trying to use a recycled bitmap异常
 *
 * @author lary.huang
 * @version v 1.4.8 2017/12/5 XLXZ Exp $
 * @email huangyang@xianglin.cn
 */
@SuppressLint("AppCompatCustomView")
public class MyImageView extends ImageView {
    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        try {
            super.onDraw(canvas);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
    }
}
