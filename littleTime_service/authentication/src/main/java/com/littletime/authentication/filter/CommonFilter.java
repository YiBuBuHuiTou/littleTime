package com.littletime.authentication.filter;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author YiBuBuHuiTou
 * 共通过滤器
 * 用来记录请求相关信息 access_log
 */
@WebFilter(urlPatterns = {"/*"}, filterName = "commonFilter")
public class CommonFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("servlet statup.");
    }

    @Override
    public void destroy() {
        System.out.println("servlet shutdown.");
    }
}
