package com.haiyoung.oyotest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
public class OyotestApplication {

    public static void main(String[] args) {
        SpringApplication.run(OyotestApplication.class, args);
    }

}

