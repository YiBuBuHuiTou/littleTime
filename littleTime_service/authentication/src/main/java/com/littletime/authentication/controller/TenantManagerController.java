package com.littletime.authentication.controller;

import com.littletime.authentication.common.rabbitmq.RabbitCommonSender;
import com.littletime.authentication.service.TenantBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author YiBuBuHuiTou
 * tenant controller
 */
@RestController
public class TenantManagerController {
    // request 请求
    private HttpServletRequest request;

    // rabbit mq 发送
    private RabbitCommonSender rabbitCommonSender;

    // 用户service
    private TenantBaseService tenantBaseService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TenantManagerController.class);

    @Autowired
    TenantManagerController(RabbitCommonSender rabbitCommonSender, TenantBaseService tenantBaseService,
                          HttpServletRequest request) {
        this.rabbitCommonSender = rabbitCommonSender;
        this.tenantBaseService = tenantBaseService;
        this.request = request;
    }

    /**
     * 添加租户
     * @param tenantInfo
     * @return
     */
    @RequestMapping(value = "/authentication/addTenant", method = RequestMethod.POST)
    public String addTenant(@RequestBody Map<String, String> tenantInfo) {

        return "";
    }

    /**
     * 更新租户信息
     * @param tenantInfo
     * @return
     */
    @RequestMapping(value = "/authentication/updateTenant", method = RequestMethod.POST)
    public String updateTenant(@RequestBody Map<String, String> tenantInfo) {

        return "";
    }

    @RequestMapping(value = "/authentication/deleteTenant", method = RequestMethod.POST)
    public String deleteTenant(@RequestBody String credential) {

        return "";
    }


}
