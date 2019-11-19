package com.cxd.littletime.common.exception;

/**
 * @author YiBuBuHuiTou
 */
public class NotSupportedKeyException extends  Exception {
    private String key;

    public NotSupportedKeyException(String key) {
        this.key = key;
    }
    public String getExceptionMessage() {
        return this.key + " is not supported! please change another key.";
    }

}