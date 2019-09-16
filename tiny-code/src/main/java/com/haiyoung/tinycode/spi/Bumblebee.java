package com.haiyoung.tinycode.spi;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-08-20 12:58
 * @Version 1.0
 */
public class Bumblebee implements Robot {

    @Override
    public void sayHello() {
        System.out.println("Hello, I am Bumblebee.");
    }
}
