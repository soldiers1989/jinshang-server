/**
 * 枚举朝代
 *
 * @author xiazy
 * @create 2018-05-16 16:27
 **/
public enum Dynasty {
    QIN("秦",1),SUI("隋",2),TANG("唐",3),HAN("汉",4),SONG("宋",5),YUAN("元",6),MING("明",7),QING("清",8);
    private String name;
    private int index;

    private Dynasty(String name, int index) {
        this.name = name;
        this.index = index;
    }

    @Override
    public String toString() {
        return this.index+":"+this.name;
    }

    public static String getName(int index){
        for (Dynasty dynasty:Dynasty.values()) {
            if (dynasty.getIndex()==index){
                return dynasty.getName();
            }
        }
        return null;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
