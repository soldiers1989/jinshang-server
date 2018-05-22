package project.jinshang.mod_admin.mod_creditapplyrecord.bean;

/**
 * create : wyh
 * date : 2018/1/19
 */

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 生成中的账单
 */
public class CreatingAccount {
    @ApiModelProperty(notes = "账期")
    private String accountDate;

    @ApiModelProperty(notes = "结算期")
    private  String jiesuanDate;

    @ApiModelProperty(notes = "已使用授信用户")
    private  int userMember;

    @ApiModelProperty(notes = "已授出金额")
    private BigDecimal outMoney;

    public String getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(String accountDate) {
        this.accountDate = accountDate;
    }

    public String getJiesuanDate() {
        return jiesuanDate;
    }

    public void setJiesuanDate(String jiesuanDate) {
        this.jiesuanDate = jiesuanDate;
    }

    public int getUserMember() {
        return userMember;
    }

    public void setUserMember(int userMember) {
        this.userMember = userMember;
    }

    public BigDecimal getOutMoney() {
        return outMoney;
    }

    public void setOutMoney(BigDecimal outMoney) {
        this.outMoney = outMoney;
    }
}
