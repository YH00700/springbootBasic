package com.ruanhang.springbootBasic.TestMQ;


import com.ruanhang.springbootBasic.config.RabbitmqConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 生产者测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProdcerTopicsMQTests {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void Producer_topics_springbootTest() {

        //使用rabbitTemplate发送消息
        String message = "send email message to user";
        /**
         * 参数：
         * 1、交换机名称
         * 2、routingKey
         * 3、消息内容
         */
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS_INFORM, "inform.email", message);

    }

}
