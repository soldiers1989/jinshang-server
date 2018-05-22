package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class ProductEvaModel {

    @ApiModelProperty(notes = "宝贝与描述相符")
    private Short eva1;

    @ApiModelProperty(notes = "卖家服务")
    private Short eva2;

    @ApiModelProperty(notes = "物流服务")
    private Short eva3;

    @ApiModelProperty(notes = "评价时间")
    private Date evatime;

    @ApiModelProperty(notes = "评价人")
    private String username;

    @ApiModelProperty(notes = "评价人头像")
    private String favicon;

    @ApiModelProperty(notes = "是否匿名1=不匿名2=匿名")
    private Short isanonymous;

    @ApiModelProperty(notes = "买家心得")
    private String buyersexperience;

    public Short getEva1() {
        return eva1;
    }

    public void setEva1(Short eva1) {
        this.eva1 = eva1;
    }

    public Short getEva2() {
        return eva2;
    }

    public void setEva2(Short eva2) {
        this.eva2 = eva2;
    }

    public Short getEva3() {
        return eva3;
    }

    public void setEva3(Short eva3) {
        this.eva3 = eva3;
    }

    public Date getEvatime() {
        return evatime;
    }

    public void setEvatime(Date evatime) {
        this.evatime = evatime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFavicon() {
        return favicon;
    }

    public void setFavicon(String favicon) {
        this.favicon = favicon;
    }

    public Short getIsanonymous() {
        return isanonymous;
    }

    public void setIsanonymous(Short isanonymous) {
        this.isanonymous = isanonymous;
    }

    public String getBuyersexperience() {
        return buyersexperience;
    }

    public void setBuyersexperience(String buyersexperience) {
        this.buyersexperience = buyersexperience;
    }
}
