package com.haiyoung.hyweb.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TimerTest {

    @Scheduled(cron = "0/10 * * * * *")
    public void timerTest(){
        System.out.println(System.currentTimeMillis());
    }
}
