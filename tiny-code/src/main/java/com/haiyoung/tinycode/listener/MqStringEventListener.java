package com.haiyoung.tinycode.listener;

import com.haiyoung.tinycode.event.MqStringEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-08-17 20:29
 * @Version 1.0
 */
@Slf4j
@Component
public class MqStringEventListener {

    @EventListener(value = MqStringEvent.class, condition = " 'STRING_EVENT'.equals(#event.tag) ")
    public void stringEventListener(MqStringEvent event){
        System.out.println(event);
    }

}
