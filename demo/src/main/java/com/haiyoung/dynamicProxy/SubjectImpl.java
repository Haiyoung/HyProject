package com.haiyoung.dynamicProxy;

public class SubjectImpl implements Subject{
    @Override
    public void hello() {
        System.out.println("hello ... ... ...");
    }

    @Override
    public void haha(String arg) {
        System.out.println("haha ... ... ..."+arg);

    }
}
