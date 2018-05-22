package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class OperateLog {
    private Long id;

    @ApiModelProperty(notes = "操作人id")
    private Long opid;

    @ApiModelProperty(notes = "操作人")
    private String opname;

    @ApiModelProperty(notes = "操作内容")
    private String content;

    @ApiModelProperty(notes = "操作类型0=订单操作1=退货操作")
    private Short optype;

    @ApiModelProperty(notes = "操作时间")
    private Date optime;

    @ApiModelProperty(notes = "订单id")
    private Long orderid;

    @ApiModelProperty(notes = "订单编号")
    private String orderno;

    @ApiModelProperty(notes = "订单商品id")
    private Long orderpdid;

    public Long getOrderpdid() {
        return orderpdid;
    }

    public void setOrderpdid(Long orderpdid) {
        this.orderpdid = orderpdid;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOpid() {
        return opid;
    }

    public void setOpid(Long opid) {
        this.opid = opid;
    }

    public String getOpname() {
        return opname;
    }

    public void setOpname(String opname) {
        this.opname = opname == null ? null : opname.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Short getOptype() {
        return optype;
    }

    public void setOptype(Short optype) {
        this.optype = optype;
    }

    public Date getOptime() {
        return optime;
    }

    public void setOptime(Date optime) {
        this.optime = optime;
    }
}