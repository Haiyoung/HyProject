package com.haiyoung.dynamicProxy;

/**
 * Created by Haiyoung on 2018/7/16.
 */
public class StProxyTest {

    public static void main(String[] args){
        SubjectImpl sub = new SubjectImpl();
        StaticProxy stp = new StaticProxy(sub);

        stp.haha("gaga");
    }
}
