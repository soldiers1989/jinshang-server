package project.jinshang.mod_member.bean;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

/**
 * create : wyh
 * date : 2018/2/24
 */
public class WeixinInfo {
    @ApiModelProperty(notes = "用户的唯一标识")
    private String openid;
    @ApiModelProperty(notes = "用户昵称")
    private String nickname;
    @ApiModelProperty(notes = "用户的性别，值为1时是男性，值为2时是女性，值为0时是未知")
    private String sex;
    @ApiModelProperty(notes = "用户个人资料填写的省份")
    private String province;
    @ApiModelProperty(notes = "普通用户个人资料填写的城市")
    private String city;
    @ApiModelProperty(notes = "国家，如中国为CN")
    private String country;
    private String unionid;
    @ApiModelProperty(notes = "用户头像")
    private String headimgurl;


    public String getOpenid() {
        return openid;
    }
    public String getNickname() {
        return nickname;
    }
    public String getSex() {
        return sex;
    }
    public String getProvince() {
        return province;
    }
    public String getCity() {
        return city;
    }
    public String getCountry() {
        return country;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

}
