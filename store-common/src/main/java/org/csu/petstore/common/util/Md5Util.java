package org.csu.petstore.common.util;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * MD5 加密工具类
 */
public class Md5Util {

    private static final String SALT = "csu_management_system_2025";

    /**
     * MD5 加密（加盐）
     * @param password 原始密码
     * @return 加密后的密文
     */
    public static String encrypt(String password) {
        return DigestUtils.md5DigestAsHex((password + SALT).getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 验证密码
     * @param password 原始密码
     * @param encryptedPassword 加密后的密码
     * @return 是否匹配
     */
    public static boolean verify(String password, String encryptedPassword) {
        String encrypted = encrypt(password);
        return encrypted.equals(encryptedPassword);
    }

    /**
     * 自定义 MD5 加密（指定盐值）
     * @param password 原始密码
     * @param salt 盐值
     * @return 加密后的密文
     */
    public static String encryptWithSalt(String password, String salt) {
        return DigestUtils.md5DigestAsHex((password + salt).getBytes(StandardCharsets.UTF_8));
    }
}
