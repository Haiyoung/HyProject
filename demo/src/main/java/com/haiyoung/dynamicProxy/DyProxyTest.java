package com.haiyoung.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class DyProxyTest {

    public static void main(String[] args){

        Subject subject = new SubjectImpl();

        InvocationHandler handler = new DynamicProxy(subject);

        Subject sub = (Subject) Proxy.newProxyInstance(subject.getClass().getClassLoader(),
                subject.getClass().getInterfaces(), handler);

        System.out.println(sub.getClass().getName());

        sub.hello();

        sub.haha(" gaga");
    }
}
