package org.csu.petstore.common;

/**
 * 通用常量定义
 */
public class Constants {

    private Constants() {
        throw new IllegalStateException("Constants class");
    }

    // ==================== 状态码 ====================
    
    /**
     * 成功状态码
     */
    public static final int SUCCESS_CODE = 200;
    
    /**
     * 错误状态码
     */
    public static final int ERROR_CODE = 500;
    
    /**
     * 未授权状态码
     */
    public static final int UNAUTHORIZED_CODE = 401;
    
    /**
     * 禁止访问状态码
     */
    public static final int FORBIDDEN_CODE = 403;
    
    /**
     * 未找到资源状态码
     */
    public static final int NOT_FOUND_CODE = 404;
    
    /**
     * 请求参数错误状态码
     */
    public static final int BAD_REQUEST_CODE = 400;

    // ==================== 消息 ====================

    /**
     * 成功消息
     */
    public static final String SUCCESS_MESSAGE = "操作成功";
    
    /**
     * 失败消息
     */
    public static final String ERROR_MESSAGE = "操作失败";
    
    /**
     * 系统异常消息
     */
    public static final String SYSTEM_ERROR_MESSAGE = "系统异常，请稍后重试";

    // ==================== 分页 ====================

    /**
     * 默认分页页码
     */
    public static final int DEFAULT_PAGE_NUM = 1;
    
    /**
     * 默认分页大小
     */
    public static final int DEFAULT_PAGE_SIZE = 10;
    
    /**
     * 最大分页大小
     */
    public static final int MAX_PAGE_SIZE = 100;

    // ==================== 编码 ====================

    /**
     * UTF-8 编码
     */
    public static final String UTF8 = "UTF-8";
    
    /**
     * GBK 编码
     */
    public static final String GBK = "GBK";

    // ==================== 密码 ====================

    /**
     * 默认密码（加密用）
     */
    public static final String DEFAULT_PASSWORD = "123456";
    
    /**
     * 密码最小长度
     */
    public static final int PASSWORD_MIN_LENGTH = 6;
    
    /**
     * 密码最大长度
     */
    public static final int PASSWORD_MAX_LENGTH = 20;

    // ==================== 角色 ====================

    /**
     * 管理员角色
     */
    public static final String ROLE_ADMIN = "ADMIN";
    
    /**
     * 普通用户角色
     */
    public static final String ROLE_USER = "USER";

    // ==================== 状态 ====================

    /**
     * 启用状态
     */
    public static final Integer STATUS_ENABLE = 1;
    
    /**
     * 禁用状态
     */
    public static final Integer STATUS_DISABLE = 0;
    
    /**
     * 待审核状态
     */
    public static final Integer STATUS_PENDING = 2;
    
    /**
     * 已删除状态
     */
    public static final Integer STATUS_DELETED = -1;

    // ==================== 删除标志 ====================

    /**
     * 删除标志：正常
     */
    public static final Integer DEL_FLAG_NORMAL = 0;
    
    /**
     * 删除标志：已删除
     */
    public static final Integer DEL_FLAG_DELETED = 1;

    // ==================== 订单状态 ====================

    /**
     * 订单状态：待付款
     */
    public static final Integer ORDER_STATUS_PENDING = 0;
    
    /**
     * 订单状态：已付款/待发货
     */
    public static final Integer ORDER_STATUS_PAID = 1;
    
    /**
     * 订单状态：已发货
     */
    public static final Integer ORDER_STATUS_SHIPPED = 2;
    
    /**
     * 订单状态：已完成
     */
    public static final Integer ORDER_STATUS_COMPLETED = 3;
    
    /**
     * 订单状态：已取消
     */
    public static final Integer ORDER_STATUS_CANCELLED = 4;
    
    /**
     * 订单状态：退款中
     */
    public static final Integer ORDER_STATUS_REFUNDING = 5;
    
    /**
     * 订单状态：已退款
     */
    public static final Integer ORDER_STATUS_REFUNDED = 6;

    // ==================== 支付类型 ====================

    /**
     * 支付类型：支付宝
     */
    public static final String PAY_TYPE_ALIPAY = "ALIPAY";
    
    /**
     * 支付类型：微信
     */
    public static final String PAY_TYPE_WECHAT = "WECHAT";
    
    /**
     * 支付类型：银行卡
     */
    public static final String PAY_TYPE_BANK = "BANK";
    
    /**
     * 支付类型：货到付款
     */
    public static final String PAY_TYPE_COD = "COD";

    // ==================== 文件上传 ====================

    /**
     * 最大文件大小：10MB
     */
    public static final long MAX_FILE_SIZE = 10 * 1024 * 1024;
    
    /**
     * 允许的图片扩展名
     */
    public static final String[] IMAGE_EXTENSIONS = {"jpg", "jpeg", "png", "gif", "bmp", "webp"};
    
    /**
     * 允许的文件扩展名
     */
    public static final String[] ALLOWED_EXTENSIONS = {"jpg", "jpeg", "png", "gif", "pdf", "doc", "docx", "xls", "xlsx"};

    // ==================== Redis Key 前缀 ====================

    /**
     * Redis Key 前缀：用户
     */
    public static final String REDIS_KEY_USER = "user:";
    
    /**
     * Redis Key 前缀：订单
     */
    public static final String REDIS_KEY_ORDER = "order:";
    
    /**
     * Redis Key 前缀：商品
     */
    public static final String REDIS_KEY_PRODUCT = "product:";
    
    /**
     * Redis Key 前缀：验证码
     */
    public static final String REDIS_KEY_CAPTCHA = "captcha:";
    
    /**
     * Redis Key 前缀：Token
     */
    public static final String REDIS_KEY_TOKEN = "token:";

    // ==================== JWT ====================

    /**
     * JWT 密钥
     */
    public static final String JWT_SECRET = "my_pet_store_secret_key_2025";
    
    /**
     * JWT 过期时间：7天（毫秒）
     */
    public static final long JWT_EXPIRATION = 7 * 24 * 60 * 60 * 1000;
    
    /**
     * JWT Token 前缀
     */
    public static final String JWT_TOKEN_PREFIX = "Bearer ";
    
    /**
     * JWT Header 名称
     */
    public static final String JWT_HEADER_NAME = "Authorization";

    // ==================== 正则表达式 ====================

    /**
     * 邮箱正则表达式
     */
    public static final String REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    
    /**
     * 手机号正则表达式（中国大陆）
     */
    public static final String REGEX_PHONE = "^1[3-9]\\d{9}$";
    
    /**
     * 用户名正则表达式（字母、数字、下划线，4-16位）
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z0-9_]{4,16}$";
    
    /**
     * 密码正则表达式（至少包含字母和数字，6-20位）
     */
    public static final String REGEX_PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,20}$";
}
