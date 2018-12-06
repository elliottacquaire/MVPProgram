/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */
package com.li.mvpprogram.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import com.li.mvpprogram.R;
import com.li.mvpprogram.utils.AmountUtils;
import com.li.mvpprogram.utils.ArrayUtils;
import com.li.mvpprogram.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * pengyang 2016-11-1 新增小数位和千分位分隔符
 *
 * @author https://github.com/a-voyager/ScrollNumber
 */
public class MultiScrollNumber extends LinearLayout {
    private Context mContext;
    private List<Integer> mTargetNumbers = new ArrayList<>();
    private List<Integer> mPrimaryNumbers = new ArrayList<>();
    private List<ScrollNumber> mScrollNumbers = new ArrayList<>();
    private int mTextSize = 130;

    private int[] mTextColors = new int[]{R.color.white};
    private Interpolator mInterpolator = new AccelerateDecelerateInterpolator();
    private String mFontFileName;

    public MultiScrollNumber(Context context) {
        this(context, null);
    }

    public MultiScrollNumber(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiScrollNumber(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.MultiScrollNumber);
        int primaryNumber = typedArray.getInteger(R.styleable.MultiScrollNumber_primary_number, 0);
        int targetNumber = typedArray.getInteger(R.styleable.MultiScrollNumber_target_number, 0);
        int numberSize = typedArray.getInteger(R.styleable.MultiScrollNumber_number_size, 130);

        setNumber(primaryNumber, targetNumber);
        setTextSize(numberSize);

        typedArray.recycle();

        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);


    }


    /*小数*/
    public void setFloat(String val) {
        resetView();


        String temp = AmountUtils.getCommaFormat(val);


        if ("0.00".equals(temp)) {

            for (int i = 0; i < 4; i++) {

                ScrollNumber scrollNumber = null;
                if (i == 1) {
                    scrollNumber = new ScrollNumber(mContext, true, false);
                } else {
                    scrollNumber = new ScrollNumber(mContext, false, false);
                }

                scrollNumber.setZero(true);
                scrollNumber.setTextSize(mTextSize);
                scrollNumber.setTextColor(ContextCompat
                        .getColor(mContext, mTextColors[i % mTextColors.length]));
                scrollNumber.invalidate();
                addView(scrollNumber);
            }


            return;
        }


        List<Integer> ints = new ArrayList<Integer>();
        char[] temps = temp.toCharArray();

        temps = ArrayUtils.reverseArray2(temps);

        for (int i = 0; i < temps.length; i++) {
            if (temps[i] == '.' || temps[i] == ',') {
                ints.add(i);
            }
        }

        long number;

        String numbertemp = temp.replace(",", "0").replace(".", "0");

        number = StringUtils.parseLong(numbertemp);


        while (number > 0) {
            long i = number % 10;
            mTargetNumbers.add((int) i);
            number /= 10;
        }

        for (int i = mTargetNumbers.size() - 1; i >= 0; i--) {

            ScrollNumber scrollNumber = null;

            if (ints.contains(i)) {
                if (i == 2) {
                    scrollNumber = new ScrollNumber(mContext, true, false);
                } else {
                    scrollNumber = new ScrollNumber(mContext, false, true);
                }

            } else {
                scrollNumber = new ScrollNumber(mContext, false, false);

            }

            scrollNumber.setTextColor(ContextCompat
                    .getColor(mContext, mTextColors[i % mTextColors.length]));
            scrollNumber.setTextSize(mTextSize);
            scrollNumber.setInterpolator(mInterpolator);
            if (!TextUtils.isEmpty(mFontFileName))
                scrollNumber.setTextFont(mFontFileName);
            scrollNumber.setNumber(0, mTargetNumbers.get(i), i * 10);
            mScrollNumbers.add(scrollNumber);
            addView(scrollNumber);
        }
    }

    public void setNumber(long val) {
        resetView();

        long number = val;
        while (number > 0) {
            long i = number % 10;
            mTargetNumbers.add((int) i);
            number /= 10;
        }

        for (int i = mTargetNumbers.size() - 1; i >= 0; i--) {


            ScrollNumber scrollNumber = null;

            scrollNumber = new ScrollNumber(mContext, false, false);


            scrollNumber.setTextColor(ContextCompat
                    .getColor(mContext, mTextColors[i % mTextColors.length]));
            scrollNumber.setTextSize(mTextSize);
            scrollNumber.setInterpolator(mInterpolator);
            if (!TextUtils.isEmpty(mFontFileName))
                scrollNumber.setTextFont(mFontFileName);
            scrollNumber.setNumber(0, mTargetNumbers.get(i), i * 10);
            mScrollNumbers.add(scrollNumber);
            addView(scrollNumber);
        }
    }

    private void resetView() {
        mTargetNumbers.clear();
        mScrollNumbers.clear();
        removeAllViews();
    }


    public void setNumber(long from, long to) {
        if (to < from)
            throw new UnsupportedOperationException("'to' value must > 'from' value");

        resetView();
        // operate to
        long number = to, count = 0;
        while (number > 0) {
            long i = number % 10;
            mTargetNumbers.add((int) i);
            number /= 10;
            count++;
        }
        // operate from
        number = from;
        while (count > 0) {
            long i = number % 10;
            mPrimaryNumbers.add((int) i);
            number /= 10;
            count--;
        }

        for (int i = mTargetNumbers.size() - 1; i >= 0; i--) {

            ScrollNumber scrollNumber = null;
            scrollNumber = new ScrollNumber(mContext, false, false);


            scrollNumber.setTextColor(ContextCompat
                    .getColor(mContext, mTextColors[i % mTextColors.length]));
            scrollNumber.setTextSize(mTextSize);
            if (!TextUtils.isEmpty(mFontFileName))
                scrollNumber.setTextFont(mFontFileName);
            scrollNumber.setNumber(mPrimaryNumbers.get(i), mTargetNumbers.get(i), i * 10);
            mScrollNumbers.add(scrollNumber);
            addView(scrollNumber);
        }

    }

    public void setTextColors(@ColorRes int[] textColors) {
        if (textColors == null || textColors.length == 0)
            throw new IllegalArgumentException("color array couldn't be empty!");
        mTextColors = textColors;
        for (int i = mScrollNumbers.size() - 1; i >= 0; i--) {
            ScrollNumber scrollNumber = mScrollNumbers.get(i);
            scrollNumber.setTextColor(ContextCompat
                    .getColor(mContext, mTextColors[i % mTextColors.length]));
        }
    }


    public void setTextSize(int textSize) {
        if (textSize <= 0) throw new IllegalArgumentException("text size must > 0!");
        mTextSize = textSize;
        for (ScrollNumber s : mScrollNumbers) {
            s.setTextSize(textSize);
        }
    }

    public void setInterpolator(Interpolator interpolator) {
        if (interpolator == null)
            throw new IllegalArgumentException("interpolator couldn't be null");
        mInterpolator = interpolator;
        for (ScrollNumber s : mScrollNumbers) {
            s.setInterpolator(interpolator);
        }
    }

    public void setTextFont(String fileName) {
        if (TextUtils.isEmpty(fileName)) throw new IllegalArgumentException("file name is null");
        mFontFileName = fileName;
        for (ScrollNumber s : mScrollNumbers) {
            s.setTextFont(fileName);
        }
    }


}
