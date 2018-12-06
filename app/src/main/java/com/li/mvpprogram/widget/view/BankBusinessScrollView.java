package com.li.mvpprogram.widget.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ScrollView;
import com.github.mikephil.charting.charts.LineChart;

import java.util.LinkedList;
import java.util.List;

/**
 * 兼容图表
 *
 * @author pengyang
 * @version v 1.0.0 2016/11/3 15:58 XLXZ Exp $
 * @email pengyang@xianglin.cn
 */


public class BankBusinessScrollView extends ScrollView {

    private int downX;
    private int downY;
    private int mTouchSlop;
    private List<LineChart> mViews = new LinkedList<LineChart>();

    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean top) {
        isTop = top;
    }

    private boolean isTop = false;//是不是滑动到了最低端 ；使用这个方法，解决了上拉加载的问题
    private OnScrollToBottomListener onScrollToBottom;

    public BankBusinessScrollView(Context context) {
        super(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public BankBusinessScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public BankBusinessScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            getAlLViews(mViews, this);
        }
    }

    private void getAlLViews(List<LineChart> mViews, ViewGroup parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            if (child instanceof LineChart) {
                mViews.add((LineChart) child);
            } else if (child instanceof ViewGroup) {
                getAlLViews(mViews, (ViewGroup) child);
            }
        }
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX,
                                  boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        if (scrollY != 0 && null != onScrollToBottom && isTop()) {
            onScrollToBottom.onScrollBottomListener(clampedY);
        }
    }

    public void setOnScrollToBottomLintener(OnScrollToBottomListener listener) {
        onScrollToBottom = listener;
    }

    public interface OnScrollToBottomListener {
        public void onScrollBottomListener(boolean isBottom);
    }


    private LineChart getTouchView(List<LineChart> lineCharts, MotionEvent ev) {
        if (lineCharts == null || lineCharts.size() == 0) {
            return null;
        }
        Rect mRect = new Rect();
        for (LineChart v : lineCharts) {
            v.getHitRect(mRect);
            if (!mRect.contains((int) ev.getX(), (int) ev.getY())) {
                return v;
            }
        }
        return null;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {

        LineChart lineChart = getTouchView(mViews, e);

        if (lineChart != null) {
            return false;
        }

        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                setTop(false);
                downX = (int) e.getRawX();
                downY = (int) e.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) e.getRawY();
                if (downY - moveY > 0) {
                    setTop(true);
                } else {
                    setTop(false);
                }
                if (Math.abs(moveY - downY) > mTouchSlop) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(e);
    }
}
