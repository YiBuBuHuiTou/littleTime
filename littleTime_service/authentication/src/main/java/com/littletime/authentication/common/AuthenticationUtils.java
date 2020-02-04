package com.littletime.authentication.common;

import com.cxd.littletime.common.util.IpUtils;
import com.cxd.littletime.common.util.StringUtils;
import com.littletime.authentication.Bean.Tenant;
import com.littletime.authentication.Bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author YiBuBuHuiTou
 */
public class AuthenticationUtils {

    // 日志
    private static Logger LOGGER = LoggerFactory.getLogger(AuthenticationUtils.class);
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

    /**
     * 创建简单User对象
     * @param body
     * @return
     */
    public static User buildSimpleUser(Map<String, String> body) {
        // 数据库主键id
        Long id = null;
        // 用户名
        String userName = null;
        // 身份id
        String credential = null;
        //密码
        String password = null;
        // 角色
        int role = -1;
        // 权限
        int privilege = -1;
        //token
        String token = null;

        String tmpId = body.get("id");
        String tmpUserName = body.get("userName");
        String tmpCredential = body.get("credential");
        String tmpPassword = body.get("password");
        String tmpRole = body.get("role");
        String tmpPrivilege = body.get("privilege");
        String tmpToken = body.get("tmpToken");
        if (tmpId != null) {
            tmpId = tmpId.trim();
        }
        if (tmpUserName != null) {
            tmpUserName = tmpUserName.trim();
        }
        if (tmpCredential != null) {
            tmpCredential = tmpCredential.trim();
        }
        if (tmpRole != null) {
            tmpRole = tmpRole.trim();
        }
        if (tmpPrivilege != null) {
            tmpPrivilege = tmpPrivilege.trim();
        }
        if (tmpToken != null) {
            tmpToken = tmpToken.trim();
        }




        try {
            if (!StringUtils.isEmpty(tmpId)) {
                id = Long.parseLong(tmpId);
            }
        } catch (NumberFormatException e) {
            LOGGER.error("id is invalid. id = " + tmpId,e);
            return null;
        }
        if (!StringUtils.isEmpty(tmpUserName)) {
            userName = tmpUserName;
        }

        if (!StringUtils.isEmpty(tmpCredential)) {
            credential = tmpCredential;
        }
        if (!StringUtils.isEmpty(tmpPassword)) {
            password = tmpPassword;
        }
        try {
            if (!StringUtils.isEmpty(tmpRole)) {
                role = Integer.parseInt(tmpRole);
            }
            if (!StringUtils.isEmpty(tmpPrivilege)) {
                privilege = Integer.parseInt(tmpPrivilege);
            }
        } catch (NumberFormatException e) {
            LOGGER.error("role or privilege is invalid. role = " + tmpRole + "privilege = " + tmpPrivilege, e);
            return null;
        }
        if (!StringUtils.isEmpty(tmpToken)) {
            token = tmpToken;
        }
        User user = new User();
        if (id != null) {
            user.setId(id);
        }
        if (userName != null) {
            user.setUserName(userName);
        }
        if (credential != null) {
            user.setCredential(credential);
        }
        if (password != null) {
            user.setPassword(password);
        }
        if (role < 0) {
            user.setRole(role);
        }
        if (privilege < 0) {
            user.setPrivilege(privilege);
        }
        if (!StringUtils.isEmpty(token)) {
            user.setToken(token);
        }
        return user;
    }
}
