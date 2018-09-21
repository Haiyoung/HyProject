package com.haiyoung.ProxyTest;

public class CglibProxyTest {
    public static void main(String[] args){
        Subject subject = (Subject) new CglibProxy().bind(new RealSubject());
        subject.hello();
        subject.hello("CglibProxy");
    }
}
/*
before... this is cglib proxy
hello world
after... this is cglib proxy
before... this is cglib proxy
hello CglibProxy
after... this is cglib proxy*/
