package com.haiyoung.hyweb.beanCopier;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.DebuggingClassWriter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-03-22 19:00
 * @Version 1.0
 */
public class CopyTest {

    public static void main(String[] args) {

        /**
         * 将cglib生成的代理类的class文件打印到指定目录
         */
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/haiyoung/logs");

        BeanA beanA = new BeanA();

        beanA.setName("aaa");

        List<ClassA> list = new ArrayList<>();
        list.add(new ClassA("001", "001"));

        beanA.setAList(list);

        BeanB beanB = new BeanB();

        BeanCopier beanCopier = BeanCopier.create(BeanA.class, BeanB.class,false);

        beanCopier.copy(beanA, beanB,null);

        System.out.println(beanB);

        List<ClassB> list1 = beanB.getAList();

        System.out.println(list1);

        ClassB classB = list1.get(0);

        System.out.println(classB);
    }

//    public static void main(String[] args) {
//
//        /**
//         * 将cglib生成的代理类的class文件打印到指定目录
//         */
//        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/haiyoung/logs");
//
//        BeanA1 beanA1 = new BeanA1();
//
//        beanA1.setName("aaa");
//
//        beanA1.setClassName(new ClassA("001", "001"));
//
//        BeanB1 beanB1 = new BeanB1();
//
//        BeanCopier beanCopier = BeanCopier.create(BeanA1.class, BeanB1.class,false);
//
//        beanCopier.copy(beanA1, beanB1,null);
//
//        System.out.println(beanB1);
//
//    }
}
