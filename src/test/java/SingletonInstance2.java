/**
 * 单例模式饿汉
 *
 * @author xiazy
 * @create 2018-05-16 15:51
 **/
public class SingletonInstance2 {
//    private static SingletonInstance2 instance2=new SingletonInstance2();

    private static SingletonInstance2 instance2=null;
    static {
        instance2=new SingletonInstance2();
    }
    private SingletonInstance2() {
    }

    public static SingletonInstance2 getInstance2() {
        return instance2;
    }

}
