package com.littletime.authentication;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.webservices.WebServicesAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author YiBuBuHuiTou
 */
// 暂不启用web service
@SpringBootApplication(exclude = WebServicesAutoConfiguration.class)
//启用rabbitmq
@EnableRabbit
//开启缓存
@EnableCaching
// 扫描相应代码，找到相应的过滤器类，并注册过滤器
@ServletComponentScan(basePackages = "com.littletime.authentication.filter")
public class AuthenticationApplication extends SpringBootServletInitializer {

    /**  
     * 使用外部tomcat
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AuthenticationApplication.class);
    }

    /**
     * springboot启动器
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApplication.class, args);
    }

}
