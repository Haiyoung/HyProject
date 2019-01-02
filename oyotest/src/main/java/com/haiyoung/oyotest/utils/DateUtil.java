package com.haiyoung.oyotest.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Slf4j
public class DateUtil {

    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String YYYYMMDD_HHMM = "yyyy年MM月dd日 HH:mm";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间格式化成字符串
     *
     * @param date    Date
     * @param pattern StrUtils.DATE_TIME_PATTERN || StrUtils.DATE_PATTERN， 如果为空，则为yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static String dateFormat(Date date, String pattern) {
        if (StringUtils.isBlank(pattern)) {
            pattern = DateUtil.DATE_PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 将字符串转成Date
     *
     * @param strTime 数字字符串
     * @param pattern 类型
     * @return 日期
     * @throws ParseException 转换异常
     */
    public static Date stringLongToDate(String strTime, String pattern) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            return new Date(Long.valueOf(strTime));
        } catch (Exception e) {
            return formatter.parse(strTime);
        }
    }
}
