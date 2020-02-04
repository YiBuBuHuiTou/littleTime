package com.littletime.authentication.config;

import com.littletime.authentication.interceptor.CommonInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author YiBuBuHuiTou
 * 拦截器配置类
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
     *
     * 默认国际化语言 简体中文
     */
    @Bean
    public LocaleResolver  localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.CHINA);
        return sessionLocaleResolver;
    }

    /**
     * 根据 请求的lang参数设置国际化语言
     * @return
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
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
        //添加国际化拦截器
        registry.addInterceptor(localeChangeInterceptor());
    }
}
