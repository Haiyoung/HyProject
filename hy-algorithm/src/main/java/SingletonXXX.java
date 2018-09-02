/**
 * Created by Haiyoung on 2018/8/26.
 */
public class SingletonXXX {

    //volatile关键字保证 instance = new SingletonXXX(); 对应的指令不会重排序
    private volatile static SingletonXXX instance = null;

    private SingletonXXX(){}

    public SingletonXXX getInstance(){
        if(instance == null){
            synchronized(SingletonXXX.class){
                if(instance == null){
                    instance = new SingletonXXX();
                }
            }
        }
        return instance;
    }
}
