/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */
package com.li.mvpprogram.widget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

/**
 * pengyang 2016-11-1 新增小数位和千分位分隔符
 *
 * @author https://github.com/a-voyager/ScrollNumber
 */
public class ScrollNumber extends View {

    private int mDeltaNum;
    private int mCurNum;
    private int mNextNum;
    private int mTargetNum;
    private Context mContext;

    private float mOffset;
    private Paint mPaint;
    private Interpolator mInterpolator = new AccelerateDecelerateInterpolator();


    private boolean isPoint;
    private boolean isComma;
    private boolean isZero;
    private int mTextCenterX;
    private int mTextHeight;
    private Rect mTextBounds = new Rect();
    private int mTextSize = sp2px(130);
    private int mTextColor = 0xFF000000;
    private Typeface mTypeface;

    public ScrollNumber(Context context) {
        this(context, null);
    }

    public ScrollNumber(Context context, boolean isPoint, boolean isComma) {
        this(context, null);
        initView(context, isPoint, isComma);
    }

    public ScrollNumber(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollNumber(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

//        setNumber(0, 6, 1000);

    }

    public void setZero(boolean zero) {
        isZero = zero;
    }

    private void initView(Context context, boolean isPoint, boolean isComma) {

        mContext = context;

        this.isPoint = isPoint;
        this.isComma = isComma;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);

        if (mTypeface != null) {
            mPaint.setTypeface(mTypeface);
        } else {
            Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
            mPaint.setTypeface(font);
        }
        ;

        measureTextHeight();

    }


    public void setNumber(final int from, final int to, long delay) {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                setFromNumber(from);
                setTargetNumber(to);
                mDeltaNum = to - from;
            }
        }, delay);
    }

    public void setTextSize(int textSize) {
        this.mTextSize = sp2px(textSize);
        mPaint.setTextSize(mTextSize);
        measureTextHeight();
        requestLayout();
        invalidate();
    }


    public void setTextFont(String fileName) {
        if (TextUtils.isEmpty(fileName))
            throw new IllegalArgumentException("please check file name end with '.ttf' or '.otf'");
        mTypeface = Typeface.createFromAsset(mContext.getAssets(), fileName);
        if (mTypeface == null) throw new RuntimeException("please check your font!");
        mPaint.setTypeface(mTypeface);
        requestLayout();
        invalidate();
    }

    public void setTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
        mPaint.setColor(mTextColor);
        invalidate();
    }

    public void setInterpolator(Interpolator interpolator) {
        mInterpolator = interpolator;
    }

    private void measureTextHeight() {
        mPaint.getTextBounds(mCurNum + "", 0, 1, mTextBounds);
        mTextHeight = mTextBounds.height();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width, height);

        mTextCenterX = (getMeasuredWidth() - getPaddingLeft() - getPaddingRight()) / 2;
    }

    private int measureHeight(int measureSpec) {
        int mode = MeasureSpec.getMode(measureSpec);
        int val = MeasureSpec.getSize(measureSpec);
        int result = 0;
        switch (mode) {
            case MeasureSpec.EXACTLY:
                result = val;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                mPaint.getTextBounds("0", 0, 1, mTextBounds);
                result = mTextBounds.height();
                break;
        }
        result = mode == MeasureSpec.AT_MOST ? Math.min(result, val) : result;
        return result + getPaddingTop() + getPaddingBottom() + dp2px(2);
    }

    private int measureWidth(int measureSpec) {
        int mode = MeasureSpec.getMode(measureSpec);
        int val = MeasureSpec.getSize(measureSpec);
        int result = 0;
        switch (mode) {
            case MeasureSpec.EXACTLY:
                result = val;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                mPaint.getTextBounds("0", 0, 1, mTextBounds);
                result = mTextBounds.width();

                if (isPoint || isComma) {
                    result = 0;
                }

                break;
        }
        result = mode == MeasureSpec.AT_MOST ? Math.min(result, val) : result;
        return result + getPaddingLeft() + getPaddingRight() + 5;
    }


    @Override
    protected void onDraw(Canvas canvas) {


        if (isPoint) {
            drawPoint(canvas);
            return;
        }
        if (isComma) {
            drawComma(canvas);
            return;
        }
        if (isZero) {
            drawZero(canvas);
            return;
        }

        if (mCurNum != mTargetNum) {
            postDelayed(mScrollRunnable, 0);
            if (mOffset <= -1) {
                mOffset = 0;
                calNum(mCurNum + 1);
            }
        }

        canvas.translate(0, mOffset * getMeasuredHeight());
        drawSelf(canvas);
        drawNext(canvas);
//        canvas.restore();
    }


    private void setFromNumber(int number) {
        if (number < 0 || number > 9)
            throw new RuntimeException("invalidate number , should in [0,9]");
        calNum(number);
        mOffset = 0;
        invalidate();
    }


    private void calNum(int number) {
        number = number == -1 ? 9 : number;
        number = number == 10 ? 0 : number;
        mCurNum = number;
        mNextNum = number + 1 == 10 ? 0 : number + 1;
    }

    private Runnable mScrollRunnable = new Runnable() {
        @Override
        public void run() {
            float x = (float) (1 - 1.0 * (mTargetNum - mCurNum) / mDeltaNum);
            mOffset -= 0.15f * (1 - mInterpolator.getInterpolation(x) + 0.1);
            invalidate();
        }
    };

    private void drawZero(Canvas canvas) {
        int y = getMeasuredHeight() / 2;
        canvas.drawText("0", mTextCenterX, y + mTextHeight / 2, mPaint);
    }

    private void drawComma(Canvas canvas) {
        int y = getMeasuredHeight() / 2;
        canvas.drawText(",", mTextCenterX, y + mTextHeight / 2, mPaint);
    }

    private void drawPoint(Canvas canvas) {
        int y = getMeasuredHeight() / 2;
        canvas.drawText(".", mTextCenterX, y + mTextHeight / 2, mPaint);
    }

    private void drawNext(Canvas canvas) {
        int y = getMeasuredHeight() * 3 / 2;
        canvas.drawText(mNextNum + "", mTextCenterX, y + mTextHeight / 2,
                mPaint);
    }

    private void drawSelf(Canvas canvas) {
        int y = getMeasuredHeight() / 2;
        canvas.drawText(mCurNum + "", mTextCenterX, y + mTextHeight / 2, mPaint);
    }


    public void setTargetNumber(int nextNum) {
        this.mTargetNum = nextNum;
        invalidate();
    }

    private int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }

    private int sp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                dpVal, getResources().getDisplayMetrics());
    }


}
