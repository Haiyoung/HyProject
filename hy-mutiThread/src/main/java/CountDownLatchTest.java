import java.util.concurrent.CountDownLatch;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-02-23 21:25
 * @Version 1.0
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {

        final CountDownLatch countDownLatch = new CountDownLatch(100);

        for(int i = 0; i<100; i++){
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("aaaaa: "+System.currentTimeMillis());
                    countDownLatch.countDown();
                }
            }).start();

        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("---------------end---------------");
    }
}
