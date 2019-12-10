package com.hejz.springbootrabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生产者
 */
@Component
public class XdelaySender {
    private final static Logger logger = LoggerFactory.getLogger(XdelaySender.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String msg, int delayTime) {
        logger.info("msg= " + msg + ".delayTime" + delayTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.rabbitTemplate.convertAndSend(XdelayConfig.DELAYED_EXCHANGE_XDELAY, XdelayConfig.DELAY_ROUTING_KEY_XDELAY, msg, message -> {
            message.getMessageProperties().setDelay(delayTime);
            System.out.println(sdf.format(new Date()) + " Delay sent.");
            return message;
        });
    }
}