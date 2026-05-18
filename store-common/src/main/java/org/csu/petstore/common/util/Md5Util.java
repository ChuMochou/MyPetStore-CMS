package org.csu.petstore.common.util;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * MD5 加密工具类
 * 提供多种加密方法
 */
public class Md5Util {

    /**
     * 默认盐值
     */
    private static final String DEFAULT_SALT = "csu_management_system_2025";

    private Md5Util() {
        throw new IllegalStateException("Utility class");
    }

    // ==================== MD5 加密 ====================

    /**
     * MD5 加密（加盐）
     *
     * @param password 原始密码
     * @return 加密后的密文（十六进制字符串）
     */
    public static String encrypt(String password) {
        return encrypt(password, DEFAULT_SALT);
    }

    /**
     * MD5 加密（自定义盐值）
     *
     * @param password 原始密码
     * @param salt     盐值
     * @return 加密后的密文（十六进制字符串）
     */
    public static String encrypt(String password, String salt) {
        if (StringUtil.isEmpty(password)) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        String saltedPassword = password + salt;
        return DigestUtils.md5DigestAsHex(saltedPassword.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 验证密码
     *
     * @param password          原始密码
     * @param encryptedPassword 加密后的密码
     * @return 是否匹配
     */
    public static boolean verify(String password, String encryptedPassword) {
        return verify(password, DEFAULT_SALT, encryptedPassword);
    }

    /**
     * 验证密码（自定义盐值）
     *
     * @param password          原始密码
     * @param salt              盐值
     * @param encryptedPassword 加密后的密码
     * @return 是否匹配
     */
    public static boolean verify(String password, String salt, String encryptedPassword) {
        String encrypted = encrypt(password, salt);
        return encrypted.equals(encryptedPassword);
    }

    // ==================== SHA-256 加密 ====================

    /**
     * SHA-256 加密（加盐）
     *
     * @param password 原始密码
     * @return 加密后的密文（十六进制字符串）
     */
    public static String sha256Encrypt(String password) {
        return sha256Encrypt(password, DEFAULT_SALT);
    }

    /**
     * SHA-256 加密（自定义盐值）
     *
     * @param password 原始密码
     * @param salt     盐值
     * @return 加密后的密文（十六进制字符串）
     */
    public static String sha256Encrypt(String password, String salt) {
        if (StringUtil.isEmpty(password)) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String saltedPassword = password + salt;
            byte[] hash = digest.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

    /**
     * 验证 SHA-256 密码
     *
     * @param password          原始密码
     * @param encryptedPassword 加密后的密码
     * @return 是否匹配
     */
    public static boolean verifySha256(String password, String encryptedPassword) {
        return verifySha256(password, DEFAULT_SALT, encryptedPassword);
    }

    /**
     * 验证 SHA-256 密码（自定义盐值）
     *
     * @param password          原始密码
     * @param salt              盐值
     * @param encryptedPassword 加密后的密码
     * @return 是否匹配
     */
    public static boolean verifySha256(String password, String salt, String encryptedPassword) {
        String encrypted = sha256Encrypt(password, salt);
        return encrypted.equals(encryptedPassword);
    }

    // ==================== Base64 编码/解码 ====================

    /**
     * Base64 编码
     *
     * @param data 原始数据
     * @return Base64 编码后的字符串
     */
    public static String base64Encode(String data) {
        if (StringUtil.isEmpty(data)) {
            return null;
        }
        return Base64.getEncoder().encodeToString(data.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Base64 解码
     *
     * @param encodedData Base64 编码的字符串
     * @return 解码后的原始字符串
     */
    public static String base64Decode(String encodedData) {
        if (StringUtil.isEmpty(encodedData)) {
            return null;
        }
        byte[] decodedBytes = Base64.getDecoder().decode(encodedData);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }

    // ==================== 多次加密（增强安全性）====================

    /**
     * 多次 MD5 加密（更安全的密码存储）
     *
     * @param password 原始密码
     * @param times    加密次数
     * @return 加密后的密文
     */
    public static String multiEncrypt(String password, int times) {
        return multiEncrypt(password, DEFAULT_SALT, times);
    }

    /**
     * 多次 MD5 加密（自定义盐值）
     *
     * @param password 原始密码
     * @param salt     盐值
     * @param times    加密次数
     * @return 加密后的密文
     */
    public static String multiEncrypt(String password, String salt, int times) {
        if (StringUtil.isEmpty(password)) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (times <= 0) {
            throw new IllegalArgumentException("Times must be greater than 0");
        }
        
        String result = password;
        for (int i = 0; i < times; i++) {
            result = encrypt(result, salt);
        }
        return result;
    }

    // ==================== 辅助方法 ====================

    /**
     * 字节数组转十六进制字符串
     *
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
