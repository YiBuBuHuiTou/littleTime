package com.littletime.authentication.common.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author YiBuBuHuiTou
 * rabbitmq 接收
 */
@Component
public class RabbitCommonReceiver {
    // rabbit template
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitCommonReceiver(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "direct_mail_send_queue")
    public void directMailSendHandler(String message) {
        System.out.println( "direct_mail_send_queue" + message);
    }
    @RabbitListener(queues = "direct_sms_send_queue")
    public void topicMailSendHandler(String message) {
        System.out.println( "direct_sms_send_queue" + message);

    }
    @RabbitListener(queues = "topic.auth.mail_send_queue")
    public void topicSMSSendHandler(String message) {
        System.out.println( "topic.auth.mail_send_queue" + message);

    }
    @RabbitListener(queues = "topic.auth.sms_send_queue")
    public void fanoutMailSendHandler(String message) {
        System.out.println("topic.auth.sms_send_queue" + message);

    }
    @RabbitListener(queues = "fanout.auth.mail_send_queue")
    public void fanoutSMSSendHandler(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException {
        channel.basicAck(deliveryTag, false);
        System.out.println("fanout.auth.mail_send_queue" + message);
    }
    @RabbitListener(queues = "fanout.auth.sms_send_queue")
    public void directSMSSendHandler(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) {
        System.out.println("fanout.auth.sms_send_queue" + message);

    }

}
