package com.cxd.littletime.common.Bean;

/**
 * @Author YiBuBuHuiTou
 * @Description
 * @Date 2020/3/25 22:42
 * @Version 1.0
 **/
public class CommonResultBean {

    private static final int SUCCESS_CODE = 1001;
    private static final int ACCESS_DENIED_CODE = 2001;
    private static final int NOT_PERMITTED_CODE = 3001;
    private static final int  EXCEPTION_EXIST_CODE = 4001;
    private static final int UNKNOWN_CODE = 5001;

    private static final String SUCCESS = "SUCCESS";
    private static final String ACCESS_DENIED = "ACCESS_DENIED";
    private static final String NOT_PERMITTED = "NOT_PERMITTED";
    private static final String EXCEPTION_EXIST = "EXCEPTION_EXIST";

    private int code;

    private String result;

    private String message;

    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
