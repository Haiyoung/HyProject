package com.haiyoung.hyweb;

import com.haiyoung.hyweb.RabbitMQ.RabbitmqSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitMQTest {

    @Autowired
    private RabbitmqSender rabbitmqSender;

    @Test
    public void hello() throws Exception {
        rabbitmqSender.send();
    }

}
