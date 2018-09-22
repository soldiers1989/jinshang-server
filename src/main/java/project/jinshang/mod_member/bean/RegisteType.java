package project.jinshang.mod_member.bean;

/**
 *注册来源
 * @author xiazy
 * @date  2018/6/27 10:22 
 */
public enum RegisteType {
    TYPE01(1,"PC端"),
    TYPE02(2,"微信端"),
    TYPE03(3,"IOS"),
    TYPE04(4,"安卓"),
    TYPE05(5,"活动");
    private Short type;
    private String typeName;

    RegisteType(int type, String typeName) {
        this.type = (short)type;
        this.typeName = typeName;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
