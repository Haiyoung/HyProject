import java.util.concurrent.*;

/**
 * Created by Haiyoung on 2017/11/2.
 */
public class MutiThreadTest001 {

    public static Integer Fibonacci(int n){
        if(n < 2){
            return n;
        }
        return Fibonacci(n-1)+Fibonacci(n-2);
    }

    public static void RunnableDemo(){
        new Thread(new Runnable() {
            public void run() {
                System.out.println("Runnable作为Thread的参数，没有返回值："+Fibonacci(20));
            }
        }).start();
    }

    static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void CallableDemo(){

        //Runnable作为参数,没有返回值
        Future<?> result = executorService.submit(new Runnable() {
            public void run() {
                Fibonacci(20);
            }
        });
        try {
            System.out.println("提交Runnable没有返回值："+result.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Callalble作为参数，有返回值
        Future<Integer> result2 = executorService.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                return Fibonacci(20);
            }
        });
        try {
            System.out.println("提交Runnable没有返回值："+result2.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //FutureTask
        FutureTask<Integer> futureTask = new FutureTask<Integer>(
                new Callable<Integer>() {
                    public Integer call() throws Exception {
                        return Fibonacci(20);
                    }
                }
        );
        executorService.submit(futureTask);
        try {
            System.out.println("TutureTask可以接受Callable 和 Runnable 为参数："+ futureTask.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args){
        RunnableDemo();
        CallableDemo();
    }
}
