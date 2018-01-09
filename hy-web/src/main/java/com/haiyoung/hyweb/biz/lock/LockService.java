package com.haiyoung.hyweb.biz.lock;

import com.haiyoung.hyweb.biz.file.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;

@Service
public class LockService {
    private static final Logger log = LoggerFactory.getLogger(LockService.class);


    private String getCurrentThread(){
        String jvm = ManagementFactory.getRuntimeMXBean().getName();
        String threadId = String.valueOf(Thread.currentThread().getId());
        return threadId + "@" + jvm;
    }
}
