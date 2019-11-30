package com.haiyoung.tinycode.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-11-30 21:09
 * @Version 1.0
 */
@Slf4j
@Component
public class SpringApplicationEventListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
//        if(event instanceof ApplicationEnvironmentPreparedEvent){
//            System.out.println("-----------------------ApplicationEnvironmentPreparedEvent-----------------------");
//        }
//        if(event instanceof ApplicationContextInitializedEvent){
//            System.out.println("-----------------------ApplicationContextInitializedEvent-----------------------");
//        }
//        if(event instanceof ApplicationFailedEvent){
//            System.out.println("-----------------------ApplicationFailedEvent-----------------------");
//        }
//        if(event instanceof ApplicationPreparedEvent){
//            System.out.println("-----------------------ApplicationPreparedEvent-----------------------");
//        }
//        if(event instanceof ApplicationStartingEvent){
//            System.out.println("-----------------------ApplicationStartingEvent-----------------------");
//        }
//        if(event instanceof ApplicationStartedEvent){
//            System.out.println("-----------------------ApplicationStartedEvent-----------------------");
//        }
//        if(event instanceof ApplicationReadyEvent){
//            System.out.println("-----------------------ApplicationReadyEvent-----------------------");
//        }
        System.out.println("--------------------"+event.getClass().getName()+"------------------");
    }
}
