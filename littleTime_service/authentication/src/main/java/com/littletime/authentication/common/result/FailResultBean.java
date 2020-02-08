package com.littletime.authentication.common.result;

/**
 * @author YiBuBuHuiTou
 * 接口调用失败Bean
 */
public class FailResultBean extends CommonResultBean {
    public FailResultBean() {
        this.status = CommonResultBean.ERROR;
        this.code = CommonResultBean.ERROR_CODE;
        this.ttl = CommonResultBean.DEFAULT_TTL;
        this.message = CommonResultBean.ERROR_MESSAGE;
    }
}
