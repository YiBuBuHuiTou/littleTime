package com.littletime.authentication.config;

import com.littletime.authentication.interceptor.CommonInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YiBuBuHuiTou
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * 使用Bean注解将返回的拦截器实例注入spring，否则该拦截器中的自动注入Bean将不起作用
     * @return  l拦截器实例
     */
    @Bean
    public HandlerInterceptorAdapter getCommonInterceptor() {
        return new CommonInterceptor();
    }
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
        // 注册拦截器规则的同时注入spring ，使用上面的getCommonInterceptor方法获取拦截器实例
        registry.addInterceptor(getCommonInterceptor()).addPathPatterns(interceptorEnableList).excludePathPatterns(interceptorDisableList);
    }
}
