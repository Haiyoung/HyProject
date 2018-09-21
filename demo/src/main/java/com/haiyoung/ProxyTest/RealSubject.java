package com.haiyoung.ProxyTest;

/**
 * Created by Haiyoung on 2018/7/22.
 */
public class RealSubject implements Subject{
    @Override
    public void hello() {
        System.out.println("hello world");
    }

    @Override
    public void hello(String arg) {
        System.out.println("hello "+arg);
    }
}
