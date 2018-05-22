package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

public class Attvalue {
    private Long id;

    @ApiModelProperty(notes = "值")
    private String paramvalue;

    @ApiModelProperty(notes = "属性名id")
    private Long attid;

    @ApiModelProperty(notes = "备注")
    private String mark;

    @ApiModelProperty(notes = "序号")
    private Integer sort;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParamvalue() {
        return paramvalue;
    }

    public void setParamvalue(String paramvalue) {
        this.paramvalue = paramvalue == null ? null : paramvalue.trim();
    }

    public Long getAttid() {
        return attid;
    }

    public void setAttid(Long attid) {
        this.attid = attid;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}