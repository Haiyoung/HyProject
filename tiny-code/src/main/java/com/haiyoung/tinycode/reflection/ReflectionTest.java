package com.haiyoung.tinycode.reflection;

import java.lang.reflect.Method;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-09-17 14:41
 * @Version 1.0
 */

public class ReflectionTest {

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.haiyoung.tinycode.reflection.Animal");
        Object object = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("printAnimal", String.class);
        for (int i = 0; i < 16; i++) {
            method.invoke(object, Integer.toString(i));
        }
    }
}
