package com.haiyoung.dynamicProxy;

/**
 * Created by Haiyoung on 2018/7/16.
 */
public class CglibProxyTest {

    public static void main(String[] args){

        SubjectImpl subject = new SubjectImpl();
        CglibProxy cglibProxy = new CglibProxy();
        SubjectImpl proxy = (SubjectImpl) cglibProxy.bind(subject);
        proxy.hello();
    }
}
