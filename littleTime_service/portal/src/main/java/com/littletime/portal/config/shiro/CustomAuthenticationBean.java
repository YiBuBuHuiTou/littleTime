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

    private String accountName;

    private String accountPassword;

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }
}
