package com.haiyoung.ProxyTest;

/**
 * Created by Haiyoung on 2018/7/22.
 */
public class ProxyTest {
    public static void main(String[] args){
        Subject realSubject = new RealSubject();
        Proxy staticProxy = new Proxy(realSubject);
        staticProxy.hello();
        staticProxy.hello("Haiyoung");
    }
}
/*before ... ... ...
hello world
after ... ... ...
before ... ... ...
hello Haiyoung
after ... ... ...*/
