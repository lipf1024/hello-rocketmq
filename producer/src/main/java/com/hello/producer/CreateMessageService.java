package com.hello.producer;


import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CreateMessageService implements CommandLineRunner {

    @Autowired
    private RocketMQTemplate template;

    @Override
    public void run(String... args) throws Exception {
        while (true){

            final Integer hashKey=new Random().nextInt(20);
            final Message<String> message= MessageBuilder.withPayload("hello world,current hashKey "+hashKey+",current time "+System.currentTimeMillis()).build();
            template.syncSendOrderly("hello-topic",message,hashKey.toString());

            Thread.sleep(100);
        }
    }
}
