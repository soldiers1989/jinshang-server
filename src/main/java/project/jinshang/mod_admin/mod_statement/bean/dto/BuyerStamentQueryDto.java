package project.jinshang.mod_admin.mod_statement.bean.dto;

import java.util.Date;

/**
 * 下单客户对账单查询
 *
 * @author xiazy
 * @create 2018-09-13 17:02
 **/
public class BuyerStamentQueryDto {
    private Date statementStartTime;
    private Date statementEndTime;
    private Long memberid;
    private String companyname;
    private String realname;
    private String username;
    private String invoiceheadup;

    public Date getStatementStartTime() {
        return statementStartTime;
    }

    public void setStatementStartTime(Date statementStartTime) {
        this.statementStartTime = statementStartTime;
    }

    public Date getStatementEndTime() {
        return statementEndTime;
    }

    public void setStatementEndTime(Date statementEndTime) {
        this.statementEndTime = statementEndTime;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getInvoiceheadup() {
        return invoiceheadup;
    }

    public void setInvoiceheadup(String invoiceheadup) {
        this.invoiceheadup = invoiceheadup;
    }
}
