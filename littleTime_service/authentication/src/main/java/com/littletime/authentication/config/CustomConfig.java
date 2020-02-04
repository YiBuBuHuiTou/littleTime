package com.littletime.authentication.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author YiBuBuHuiTou
 * 自定义配置类
 */

@Component
@PropertySource(value = {"classpath:custom_config.properties"})
@ConfigurationProperties(prefix = "custom")
public class CustomConfig {

    /**
     * authentication 功能配置
     */
    private Map<String, Boolean> sysConfig;

    /**
     * ip 白名单列表获取
     */
    private List<String> whiteList;

    /**
     *ip 黑名单列表获取
     */
    private List<String> blackList;


    public Map<String, Boolean> getSysConfig() {
        return sysConfig;
    }

    public void setSysConfig(Map<String, Boolean> sysConfig) {
        this.sysConfig = sysConfig;
    }

    public List<String> getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(List<String> whiteList) {
        this.whiteList = whiteList;
    }

    public List<String> getBlackList() {
        return blackList;
    }

    public void setBlackList(List<String> blackList) {
        this.blackList = blackList;
    }
}
