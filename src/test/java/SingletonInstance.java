/**
 * 单例模式
 *
 * @author xiazy
 * @create 2018-05-16 15:45
 **/
public class SingletonInstance {
    private static SingletonInstance singletonInstance=null;

    private SingletonInstance() {
    }

    public static synchronized SingletonInstance getInstance(){
        if (singletonInstance==null){
            return new SingletonInstance();
        }else{
            return singletonInstance;
        }
    }
    public String Sing(){
        return "MoZact";
    }
}
