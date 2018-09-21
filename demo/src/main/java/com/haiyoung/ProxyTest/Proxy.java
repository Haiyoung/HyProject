package com.haiyoung.ProxyTest;

/**
 * Created by Haiyoung on 2018/7/16.
 */
public class Proxy extends RealSubject implements Subject {

    private Subject subject;

    public Proxy(Subject subject){
        this.subject = subject;
    }

    @Override
    public void hello() {
        System.out.println("before ... ... ...");
        subject.hello();
        System.out.println("after ... ... ...");
    }

    @Override
    public void hello(String arg) {
        System.out.println("before ... ... ...");
        subject.hello(arg);
        System.out.println("after ... ... ...");
    }
}
