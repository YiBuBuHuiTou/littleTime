package com.littletime.portal.config.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @Author YiBuBuHuiTou
 * @Description
 * @Date 2020/3/21 22:28
 * @Version 1.0
 **/
public class CustomAuthenticationBean extends UsernamePasswordToken {

    public CustomAuthenticationBean() {
        super();
    }
    private int loginType;


    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }
}
