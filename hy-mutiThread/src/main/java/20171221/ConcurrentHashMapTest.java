import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {
    /*
    * 测试同步容器
    * */
    public static final Map<Integer,Integer> map = new HashMap<Integer, Integer>();

    public static final ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<Integer, Integer>();

    public static void main(String[] args) throws InterruptedException {

        Thread thread01 = new Thread(){

            @Override
            public void run() {
                for(int i = 0;i<2500;i++){
//                    map.put(i, i);
                    concurrentHashMap.put(i, i);
                }
            }
        };

        Thread thread02 = new Thread(){

            @Override
            public void run() {
                for(int  j= 2500;j<5000;j++){
//                    map.put(j, j);
                    concurrentHashMap.put(j, j);
                }
            }
        };

        thread01.start();
        thread02.start();

        Thread.currentThread().sleep(1000);

        for(int i=0;i<5000;i++){
            if(i != concurrentHashMap.get(i)){//使用同步容器，不会报空指针异常，反之，则会有时出现异常
//                System.out.println(i+" : "+map.get(i));
                System.out.println(i+" : "+concurrentHashMap.get(i));
            }
        }
    }
}
