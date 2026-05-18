package org.csu.petstore.common.util;

import org.csu.petstore.common.Result;
import org.csu.petstore.common.dto.PageResult;

import java.util.List;

/**
 * 响应工具类
 * 简化 Controller 层的响应构建
 */
public class ResponseUtil {

    private ResponseUtil() {
        throw new IllegalStateException("Utility class");
    }

    // ==================== 成功响应 ====================

    /**
     * 构建成功响应（无数据）
     *
     * @return 统一响应结果
     */
    public static Result<Void> success() {
        return Result.success();
    }

    /**
     * 构建成功响应（带数据）
     *
     * @param data 响应数据
     * @param <T>  数据类型
     * @return 统一响应结果
     */
    public static <T> Result<T> success(T data) {
        return Result.success(data);
    }

    /**
     * 构建成功响应（自定义消息）
     *
     * @param message 响应消息
     * @param data    响应数据
     * @param <T>     数据类型
     * @return 统一响应结果
     */
    public static <T> Result<T> success(String message, T data) {
        return Result.success(message, data);
    }

    /**
     * 构建成功响应（自定义消息，无数据）
     *
     * @param message 响应消息
     * @return 统一响应结果
     */
    public static <T> Result<T> successMessage(String message) {
        return Result.successMessage(message);
    }

    // ==================== 失败响应 ====================

    /**
     * 构建失败响应
     *
     * @return 统一响应结果
     */
    public static Result<Void> error() {
        return Result.error();
    }

    /**
     * 构建失败响应（自定义消息）
     *
     * @param message 错误消息
     * @return 统一响应结果
     */
    public static <T> Result<T> error(String message) {
        return Result.error(message);
    }

    /**
     * 构建失败响应（自定义状态码和消息）
     *
     * @param code    错误码
     * @param message 错误消息
     * @return 统一响应结果
     */
    public static <T> Result<T> error(Integer code, String message) {
        return Result.error(code, message);
    }

    // ==================== 分页响应 ====================

    /**
     * 构建分页成功响应
     *
     * @param total    总记录数
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param list     数据列表
     * @param <T>      数据类型
     * @return 统一响应结果
     */
    public static <T> Result<PageResult<T>> pageSuccess(Long total, Integer pageNum, Integer pageSize, List<T> list) {
        PageResult<T> pageResult = PageResult.of(total, pageNum, pageSize, list);
        return Result.success(pageResult);
    }

    /**
     * 构建分页成功响应（直接传入 PageResult）
     *
     * @param pageResult 分页结果
     * @param <T>        数据类型
     * @return 统一响应结果
     */
    public static <T> Result<PageResult<T>> pageSuccess(PageResult<T> pageResult) {
        return Result.success(pageResult);
    }

    // ==================== 特殊响应 ====================

    /**
     * 构建未授权响应
     *
     * @return 统一响应结果
     */
    public static Result<Void> unauthorized() {
        return Result.unauthorized();
    }

    /**
     * 构建未授权响应（自定义消息）
     *
     * @param message 错误消息
     * @return 统一响应结果
     */
    public static <T> Result<T> unauthorized(String message) {
        return Result.unauthorized(message);
    }

    /**
     * 构建禁止访问响应
     *
     * @return 统一响应结果
     */
    public static Result<Void> forbidden() {
        return Result.forbidden();
    }

    /**
     * 构建禁止访问响应（自定义消息）
     *
     * @param message 错误消息
     * @return 统一响应结果
     */
    public static <T> Result<T> forbidden(String message) {
        return Result.forbidden(message);
    }

    /**
     * 构建资源未找到响应
     *
     * @return 统一响应结果
     */
    public static Result<Void> notFound() {
        return Result.notFound();
    }

    /**
     * 构建资源未找到响应（自定义消息）
     *
     * @param message 错误消息
     * @return 统一响应结果
     */
    public static <T> Result<T> notFound(String message) {
        return Result.notFound(message);
    }

    // ==================== 条件响应 ====================

    /**
     * 根据条件构建响应
     *
     * @param condition 条件
     * @param successData 成功时的数据
     * @param errorMessage 失败时的错误消息
     * @param <T> 数据类型
     * @return 统一响应结果
     */
    public static <T> Result<T> condition(boolean condition, T successData, String errorMessage) {
        if (condition) {
            return Result.success(successData);
        } else {
            return Result.error(errorMessage);
        }
    }

    /**
     * 根据条件构建响应（无数据）
     *
     * @param condition 条件
     * @param successMessage 成功时的消息
     * @param errorMessage 失败时的错误消息
     * @return 统一响应结果
     */
    public static Result<Void> condition(boolean condition, String successMessage, String errorMessage) {
        if (condition) {
            return Result.successMessage(successMessage);
        } else {
            return Result.error(errorMessage);
        }
    }

    // ==================== 空值检查响应 ====================

    /**
     * 检查对象是否为空，为空则返回错误响应
     *
     * @param obj 待检查的对象
     * @param errorMessage 错误消息
     * @param <T> 数据类型
     * @return 统一响应结果
     */
    public static <T> Result<T> checkNotNull(T obj, String errorMessage) {
        if (obj == null) {
            return Result.error(errorMessage);
        }
        return Result.success(obj);
    }

    /**
     * 检查字符串是否为空，为空则返回错误响应
     *
     * @param str 待检查的字符串
     * @param errorMessage 错误消息
     * @param <T> 数据类型
     * @return 统一响应结果
     */
    public static <T> Result<T> checkNotEmpty(String str, String errorMessage) {
        if (StringUtil.isEmpty(str)) {
            return Result.error(errorMessage);
        }
        return Result.success((T) str);
    }

    /**
     * 检查列表是否为空，为空则返回错误响应
     *
     * @param list 待检查的列表
     * @param errorMessage 错误消息
     * @param <T> 数据类型
     * @return 统一响应结果
     */
    public static <T> Result<List<T>> checkListNotEmpty(List<T> list, String errorMessage) {
        if (list == null || list.isEmpty()) {
            return Result.error(errorMessage);
        }
        return Result.success(list);
    }
}
