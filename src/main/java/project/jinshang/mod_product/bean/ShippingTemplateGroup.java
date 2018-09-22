package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

public class ShippingTemplateGroup {
    private Long id;

    @ApiModelProperty(notes = "卖家id")
    private Long memberid;

    private Boolean selflifting;

    @ApiModelProperty(notes = "顺丰到付开关")
    private Boolean sfarrivepay;

    @ApiModelProperty(notes = "快递开关")
    private Boolean expresspay;

    @ApiModelProperty(notes = "物流自提开关")
    private Boolean expreselflifting;

    @ApiModelProperty(notes = "物流到户开关")
    private Boolean exprehousehoid;

    @ApiModelProperty(notes = "快递模板")
    private Long expretemp;

    @ApiModelProperty(notes = "物流自提模板")
    private Long expreselftemp;

    @ApiModelProperty(notes = "物流到户模板")
    private Long exprehousetemp;

    @ApiModelProperty(notes = "合集名称")
    private String name;
    @ApiModelProperty(notes = "自提地址")
    private String switch1address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public Boolean getSelflifting() {
        return selflifting;
    }

    public void setSelflifting(Boolean selflifting) {
        this.selflifting = selflifting;
    }

    public Boolean getSfarrivepay() {
        return sfarrivepay;
    }

    public void setSfarrivepay(Boolean sfarrivepay) {
        this.sfarrivepay = sfarrivepay;
    }

    public Boolean getExpresspay() {
        return expresspay;
    }

    public void setExpresspay(Boolean expresspay) {
        this.expresspay = expresspay;
    }

    public Boolean getExpreselflifting() {
        return expreselflifting;
    }

    public void setExpreselflifting(Boolean expreselflifting) {
        this.expreselflifting = expreselflifting;
    }

    public Boolean getExprehousehoid() {
        return exprehousehoid;
    }

    public void setExprehousehoid(Boolean exprehousehoid) {
        this.exprehousehoid = exprehousehoid;
    }

    public Long getExpretemp() {
        return expretemp;
    }

    public void setExpretemp(Long expretemp) {
        this.expretemp = expretemp;
    }

    public Long getExpreselftemp() {
        return expreselftemp;
    }

    public void setExpreselftemp(Long expreselftemp) {
        this.expreselftemp = expreselftemp;
    }

    public Long getExprehousetemp() {
        return exprehousetemp;
    }

    public void setExprehousetemp(Long exprehousetemp) {
        this.exprehousetemp = exprehousetemp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSwitch1address() {
        return switch1address;
    }

    public void setSwitch1address(String switch1address) {
        this.switch1address = switch1address == null ? null : switch1address.trim();
    }
}