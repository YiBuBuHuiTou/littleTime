package com.littletime.portal.config.shiro;

import com.littletime.portal.config.http.HttpResult;
import com.littletime.portal.service.HttpService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author YiBuBuHuiTou
 * @Description
 * @Date 2020/3/23 21:06
 * @Version 1.0
 **/
public class CustomCredentialMacher extends SimpleCredentialsMatcher {

    @Autowired
    private HttpService httpService;

    @Value("${http.authentication_base_url}")
    private String baseUrl;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        Map<String,String> map = new HashMap<>();
        map.put("userName", info.getPrincipals().getPrimaryPrincipal().toString());
        map.put("password", info.getCredentials().toString());
        HttpResult httpResult = null;
        try {
            httpResult = httpService.doPost(baseUrl+"/authentication/signIn", map);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return httpResult.getCode() == 200;
    }
}
