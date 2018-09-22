package project.jinshang.mod_admin.mod_creditapplyrecord.bean;

import io.swagger.annotations.ApiModelProperty;

/**
 * create : wyh
 * date : 2018/1/22
 */
public class AccountDetailQuery {

    @ApiModelProperty(notes = "账期")
    private String settlement;
    @ApiModelProperty(notes = "结算账单号")
    private String billno;

    @ApiModelProperty(notes = "会员id")
    private long memberid;

    @ApiModelProperty(notes = "会员姓名")
    private String membername;

    @ApiModelProperty(notes = "公司名称")
    private String companyname;

    @ApiModelProperty(notes = "状态")
    private Short state;

    @ApiModelProperty(notes = "")
    private String clerkname;


    public String getSettlement() {
        return settlement;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public long getMemberid() {
        return memberid;
    }

    public void setMemberid(long memberid) {
        this.memberid = memberid;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public String getClerkname() {
        return clerkname;
    }

    public void setClerkname(String clerkname) {
        this.clerkname = clerkname;
    }
}
