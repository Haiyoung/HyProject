package com.haiyoung.ProxyTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class DyProxyTest {
    public static void main(String[] args){

        Subject subject = (Subject) new DynamicProxy().bind(new RealSubject());
        subject.hello();
        subject.hello("DynamicProxy");
    }
}
/*
before ... ... ...
Method:public abstract void com.haiyoung.ProxyTest.Subject.hello()
hello world
after ... ... ...
before ... ... ...
Method:public abstract void com.haiyoung.ProxyTest.Subject.hello(java.lang.String)
hello DynamicProxy
after ... ... ...*/
