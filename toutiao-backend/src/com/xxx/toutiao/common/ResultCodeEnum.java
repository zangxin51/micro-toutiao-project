package com.xxx.toutiao.common;

/**
 * 统一返回结果状态信息类
 */
public enum ResultCodeEnum {
    SUCCESS(200, "SUCCESS"),
    USERNAME_ERROR(501, "USERNAME ERROR"),
    PASSWORD_ERROR(503, "PASSWORD ERROR"),
    NOT_LONGIN(504, "NOT LOGIN"),
    USERNAME_USED(505, "USERNAME USED"),
    SYSTEM_ERROR(506, "SYSTEM_ERROR");

    private Integer code;
    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
