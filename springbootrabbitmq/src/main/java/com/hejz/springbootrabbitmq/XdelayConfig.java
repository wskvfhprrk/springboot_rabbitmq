package com.hejz.springbootrabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class XdelayConfig {
 
    /**
     * 立即消费的队列名称
     */
    public static final String IMMEDIATE_QUEUE_XDELAY = "queue.xdelay.immediate";
    /**
     * 延时的exchange
     */
    public static final String DELAYED_EXCHANGE_XDELAY = "exchange.xdelay.delayed";
    public static final String DELAY_ROUTING_KEY_XDELAY = "routingkey.xdelay.delay";

    /**
     * 创建一个立即消费队列
     *
     * @return
     */
    @Bean
    public Queue immediateQueue() {
        // 第一个参数是创建的queue的名字，第二个参数是是否支持持久化
        return new Queue(IMMEDIATE_QUEUE_XDELAY, true);
    }

    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(DELAYED_EXCHANGE_XDELAY, "x-delayed-message", true, false, args);
    }

    /**
     * 把立即消费的队列和延时消费的exchange绑定在一起
     *
     * @return
     */
    @Bean
    public Binding bindingNotify() {
        return BindingBuilder.bind(immediateQueue()).to(delayExchange()).with(DELAY_ROUTING_KEY_XDELAY).noargs();
    }
}