/**
 * 单例模式内部静态类
 *
 * @author xiazy
 * @create 2018-05-16 15:59
 **/
public class SingletonInstance3 {
    private SingletonInstance3() {
    }

    private static  class SingletonHolder{
        private static  SingletonInstance3 instance3=new SingletonInstance3();
    }

    public static final SingletonInstance3 getInstance(){
        return SingletonHolder.instance3;
    }

    public String Sing(){
        return "Beethoven";
    }
}
