package com.littletime.authentication.controller;

import com.littletime.authentication.config.CustomConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

/**
 * @author YiBuBuHuiTou
 */
@Controller
public class UserManagerController {
    @Autowired
    CustomConfig custom;

    @ResponseBody
    @RequestMapping(value="/hello")
    public String resiter() {
        System.out.println("================" + custom.getTest());
        System.out.println("================" + custom.getSysConfig().get("encryptEnable"));

        return "success";
    }

}
