package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

/**
 * 订单取消(主动)传值实体类
 *
 * @author xiazy
 * @create 2018-06-05 15:48
 **/
public class Js003 {
    @ApiModelProperty(notes = "AppId")
    private String appid;
    @ApiModelProperty(notes = "检验码")
    private String notify;
    @ApiModelProperty(notes = "接口编码")
    private String type;
    @ApiModelProperty(notes = "订单号")
    private String orderno;
    @ApiModelProperty(notes = "取消类型")
    private String canceltype;
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

    public String getCanceltype() {
        return canceltype;
    }

    public void setCanceltype(String canceltype) {
        this.canceltype = canceltype;
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
