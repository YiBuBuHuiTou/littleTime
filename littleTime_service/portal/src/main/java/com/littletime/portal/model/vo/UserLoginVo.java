package com.littletime.portal.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author YiBuBuHuiTou
 * @Description
 * @Date 2020/3/25 22:23
 * @Version 1.0
 **/
@ApiModel(value = "用户登录", description = "用户登录参数")
public class UserLoginVo {

    @ApiModelProperty(name = "loginType", value = "登录类型 0：账号密码， 1：电话邮箱", position = 0)
    private int loginType;

    @ApiModelProperty(name = "userName",value = "账号/手机/邮箱", position = 1)
    private String userName;

    @ApiModelProperty(name = "password",value = "密码", position = 2)
    private String password;

    @ApiModelProperty(name = "phoneNumber",value = "手机号",position = 3)
    private String phoneNumber;

    @ApiModelProperty(name = "sms",value = "短信验证码", position = 4)
    private String sms;

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }
}
