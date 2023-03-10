package com.freeedu.commonserver.entity;

/**
 * @author Wd
 */
public class Resp<T> {
    private Integer code;
    private String message;
    private T data;

    private Resp() {
    }

    private Resp(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public Boolean isSuccess() {
        return this.code == 0;
    }

    public T getData() {
        return this.data;
    }

    public static <T> Resp<T> success(T data) {
        return new Resp(0, "success", data);
    }

    public static Resp success() {
        return new Resp(0, "success", (Object)null);
    }

    public static Resp error(Integer code, String message) {
        return new Resp(code, message, (Object)null);
    }

    public static Resp error() {
        return new Resp(50000, "异常", (Object)null);
    }

    public static Resp error(String message) {
        return new Resp(50000, message, (Object)null);
    }
}
