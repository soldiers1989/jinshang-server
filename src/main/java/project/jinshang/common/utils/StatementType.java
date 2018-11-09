package project.jinshang.common.utils;

/**
 * 对账单类型枚举
 *
 * @author xiazy
 * @create 2018-09-21 15:10
 **/
public enum  StatementType {
    StType1(1,"收款"),
    StType2(2,"发货"),
    StType3(3,"退货"),
    StType4(4,"违约金"),
    StType5(5,"退款"),
    StType6(6,"充值"),
    StType7(7,"提现"),
    StType8(8,"开票");

    int tyep ;
    String typename;

    StatementType(int tyep, String typename) {
        this.tyep = tyep;
        this.typename = typename;
    }

    public   static  String transfortype(int  type){
        for (StatementType one:StatementType.values()){
            if (type==one.getTyep()){
                return one.getTypename();
            }
        }
        return "";
    }

    public int getTyep() {
        return tyep;
    }

    public void setTyep(int tyep) {
        this.tyep = tyep;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }
}
