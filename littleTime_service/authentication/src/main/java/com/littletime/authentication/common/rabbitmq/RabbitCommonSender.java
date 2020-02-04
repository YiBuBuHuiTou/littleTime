package com.littletime.authentication.common.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author YiBuBuHuiTou
 * 消息生产者类
 */
@Component
public class RabbitCommonSender {

    //注入rabbitTemplate
    private RabbitTemplate rabbitTemplate;

    /**
     * 构造方法注入
     * @param rabbitTemplate
     */
    @Autowired
    public RabbitCommonSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void directSend() {
        rabbitTemplate.convertAndSend("fanoutExchange",null,"test1234".getBytes());
    }


}
