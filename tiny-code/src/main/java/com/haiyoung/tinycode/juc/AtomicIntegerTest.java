package com.haiyoung.tinycode.juc;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        CountDownLatch countDownLatch = new CountDownLatch(5);
    }
}
