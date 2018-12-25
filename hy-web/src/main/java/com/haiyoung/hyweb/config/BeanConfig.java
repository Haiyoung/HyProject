package com.haiyoung.hyweb.config;

import com.haiyoung.hyweb.util.SnowFlakeUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Haiyang
 * @Date: 2018/12/25 下午7:08
 */
@Configuration
public class BeanConfig {

    @Bean
    public SnowFlakeUtil snowFlakeUtil(){
        //:todo eureka or zookeeper dispatch id
        return new SnowFlakeUtil(1,1);
    }

}
