package com.haiyoung.distributedlock.lockSelfDef;

import java.io.IOException;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-02-12 20:58
 * @Version 1.0
 */
public class ZKLockTest {

//    public static void main(String[] args) {
//        System.out.println("xxxx");
//    }

    public static void main(String[] args) throws IOException, InterruptedException {

        LockInfo lockInfo1 = new LockInfo("/zk-lock", "person1");
        LockInfo lockInfo2 = new LockInfo("/zk-lock", "person2");
        LockInfo lockInfo3 = new LockInfo("/zk-lock", "person3");
        ZkLock zLock1 = new ZkLock(lockInfo1);
        ZkLock zLock2 = new ZkLock(lockInfo2);
        ZkLock zLock3 = new ZkLock(lockInfo3);

        new Thread(new Runnable() {

            @Override
            public void run() {
                zLock1.lock();
            }
        }).start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                zLock2.lock();
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                zLock3.lock();
            }
        }).start();

        //停3秒钟
        Thread.sleep(3000);

        new Thread(new Runnable() {

            @Override
            public void run() {
                zLock1.unlock();
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                zLock2.unlock();
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                zLock3.unlock();
            }
        }).start();

    }
}
