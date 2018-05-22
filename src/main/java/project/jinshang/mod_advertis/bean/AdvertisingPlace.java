package project.jinshang.mod_advertis.bean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class AdvertisingPlace {

    @ApiModelProperty(notes = "id")
    private Long id;

    @ApiModelProperty(notes = "广告位置名")
    private String position;

    @ApiModelProperty(notes = "广告简介")
    private String describe;

    @ApiModelProperty(notes = "广告种类")
    private String advtype;

    @ApiModelProperty(notes = "展示方式")
    private String showtype;

    @ApiModelProperty(notes = "获取标记")
    private String gettag;

    @ApiModelProperty(notes = "是否停用 1=停用")
    private Short stop;

    @ApiModelProperty(notes = "广告位宽度")
    private Float weight;

    @ApiModelProperty(notes = "广告位高度")
    private Float height;

    @ApiModelProperty(notes = "添加时间")
    private Date createtime;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getAdvtype() {
        return advtype;
    }


    public void setAdvtype(String advtype) {
        this.advtype = advtype;
    }

    public String getShowtype() {
        return showtype;
    }

    public void setShowtype(String showtype) {
        this.showtype = showtype;
    }

    public String getGettag() {
        return gettag;
    }


    public void setGettag(String gettag) {
        this.gettag = gettag;
    }

    public Short getStop() {
        return stop;
    }

    public void setStop(Short stop) {
        this.stop = stop;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}