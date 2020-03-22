package com.littletime.portal.controller;

import com.littletime.portal.config.shiro.CustomAuthenticationBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author YiBuBuHuiTou
 * @Description
 * @Date 2020/3/21 18:39
 * @Version 1.0
 **/
@RestController
public class UserController {


    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    public String login() {
        CustomAuthenticationBean customAuthenticationBean = new CustomAuthenticationBean();
        customAuthenticationBean.setLoginType(1);
        customAuthenticationBean.setUsername("111");
        SecurityUtils.getSubject().login(customAuthenticationBean);
        return null;
    }
}
