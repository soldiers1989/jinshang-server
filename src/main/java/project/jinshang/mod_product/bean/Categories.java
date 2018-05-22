package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Categories implements Serializable{
    private Long id;

    private String name;

    private Long parentid;

    private String title;

    @ApiModelProperty(notes = "关键字")
    private String keywords;

    @ApiModelProperty(notes = "描述")
    private String description;

    private String img;


    @ApiModelProperty(notes = "排序")
    private Integer sort;

    @ApiModelProperty(notes = "佣金比率")
    private  BigDecimal brokeragerate;

    @ApiModelProperty(notes = "服务费比率")
    private BigDecimal servicesrate;

    @ApiModelProperty(notes = "类型 紧固件、其他")
    private String catetype;

//    @ApiModelProperty(notes = "加价率")
//    private BigDecimal uprate;
//
//    @ApiModelProperty(notes = "金牌优惠率")
//    private BigDecimal goldmemberrate;
//
//    @ApiModelProperty(notes = "服务商优惠率")
//    private BigDecimal serverrate;
//
//    @ApiModelProperty(notes = "三级批发价优惠率")
//    private BigDecimal thirdrate;
//
//    @ApiModelProperty(notes = "二级批发价优惠率")
//    private BigDecimal secondrate;
//
//    @ApiModelProperty(notes = "一级批发价优惠率")
//    private BigDecimal firstrate;
//
//    @ApiModelProperty(notes = "计费率（财务）")
//    private BigDecimal freerate;
//
//    @ApiModelProperty(notes = "运营费用（财务）")
//    private BigDecimal businessrate;
//
//    @ApiModelProperty(notes = "支付费用率（财务）")
//    private BigDecimal payrate;
//
//    @ApiModelProperty(notes = "服务商净利率（财务）")
//    private BigDecimal servernetrate;


    private  int level;




    private List<Categories> list;

    public List<Categories> getList() {
        return list;
    }

    public void setList(List<Categories> list) {
        this.list = list;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

//    public BigDecimal getUprate() {
//        return uprate;
//    }
//
//    public void setUprate(BigDecimal uprate) {
//        this.uprate = uprate;
//    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

//    public BigDecimal getGoldmemberrate() {
//        return goldmemberrate;
//    }
//
//    public void setGoldmemberrate(BigDecimal goldmemberrate) {
//        this.goldmemberrate = goldmemberrate;
//    }
//
//    public BigDecimal getServerrate() {
//        return serverrate;
//    }
//
//    public void setServerrate(BigDecimal serverrate) {
//        this.serverrate = serverrate;
//    }
//
//    public BigDecimal getThirdrate() {
//        return thirdrate;
//    }
//
//    public void setThirdrate(BigDecimal thirdrate) {
//        this.thirdrate = thirdrate;
//    }
//
//    public BigDecimal getSecondrate() {
//        return secondrate;
//    }
//
//    public void setSecondrate(BigDecimal secondrate) {
//        this.secondrate = secondrate;
//    }
//
//    public BigDecimal getFirstrate() {
//        return firstrate;
//    }
//
//    public void setFirstrate(BigDecimal firstrate) {
//        this.firstrate = firstrate;
//    }
//
//    public BigDecimal getFreerate() {
//        return freerate;
//    }
//
//    public void setFreerate(BigDecimal freerate) {
//        this.freerate = freerate;
//    }
//
//    public BigDecimal getBusinessrate() {
//        return businessrate;
//    }
//
//    public void setBusinessrate(BigDecimal businessrate) {
//        this.businessrate = businessrate;
//    }
//
//    public BigDecimal getPayrate() {
//        return payrate;
//    }
//
//    public void setPayrate(BigDecimal payrate) {
//        this.payrate = payrate;
//    }
//
//    public BigDecimal getServernetrate() {
//        return servernetrate;
//    }
//
//    public void setServernetrate(BigDecimal servernetrate) {
//        this.servernetrate = servernetrate;
//    }

    public BigDecimal getBrokeragerate() {
        return brokeragerate;
    }

    public void setBrokeragerate(BigDecimal brokeragerate) {
        this.brokeragerate = brokeragerate;
    }


    public BigDecimal getServicesrate() {
        return servicesrate;
    }

    public void setServicesrate(BigDecimal servicesrate) {
        this.servicesrate = servicesrate;
    }

    public String getCatetype() {
        return catetype;
    }

    public void setCatetype(String catetype) {
        this.catetype = catetype;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}