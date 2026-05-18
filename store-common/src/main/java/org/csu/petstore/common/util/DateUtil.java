package org.csu.petstore.common.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * 日期工具类
 * 基于 Java 8+ 的现代时间 API
 */
public class DateUtil {

    /**
     * 默认日期时间格式
     */
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * 默认日期格式
     */
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    
    /**
     * 默认时间格式
     */
    private static final String TIME_PATTERN = "HH:mm:ss";
    
    /**
     * 默认时区
     */
    private static final ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();

    private DateUtil() {
        throw new IllegalStateException("Utility class");
    }

    // ==================== LocalDateTime 格式化 ====================

    /**
     * 格式化 LocalDateTime
     *
     * @param dateTime 日期时间对象
     * @return 格式化后的字符串
     */
    public static String format(LocalDateTime dateTime) {
        return format(dateTime, DEFAULT_PATTERN);
    }

    /**
     * 格式化 LocalDateTime（自定义格式）
     *
     * @param dateTime 日期时间对象
     * @param pattern  格式模式
     * @return 格式化后的字符串
     */
    public static String format(LocalDateTime dateTime, String pattern) {
        if (dateTime == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }

    /**
     * 格式化 LocalDate
     *
     * @param date 日期对象
     * @return 格式化后的字符串
     */
    public static String format(LocalDate date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 格式化 LocalDate（自定义格式）
     *
     * @param date    日期对象
     * @param pattern 格式模式
     * @return 格式化后的字符串
     */
    public static String format(LocalDate date, String pattern) {
        if (date == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }

    /**
     * 格式化 LocalTime
     *
     * @param time 时间对象
     * @return 格式化后的字符串
     */
    public static String format(LocalTime time) {
        return format(time, TIME_PATTERN);
    }

    /**
     * 格式化 LocalTime（自定义格式）
     *
     * @param time    时间对象
     * @param pattern 格式模式
     * @return 格式化后的字符串
     */
    public static String format(LocalTime time, String pattern) {
        if (time == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return time.format(formatter);
    }

    // ==================== Date 格式化（兼容旧代码）====================

    /**
     * 格式化 Date 对象
     *
     * @param date 日期对象
     * @return 格式化后的字符串
     */
    public static String format(Date date) {
        return format(date, DEFAULT_PATTERN);
    }

    /**
     * 格式化 Date 对象（自定义格式）
     *
     * @param date    日期对象
     * @param pattern 格式模式
     * @return 格式化后的字符串
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        Instant instant = date.toInstant();
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, DEFAULT_ZONE_ID);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }

    // ==================== 解析字符串为日期 ====================

    /**
     * 解析字符串为 LocalDateTime
     *
     * @param text    日期时间字符串
     * @param pattern 格式模式
     * @return LocalDateTime 对象
     */
    public static LocalDateTime parseLocalDateTime(String text, String pattern) {
        if (StringUtil.isEmpty(text)) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(text, formatter);
    }

    /**
     * 解析字符串为 LocalDate
     *
     * @param text    日期字符串
     * @param pattern 格式模式
     * @return LocalDate 对象
     */
    public static LocalDate parseLocalDate(String text, String pattern) {
        if (StringUtil.isEmpty(text)) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(text, formatter);
    }

    // ==================== 获取当前时间 ====================

    /**
     * 获取当前 LocalDateTime
     *
     * @return 当前日期时间
     */
    public static LocalDateTime now() {
        return LocalDateTime.now(DEFAULT_ZONE_ID);
    }

    /**
     * 获取当前 LocalDate
     *
     * @return 当前日期
     */
    public static LocalDate today() {
        return LocalDate.now(DEFAULT_ZONE_ID);
    }

    /**
     * 获取当前时间戳（毫秒）
     *
     * @return 时间戳
     */
    public static Long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前 Date 对象
     *
     * @return 当前日期
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    // ==================== 日期计算 ====================

    /**
     * 计算两个日期之间的天数差
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 天数差
     */
    public static long daysBetween(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    /**
     * 计算两个日期时间之间的小时差
     *
     * @param startDateTime 开始日期时间
     * @param endDateTime   结束日期时间
     * @return 小时差
     */
    public static long hoursBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return ChronoUnit.HOURS.between(startDateTime, endDateTime);
    }

    /**
     * 计算两个日期时间之间的分钟差
     *
     * @param startDateTime 开始日期时间
     * @param endDateTime   结束日期时间
     * @return 分钟差
     */
    public static long minutesBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return ChronoUnit.MINUTES.between(startDateTime, endDateTime);
    }

    /**
     * 增加天数
     *
     * @param date  日期对象
     * @param days  天数
     * @return 新的日期对象
     */
    public static LocalDate plusDays(LocalDate date, long days) {
        return date.plusDays(days);
    }

    /**
     * 增加小时
     *
     * @param dateTime 日期时间对象
     * @param hours    小时数
     * @return 新的日期时间对象
     */
    public static LocalDateTime plusHours(LocalDateTime dateTime, long hours) {
        return dateTime.plusHours(hours);
    }

    /**
     * 减少天数
     *
     * @param date  日期对象
     * @param days  天数
     * @return 新的日期对象
     */
    public static LocalDate minusDays(LocalDate date, long days) {
        return date.minusDays(days);
    }

    /**
     * 减少小时
     *
     * @param dateTime 日期时间对象
     * @param hours    小时数
     * @return 新的日期时间对象
     */
    public static LocalDateTime minusHours(LocalDateTime dateTime, long hours) {
        return dateTime.minusHours(hours);
    }

    // ==================== 类型转换 ====================

    /**
     * Date 转 LocalDateTime
     *
     * @param date Date 对象
     * @return LocalDateTime 对象
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return LocalDateTime.ofInstant(date.toInstant(), DEFAULT_ZONE_ID);
    }

    /**
     * LocalDateTime 转 Date
     *
     * @param localDateTime LocalDateTime 对象
     * @return Date 对象
     */
    public static Date toDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return Date.from(localDateTime.atZone(DEFAULT_ZONE_ID).toInstant());
    }

    /**
     * LocalDate 转 Date
     *
     * @param localDate LocalDate 对象
     * @return Date 对象
     */
    public static Date toDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return Date.from(localDate.atStartOfDay(DEFAULT_ZONE_ID).toInstant());
    }

    // ==================== 其他实用方法 ====================

    /**
     * 获取某月的第一天
     *
     * @param date 日期对象
     * @return 该月第一天的 LocalDate
     */
    public static LocalDate getFirstDayOfMonth(LocalDate date) {
        return date.withDayOfMonth(1);
    }

    /**
     * 获取某月的最后一天
     *
     * @param date 日期对象
     * @return 该月最后一天的 LocalDate
     */
    public static LocalDate getLastDayOfMonth(LocalDate date) {
        return date.withDayOfMonth(date.lengthOfMonth());
    }

    /**
     * 判断是否为今天
     *
     * @param date 待判断的日期
     * @return true 如果是今天
     */
    public static boolean isToday(LocalDate date) {
        return date != null && date.equals(today());
    }

    /**
     * 判断是否为周末
     *
     * @param date 待判断的日期
     * @return true 如果是周末
     */
    public static boolean isWeekend(LocalDate date) {
        if (date == null) {
            return false;
        }
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }
}
