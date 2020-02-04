package com.littletime.authentication.filter;


import com.cxd.littletime.common.util.JWTUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        String jwt = request.getHeader("Authorization");
        boolean valid = JWTUtils.validateJWT(jwt);
        if (valid) {
            //TODO 可以刷新token 校验其他数据
            System.out.println("token 校验成功");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            //TODO token 无效时处理【暂不处理】
            System.out.println("token 校验失败");
            filterChain.doFilter(servletRequest, servletResponse);
        }
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
