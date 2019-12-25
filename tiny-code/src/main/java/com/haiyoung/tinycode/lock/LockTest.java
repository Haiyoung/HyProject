package com.haiyoung.tinycode.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock(false);
        try {
            lock.tryLock();


        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }
}
