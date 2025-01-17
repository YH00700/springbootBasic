package com.ruanhang.springbootBasic.mq;

import com.rabbitmq.client.Channel;
import com.ruanhang.springbootBasic.config.RabbitmqConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * mq监听类
 */
@Component
public class ReceiveHandler {

    /**
     * 监听email队列
     * @param msg
     * @param message
     * @param channel
     */
    @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_EMAIL})
    public void receive_email(Object msg, Message message, Channel channel) {
        System.out.println("QUEUE_INFORM_EMAIL msg" + msg);
    }

    /**
     * 监听sms队列
     * @param msg
     * @param message
     * @param channel
     */
    @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_SMS})
    public void receive_sms(Object msg, Message message, Channel channel) {
        System.out.println("QUEUE_INFORM_SMS msg" + msg);
    }

}
