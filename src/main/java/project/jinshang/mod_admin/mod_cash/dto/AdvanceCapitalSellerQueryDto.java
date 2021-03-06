package project.jinshang.mod_admin.mod_cash.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * create : wyh
 * date : 2018/1/2
 */
public class AdvanceCapitalSellerQueryDto {


    @ApiModelProperty(notes = "用户id")
    private  Long memberid;

    @ApiModelProperty(notes = "会员名称")
    private  String realname;

    @ApiModelProperty(notes = "会员用户名")
    private  String username;

    @ApiModelProperty(notes = "商家公司名称")
    private  String companyname;

    @ApiModelProperty(notes = "店铺名称")
    private  String shopname;


    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
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

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }
}
