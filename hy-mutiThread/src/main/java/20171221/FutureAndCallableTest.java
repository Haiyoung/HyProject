import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class FutureAndCallableTest {

    public static void futureTest() throws ExecutionException, InterruptedException {

        Future<String> future = Executors.newSingleThreadExecutor().submit(
                new Callable<String>() {
                    public String call() throws Exception {
                        return "Future 参数为Callabe!";
                    }
                }
        );
        System.out.println(future.get());
    }

    public static void runableTest(){
        Thread thread = new Thread(
                new Runnable() {
                    public void run() {
                        System.out.println("Runable 作为Thread的参数！");
                    }
                }
        );
        thread.start();
    }

    static Runnable runnable = new Runnable() {
        int i = 0;
        public void run() {
            while (i<20){
                System.out.println(i++);

            }
        }
    };

    public static void runableTest02(){
        Thread thread01 = new Thread(runnable);
        Thread thread02 = new Thread(runnable);
        thread01.start();
        thread02.start();
    }

    static Integer j = 0;
    static List<Integer> list = new ArrayList<Integer>(100000);
    static Set<Integer> set =  new HashSet<Integer>(100000);
    public static void threadTest(){

        Thread thread03 = new Thread(){
            @Override
            public void run() {
                synchronized (j){
                    while (j<=10000){
                        list.add(j++);
                        set.add(j);
//                    System.out.println(j++);
                    }
                }
            }
        };

        Thread thread04 = new Thread(){
            @Override
            public void run() {
                synchronized (j){
                    while (j<=10000){
                        list.add(j++);
                        set.add(j);
//                    System.out.println(j++);
                    }
                }
            }
        };

        thread03.start();
        thread04.start();
    }


    //主程序入口
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        futureTest();
//        runableTest();
//        runableTest02();
        threadTest();
        Thread.currentThread().sleep(3000);
        System.out.println(list.size());
        System.out.println(set.size());
    }
}
