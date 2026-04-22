package org.csu.petstore.common;

/**
 * 统一返回结果类
 * @param <T> 数据类型
 */
public class Result<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    /**
     * 时间戳
     */
    private Long timestamp;

    public Result() {
        this.timestamp = System.currentTimeMillis();
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 成功返回结果
     */
    public static <T> Result<T> success() {
        return new Result<>(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, null);
    }

    /**
     * 成功返回结果（带数据）
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, data);
    }

    /**
     * 成功返回结果（自定义消息）
     */
    public static <T> Result<T> success(String message, T data) {
        return new Result<>(Constants.SUCCESS_CODE, message, data);
    }

    /**
     * 失败返回结果
     */
    public static <T> Result<T> error() {
        return new Result<>(Constants.ERROR_CODE, Constants.ERROR_MESSAGE, null);
    }

    /**
     * 失败返回结果（自定义消息）
     */
    public static <T> Result<T> error(String message) {
        return new Result<>(Constants.ERROR_CODE, message, null);
    }

    /**
     * 失败返回结果（自定义状态码和消息）
     */
    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(code, message, null);
    }

    // Getters and Setters

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
