package com.littletime.authentication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

/**
 * @author YiBuBuHuiTou
 */
@Controller
public class UserManagerController {

    @ResponseBody
    @RequestMapping(value="/hello")
    public String resiter() {
        return "success";
    }

}
