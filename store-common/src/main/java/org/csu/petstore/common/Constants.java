package org.csu.petstore.common;

/**
 * 通用常量定义
 */
public class Constants {

    private Constants() {
        throw new IllegalStateException("Constants class");
    }

    // 成功状态码
    public static final int SUCCESS_CODE = 200;
    
    // 错误状态码
    public static final int ERROR_CODE = 500;
    
    // 未授权状态码
    public static final int UNAUTHORIZED_CODE = 401;
    
    // 禁止访问状态码
    public static final int FORBIDDEN_CODE = 403;
    
    // 未找到资源状态码
    public static final int NOT_FOUND_CODE = 404;

    // 成功消息
    public static final String SUCCESS_MESSAGE = "操作成功";
    
    // 失败消息
    public static final String ERROR_MESSAGE = "操作失败";

    // 默认分页页码
    public static final int DEFAULT_PAGE_NUM = 1;
    
    // 默认分页大小
    public static final int DEFAULT_PAGE_SIZE = 10;

    // UTF-8 编码
    public static final String UTF8 = "UTF-8";
    
    // 默认密码（加密用）
    public static final String DEFAULT_PASSWORD = "123456";

    // 管理员角色
    public static final String ROLE_ADMIN = "ADMIN";
    
    // 普通用户角色
    public static final String ROLE_USER = "USER";

    // 启用状态
    public static final Integer STATUS_ENABLE = 1;
    
    // 禁用状态
    public static final Integer STATUS_DISABLE = 0;

    // 删除标志：正常
    public static final Integer DEL_FLAG_NORMAL = 0;
    
    // 删除标志：已删除
    public static final Integer DEL_FLAG_DELETED = 1;
}
