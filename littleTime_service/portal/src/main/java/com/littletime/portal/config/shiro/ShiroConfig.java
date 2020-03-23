package com.littletime.portal.config.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author YiBuBuHuiTou
 * @Description shiro config class
 * @Date 2020/3/20 23:51
 * @Version 1.0
 **/
@Configuration
public class ShiroConfig {

    private static  final Logger log = LoggerFactory.getLogger(ShiroConfig.class);


    /**
     * security
     * @return
     */
    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("shiroAuthorizingRealm") ShiroAuthorizingRealm shiroAuthorizingRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(shiroAuthorizingRealm);
        return defaultWebSecurityManager;
    }

    @Bean(name = "shiroAuthorizingRealm")
    public ShiroAuthorizingRealm shiroAuthorizingRealm(@Qualifier("credentialMatcher") CustomCredentialMacher credentialMacher) {
        ShiroAuthorizingRealm shiroAuthorizingRealm = new ShiroAuthorizingRealm();
        shiroAuthorizingRealm.setCredentialsMatcher(credentialMacher);
        return shiroAuthorizingRealm;
    }

    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/user/login","anon");
        filterMap.put("/user/logout","anon");
        filterMap.put("/user/*", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "credentialMatcher")
    public CustomCredentialMacher credentialMacher() {
        CustomCredentialMacher customCredentialMacher = new CustomCredentialMacher();
        return customCredentialMacher;
    }

}
