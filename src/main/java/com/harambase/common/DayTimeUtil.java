package com.harambase.common;

import java.util.Calendar;
import java.util.Date;

public class DayTimeUtil {

    public static String[] getWeek(){
       return new String[]{"周一","周二","周三","周四","周五","周六","周日"};
    }

    public static String[] getDay24Hours(){
        String[] hour = new String[25];
        for(int i = 1; i<=24; i++){
//            if(i < 10)
//                hour[i] = "0" + i;
//            else
                hour[i] = String.valueOf(i);
        }
        return hour;
    }

    /**
     *
     * @return 返回String类型时间数组{YYYY,MM,DD,HH,MM,SS}
     */

    public static String[] getCurTimeArray(){

        String date = DateUtil.DateToStr(new Date());

        String[] day  = date.split(" ")[0].split("-");
        String[] time = date.split(" ")[1].split(":");

        String[] timeArray = new String[6];
        for(int i = 0; i <3; i++)
            timeArray[i] = day[i];
        for(int i = 3; i <6; i++)
            timeArray[i] = time[i-3];

        return timeArray;
    }

    public static String[] getBack24Hours(int curHour){
        String[] hour = new String[25];
        int index = 0;
        for(int i = curHour; i>=0; i--){
            if(i < 10)
                hour[index] ="0" + i + "时";
            else
                hour[index] = i + "时";
            index++;
        }
        for(int i = 23; i>=curHour; i--){
            if(i < 10)
                hour[index] ="0" + i + "时";
            else
                hour[index] = i + "时";
            index++;
        }
        return hour;
    }

    public static void main(String[] args){
        for(int i = 0; i<6; i++)
            System.out.println(getCurTimeArray()[i]);
    }

    public static String[] get30DayMonth(){
        String[] month = new String[31];
        for(int i = 1; i <= 30; i++)
            month[i] = String.valueOf(i);

        return month;
    }

    public static String[] get31DayMonth(){
        String[] month = new String[32];
        for(int i = 1; i <= 31; i++)
            month[i] = String.valueOf(i);

        return month;
    }

    public static String[] get28DayMonth(){
        String[] month = new String[29];
        for(int i = 1; i <= 28; i++)
            month[i] = String.valueOf(i);

        return month;
    }

    public static String[] get29DayMonth(){
        String[] month = new String[30];
        for(int i = 1; i <= 29; i++)
            month[i] = String.valueOf(i);

        return month;
    }

    public static String[] getBack7Days() {
        String[] week = new String[7];
        for(int i = 0; i <7; i++){
            String date = DateUtil.getDateFromNow(Calendar.DATE,-6+i);
            week[i] = date.split(" ")[0];
        }
        return week;
    }

    public static String[] getBack30DayMonth() {
        String[] month = new String[30];
        for(int i = 0; i <30; i++){
            String date = DateUtil.getDateFromNow(Calendar.DATE,-29+i);
            month[i] = date.split(" ")[0];
        }
        return month;
    }

    public static String[] getBack14Days() {
        String[] back14Days = new String[14];
        for(int i = 0; i < 14; i++){
            String date = DateUtil.getDateFromNow(Calendar.DATE,-13+i);
            back14Days[i] = date.split(" ")[0];
        }
        return back14Days;
    }

    public static String[] getWeekDays() {
        String[] week = new String[7];
        for (int i = 0; i < 7; i++) {
            String date = DateUtil.getDateFromNow(Calendar.DATE, -i);
            week[6 - i] = date.split(" ")[0];
        }
        return week;
    }

    public static String[] getMonthDay() {
        String[] month = new String[30];
        for (int i = 0; i < 30; i++) {
            String date = DateUtil.getDateFromNow(Calendar.DATE, -i);
            month[29 - i] = date.split(" ")[0];
        }
        return month;
    }

    public static Long[] getDayHours(int curHour) {
        Long[] hour = new Long[24];
        for (int i = 0; i < 24; i++) {
            hour[23 - i] = new Date().getTime() - 1000 * 60 * 60 * i;
        }
        return hour;
    }
}
