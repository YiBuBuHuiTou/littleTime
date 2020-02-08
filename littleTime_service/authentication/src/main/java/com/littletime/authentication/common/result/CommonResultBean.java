package com.littletime.authentication.common.result;

import com.alibaba.fastjson.JSON;

/**
 * @author YiBuBuHuiTou
 * 接口返回共通对象
 */
public class CommonResultBean {

    protected static final String SUCCESS = "success";

    protected static final String ERROR = "error";

    protected static final String WARNING = "warning";

    protected static final int SUCCESS_CODE = 0;

    protected static final int ERROR_CODE = 1;

    protected static final int WARNING_CODE = 2;

    protected static final long DEFAULT_TTL = -1L;

    protected static final String SUCCESS_MESSAGE = "接口调用成功";

    protected static final String ERROR_MESSAGE = "接口调用失败";

    protected static final String WARNING_MESSAGE = "接口调用成功，但存在警告";


    //状态
    protected String status;
    //状态码
    protected int code;
    // 过期时间
    protected long ttl;
    //消息
    protected String message;
    //数据
    protected String data;
    //数据格式
    protected String format = "json";

    @Override
    public String toString() {
        if("json".equals(format)) {
            return JSON.toJSONString(this);
        } else if ("xml".equals(format)){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<Result>");
            stringBuilder.append("<status>").append(this.status).append("</status>");
            stringBuilder.append("<code>").append(this.code).append("</code>");
            stringBuilder.append("<ttl>").append(this.ttl).append("</ttl>");
            stringBuilder.append("<message>").append(this.message).append("</message>");
            stringBuilder.append("<data>").append(this.data).append("<data>");
            stringBuilder.append("</Result>");
            return stringBuilder.toString();
        } else {
            return null;
        }

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
