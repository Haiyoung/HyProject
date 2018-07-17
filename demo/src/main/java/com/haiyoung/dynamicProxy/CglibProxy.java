package com.haiyoung.dynamicProxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Haiyoung on 2018/7/16.
 */
public class CglibProxy {

    private Object obj;

    public Object bind(final Object targey){

        this.obj = targey;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("this is cglib proxy");
                Object res = method.invoke(targey, objects);
                return res;
            }
        });
        return enhancer.create();
    }
}
