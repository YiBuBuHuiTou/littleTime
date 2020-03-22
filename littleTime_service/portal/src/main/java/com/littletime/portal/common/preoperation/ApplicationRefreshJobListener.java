package com.littletime.portal.common.preoperation;

import com.littletime.portal.service.ApplicationCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Author YiBuBuHuiTou
 * @Description
 * @Date 2020/3/22 13:48
 * @Version 1.0
 **/
@Component
public class ApplicationRefreshJobListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ApplicationCommonService applicationCommonService;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        applicationCommonService.preInsertDB();
    }
}
