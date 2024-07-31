package com.xxx.toutiao.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 全局统一返回结果类
 * 返回码,返回消息,返回数据三个对象
 */
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public Result() {

    }

    protected static <T> Result build(T data) {
        Result<T> result = new Result<>();
        if (data != null) {
            result.setData(data);
        }
        return result;
    }

    public static <T> Result<T> build(T body, Integer code, String message) {
        Result result = build(body);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> build(T body, ResultCodeEnum resultCodeEnum) {
        Result result = build(body);
        result.setMessage(resultCodeEnum.getMessage());
        result.setCode(resultCodeEnum.getCode());
        return result;
    }

    /**
     * 成功
     *
     * @param data data
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok(T data) {
        Result<T> result = build(data, ResultCodeEnum.SUCCESS);
        return result;
    }

    /**
     * 失败
     *
     * @param data data
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(T data) {
        Result<T> result = build(data, ResultCodeEnum.SYSTEM_ERROR);
        return result;
    }


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
}
