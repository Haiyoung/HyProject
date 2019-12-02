package com.haiyoung.tinycode.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-08-17 20:27
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MqStringEvent2 {

    private String topic;

    private String tag;

    private String key;

    private String msg;

    private String messageId;
}
