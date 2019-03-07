package threadLocal;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-03-07 11:50
 * @Version 1.0
 */
public class ThreadLocalTest {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    /**
     * 获取线程变量
     * @return
     */
    public static String get(){
        return threadLocal.get();
    }

    /**
     * 设置线程变量
     * @param str
     */
    public static void set(String str){
        threadLocal.set(str);
    }

    /**
     * 清除线程变量
     */
    public static void clean(){
        threadLocal.remove();
    }

}
