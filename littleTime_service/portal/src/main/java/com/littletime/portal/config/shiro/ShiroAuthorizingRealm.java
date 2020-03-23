package com.littletime.portal.config.shiro;

import com.littletime.portal.model.UserInfo;
import com.littletime.portal.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

/**
 * @Author YiBuBuHuiTou
 * @Description
 * @Date 2020/3/21 0:01
 * @Version 1.0
 **/
public class ShiroAuthorizingRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(ShiroAuthorizingRealm.class);

    @Autowired
    private UserService userService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("===========权限配置=========");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo = (UserInfo)principalCollection.getPrimaryPrincipal();
        simpleAuthorizationInfo.setRoles(Collections.singleton(userInfo.getRole().getRoleName()));
        simpleAuthorizationInfo.setStringPermissions(Collections.singleton(userInfo.getPermission()));
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        CustomAuthenticationBean customAuthenticationBean = (CustomAuthenticationBean)authenticationToken;

        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("111");
        return new SimpleAuthenticationInfo(customAuthenticationBean.getPrincipal(), "password", getName());
    }
}
