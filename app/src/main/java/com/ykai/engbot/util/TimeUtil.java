package com.ykai.engbot.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ykai on 17/11/25.
 */
public class TimeUtil {

    public static String getWeekOfDate(String timeStamp) {
        Date date =new Date( (Long.valueOf(timeStamp)) );
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");

        String week = dateFm.format(date);
        return week;

    }

    public static String getMonthAndDay(String timeStamp){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日");
        long lt = new Long(timeStamp);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String getMinAndSeconds(String timeStamp){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        long lt = new Long(timeStamp);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String getDuration(String begin,String end){

        Long beginMinS = Long.valueOf(begin);
        Long endMinS = Long.valueOf(end);

        Long S= (endMinS - beginMinS)/1000;
        if(S<0){
            return "";
        }

        Long min = S/60;
        Long hour = min/60;

        if (min<=0){
            return S+"秒";
        }

        if(hour<=0){
            return min+"分钟";
        }


        return hour+"小时"+min+"分钟";
    }
}
