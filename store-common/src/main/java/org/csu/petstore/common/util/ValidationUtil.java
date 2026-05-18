package org.csu.petstore.common.util;

import java.util.regex.Pattern;

/**
 * 验证工具类
 * 提供常用的数据验证方法
 */
public class ValidationUtil {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "^1[3-9]\\d{9}$"
    );

    private static final Pattern USERNAME_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9_]{4,16}$"
    );

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,20}$"
    );

    private static final Pattern URL_PATTERN = Pattern.compile(
            "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$"
    );

    private static final Pattern CHINESE_PATTERN = Pattern.compile(
            "[\\u4e00-\\u9fa5]+"
    );

    private static final Pattern ID_CARD_PATTERN = Pattern.compile(
            "^[1-9]\\d{5}(18|19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])\\d{3}[\\dXx]$"
    );

    private ValidationUtil() {
        throw new IllegalStateException("Utility class");
    }

    // ==================== 基本验证 ====================

    /**
     * 验证字符串是否不为空
     *
     * @param str 待验证的字符串
     * @return true 如果不为空
     */
    public static boolean notEmpty(String str) {
        return StringUtil.isNotEmpty(str);
    }

    /**
     * 验证字符串是否为空
     *
     * @param str 待验证的字符串
     * @return true 如果为空
     */
    public static boolean isEmpty(String str) {
        return StringUtil.isEmpty(str);
    }

    /**
     * 验证字符串是否不为空白
     *
     * @param str 待验证的字符串
     * @return true 如果不为空白
     */
    public static boolean notBlank(String str) {
        return StringUtil.isNotBlank(str);
    }

    // ==================== 邮箱验证 ====================

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

    // ==================== 手机号验证 ====================

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

    // ==================== 用户名验证 ====================

    /**
     * 验证用户名格式（字母、数字、下划线，4-16位）
     *
     * @param username 用户名
     * @return true 如果用户名格式正确
     */
    public static boolean isValidUsername(String username) {
        if (isEmpty(username)) {
            return false;
        }
        return USERNAME_PATTERN.matcher(username).matches();
    }

    // ==================== 密码验证 ====================

    /**
     * 验证密码格式（至少包含字母和数字，6-20位）
     *
     * @param password 密码
     * @return true 如果密码格式正确
     */
    public static boolean isValidPassword(String password) {
        if (isEmpty(password)) {
            return false;
        }
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    /**
     * 验证密码强度
     *
     * @param password 密码
     * @return 密码强度：WEAK（弱）、MEDIUM（中）、STRONG（强）
     */
    public static PasswordStrength checkPasswordStrength(String password) {
        if (isEmpty(password)) {
            return PasswordStrength.WEAK;
        }

        int length = password.length();
        boolean hasLower = password.matches(".*[a-z].*");
        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");

        int score = 0;
        if (length >= 8) score++;
        if (length >= 12) score++;
        if (hasLower && hasUpper) score++;
        if (hasDigit) score++;
        if (hasSpecial) score++;

        if (score >= 4) {
            return PasswordStrength.STRONG;
        } else if (score >= 2) {
            return PasswordStrength.MEDIUM;
        } else {
            return PasswordStrength.WEAK;
        }
    }

    // ==================== URL 验证 ====================

    /**
     * 验证 URL 格式
     *
     * @param url URL 地址
     * @return true 如果 URL 格式正确
     */
    public static boolean isValidUrl(String url) {
        if (isEmpty(url)) {
            return false;
        }
        return URL_PATTERN.matcher(url).matches();
    }

    // ==================== 身份证号验证 ====================

    /**
     * 验证身份证号格式（中国大陆）
     *
     * @param idCard 身份证号
     * @return true 如果身份证号格式正确
     */
    public static boolean isValidIdCard(String idCard) {
        if (isEmpty(idCard)) {
            return false;
        }
        return ID_CARD_PATTERN.matcher(idCard).matches();
    }

    // ==================== 中文验证 ====================

    /**
     * 验证是否全为中文字符
     *
     * @param str 待验证的字符串
     * @return true 如果全为中文
     */
    public static boolean isAllChinese(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return CHINESE_PATTERN.matcher(str).matches();
    }

    /**
     * 验证是否包含中文字符
     *
     * @param str 待验证的字符串
     * @return true 如果包含中文
     */
    public static boolean containsChinese(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return CHINESE_PATTERN.matcher(str).find();
    }

    // ==================== 数字验证 ====================

    /**
     * 验证是否为整数
     *
     * @param str 待验证的字符串
     * @return true 如果是整数
     */
    public static boolean isInteger(String str) {
        if (isEmpty(str)) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 验证是否为长整数
     *
     * @param str 待验证的字符串
     * @return true 如果是长整数
     */
    public static boolean isLong(String str) {
        if (isEmpty(str)) {
            return false;
        }
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 验证是否为浮点数
     *
     * @param str 待验证的字符串
     * @return true 如果是浮点数
     */
    public static boolean isDouble(String str) {
        if (isEmpty(str)) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 验证数值是否在范围内
     *
     * @param value 待验证的数值
     * @param min   最小值
     * @param max   最大值
     * @return true 如果在范围内
     */
    public static boolean isInRange(int value, int min, int max) {
        return value >= min && value <= max;
    }

    /**
     * 验证数值是否在范围内
     *
     * @param value 待验证的数值
     * @param min   最小值
     * @param max   最大值
     * @return true 如果在范围内
     */
    public static boolean isInRange(long value, long min, long max) {
        return value >= min && value <= max;
    }

    /**
     * 验证数值是否在范围内
     *
     * @param value 待验证的数值
     * @param min   最小值
     * @param max   最大值
     * @return true 如果在范围内
     */
    public static boolean isInRange(double value, double min, double max) {
        return value >= min && value <= max;
    }

    // ==================== 长度验证 ====================

    /**
     * 验证字符串长度是否在范围内
     *
     * @param str 待验证的字符串
     * @param min 最小长度
     * @param max 最大长度
     * @return true 如果长度在范围内
     */
    public static boolean isLengthInRange(String str, int min, int max) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        return length >= min && length <= max;
    }

    // ==================== 密码强度枚举 ====================

    /**
     * 密码强度枚举
     */
    public enum PasswordStrength {
        /**
         * 弱密码
         */
        WEAK,
        
        /**
         * 中等强度密码
         */
        MEDIUM,
        
        /**
         * 强密码
         */
        STRONG
    }
}
