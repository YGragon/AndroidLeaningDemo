package com.dongxi.rxdemo.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 时间工具类
 *      时间格式转换
 *      获取当前时间
 *      时间距离发布多久
 * Created by Administrator on 2017/8/31.
 */

public class DateUtils {
    private static final String TAG = "DateUtils";

    /**
     * 获取当前时间
     * @return
     */
    public static String getCurrentData(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    /**
     * 时间格式
     *      08月20日 星期一
     * @param data
     * @return
     */
    public static String getMonthDayWeek(Date data){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日 EE",Locale.CHINA);
        String format = simpleDateFormat.format(data);
        return format ;
    }
    /**
     * 时间格式
     *      2017年08月20日
     * @param data
     * @return
     */
    public static String getYearMonthDay(Date data){
        if (data != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
            String format = simpleDateFormat.format(new Date(0));
            return format;
        }else {
            return "data in null" ;
        }
    }

    /**
     * 将时间戳转为代表"距现在多久之前"的字符串
     * @param createdTime   时间
     * @return
     */

        public static String timeAgo(Date createdTime) {
            Log.e(TAG, "timeAgo: " );
            SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA);
            if (createdTime != null) {
                long agoTimeInMin = (new Date(System.currentTimeMillis()).getTime() - createdTime.getTime()) / 1000 / 60;
                //如果在当前时间以前一分钟内
                if (agoTimeInMin <= 1) {
                    return "刚刚";
                } else if (agoTimeInMin <= 60) {
                    //如果传入的参数时间在当前时间以前10分钟之内
                    return agoTimeInMin + "分钟前";
                } else if (agoTimeInMin <= 60 * 24) {
                    return agoTimeInMin / 60 + "小时前";
                }else if (agoTimeInMin > 60*24){
                    return agoTimeInMin / (60*24) +"天前" ;
                }else {
                    return format.format(new Date(0));  // 返回 08-03
                }
            }else {
                return "time is null";
            }
        }
}
