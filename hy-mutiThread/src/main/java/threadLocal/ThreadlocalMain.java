package threadLocal;

import java.util.Random;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-03-07 11:57
 * @Version 1.0
 */
public class ThreadlocalMain {

    private static Integer j = 0;

    public static void main(String[] args) throws Exception{

        for(int i = 0; i<3; i++){
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ThreadLocalTest.set("string_"+new Random().nextFloat());
                    System.out.println(ThreadLocalTest.get());
                }
            }).start();
        }

        System.out.println("-----------");
    }
}
