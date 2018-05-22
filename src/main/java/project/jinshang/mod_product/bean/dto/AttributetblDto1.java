package project.jinshang.mod_product.bean.dto;

import io.swagger.annotations.ApiModelProperty;
import project.jinshang.mod_product.bean.Attvalue;

import java.util.List;

public class AttributetblDto1 {
    private Long id;

    @ApiModelProperty(notes = "属性名")
    private String name;

    @ApiModelProperty(notes = "品名id")
    private Long productnameid;

    @ApiModelProperty(notes = "备注")
    private String remark;

    @ApiModelProperty(notes = "序号")
    private Integer sort;

    @ApiModelProperty(notes = "连接符")
    private String connector;

    @ApiModelProperty(notes = "属性值")
    private List<Attvalue> attvalues;

    private  String value;


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

    public Long getProductnameid() {
        return productnameid;
    }

    public void setProductnameid(Long productnameid) {
        this.productnameid = productnameid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector == null ? null : connector.trim();
    }

    public List<Attvalue> getAttvalues() {
        return attvalues;
    }

    public void setAttvalues(List<Attvalue> attvalues) {
        this.attvalues = attvalues;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}