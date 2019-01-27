package com.haiyoung.lbtest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-01-27 13:48
 * @Version 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogbackTest {

    @Test
    public void logbackTest(){
        log.info("----------------info--------------------");
        log.error("----------------access--------------------");
        log.warn("----------------error--------------------");
    }
}
