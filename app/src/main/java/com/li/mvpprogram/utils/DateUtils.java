package com.li.mvpprogram.utils;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * [获取第一天，最后一天]
 *
 * @author ex-caoyanchang
 * @version v 3.1.0 2017/7/10 18:05
 * @email ex-caoyanchang@xianglin.cn
 */

public class DateUtils {


    /**
     * 得到某年某周的第一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getFirstDayOfWeek(int year, int week) {
        week = week - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);

        Calendar cal = (Calendar) calendar.clone();
        cal.add(Calendar.DATE, week * 7);

        return getFirstDayOfWeek(cal.getTime());
    }

    /**
     * 得到某年某周的最后一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getLastDayOfWeek(int year, int week) {
        week = week - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);
        Calendar cal = (Calendar) calendar.clone();
        cal.add(Calendar.DATE, week * 7);

        return getLastDayOfWeek(cal.getTime());
    }

    /**
     * 取得当前日期所在周的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK,
                calendar.getFirstDayOfWeek()); // Sunday
        return calendar.getTime();
    }

    /**
     * 取得当前日期所在周的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK,
                calendar.getFirstDayOfWeek() + 6); // Saturday
        return calendar.getTime();
    }

    /**
     * 取得当前日期所在周的前一周最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfLastWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfWeek(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.WEEK_OF_YEAR) - 1);
    }

    /**
     * 返回指定日期的月的第一天
     *
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), 1);
        Date d = calendar.getTime();
        return d;
    }

    /**
     * 返回指定年月的月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的月的最后一天
     *
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), 1);
        calendar.roll(Calendar.DATE, -1);
        Date d = calendar.getTime();
        return d;
    }

    /**
     * 返回指定年月的月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的上个月的最后一天
     *
     * @return
     */
    public static Date getLastDayOfLastMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) - 1, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的下个月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfNextMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date d = calendar.getTime();

        return d;
    }

    /**
     * 返回指定日期的季的第一天
     *
     * @return
     */
    public static Date getFirstDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getFirstDayOfQuarter(calendar.get(Calendar.YEAR),
                getQuarterOfYear(date));
    }

    /**
     * 返回指定年季的季的第一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getFirstDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 1 - 1;
        } else if (quarter == 2) {
            month = 4 - 1;
        } else if (quarter == 3) {
            month = 7 - 1;
        } else if (quarter == 4) {
            month = 10 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getFirstDayOfMonth(year, month);
    }

    /**
     * 返回指定日期的季的最后一天
     *
     * @return
     */
    public static Date getLastDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfQuarter(calendar.get(Calendar.YEAR),
                getQuarterOfYear(date));
    }

    /**
     * 返回指定年季的季的最后一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 3 - 1;
        } else if (quarter == 2) {
            month = 6 - 1;
        } else if (quarter == 3) {
            month = 9 - 1;
        } else if (quarter == 4) {
            month = 12 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getLastDayOfMonth(year, month);
    }

    /**
     * 返回指定日期的上一季的最后一天
     *
     * @return
     */
    public static Date getLastDayOfLastQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfLastQuarter(calendar.get(Calendar.YEAR),
                getQuarterOfYear(date));
    }

    /**
     * 返回指定年季的上一季的最后一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfLastQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 12 - 1;
        } else if (quarter == 2) {
            month = 3 - 1;
        } else if (quarter == 3) {
            month = 6 - 1;
        } else if (quarter == 4) {
            month = 9 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getLastDayOfMonth(year, month);
    }

    /**
     * 返回指定日期的季度
     *
     * @param date
     * @return
     */
    public static int getQuarterOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) / 3 + 1;
    }

    /**
     * 当前年份的第一天
     *
     * @return
     */
    public static Date getFirstDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                0, 1);
        Date d = calendar.getTime();
        return d;

    }


    /**
     * 当前年份的最后一天
     *
     * @return
     */
    public static Date getLastDayofYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                11, 31);
        Date d = calendar.getTime();
        return d;
    }

    /**
     * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
     * (没有年的判断)
     *
     * @param date
     * @return
     */
    public static String getSeason(Date date) {

        String season = "一";

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = "第一季度";
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = "第二季度";
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = "第三季度";
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = "第四季度";
                break;
            default:
                break;
        }
        return season;
    }


    /**
     * 是否在当前月
     *
     * @param date
     * @return
     */
    public static boolean isInNowMonth(Date date) {
        if (!isInNowYear(date)) return false;//是在当前年
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        String dt = dateFormat.format(date);
        String MM = dt.substring(4, 6);//截取系统月份

        String nowDt = dateFormat.format(new Date());
        String mm = nowDt.substring(4, 6);//截取当前月份
        if (MM.equals(mm)) {
            return true;
        }
        return false;
    }

    /**
     * 是否在当前季度，先判断是否在同一年，再判断季度
     *
     * @param date
     * @return
     */
    public static boolean isInNowQuarter(Date date) {

        if (!isInNowYear(date)) return false;//是在当前年
        String season1 = getSeason(new Date());
        String season2 = getSeason(date);
        if (season1.equals(season2)) {
            return true;
        }
        return false;
    }

    /**
     * 是否当前年
     *
     * @param date
     * @return
     */
    public static boolean isInNowYear(Date date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        String dt = dateFormat.format(date);
        String DD = dt.substring(0, 4);//截取系统月份

        String nowDt = dateFormat.format(new Date());
        String dd = nowDt.substring(0, 4);//截取当前月份
        if (DD.equals(dd)) {
            return true;
        } else {
            return false;
        }

    }


    /**
     * 判断是否是今天
     *
     * @param date
     * @return
     */
    public static boolean isToday(Date date) {
        if (date == null) {
            return false;
        }
        Date dateNow = new Date();
        if (date.getYear() == dateNow.getYear() && date.getMonth() == dateNow.getMonth() && date.getDay() == dateNow.getDay()) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否是昨天
     *
     * @param date
     * @return
     */
    public static boolean isYesterday(Date date) {
        if (date == null) {
            return false;
        }
        Date dateNow = new Date();
        Date dateYesterday = new Date(dateNow.getTime() - 24 * 60 * 60 * 1000);

        if (date.getYear() == dateYesterday.getYear() && date.getMonth() == dateYesterday.getMonth() && date.getDay() == dateYesterday.getDay()) {
            return true;
        }
        return false;
    }

    /**
     * 将输入数字转换成汉字大写
     *
     * @param n
     * @return String
     */
    public static String digitUppercase(double n) {
        String fraction[] = {"角", "分"};
        String digit[] = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        String unit[][] = {{"元", "万", "亿"}, {"", "拾", "佰", "仟"}};

        String head = n < 0 ? "负" : "";
        n = Math.abs(n);

        String s = "";
        for (int i = 0; i < fraction.length; i++) {
            s += (digit[(int) (Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(零.)+", "");
        }
        if (s.length() < 1) {
            s = "整";
        }
        int integerPart = (int) Math.floor(n);

        for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
            String p = "";
            for (int j = 0; j < unit[1].length && n > 0; j++) {
                p = digit[integerPart % 10] + unit[1][j] + p;
                integerPart = integerPart / 10;
            }
            s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
        }
        return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
    }

    /**
     * 格式化为两位小数，并且不四舍五入
     */
    public static String modifyNumber(String editMoney) {
        if (TextUtils.isEmpty(editMoney)) return "";
        BigDecimal money = new BigDecimal(editMoney);
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);
        String s = decimalFormat.format(money);
        return s;
    }

    public static String TIME_MORNING = "TIME_MORNING";
    public static String TIME_AM = "TIME_AM";
    public static String TIME_PM = "TIME_PM";
    public static String TIME_EVENING = "TIME_EVENING";
    /**
     * 判断当前手机时间区间
     * @return
     */
    public static String getDataInterval(){
        //当前时间区间，默认早上
        String str = "TIME_MORNING";
        // 当前日期
        Calendar cal = Calendar.getInstance();
        // 获取小时
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        // 获取分钟
        int minute = cal.get(Calendar.MINUTE);
        // 从0:00分开是到目前为止的分钟数
        int minuteOfDay = hour * 60 + minute;
        // 起始时间 00:00的分钟数
        final int start = 0* 60 + 0;
        // 结束时间 9:00的分钟数
        final int morning = 9 * 60;
        // 结束时间 17:00的分钟数
        final int am = 17 * 60;
        // 结束时间 20:00的分钟数
        final int pm = 20 * 60;
        // 结束时间 24:00的分钟数
        final int evening = 24 * 60;
        if (minuteOfDay >= start && minuteOfDay < morning) {
            str = TIME_MORNING;
        } else if(minuteOfDay >= morning && minuteOfDay < am) {
            str = TIME_AM;
        } else if(minuteOfDay >= am && minuteOfDay < pm) {
            str = TIME_PM;
        } else if(minuteOfDay >= pm && minuteOfDay < evening) {
            str = TIME_EVENING;
        }
        return str;
    }
}

