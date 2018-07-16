package com.haiyoung.dynamicProxy;

/**
 * Created by Haiyoung on 2018/7/16.
 */
public class StaticProxy implements Subject{

    private Subject subject;

    public StaticProxy(Subject subject){
        this.subject = subject;
    }

    @Override
    public void hello() {

    }

    @Override
    public void haha(String arg) {
        System.out.println("before ... ... ...");
        subject.haha(arg);
        System.out.println("after ... ... ...");
    }
}
