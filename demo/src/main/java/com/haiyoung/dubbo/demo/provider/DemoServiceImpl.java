package com.haiyoung.dubbo.demo.provider;

import com.haiyoung.dubbo.demo.DemoService;

public class DemoServiceImpl implements DemoService {
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
