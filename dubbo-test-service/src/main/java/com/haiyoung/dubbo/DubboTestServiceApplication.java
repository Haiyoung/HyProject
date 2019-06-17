package com.haiyoung.dubbo;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubboConfig
@EnableDubbo(scanBasePackages = "com.haiyoung.dubbo.apiImpl")
@SpringBootApplication
public class DubboTestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboTestServiceApplication.class, args);
    }

}
