package project.jinshang.mod_member.bean;

/**
 * create : wyh
 * date : 2018/2/24
 */
public class WeixinInfo {
    private String openid;
    private String nickname;
    private String sex;
    private String province;
    private String city;
    private String country;
    private  String unionid;



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
}
