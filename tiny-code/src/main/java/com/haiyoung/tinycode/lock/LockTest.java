package com.haiyoung.tinycode.lock;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock(false);
        try {
            lock.tryLock();

            Map<String, String> map=new HashMap<>();
            map.put("a", "aaa");
            map.put("b", "bbb");
            map.put("c", "ccc");
            map.put("d", "ddd");

            Iterator iterator = map.keySet().iterator();
            while (iterator.hasNext()){
                String key = (String) iterator.next();
                String value = map.get(key);
                System.out.println(key+"---"+value);
            }
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }
}
