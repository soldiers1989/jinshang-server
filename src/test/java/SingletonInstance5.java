public class SingletonInstance5 {
    private SingletonInstance5() {
    }
    private volatile static SingletonInstance5 instance5;
    public static SingletonInstance5 getInstance5(){
        if(instance5==null){
            synchronized (SingletonInstance5.class){
                if (instance5==null) {
                    instance5=new SingletonInstance5();
                }
            }
        }
        return instance5;
    }


    public String Song(){
        return "Liszt";
    }

}



