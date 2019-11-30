package com.littletime.authentication.controller;

import com.littletime.authentication.config.CustomConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author YiBuBuHuiTou
 */
@Controller
public class UserManagerController {
    @ResponseBody
    @RequestMapping(value="/hello")
    public String hello() {
        return "success";
    }

    @ResponseBody
    @RequestMapping(value="/test")
    public String test() {
        return "test";
    }
}
