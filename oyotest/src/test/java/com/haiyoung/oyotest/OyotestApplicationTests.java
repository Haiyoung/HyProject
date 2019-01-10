package com.haiyoung.oyotest;

import com.haiyoung.oyotest.Service.OrderService;
import com.haiyoung.oyotest.biz.PO.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OyotestApplicationTests {

    @Autowired
    private OrderService orderService;

    @Test
    public void contextLoads() {

        Order order_0 = new Order();
        order_0.setOrderId(0L);
        order_0.setUserId(111L);

        Order order_1 = new Order();
        order_1.setOrderId(1L);
        order_1.setUserId(111L);

        Order order_2 = new Order();
        order_2.setOrderId(2L);
        order_2.setUserId(111L);

        orderService.intertOne(order_0);
        orderService.intertOne(order_1);
        orderService.intertOne(order_2);


    }

}

