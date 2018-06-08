package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
import java.util.List;

/**
 * 订单下达(主动)传值实体类
 *
 * @author xiazy
 * @create 2018-06-05 11:35
 **/
public class Js001 {
    @ApiModelProperty(notes = "AppId")
    private String appid;
    @ApiModelProperty(notes = "检验码")
    private String notify;
    @ApiModelProperty(notes = "接口编码")
    private String type;
    @ApiModelProperty(notes = "订单号")
    private String orderno;
    @ApiModelProperty(notes = "收货人")
    private String shipto;
    @ApiModelProperty(notes = "收货地址")
    private String receivingaddress;
    @ApiModelProperty(notes = "联系电话")
    private String phone;
    @ApiModelProperty(notes = "销售明细JSON")
    private String salesjson;
    @ApiModelProperty(notes = "备用参数JSON")
    private ExtendParam extendjson;
    @ApiModelProperty(notes = "时间戳")
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

    public String getShipto() {
        return shipto;
    }

    public void setShipto(String shipto) {
        this.shipto = shipto;
    }

    public String getReceivingaddress() {
        return receivingaddress;
    }

    public void setReceivingaddress(String receivingaddress) {
        this.receivingaddress = receivingaddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getSalesjson() {
        return salesjson;
    }

    public void setSalesjson(String salesjson) {
        this.salesjson = salesjson;
    }
}
