package com.littletime.portal.config.http;

/**
 * @Author YiBuBuHuiTou
 * @Description
 * @Date 2020/3/22 22:53
 * @Version 1.0
 **/
public class  HttpResult<T> {
    private int code;

    private String message;

    private T data;

    public HttpResult() {

    }
    public HttpResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
