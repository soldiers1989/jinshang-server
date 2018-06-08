package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

/**
 * Js006
 *库存同步(主动)对应传值实体类
 * @author xiazy
 * @create 2018-06-06 17:34
 **/
public class Js006 {
    @ApiModelProperty(notes = "AppId")
    private String appid;
    @ApiModelProperty(notes = "检验码")
    private String notify;
    @ApiModelProperty(notes = "接口编码")
    private String type;
    @ApiModelProperty(notes = "商品明细JSON")
    private String pdjson;
    @ApiModelProperty(notes = "仓库识别编码")
    private String store;
    @ApiModelProperty(notes = "页数JSON")
    private String pagejson;
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

    public String getPdjson() {
        return pdjson;
    }

    public void setPdjson(String pdjson) {
        this.pdjson = pdjson;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getPagejson() {
        return pagejson;
    }

    public void setPagejson(String pagejson) {
        this.pagejson = pagejson;
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
