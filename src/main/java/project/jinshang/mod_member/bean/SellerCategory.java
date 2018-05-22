package project.jinshang.mod_member.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class SellerCategory {
    private Long id;

    @ApiModelProperty(notes = "分类id")
    private Long categoryid;

    @ApiModelProperty(notes = "佣金比率")
    private BigDecimal brokeragerate;

    @ApiModelProperty(notes = "分类名称")
    private String name;
    @ApiModelProperty(notes = "父级id")
    private Long parentid;
    @ApiModelProperty(notes = "标题")
    private String title;
    @ApiModelProperty(notes = "关键字")
    private String keywords;
    @ApiModelProperty(notes = "描述")
    private String description;
    @ApiModelProperty(notes = "分类图片")
    private String img;
    @ApiModelProperty(notes = "排序")
    private Integer sort;
    @ApiModelProperty(notes = "卖家id")
    private Long sellerid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
    }

    public BigDecimal getBrokeragerate() {
        return brokeragerate;
    }

    public void setBrokeragerate(BigDecimal brokeragerate) {
        this.brokeragerate = brokeragerate;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getSellerid() {
        return sellerid;
    }

    public void setSellerid(Long sellerid) {
        this.sellerid = sellerid;
    }
}