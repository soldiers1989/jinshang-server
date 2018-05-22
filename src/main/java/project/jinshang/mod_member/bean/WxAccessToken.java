package project.jinshang.mod_member.bean;

import java.io.Serializable;

/**
 * create : wyh
 * date : 2018/2/24
 */
public class WxAccessToken implements Serializable{
    private String access_token;
    private String errcode;
    private String openid;
    private String unionid;


    public String getAccess_token() {
        return access_token;
    }
    public String getErrcode() {
        return errcode;
    }
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }
    public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    public String getUnionid() {
        return unionid;
    }
    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Override
    public String toString() {
        return "WxAccessToken{" +
                "access_token='" + access_token + '\'' +
                ", errcode='" + errcode + '\'' +
                ", openid='" + openid + '\'' +
                ", unionid='" + unionid + '\'' +
                '}';
    }
}
