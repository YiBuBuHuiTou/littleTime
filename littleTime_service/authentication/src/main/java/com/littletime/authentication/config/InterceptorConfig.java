package com.littletime.authentication.config;

import com.littletime.authentication.interceptor.CommonInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YiBuBuHuiTou
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    /**
     * 自定义拦截规则
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 设置拦截器过滤list
        List<String> interceptorEnableList = new ArrayList<String>();
        // 设置跳过拦截器的list
        List<String> interceptorDisableList = new ArrayList<String>();
        interceptorEnableList.add("/*");
        interceptorDisableList.add("/test");
        // 注册拦截器规则
        registry.addInterceptor(new CommonInterceptor()).addPathPatterns(interceptorEnableList).excludePathPatterns(interceptorDisableList);
    }
}
