package project.jinshang.mod_product.bean;

import java.math.BigDecimal;
import java.util.Date;

public class SellerBillRecordQueryParam {

    private String diliveryno;

    private Long sellerid;

    private String sellername;

    private Date startTime;

    private Date endTime;

    private String billno;

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public String getDiliveryno() {
        return diliveryno;
    }

    public void setDiliveryno(String diliveryno) {
        this.diliveryno = diliveryno;
    }

    public Long getSellerid() {
        return sellerid;
    }

    public void setSellerid(Long sellerid) {
        this.sellerid = sellerid;
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
