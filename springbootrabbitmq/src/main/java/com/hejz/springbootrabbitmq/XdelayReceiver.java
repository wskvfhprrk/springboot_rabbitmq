package com.hejz.springbootrabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 消费者
 */
@Component
@EnableRabbit
@Configuration
public class XdelayReceiver {
    private final static Logger logger = LoggerFactory.getLogger(XdelayReceiver.class);

    @RabbitListener(queues = com.hejz.springbootrabbitmq.XdelayConfig.IMMEDIATE_QUEUE_XDELAY)
    public void get(String msg) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("收到延时消息时间：" + sdf.format(new Date()) + " Delay sent.");
        logger.info("收到延时消息:" + msg);
    }
}