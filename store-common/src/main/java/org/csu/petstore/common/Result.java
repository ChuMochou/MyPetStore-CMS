package org.csu.petstore.common;

import java.io.Serializable;

/**
 * 统一返回结果类
 * @param <T> 数据类型
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 成功返回结果（自定义消息，无数据）
     */
    public static <T> Result<T> successMessage(String message) {
        return new Result<>(Constants.SUCCESS_CODE, message, null);
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

    /**
     * 未授权返回结果
     */
    public static <T> Result<T> unauthorized() {
        return new Result<>(Constants.UNAUTHORIZED_CODE, "未授权访问", null);
    }

    /**
     * 未授权返回结果（自定义消息）
     */
    public static <T> Result<T> unauthorized(String message) {
        return new Result<>(Constants.UNAUTHORIZED_CODE, message, null);
    }

    /**
     * 禁止访问返回结果
     */
    public static <T> Result<T> forbidden() {
        return new Result<>(Constants.FORBIDDEN_CODE, "禁止访问", null);
    }

    /**
     * 禁止访问返回结果（自定义消息）
     */
    public static <T> Result<T> forbidden(String message) {
        return new Result<>(Constants.FORBIDDEN_CODE, message, null);
    }

    /**
     * 资源未找到返回结果
     */
    public static <T> Result<T> notFound() {
        return new Result<>(Constants.NOT_FOUND_CODE, "资源不存在", null);
    }

    /**
     * 资源未找到返回结果（自定义消息）
     */
    public static <T> Result<T> notFound(String message) {
        return new Result<>(Constants.NOT_FOUND_CODE, message, null);
    }

    /**
     * 判断是否成功
     *
     * @return true 如果状态码为成功
     */
    public boolean isSuccess() {
        return this.code != null && this.code == Constants.SUCCESS_CODE;
    }

    /**
     * 判断是否失败
     *
     * @return true 如果状态码不为成功
     */
    public boolean isError() {
        return !isSuccess();
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

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", timestamp=" + timestamp +
                '}';
    }
}
