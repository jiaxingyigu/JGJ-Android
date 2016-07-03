package com.yigu.jgj.commom.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by brain on 2016/7/3.
 */
public class DateUtil {
    private static final String YMDHM = "yyyyMMddHHmmss";
    private static DateUtil dateUtil;

    public static DateUtil getInstance() {
        if (dateUtil == null) {
            dateUtil = new DateUtil();
        }
        return dateUtil;
    }

    public long date2String(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(YMDHM);
        String dateStr = dateFormat.format(date);
        long longDate = 0;
        try {
            longDate = dateFormat.parse(dateStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return longDate;
    }

    /**
     * 时间转换 月，天，小时，分，秒
     *
     * @param date
     * @return
     */
    public String date2PublishTime(Date date) {
        long million = 0;
        long ONE_SECOND_IN_MILLIONSECOND = (long) 1000;
        long ONE_MINUTE_IN_MILLIONSECOND = (long) 1000 * 60;
        long ONE_HOUR_IN_MILLIONSECOND = (long) 1000 * 60 * 60;
        long ONE_DAY_IN_MILLIONSECOND = (long) 1000 * 60 * 60 * 24;
        long ONE_WEEK_IN_MILLIONSECOND = (long) 1000 * 60 * 60 * 24 * 7;
        long ONE_MONTH_IN_MILLIONSECOND = (long) 1000 * 60 * 60 * 24 * 30;
        long ONE_YEAR_IN_MILLIONSECOND = (long) 1000 * 60 * 60 * 24 * 365;
        DebugLog.i("date" + date + "newDate" + new Date());
        SimpleDateFormat dateFormat = new SimpleDateFormat(YMDHM);
        String dateStr = dateFormat.format(date);
        try {
            long millionSeco = dateFormat.parse(dateStr).getTime();
            long nowMillion = dateFormat.parse(dateFormat.format(new Date())).getTime();
            million = nowMillion - millionSeco;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DebugLog.i("million=" + million + "ONE_YEAR_IN_MILLIONSECOND" + ONE_YEAR_IN_MILLIONSECOND);
        if (million / ONE_YEAR_IN_MILLIONSECOND > 0) {
            return million / ONE_YEAR_IN_MILLIONSECOND + "年前";
        } else if (million / ONE_MONTH_IN_MILLIONSECOND > 0)
            return million / ONE_MONTH_IN_MILLIONSECOND + "月前";
        else if (million / ONE_WEEK_IN_MILLIONSECOND > 0)
            return million / ONE_WEEK_IN_MILLIONSECOND + "周前";
        else if (million / ONE_DAY_IN_MILLIONSECOND > 0)
            return million / ONE_DAY_IN_MILLIONSECOND + "天前";
        else if (million / ONE_HOUR_IN_MILLIONSECOND > 0)
            return million / ONE_HOUR_IN_MILLIONSECOND + "小时前";
        else if (million / ONE_MINUTE_IN_MILLIONSECOND > 0)
            return million / ONE_MINUTE_IN_MILLIONSECOND + "分钟前";
        else if (million / ONE_SECOND_IN_MILLIONSECOND > 0)
            return million / ONE_SECOND_IN_MILLIONSECOND + "秒前";
        else return "刚刚";
    }
}
