package com.juzipi.commonutil.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;

/**
 * @Author juzipi
 * @Date 2021/6/5 10:07
 * @Info
 */
public class DateUtils {


    /**
     * 将Date日期转为DateTime
     * @param date
     * @param timeString
     * @return
     */
    public static DateTime getDatetime(Date date, String timeString){
        String dateTimeString = new DateTime(date).toString("yyyy-MM-dd") + "" + timeString;
        DateTime dateTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm").parseDateTime(dateTimeString);
        return dateTime;
    }




}
