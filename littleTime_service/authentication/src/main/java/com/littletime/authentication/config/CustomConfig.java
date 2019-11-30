package com.littletime.authentication.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author YiBuBuHuiTou
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
     * authencation 其他配置
     */
    private Map<String, Boolean> otherConfig;

    private String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public Map<String, Boolean> getSysConfig() {
        return sysConfig;
    }

    public void setSysConfig(Map<String, Boolean> sysConfig) {
        this.sysConfig = sysConfig;
    }

    public Map<String, Boolean> getOtherConfig() {
        return otherConfig;
    }

    public void setOtherConfig(Map<String, Boolean> otherConfig) {
        this.otherConfig = otherConfig;
    }
}
