package com.littletime.authentication.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YiBuBuHuiTou
 */
@RestController
public class UserManagerController {
    @RequestMapping(value="/hello")
    public String hello() {
        return "success";
    }

    @RequestMapping(value="/test")
    public String test() {
        Logger logger = LoggerFactory.getLogger(UserManagerController.class);
        logger.info("123");
        return "test";
    }
}
