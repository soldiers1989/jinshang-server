package project.jinshang.mod_cash.bean.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 买家对账单查询Dto
 * @author xiazy
 * @create 2018-05-14 14:58
 **/
public class BuyerCapitalAccountQueryDto {

    @ApiModelProperty(notes = "开始日期")
    private Date tradetimeStart;
    @ApiModelProperty(notes = "结束日期")
    private Date tradetimeEnd;
    @ApiModelProperty(notes = "会员编号")
    private Long memberid;
    @ApiModelProperty(notes = "公司名称")
    private String companyname;
    @ApiModelProperty(notes = "开票名称")
    private String invoicename;
    @ApiModelProperty(notes = "会员姓名")
    private String realname;
    @ApiModelProperty(notes = "手机号码")
    private String mobile;
    @ApiModelProperty(notes = "会员名称")
    private String username;
    @ApiModelProperty(notes = "订单表id")
    private Long orderid;

    public Date getTradetimeStart() {
        return tradetimeStart;
    }

    public void setTradetimeStart(Date tradetimeStart) {
        this.tradetimeStart = tradetimeStart;
    }

    public Date getTradetimeEnd() {
        return tradetimeEnd;
    }

    public void setTradetimeEnd(Date tradetimeEnd) {
        this.tradetimeEnd = tradetimeEnd;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getInvoicename() {
        return invoicename;
    }

    public void setInvoicename(String invoicename) {
        this.invoicename = invoicename;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }
}
