package org.csu.petstore.common.util;

import java.util.UUID;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * 提供常用的字符串处理方法
 */
public class StringUtil {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "^1[3-9]\\d{9}$"
    );

    private StringUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 判断字符串是否为空或 null
     *
     * @param str 待检查的字符串
     * @return true 如果字符串为 null 或空字符串
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * 判断字符串是否不为空且不为 null
     *
     * @param str 待检查的字符串
     * @return true 如果字符串不为 null 且不为空字符串
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断字符串是否为空白（null、空字符串或只包含空格）
     *
     * @param str 待检查的字符串
     * @return true 如果字符串为空白
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * 判断字符串是否不为空白
     *
     * @param str 待检查的字符串
     * @return true 如果字符串不为空白
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 去除字符串首尾空白，如果为 null 则返回 null
     *
     * @param str 待处理的字符串
     * @return 去除空白后的字符串
     */
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    /**
     * 去除字符串首尾空白，如果为 null 则返回空字符串
     *
     * @param str 待处理的字符串
     * @return 去除空白后的字符串或空字符串
     */
    public static String trimToEmpty(String str) {
        return str == null ? "" : str.trim();
    }

    /**
     * 将 null 转换为空字符串
     *
     * @param str 待处理的字符串
     * @return 如果为 null 返回空字符串，否则返回原字符串
     */
    public static String nullToEmpty(String str) {
        return str == null ? "" : str;
    }

    /**
     * 将空字符串转换为 null
     *
     * @param str 待处理的字符串
     * @return 如果为空字符串返回 null，否则返回原字符串
     */
    public static String emptyToNull(String str) {
        return isEmpty(str) ? null : str;
    }

    /**
     * 生成随机 UUID 字符串（不带横线）
     *
     * @return UUID 字符串
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 验证邮箱格式
     *
     * @param email 邮箱地址
     * @return true 如果邮箱格式正确
     */
    public static boolean isValidEmail(String email) {
        if (isEmpty(email)) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * 验证手机号格式（中国大陆）
     *
     * @param phone 手机号
     * @return true 如果手机号格式正确
     */
    public static boolean isValidPhone(String phone) {
        if (isEmpty(phone)) {
            return false;
        }
        return PHONE_PATTERN.matcher(phone).matches();
    }

    /**
     * 隐藏字符串中间部分（用于脱敏显示）
     *
     * @param str   原始字符串
     * @param start 开始保留的字符数
     * @param end   结尾保留的字符数
     * @return 脱敏后的字符串
     */
    public static String hideMiddle(String str, int start, int end) {
        if (isEmpty(str)) {
            return str;
        }
        int length = str.length();
        if (length <= start + end) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str.substring(0, start));
        for (int i = 0; i < length - start - end; i++) {
            sb.append('*');
        }
        sb.append(str.substring(length - end));
        return sb.toString();
    }

    /**
     * 隐藏邮箱用户名部分
     *
     * @param email 邮箱地址
     * @return 脱敏后的邮箱
     */
    public static String hideEmail(String email) {
        if (isEmpty(email) || !email.contains("@")) {
            return email;
        }
        String[] parts = email.split("@");
        String username = parts[0];
        String domain = parts[1];
        
        if (username.length() <= 2) {
            return "*" + username.charAt(username.length() - 1) + "@" + domain;
        }
        
        return username.charAt(0) + "***" + username.charAt(username.length() - 1) + "@" + domain;
    }

    /**
     * 隐藏手机号中间四位
     *
     * @param phone 手机号
     * @return 脱敏后的手机号
     */
    public static String hidePhone(String phone) {
        if (isEmpty(phone) || phone.length() != 11) {
            return phone;
        }
        return phone.substring(0, 3) + "****" + phone.substring(7);
    }

    /**
     * 截取字符串，超过指定长度添加省略号
     *
     * @param str   原始字符串
     * @param maxLength 最大长度
     * @return 截取后的字符串
     */
    public static String truncate(String str, int maxLength) {
        if (isEmpty(str)) {
            return str;
        }
        if (str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength) + "...";
    }

    /**
     * 首字母大写
     *
     * @param str 原始字符串
     * @return 首字母大写后的字符串
     */
    public static String capitalize(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param str 原始字符串
     * @return 首字母小写后的字符串
     */
    public static String uncapitalize(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }
}