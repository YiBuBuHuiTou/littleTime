package com.littletime.portal.controller;

import com.cxd.littletime.common.Bean.CommonResultBean;
import com.littletime.portal.config.shiro.CustomAuthenticationBean;
import com.littletime.portal.model.result.PortalResultBean;
import com.littletime.portal.model.vo.UserLoginVo;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestBody;
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

    @ApiOperation(value = "用户登录API", notes = "用户登录", response = PortalResultBean.class)
    @RequestMapping(value = "/user/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public PortalResultBean login(@RequestBody @ApiParam(name = "UserLoginVo",value="UserLoginVo") UserLoginVo loginVo) {
        CustomAuthenticationBean customAuthenticationBean = new CustomAuthenticationBean();
        customAuthenticationBean.setLoginType(1);
        customAuthenticationBean.setUsername("111");
        SecurityUtils.getSubject().login(customAuthenticationBean);
        PortalResultBean portalResultBean = new PortalResultBean();
        portalResultBean.setCode(200);
        portalResultBean.setMessage("123123");
        return portalResultBean;
    }
}
