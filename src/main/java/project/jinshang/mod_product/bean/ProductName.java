package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

public class ProductName {
    private Long id;

    private String name;
    @ApiModelProperty(notes = "分类id")
    private Long typeid;
    @ApiModelProperty(notes = "计量单位")
    private String unit;

    private String mark;
    @ApiModelProperty(notes = "品名编号")
    private String prodno;

    @ApiModelProperty(notes = "分类名称")
    private  String categoryname;

    @ApiModelProperty(notes = "品名图片")
    private String pic;
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

    public Long getTypeid() {
        return typeid;
    }

    public void setTypeid(Long typeid) {
        this.typeid = typeid;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    public String getProdno() {
        return prodno;
    }

    public void setProdno(String prodno) {
        this.prodno = prodno == null ? null : prodno.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }
}