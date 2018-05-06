package com.haiyoung;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Haiyoung on 2018/5/6.
 */
@RestController
@RefreshScope
public class ConfigController {

    @Value("${test.name}")
    private String name;

    @Value("${test.city}")
    private String city;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String helloSpringCloudConfig(){

        return "Hello SpringCloudConfig, name is "+name+", city is "+ city +".";
    }
}
