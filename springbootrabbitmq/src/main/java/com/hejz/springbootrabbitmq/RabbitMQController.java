package com.hejz.springbootrabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQController {
    @Autowired
    XdelaySender xdelaySender;
    @RequestMapping("/testRabbit")
    public void testRabbit() {
        xdelaySender.send("我来发一个测试消息,10秒", 10000);//10秒
        xdelaySender.send("我来发一个测试消息，2秒", 2000);//2秒
        xdelaySender.send("我来发一个测试消息，1秒", 2000);//1秒
    }
}