package com.littletime.authentication.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author YiBuBuHuiTou
 * rabbitmq 配置类
 * 初始化rabbitmq相关 exchange queue等设置
 */
@Configuration
public class RabbitMQConfig {
    private static final String ROUTING_KEY_DIRECT_MAIL_SEND = "direct_mail_send";
    private static final String ROUTING_KEY_DIRECT_SMS_SEND = "direct_sms_send";
    private static final String ROUTING_KEY_TOPIC_MAIL_SEND = "topic.mail.#";
    private static final String ROUTING_KEY_TOPIC_SMS_SEND = "topic.sms.#";

    //rabbitmq 连接池
    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;

    //========================direct 队列================================
    /**
     * 邮件发送队列
     * direct
     * @return
     */
    @Bean(name = "directMailSendQueue")
    public Queue directMailSendQueue() {
        return new Queue("direct_mail_send_queue");
    }

    /**
     * 手机短信发送队列
     * direct
     * @return
     */
    @Bean(name = "directSMSSendQueue")
    public Queue directSMSSendQueue() {
        return new Queue("direct_sms_send_queue");
    }

    //========================topic 队列================================
    /**
     * 邮件发送队列
     * topic
     * @return
     */
    @Bean(name = "topicMailSendQueue")
    public Queue topicMailSendQueue() {
        return new Queue("topic.auth.mail_send_queue");
    }

    /**
     * 手机短信发送队列
     * topic
     * @return
     */
    @Bean(name = "topicSMSSendQueue")
    public Queue topicSMSSendQueue() {
        return new Queue("topic.auth.sms_send_queue");
    }

    //========================fanout 队列================================
    /**
     * 邮件发送队列
     * fanout
     * @return
     */
    @Bean(name = "fanoutMailSendQueue")
    public Queue fanoutMailSendQueue() {
        return new Queue("fanout.auth.mail_send_queue");
    }

    /**
     * 手机短信发送队列
     * fanout
     * @return
     */
    @Bean(name = "fanoutSMSSendQueue")
    public Queue fanoutSMSSendQueue() {
        return new Queue("fanout.auth.sms_send_queue");
    }

    //========================exchange================================

    /**
     * direct Exchange
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }

    /**
     * topic Exchange
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    /**
     * fanout Exchange
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    /**
     * 绑定 direct exchange和队列
     * @return
     */
    @Bean
    public Binding bindDirectMailQueueToExchange() {
        return BindingBuilder.bind(directMailSendQueue()).to(directExchange()).with(ROUTING_KEY_DIRECT_MAIL_SEND);
    }
    @Bean
    public Binding bindDirectSMSQueueToExchange() {
        return BindingBuilder.bind(directSMSSendQueue()).to(directExchange()).with(ROUTING_KEY_DIRECT_SMS_SEND);
    }

    /**
     * 绑定 topic exchange和队列
     * @return
     */
    @Bean
    public Binding bindTopicMailToExchange() {
        return BindingBuilder.bind(topicMailSendQueue()).to(topicExchange()).with(ROUTING_KEY_TOPIC_MAIL_SEND);
    }
    @Bean
    public Binding bindTopicSMSToExchange() {
        return BindingBuilder.bind(topicSMSSendQueue()).to(topicExchange()).with(ROUTING_KEY_TOPIC_SMS_SEND);
    }

    /**
     * 绑定 fanout exchange和队列
     * @return
     */
    @Bean
    public Binding bindFanoutMailToExchange() {
        return BindingBuilder.bind(fanoutMailSendQueue()).to(fanoutExchange());
    }
    @Bean
    public Binding bindFanoutSMSToExchange() {
        return BindingBuilder.bind(fanoutSMSSendQueue()).to(fanoutExchange());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        // 启用确认
        cachingConnectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        // 启用return 确认 (消息不可达)
        cachingConnectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        // 使用return call back 需要设置 mandatory=true
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String s) {
                //TODO
                if (ack) {
                    //消息发送成功
                    System.out.println("消息发送成功");
                } else {
                    //消息发送失败
                    System.out.println("消息发送失败");
                }
            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                //TODO
                //消息不可达
                System.out.println("消息不可达");
            }
        });
        return rabbitTemplate;
    }
}
