
package com.haiyoung;

/**
 * Created by Haiyoung on 2018/2/14.
 */
public class WaitNotify {

    private static final Object lock = new Object();

    private static int[] intArr = {1,2,3,4,5};

    public static void main(String[] args){

        th1.start();
        th2.start();
    }


    private static Thread th1 = new Thread(){
        @Override
        public void run() {
            synchronized (lock){
                System.out.println("wait: "+Thread.currentThread().getName());
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("wait结束！");
            }
        }
    };

    private static Thread th2 = new Thread(){
        @Override
        public void run() {
            synchronized (lock){
                System.out.println("notify: "+Thread.currentThread().getName());
                lock.notify();
                System.out.println("notify结束！");
            }
        }
    };


}
