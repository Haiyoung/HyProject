package com.haiyoung.tinycode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author haiyoung
 */

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TinyCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TinyCodeApplication.class, args);
    }

}
