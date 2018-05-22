package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel(description = "开票记录复合类")
public class BillRecordComplex {

    @ApiModelProperty(notes = "记录id")
    private Long id;
    @ApiModelProperty(notes = "会员编号")
    private Long memberId;
    @ApiModelProperty(notes = "会员名称")
    private String memberName;
    @ApiModelProperty(notes = "开票号")
    private String billNo;
    @ApiModelProperty(notes = "开票类型")
    private String billType;
    @ApiModelProperty(notes = "物流单号")
    private String expressNo;
    @ApiModelProperty(notes = "开票时间")
    private Date billTime;
    @ApiModelProperty(notes = "发票类型")
    private Short billingrecordtype;
    @ApiModelProperty(notes = "订单列表")
    private List<Orders> list;

    @ApiModelProperty(notes = "物流公司")
    private String expressCompany;


    @ApiModelProperty(notes = "开票地址")
    private String address;


    @ApiModelProperty(notes = "收票地址")
    private String receiveaddress;

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public Date getBillTime() {
        return billTime;
    }

    public void setBillTime(Date billTime) {
        this.billTime = billTime;
    }

    public List<Orders> getList() {
        return list;
    }

    public void setList(List<Orders> list) {
        this.list = list;
    }

    public Short getBillingrecordtype() {
        return billingrecordtype;
    }

    public void setBillingrecordtype(Short billingrecordtype) {
        this.billingrecordtype = billingrecordtype;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReceiveaddress() {
        return receiveaddress;
    }

    public void setReceiveaddress(String receiveaddress) {
        this.receiveaddress = receiveaddress;
    }
}
