package com.stepyen.xlearn.utils;

/**
 * date：2018/12/4
 * author：yfj
 * description：
 */
public class TimeUtil {


    /**
     * 返回当月天数
     * @param year
     * @param month
     * @return
     */
    public static int getDays(int year, int month) {
        int days;
        int FebDay = 28;
        if (isLeap(year))
            FebDay = 29;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days = 30;
                break;
            case 2:
                days = FebDay;
                break;
            default:
                days = 0;
                break;
        }
        return days;
    }


    /**
     * 是否是闰年
     * @param year
     * @return
     */
    public static boolean isLeap(int year) {
        if (((year % 100 == 0) && year % 400 == 0) || ((year % 100 != 0) && year % 4 == 0))
            return true;
        else
            return false;
    }


}
