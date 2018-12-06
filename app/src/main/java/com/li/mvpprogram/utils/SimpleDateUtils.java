package com.li.mvpprogram.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple date tools
 *
 * @author lary.huang
 * @version v 1.4.8 2018/5/14 XLXZ Exp $
 * @email huangyang@xianglin.cn
 */
public class SimpleDateUtils {
    /**
     * DateFormat Map
     */
    private static Map<String, ThreadLocal<SimpleDateFormat>> dateFormatMap = new HashMap<>();

    /**
     * get SimpleDateFormat
     */
    private static SimpleDateFormat getSimpleDateFormat(final String pattern) {
        //query from map
        ThreadLocal<SimpleDateFormat> sdf = dateFormatMap.get(pattern);
        if (sdf == null) {
            synchronized (SimpleDateUtils.class) {
                sdf = dateFormatMap.get(pattern);
                if (sdf == null) {
                    sdf = new ThreadLocal<SimpleDateFormat>() {
                        @Override
                        protected SimpleDateFormat initialValue() {
                            return new SimpleDateFormat(pattern);
                        }
                    };
                }
            }
        }
        return sdf.get();
    }

    /**
     * format date to string
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        return getSimpleDateFormat(pattern).format(date);
    }

    /**
     * convert string to date
     *
     * @param sourceDate
     * @param pattern
     * @return
     */
    public static Date parse(String sourceDate, String pattern) {
        Date date = null;
        try {
            date = getSimpleDateFormat(pattern).parse(sourceDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * date Compare
     *
     * @param targetDate
     * @return
     */
    public static int compare(String targetDate, String pattern) {
        Date thisDateTime = parse(format(new Date(), pattern), pattern);
        return thisDateTime.compareTo(parse(targetDate, pattern));
    }
}
