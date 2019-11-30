package com.littletime.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.webservices.WebServicesAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author YiBuBuHuiTou
 */
// 暂不启用web service
@SpringBootApplication(exclude = WebServicesAutoConfiguration.class)
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
