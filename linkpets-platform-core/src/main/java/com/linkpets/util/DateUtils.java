package com.linkpets.util;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

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
}
