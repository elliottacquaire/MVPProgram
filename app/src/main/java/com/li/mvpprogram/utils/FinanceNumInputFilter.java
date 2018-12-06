package com.li.mvpprogram.utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 金额输入工具类
 */
public class FinanceNumInputFilter implements InputFilter {
    Pattern mPattern;
    //输入的最大金额
    private double MAX_VALUE;
    //小数点后的位数
    private int POINTER_LENGTH;
    //总长度
    private int maxLenth;

    private static final String POINTER = ".";

    private static final String ZERO = "0";


    public FinanceNumInputFilter(double maxValue, int pointerLength) {
        mPattern = Pattern.compile("([0-9]|\\.)*");
        this.MAX_VALUE = maxValue;
        this.POINTER_LENGTH = pointerLength;
        setMaxLenth();

    }

    private void setMaxLenth() {
        DecimalFormat format = new DecimalFormat("#.00");
        String sMoney = format.format(MAX_VALUE);
        maxLenth = sMoney.length();
    }

    /**
     * @param source 新输入的字符串
     * @param start  source起始下标，一般为0
     * @param end    source终点下标，一般为source长度-1
     * @param dest   输入之前文本框内容
     * @param dstart source在dest起始坐标
     * @param dend   source在dest的终点坐标
     * @return 输入内容
     */
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        String sourceText = source.toString();//输入内容
        String destText = dest.toString();//原内容


        if (maxLenth != 0 && destText.length() >= maxLenth) {
            return "";
        }

//验证删除等按键
        if (TextUtils.isEmpty(sourceText)) {
            if (dstart == 0 && destText.indexOf(POINTER) == 1) {//保证小数点不在第一个位置
                return "0";
            }
            return "";
        }
        if (!TextUtils.isEmpty(sourceText) && sourceText.length() > 1) {//防止粘贴
            return "";
        }
        Matcher matcher = mPattern.matcher(source);
//已经输入小数点的情况下，只能输入数字
        if (destText.contains(POINTER)) {
            if (!matcher.matches()) {
                return "";
            } else {
                if (POINTER.equals(source)) { //只能输入一个小数点
                    return "";
                }
            }
//验证小数点精度，保证小数点后只能输入两位
            int index = destText.indexOf(POINTER);
            int length = destText.trim().length() - index;
            if (length > POINTER_LENGTH && dstart > index) {
                return "";
            }
        } else {
//没有输入小数点的情况下，只能输入小数点和数字，但首位不能输入小数点和0
            if (!matcher.matches()) {
                return "";
            } else {
                if ((POINTER.equals(sourceText)) && dstart == 0) {//第一个位置输入小数点的情况
                    return "";
                } else if (sourceText.equals(POINTER) && dstart != 0 &&
                        dstart != destText.length()) {//把光标移动到中间，输入小数点
                    return "";
                }
            }
        }

        //验证输入金额的大小
        double sumText = Double.parseDouble(destText + sourceText);
        if (sumText > MAX_VALUE) {
            return dest.subSequence(dstart, dend);
        }
        return dest.subSequence(dstart, dend) + sourceText;
    }
}
