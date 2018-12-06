/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.li.mvpprogram.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数字验证正则表达式
 */
public class NumberValidationUtils {

    private static boolean isMatch(String regex, String orginal) {
        if (orginal == null || orginal.trim().equals("")) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher isNum = pattern.matcher(orginal);
        return isNum.matches();
    }

    /**
     * 正整数
     *
     * @param orginal
     * @return
     */
    public static boolean isPositiveInteger(String orginal) {
        return isMatch("^\\+{0,1}[1-9]\\d*", orginal);
    }

    /**
     * 负整数
     *
     * @param orginal
     * @return
     */
    public static boolean isNegativeInteger(String orginal) {
        return isMatch("^-[1-9]\\d*", orginal);
    }

    /**
     * 整数
     *
     * @param orginal
     * @return
     */
    public static boolean isWholeNumber(String orginal) {
        return isMatch("[+-]{0,1}0", orginal) || isPositiveInteger(orginal) || isNegativeInteger(orginal);
    }

    /**
     * 正小数
     *
     * @param orginal
     * @return
     */
    public static boolean isPositiveDecimal(String orginal) {
//        return isMatch("\\+{0,1}[0]\\.[1-9]*|\\+{0,1}[1-9]\\d*\\.\\d*", orginal);

        return isMatch("^((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*))$", orginal);
    }

    /**
     * 负小数
     *
     * @param orginal
     * @return
     */
    public static boolean isNegativeDecimal(String orginal) {
//        return isMatch("^-[0]\\.[1-9]*|^-[1-9]\\d*\\.\\d*", orginal);
        return isMatch("^(-((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*)))$", orginal);
    }

    /**
     * 小数
     *
     * @param orginal
     * @return
     */
    public static boolean isDecimal(String orginal) {
        return isMatch("[-+]{0,1}\\d+\\.\\d*|[-+]{0,1}\\d*\\.\\d+", orginal);
    }

    /**
     * 实数
     *
     * @param orginal
     * @return
     */
    public static boolean isRealNumber(String orginal) {
        return isWholeNumber(orginal) || isDecimal(orginal);
    }

    /**
     * 正实数
     *
     * @param orginal
     * @return
     */
    public static boolean isPositiveRealNumber(String orginal) {
        return isPositiveInteger(orginal) || isPositiveDecimal(orginal);
    }

    /**
     * 手机号码
     *
     * @param orginal
     * @return
     */
    public static boolean isPhoneNumber(String orginal) {
        return isMatch("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$", orginal);
    }

    /**
     * 判断数字小数点后是否超过2位
     *
     * @param orginal
     * @return
     */
    public static boolean isTwoNumber(String orginal) {
        return isMatch("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$", orginal);
    }
}
