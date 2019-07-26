package com.linkpets.util;

import org.apache.commons.lang.StringUtils;

import java.util.Calendar;
import java.util.Date;

public class UserAnalyseUtil {


    // 普通方法
    public static String getStarSignName(String birthday) {
        if (StringUtils.isEmpty(birthday)) {
            return "";
        }
        Date date = DateUtils.getFormatDate(birthday);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int m = cal.get(Calendar.MONTH);
        int d = cal.get(Calendar.DAY_OF_MONTH);

        for (StarSignEnum c : StarSignEnum.values()) {
            String dateStart = c.getDateStart();
            String dateEnd = c.getDateEnd();
            int mStart = Integer.parseInt(dateStart.split("-")[0]) - 1;
            int dStart = Integer.parseInt(dateStart.split("-")[1]) - 1;
            int mEnd = Integer.parseInt(dateEnd.split("-")[0]) - 1;
            int dEnd = Integer.parseInt(dateEnd.split("-")[1]) - 1;
            if ((mStart == m && dStart <= d) || (m == mEnd && d <= dEnd)) {
                return c.getName();
            }
        }
        return null;
    }

    public static String getAgeFrom(String birthday) {
        if (StringUtils.isEmpty(birthday)) {
            return "";
        }
        String ageFrom = birthday.substring(2, 3);
        switch (ageFrom) {
            case "6":
                ageFrom = "60后";
                break;
            case "7":
                ageFrom = "70后";
                break;
            case "8":
                ageFrom = "80后";
                break;
            case "9":
                ageFrom = "90后";
                break;
            case "0":
                ageFrom = "00后";
                break;
            case "1":
                ageFrom = "10后";
                break;
            default:
                break;
        }
        return ageFrom;
    }

    public static String getLastLoginTime(Date lastLoginDate) {
        if (lastLoginDate == null) {
            return "";
        }

        long leftTime = System.currentTimeMillis() - lastLoginDate.getTime();
        if (leftTime / 1000 / 60 < 60) {
            return leftTime / 1000 / 60 == 0 ? "刚刚来过" : leftTime / 1000 / 60 + "分钟前来过";
        } else if (leftTime / 1000 / 60 / 60 < 24) {
            return leftTime / 1000 / 60 / 60 + "小时前来过";
        } else if (leftTime / 1000 / 60 / 60 / 24 < 30) {
            return leftTime / 1000 / 60 / 60 / 24 + "天前来过";
        } else {
            return leftTime / 1000 / 60 / 60 / 24 / 30 + "月前来过";
        }
    }


    public static void main(String[] args) {

    }

}
