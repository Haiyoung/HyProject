package com.haiyoung.hyweb.RabbitMQ;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RabbitmqSender {

//    @Autowired
//    private AmqpTemplate amqpTemplate;

    public void send(){
/*        for(int i=0; i<100000;i++){
            String context = "hello "+ new Date();
            System.out.println("AmqpSender: "+context);
            this.amqpTemplate.convertAndSend("hello", context);
        }*/
    }
}
