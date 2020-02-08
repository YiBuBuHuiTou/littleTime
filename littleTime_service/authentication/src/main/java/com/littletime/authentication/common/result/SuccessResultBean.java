package com.littletime.authentication.common.result;

/**
 * @author YiBuBuHuiTou
 * 成功调用接口的信息
 */
public class SuccessResultBean extends CommonResultBean {
    public SuccessResultBean() {
        this.status = CommonResultBean.SUCCESS;
        this.code = CommonResultBean.SUCCESS_CODE;
        this.ttl = CommonResultBean.DEFAULT_TTL;
        this.message = CommonResultBean.SUCCESS_MESSAGE;
    }
}
