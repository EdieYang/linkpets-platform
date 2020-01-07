package com.linkpets.util;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    static SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD");

    static SimpleDateFormat dfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getCurrentDay() {
        return df.format(new Date());
    }

    public static String getLastWeekDay() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        return df.format(cal.getTime());
    }

    public static String getLastMonthDay() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        return df.format(cal.getTime());
    }

    public static String getLastYearDay() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        return df.format(cal.getTime());
    }

    public static Date getFormatDateTime(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date formatDate = null;
        try {
            formatDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatDate;
    }

    public static Date getFormatDate(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date formatDate = null;
        try {
            formatDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatDate;
    }


    public static String getFormatDateStr(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = dateFormat.format(date);
        return dateStr;
    }

    public static String getFormatDateStr(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        String dateStr = dateFormat.format(date);
        return dateStr;
    }


    public static boolean diffNow(String sendTime) {
        boolean diff = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date sendDate = dateFormat.parse(sendTime);
            long leftTime = System.currentTimeMillis() - sendDate.getTime();
            if (leftTime / 1000 > 10) {
                diff = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return diff;
    }


    public static String getDateBefore(Date date) {
        if (date == null) {
            return "";
        }

        long leftTime = System.currentTimeMillis() - date.getTime();
        if (leftTime / 1000 < 60) {
            return leftTime / 1000 + "秒前";
        } else if (leftTime / 1000 / 60 < 60) {
            return leftTime / 1000 / 60 + "分钟前";
        } else if (leftTime / 1000 / 60 / 60 < 24) {
            return leftTime / 1000 / 60 / 60 + "小时前";
        } else if (leftTime / 1000 / 60 / 60 / 24 < 30) {
            return leftTime / 1000 / 60 / 60 / 24 + "天前";
        } else {
            return leftTime / 1000 / 60 / 60 / 24 / 30 + "月前";
        }
    }

    // 日期推多少，num：向前为负，向后为正
    public static Date rollNOfDate(Date date, int dateType, int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        switch (dateType) {
            case 1: {// 天
                cal.add(Calendar.DAY_OF_MONTH, num);
                break;
            }
            case 2: {// 周
                cal.add(Calendar.DAY_OF_MONTH, 7 * num);
                break;
            }
            case 3: {// 月
                cal.add(Calendar.MONTH, num);
                break;
            }
            case 4: {// 季度
                cal.add(Calendar.MONTH, 3 * num);
                break;
            }
            case 5: {// 年
                cal.add(Calendar.YEAR, num);
                break;
            }
            case 6: {//小时
                cal.add(Calendar.HOUR, num);
                break;
            }
        }
        return cal.getTime();
    }

    // 日期推多少，num：向前为负，向后为正
    public static String rollNOfDate(String dateStr, int dateType, int num, String format) {

        Date date = formatStringToDate(dateStr, format);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        switch (dateType) {
            case 1: {// 天
                cal.add(Calendar.DAY_OF_MONTH, num);
                break;
            }
            case 2: {// 周
                cal.add(Calendar.DAY_OF_MONTH, 7 * num);
                break;
            }
            case 3: {// 月
                cal.add(Calendar.MONTH, num);
                break;
            }
            case 4: {// 季度
                cal.add(Calendar.MONTH, 3 * num);
                break;
            }
            case 5: {// 年
                cal.add(Calendar.YEAR, num);
                break;
            }
            case 6: {//小时
                cal.add(Calendar.HOUR, num);
                break;
            }
            case 7: {//分钟
                cal.add(Calendar.MINUTE, num);
                break;
            }
        }
        return formatDateStrToString(formatDateTime(cal.getTime()), "yyyy-MM-dd HH:mm:ss", format);
    }

    /**
     * 将Date对象的字符串格式转换成指定格式的字符串
     *
     * @param dateStr
     * @param eformat 为null则转换成默认yyyy-MM-dd HH:mm:ss
     * @return String
     */
    public static String formatDateStrToString(String dateStr, String bformat,
                                               String eformat) {

        eformat = eformat != null ? eformat : "yyyy-MM-dd HH:mm:ss";
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        Date date = formatStringToDate(dateStr, bformat);
        SimpleDateFormat dateFormat = new SimpleDateFormat(eformat);
        String result = dateFormat.format(date);
        return result;
    }

    /**
     * 根据时间字符串和字符串格式,转换成java.util.Date对象
     *
     * @param dateString
     * @param format
     * @return
     */
    public static Date formatStringToDate(String dateString, String format) {
        format = format != null ? format : "yyyy-MM-dd HH:mm:ss";
        if (dateString == null || dateString.length() == 0) {
            return null;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        try {
            date = dateFormat.parse(dateString);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }

    public static String formatDateTime(Date date) {
        return dfTime.format(date);
    }
}
