package project.jinshang.mod_purchase.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class PurchaseQueryParam {

    @ApiModelProperty(notes = "供应商编号")
    private Long supplyid;
    @ApiModelProperty(notes = "供应商")
    private String supplier;
    @ApiModelProperty(notes = "账期开始时间")
    private Date billstart;
    @ApiModelProperty(notes = "账期结束时间")
    private Date billend;
    @ApiModelProperty(notes = "发票号")
    private String billno;
    @ApiModelProperty(notes = "快递单号")
    private String expressno;
    @ApiModelProperty(notes = "用户id")
    private Long memberid;

    private int pageNo;

    private int pageSize;

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Long getSupplyid() {
        return supplyid;
    }

    public void setSupplyid(Long supplyid) {
        this.supplyid = supplyid;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Date getBillstart() {
        return billstart;
    }

    public void setBillstart(Date billstart) {
        this.billstart = billstart;
    }

    public Date getBillend() {
        return billend;
    }

    public void setBillend(Date billend) {
        this.billend = billend;
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public String getExpressno() {
        return expressno;
    }

    public void setExpressno(String expressno) {
        this.expressno = expressno;
    }
}
