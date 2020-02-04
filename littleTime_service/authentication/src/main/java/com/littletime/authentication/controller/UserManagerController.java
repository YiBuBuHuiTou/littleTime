package com.littletime.authentication.controller;

import com.cxd.littletime.common.util.StringUtils;
import com.littletime.authentication.Bean.User;
import com.littletime.authentication.common.AuthenticationUtils;
import com.littletime.authentication.common.I18nUtils;
import com.littletime.authentication.common.rabbitmq.RabbitCommonSender;
import com.littletime.authentication.service.UserBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * @author YiBuBuHuiTou
 */
@RestController
public class UserManagerController {

    // request 请求
    private HttpServletRequest request;

    // rabbit mq 发送
    private RabbitCommonSender rabbitCommonSender;

    // 用户service
    private UserBaseService userBaseService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserManagerController.class);

    @Autowired
    UserManagerController(RabbitCommonSender rabbitCommonSender, UserBaseService userBaseService,
                          HttpServletRequest request) {
        this.rabbitCommonSender = rabbitCommonSender;
        this.userBaseService = userBaseService;
        this.request = request;
    }

    /**
     * 注册用户
     * @return
     */
    @RequestMapping(value = "/authentication/register", method = RequestMethod.POST)
    public String register(@RequestBody Map<String, String> body) throws IOException {

        String result = "";
        User newUser = AuthenticationUtils.buildSimpleUser(body);
        if(newUser == null || newUser.getUserName() == null) {
            result = "fail";
        } else {
            int tenantId = -1;
            String tenantIdStr = body.get("tenantId");
            if (tenantIdStr != null) {
                tenantIdStr = tenantIdStr.trim();
            }
            try {
                if (!StringUtils.isEmpty(tenantIdStr)) {
                    tenantId = Integer.parseInt(tenantIdStr);
                }
            } catch (NumberFormatException e) {
                LOGGER.error("tenantId is invalid. ", e);
            }
            User user =  userBaseService.addUser(newUser, tenantId);
           if (user != null) {
                result = "success";
           } else {
               result = "fail";
           }
        }
        return result;
    }

    @RequestMapping(value="/authentication/addUser", method = RequestMethod.POST)
    public String addUser() {
        return I18nUtils.getMessage("AUTHENTICATION_IP_IN_BLACK_LIST","aaa");
    }

    @RequestMapping(value="/test")
    public String test() {
        rabbitCommonSender.directSend();
        User user = userBaseService.findById(1L);
        System.out.println(user.getCredential());
        return "test";
    }

    @RequestMapping(value="/test1")
    public String test1() {
        return I18nUtils.getMessage("AUTHENTICATION_IP_NOT_IN_BLACK_LIST");
    }
}
