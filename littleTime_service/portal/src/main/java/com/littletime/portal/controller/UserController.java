package com.littletime.portal.controller;

import com.littletime.portal.config.shiro.CustomAuthenticationBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "UserController", tags = "用户相关操作")
public class UserController {


    @ApiOperation(value = "用户登录API", notes = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userName", value = "用户名/手机号邮箱"),
            @ApiImplicitParam(name="password", value = "密码"),
            @ApiImplicitParam(name="phoneNumber", value = "手机号"),
            @ApiImplicitParam(name="loginType", value = "登录类型")
    })
    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    public String login() {
        CustomAuthenticationBean customAuthenticationBean = new CustomAuthenticationBean();
        customAuthenticationBean.setLoginType(1);
        customAuthenticationBean.setUsername("111");
        SecurityUtils.getSubject().login(customAuthenticationBean);
        return "login success";
    }
}
