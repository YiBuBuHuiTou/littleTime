package com.littletime.authentication.common;

import com.cxd.littletime.common.util.IpUtils;
import com.cxd.littletime.common.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author YiBuBuHuiTou
 */
public class AuthenticationUtils {


    /**
     * 从http中获取用户ip
     * @return
     */
    public static String getIpFromHttp(HttpServletRequest request) {
        String forwarded  = request.getHeader("x-forwarded-for ");
        String ip = null;
        if (!StringUtils.isEmpty(forwarded)) {
            ip = forwarded.split(",")[0];
        }
        if (!IpUtils.isIP(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (!IpUtils.isIP(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (!IpUtils.isIP(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
