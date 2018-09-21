package com.haiyoung;

/**
 * Created by Haiyoung on 2018/9/12.
 */
public class Singleton {

    private static volatile Singleton singleton;

    private Singleton(){}

    public static Singleton getInstace(){
        if(singleton == null){
            synchronized (Singleton.class){
                if(singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
