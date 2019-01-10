package com.haiyoung.oyotest.Service;

import com.haiyoung.oyotest.biz.PO.Order;
import com.haiyoung.oyotest.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderService {

    @Resource
    private OrderMapper orderMapper;

    public int intertOne(Order order){
        return orderMapper.insert(order);
    }
}
