package com.haiyoung.tinycode;

import com.haiyoung.tinycode.event.MqStringEvent;
import com.haiyoung.tinycode.event.MqStringEvent2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-08-17 20:33
 * @Version 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventListenerTest {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void publishMessageTest(){
        System.out.println("------begin-------");
        applicationContext.publishEvent(MqStringEvent
                .builder().topic("HY")
                .tag("STRING_EVENT")
                .key("key")
                .msg("msg")
                .messageId("messageId")
                .build());
        System.out.println("------end---------");
    }

    @Test
    public void publishMessageTest2(){
        System.out.println("------begin-------");
        applicationContext.publishEvent(MqStringEvent2
                .builder().topic("HY")
                .tag("STRING_EVENT2")
                .key("key")
                .msg("msg")
                .messageId("messageId")
                .build());
        System.out.println("------end---------");
    }
}
