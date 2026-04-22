package org.csu.petstore.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil {

    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 格式化日期
     * @param date 日期对象
     * @return 格式化后的字符串
     */
    public static String format(Date date) {
        return format(date, DEFAULT_PATTERN);
    }

    /**
     * 格式化日期（自定义格式）
     * @param date 日期对象
     * @param pattern 格式
     * @return 格式化后的字符串
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 获取当前时间戳
     * @return 时间戳
     */
    public static Long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前日期
     * @return 当前日期
     */
    public static Date getCurrentDate() {
        return new Date();
    }
}
