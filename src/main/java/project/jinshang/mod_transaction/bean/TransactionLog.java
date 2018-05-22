package project.jinshang.mod_transaction.bean;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionLog {
    private Integer id;

    private String tranno;

    private String orderno;

    private String rejectedno;

    private Short trantype;

    private BigDecimal trannum;

    private Short transtate;

    private String goodsname;

    private Date trantime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTranno() {
        return tranno;
    }

    public void setTranno(String tranno) {
        this.tranno = tranno == null ? null : tranno.trim();
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public String getRejectedno() {
        return rejectedno;
    }

    public void setRejectedno(String rejectedno) {
        this.rejectedno = rejectedno == null ? null : rejectedno.trim();
    }

    public Short getTrantype() {
        return trantype;
    }

    public void setTrantype(Short trantype) {
        this.trantype = trantype;
    }

    public BigDecimal getTrannum() {
        return trannum;
    }

    public void setTrannum(BigDecimal trannum) {
        this.trannum = trannum;
    }

    public Short getTranstate() {
        return transtate;
    }

    public void setTranstate(Short transtate) {
        this.transtate = transtate;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname == null ? null : goodsname.trim();
    }

    public Date getTrantime() {
        return trantime;
    }

    public void setTrantime(Date trantime) {
        this.trantime = trantime;
    }
}