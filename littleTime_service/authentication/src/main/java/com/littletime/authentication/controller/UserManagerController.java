package com.littletime.authentication.controller;

import com.cxd.littletime.common.util.LogUtils;
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
    @Autowired
    CustomConfig customConfig;
    private Logger debuglog = LogUtils.getDebugLogger();
    private Logger accessLog = LogUtils.getAccessLogger();
    @ResponseBody
    @RequestMapping(value="/hello")
    public String resiter() {
        System.out.println("================" + customConfig.getTest());
        System.out.println("================" + customConfig.getSysConfig().get("encryptEnable"));
        debuglog.debug("debug");
        accessLog.debug("access");
        return "success";
    }

}
