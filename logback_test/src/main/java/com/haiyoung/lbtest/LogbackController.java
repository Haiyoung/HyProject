package com.haiyoung.lbtest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-01-27 13:58
 * @Version 1.0
 */
@Slf4j
@RestController
public class LogbackController {

    @GetMapping("/ping")
    public String logString(){
        log.info("----------------info--------------------");
        log.error("----------------access--------------------");
        log.warn("----------------error--------------------");
        return "pong";
    }

}
