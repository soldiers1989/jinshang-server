package project.jinshang.common.utils;

import project.jinshang.common.constant.Quantity;
import project.jinshang.mod_admin.mod_statement.bean.BuyerStatement;

public enum PayType {
    TYPE01(0,"支付宝"),
    TYPE02(1,"微信"),
    TYPE03(2,"银联"),
    TYPE04(3,"余额"),
    TYPE05(4,"授信"),
    TYPE06(5,"线下平台");
    int tyep ;
    String typename;

    PayType(int tyep,String typename) {
        this.tyep = tyep;
        this.typename = typename;
    }


    public   static  String transfortype(int  type){
        for (PayType one:PayType.values()){
            if (type==one.getTyep()){
                return one.getTypename();
            }
        }
        return "";
    }


    public static  void transForCharge(BuyerStatement buyerStatement,short chargeperform){
        if(chargeperform== Quantity.STATE_0){
            buyerStatement.setPaytype((short) TYPE02.getTyep());
        }
        if (chargeperform==Quantity.STATE_1){
            buyerStatement.setPaytype((short) TYPE01.getTyep());
        }
        if (chargeperform==Quantity.STATE_2){
            buyerStatement.setPaytype((short) TYPE06.getTyep());
        }
        if (chargeperform==Quantity.STATE_4){
            buyerStatement.setPaytype((short) TYPE03.getTyep());
        }
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
