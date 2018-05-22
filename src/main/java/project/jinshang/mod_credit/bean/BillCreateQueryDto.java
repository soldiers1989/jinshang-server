package project.jinshang.mod_credit.bean;

import java.math.BigDecimal;
import java.util.Date;

public class BillCreateQueryDto {

    private Long buyerid;

    private String billno;


    private String settlementtime;

    private Short state;



    public Long getBuyerid() {
        return buyerid;
    }

    public void setBuyerid(Long buyerid) {
        this.buyerid = buyerid;
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }


    public String getSettlementtime() {
        return settlementtime;
    }

    public void setSettlementtime(String settlementtime) {
        this.settlementtime = settlementtime;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }
}