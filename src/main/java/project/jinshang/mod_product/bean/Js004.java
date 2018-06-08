package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

/**
 * 退货(主动)传值实体类
 *
 * @author xiazy
 * @create 2018-06-05 16:34
 **/
public class Js004 {
    @ApiModelProperty(notes = "AppId")
    private String appid;
    @ApiModelProperty(notes = "检验码")
    private String notify;
    @ApiModelProperty(notes = "接口编码")
    private String type;
    @ApiModelProperty(notes = "关联订单号")
    private String orderno;
    @ApiModelProperty(notes = "退货单号")
    private String backno;
    @ApiModelProperty(notes = "收货人")
    private String contact;
    @ApiModelProperty(notes = "收货地址")
    private String backaddress;
    @ApiModelProperty(notes = "联系电话")
    private String contactphone;
    @ApiModelProperty(notes = "退货明细JSON")
    private String backjson;
    @ApiModelProperty(notes = "备用参数JSON")
    private ExtendParam extendjson;
    @ApiModelProperty(notes = "时间出")
    private String timestamp;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getBackno() {
        return backno;
    }

    public void setBackno(String backno) {
        this.backno = backno;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getBackaddress() {
        return backaddress;
    }

    public void setBackaddress(String backaddress) {
        this.backaddress = backaddress;
    }

    public String getContactphone() {
        return contactphone;
    }

    public void setContactphone(String contactphone) {
        this.contactphone = contactphone;
    }

    public String getBackjson() {
        return backjson;
    }

    public void setBackjson(String backjson) {
        this.backjson = backjson;
    }

    public ExtendParam getExtendjson() {
        return extendjson;
    }

    public void setExtendjson(ExtendParam extendjson) {
        this.extendjson = extendjson;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
