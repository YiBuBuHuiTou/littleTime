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
    private static final int EXCEPTION_EXIST_CODE = 4001;
    private static final int TOKEN_EXPIRED_CODE = 5001;
    private static final int UNKNOWN_CODE = 9001;

    private static final String SUCCESS = "SUCCESS";
    private static final String ACCESS_DENIED = "ACCESS_DENIED";
    private static final String NOT_PERMITTED = "NOT_PERMITTED";
    private static final String EXCEPTION_EXIST = "EXCEPTION_EXIST";
    private static final String TOKEN_EXPIRED = "TOKEN_EXPIRED";
    private static final String UNKNOWN = "UNKNOWN";

    private int code;

    private String result;

    private String message;

    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
        if (code == SUCCESS_CODE) {
            this.result = SUCCESS;
        } else if (code == ACCESS_DENIED_CODE) {
            this.result = ACCESS_DENIED;
        } else if (code == NOT_PERMITTED_CODE) {
            this.result = NOT_PERMITTED;
        } else if (code == EXCEPTION_EXIST_CODE) {
            this.result = EXCEPTION_EXIST;
        } else if (code == UNKNOWN_CODE) {
            this.result = TOKEN_EXPIRED;
        } else if (code == TOKEN_EXPIRED_CODE) {
            this.result = UNKNOWN;
        }
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
        if (SUCCESS.equals(result)) {
            this.code = SUCCESS_CODE;
        } else if (ACCESS_DENIED.equals(result)) {
            this.code = ACCESS_DENIED_CODE;
        } else if (NOT_PERMITTED.equals(result)) {
            this.code = NOT_PERMITTED_CODE;
        } else if (EXCEPTION_EXIST.equals(result)) {
            this.code = EXCEPTION_EXIST_CODE;
        } else if (TOKEN_EXPIRED.equals(result)) {
            this.code = TOKEN_EXPIRED_CODE;
        } else {
            this.code = UNKNOWN_CODE;
        }
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
