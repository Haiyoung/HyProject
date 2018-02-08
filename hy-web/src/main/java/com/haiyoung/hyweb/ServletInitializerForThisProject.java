package com.haiyoung.hyweb;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by Haiyoung on 2018/1/21.
 */
public class ServletInitializerForThisProject extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(HyWebApplication.class);
    }
}
