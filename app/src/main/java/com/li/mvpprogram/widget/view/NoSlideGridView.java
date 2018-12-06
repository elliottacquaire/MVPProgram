/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.li.mvpprogram.widget.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * [类功能说明]
 * 不能滑动的GridView
 * 通过重新dispatchTouchEvent方法来禁止滑动
 * 重写onMeasure，将计算高度的模式设置为 AT_MOST：高度自适应
 *
 * @author ex-zhangxiang
 * @version v 2.0.0 2017/2/16 10:08 XLXZ Exp $
 * @email ex-zhangxiang@xianglin.cn
 */
public class NoSlideGridView extends GridView {

    public NoSlideGridView(Context context) {
        super(context);
    }

    public NoSlideGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoSlideGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public NoSlideGridView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            return true;//禁止Gridview进行滑动
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (null == mInvalidRegionClickListener) {
            return super.onTouchEvent(ev);
        }
        int actionMasked = ev.getActionMasked();
        if (actionMasked == MotionEvent.ACTION_UP || actionMasked == MotionEvent.ACTION_DOWN) {
            final int motionPosition = pointToPosition((int) ev.getX(), (int) ev.getY());
            if (motionPosition == INVALID_POSITION) {
                if (MotionEvent.ACTION_DOWN == actionMasked) {
                    downMillisecond = System.currentTimeMillis();
                } else if (MotionEvent.ACTION_UP == actionMasked) {
                    long up = System.currentTimeMillis();
                    if (MARK >= up - downMillisecond) {
                        return mInvalidRegionClickListener.onInvalidRegionClick(actionMasked);
                    }
                }
            }
        }
        return super.onTouchEvent(ev);
    }

    private static final long MARK = 500L;
    private long downMillisecond = 0;
    private InvalidRegionClickListener mInvalidRegionClickListener;

    public void setInvalidClickListener(InvalidRegionClickListener listener) {
        this.mInvalidRegionClickListener = listener;
    }

    public interface InvalidRegionClickListener {
        /**
         * motionEvent 可使用 MotionEvent.ACTION_DOWN 或者 MotionEvent.ACTION_UP等来按需要进行判断
         *
         * @return 是否要终止事件的路由
         */
        boolean onInvalidRegionClick(int motionEvent);
    }
}
