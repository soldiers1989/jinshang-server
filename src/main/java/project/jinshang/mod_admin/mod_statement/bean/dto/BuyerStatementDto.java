package project.jinshang.mod_admin.mod_statement.bean.dto;

import project.jinshang.mod_admin.mod_statement.bean.BuyerStatement;

/**
 * 下单客户对账单Dto
 *
 * @author xiazy
 * @create 2018-09-13 16:48
 **/
public class BuyerStatementDto extends BuyerStatement{
    private String companyname;
    private String realname;
    private String username;
    private String invoiceheadup;

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
