package com.haiyoung.tinycode.listener;

import com.haiyoung.tinycode.event.MqStringEvent;
import com.haiyoung.tinycode.event.MqStringEvent2;
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

    @EventListener(value = {MqStringEvent2.class,  MqStringEvent.class}, condition = " 'STRING_EVENT'.equals(#event.tag) || 'STRING_EVENT2'.equals(#event.tag)")
    public void stringEventListener(Object event){
        System.out.println(event);
    }

}
