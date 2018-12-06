/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.li.mvpprogram.utils;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * 正则相关操作
 * Created by lison on 8/8/16.
 */
public class RegularUtils {

    private RegularUtils() {
        throw new UnsupportedOperationException("you can't create RegularUtils object");
    }

    // 验证手机号（现在手机号码水太深，来个短小粗暴点了只能）
    private static final String REGEX_MOBILE = "^[1]\\d{10}$";
    // 验证座机号,正确格式：xxx/xxxx-xxxxxxx/xxxxxxxx
    private static final String REGEX_TEL = "^0\\d{2,3}[- ]?\\d{7,8}";
    // 验证邮箱
    private static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    // 验证url
    private static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?";
    // 验证汉字
    private static final String REGEX_CHZ = "^[\\u4e00-\\u9fa5]+$";
    // 验证用户名,取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾,用户名必须是6-20位
    private static final String REGEX_USERNAME = "^[\\w\\u4e00-\\u9fa5]{6,20}(?<!_)$";
    // 验证IP地址
    private static final String REGEX_IP = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";
    // 英文字符
    private static final String REGEX_ENGLISH_CHAR = ".*[a-zA-Z._]+.*";
    //至少一个汉字
    private static final String REGEX_ONE_CHZ = "^.*[\\u4e00-\\u9fa5].*$";
    //中英文数字横线下划线逗号句号括号
    private static final String REGEX_LOAN_EDIT = "[-\\u4e00-\\u9fa5_a-zA-Z0-9，。（）]+";

    //If u want more please visit http://toutiao.com/i6231678548520731137/

    // 将平年和闰年的日期验证表达式合并，我们得到最终的验证日期格式为YYYY-MM-DD的正则表达式为：
    private static final String REGEX_DATE = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";

    /**
     * @param string 待验证文本
     * @return 是否符合手机号格式
     */
    public static boolean isMobile(String string) {
        return isMatch(REGEX_MOBILE, string);
    }

    /**
     * @param string 待验证文本
     * @return 是否符合座机号码格式
     */
    public static boolean isTel(String string) {
        return isMatch(REGEX_TEL, string);
    }

    /**
     * @param string 待验证文本
     * @return 是否符合邮箱格式
     */
    public static boolean isEmail(String string) {
        return isMatch(REGEX_EMAIL, string);
    }

    /**
     * @param string 待验证文本
     * @return 是否符合网址格式
     */
    public static boolean isURL(String string) {
        return isMatch(REGEX_URL, string);
    }

    /**
     * @param string 待验证文本
     * @return 是否符合汉字
     */
    public static boolean isChz(String string) {
        return isMatch(REGEX_CHZ, string);
    }

    /**
     * @param string 待验证文本
     * @return 是否符合至少一个汉字
     */
    public static boolean isOneChz(String string) {
        return isMatch(REGEX_ONE_CHZ, string);
    }

    /**
     * @param string 待验证文本
     * @return 是否符合中英文数字横线下划线逗号句号括号
     */
    public static boolean isLoanEdit(String string) {
        return isMatch(REGEX_LOAN_EDIT, string);
    }

    /**
     * @param string 待验证文本
     * @return 是否符合用户名
     */
    public static boolean isUsername(String string) {
        return isMatch(REGEX_USERNAME, string);
    }

    /**
     * @param regex  正则表达式字符串
     * @param string 要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    public static boolean isMatch(String regex, String string) {
        return !TextUtils.isEmpty(string) && Pattern.matches(regex, string);
    }

    /**
     * 判断是否包含英文字符a-z A-Z
     *
     * @param string
     * @return
     */
    public static boolean hasEnglishChar(String string) {
        return isMatch(REGEX_ENGLISH_CHAR, string);
    }

    /**
     * 判断是否是合法的日期，包括闰年平年
     *
     * @param string
     * @return
     */
    public static boolean isValidDate(String string) {
        return isMatch(REGEX_DATE, string);
    }

}
