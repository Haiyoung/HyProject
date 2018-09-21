package com.haiyoung.ProxyTest;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy extends Enhancer {

    private Object obj;

    public Object bind(final Object targey){
        this.obj = targey;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("before... this is cglib proxy");
                Object res = method.invoke(targey, objects);
                System.out.println("after... this is cglib proxy");
                return res;
            }
        });
        return enhancer.create();
    }
}
