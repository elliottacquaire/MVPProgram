/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.li.mvpprogram.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lison on 8/8/16.
 */
public class TimeUtils {

    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat("HH:mm:ss");

    private TimeUtils() {
        throw new AssertionError();
    }

    /**
     * long time to string
     *
     * @param timeInMillis
     * @param dateFormat
     * @return
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @param timeInMillis
     * @return
     */
    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /**
     * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @return
     */
    public static String getCurrentTimeInString() {
        return getTime(getCurrentTimeInLong());
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
        return getTime(getCurrentTimeInLong(), dateFormat);
    }


    /**
     * 日期逻辑
     *
     * @param date 时间
     * @return
     */
    public static String timeLogic(Date date) {

        String str = DEFAULT_DATE_FORMAT.format(date);
        String hourTime = DATE_FORMAT_DATE.format(date);
        Calendar calendar = Calendar.getInstance();
        calendar.get(Calendar.DAY_OF_MONTH);
        long now = calendar.getTimeInMillis();
        calendar.setTime(date);
        long past = calendar.getTimeInMillis();

        // 相差的秒数
        long time = (now - past) / 1000;

        StringBuffer sb = new StringBuffer();
        if (time > 0 && time < 60) { // 1分钟内
            return sb.append(time + "秒前").toString();
        } else if (time > 60 && time < 3600) {//1小时内
            return sb.append(time / 60 + "分钟前").toString();
        } else if (time >= 3600 && time < 3600 * 24) {
            return hourTime;
        } else if (time >= 3600 * 24 && time < 3600 * 48) {
            return sb.append("昨天" + hourTime).toString();
        } else {
            return str;
        }

    }

//        long current = System.currentTimeMillis();//当前时间毫秒数
//        //过期时间当天的零点
//        long zero = expiresTime / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
//        //过期时间的明天的零点
//        long twelve = zero + 24 * 60 * 60 * 1000 - 1;
//
//        long time = System.currentTimeMillis() - expiresTime;
//        //未登录用户,24小时内弹一次,零点刷新
//        if (time >= 1000 * 3600 * 24 || current >= twelve) {
//            return true;
//        } else {
//            return false;
//        }

}
