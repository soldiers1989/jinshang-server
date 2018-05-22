package project.jinshang.mod_admin.mod_paymode.bean;

import io.swagger.annotations.ApiModelProperty;

public class PayMode {
    private Long id;
    @ApiModelProperty(notes = "支付方式名称:支付宝，支付宝APP支付等")
    private String paytype;

    @ApiModelProperty(notes = "合作者身份ID")
    private String identityid;

    @ApiModelProperty(notes = "交易安全检验码")
    private String saftycode;

    @ApiModelProperty(notes = "收款支付宝账户")
    private String alipayaccount;

    @ApiModelProperty(notes = "商户支付宝账号")
    private String saleralipayaccount;

    @ApiModelProperty(notes = "privatekey")
    private String privatekey;

    @ApiModelProperty(notes = "publickey")
    private String publickey;

    @ApiModelProperty(notes = "weixinappid")
    private String weixinappid;

    @ApiModelProperty(notes = "weixinappsecret")
    private String weixinappsecret;

    @ApiModelProperty(notes = "weixinkey")
    private String weixinkey;

    @ApiModelProperty(notes = "weixinmchid")
    private String weixinmchid;

    @ApiModelProperty(notes = "商户证书")
    private String certificate;

    @ApiModelProperty(notes = "是否开启0=开启1=关闭")
    private Short open;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getIdentityid() {
        return identityid;
    }

    public void setIdentityid(String identityid) {
        this.identityid = identityid == null ? null : identityid.trim();
    }

    public String getSaftycode() {
        return saftycode;
    }

    public void setSaftycode(String saftycode) {
        this.saftycode = saftycode == null ? null : saftycode.trim();
    }

    public String getAlipayaccount() {
        return alipayaccount;
    }

    public void setAlipayaccount(String alipayaccount) {
        this.alipayaccount = alipayaccount == null ? null : alipayaccount.trim();
    }

    public String getSaleralipayaccount() {
        return saleralipayaccount;
    }

    public void setSaleralipayaccount(String saleralipayaccount) {
        this.saleralipayaccount = saleralipayaccount == null ? null : saleralipayaccount.trim();
    }

    public String getPrivatekey() {
        return privatekey;
    }

    public void setPrivatekey(String privatekey) {
        this.privatekey = privatekey == null ? null : privatekey.trim();
    }

    public String getPublickey() {
        return publickey;
    }

    public void setPublickey(String publickey) {
        this.publickey = publickey == null ? null : publickey.trim();
    }

    public String getWeixinappid() {
        return weixinappid;
    }

    public void setWeixinappid(String weixinappid) {
        this.weixinappid = weixinappid == null ? null : weixinappid.trim();
    }

    public String getWeixinappsecret() {
        return weixinappsecret;
    }

    public void setWeixinappsecret(String weixinappsecret) {
        this.weixinappsecret = weixinappsecret == null ? null : weixinappsecret.trim();
    }

    public String getWeixinkey() {
        return weixinkey;
    }

    public void setWeixinkey(String weixinkey) {
        this.weixinkey = weixinkey == null ? null : weixinkey.trim();
    }

    public String getWeixinmchid() {
        return weixinmchid;
    }

    public void setWeixinmchid(String weixinmchid) {
        this.weixinmchid = weixinmchid == null ? null : weixinmchid.trim();
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate == null ? null : certificate.trim();
    }

    public Short getOpen() {
        return open;
    }

    public void setOpen(Short open) {
        this.open = open;
    }
}