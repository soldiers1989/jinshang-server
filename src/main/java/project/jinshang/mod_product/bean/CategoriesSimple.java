package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * create : wyh
 * date : 2018/3/1
 */
public class CategoriesSimple implements Serializable{

    protected Long id;

    protected String name;

    protected Long parentid;

    protected String title;

    @ApiModelProperty(notes = "关键字")
    protected String keywords;

    @ApiModelProperty(notes = "描述")
    protected String description;

    protected String img;

    @ApiModelProperty(notes = "排序")
    protected Integer sort;


    @ApiModelProperty(notes = "类型 紧固件、其他")
    protected String catetype;


    protected  int level;

    private List<CategoriesSimple> list;


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
        this.name = name;
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
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public List<CategoriesSimple> getList() {
        return list;
    }

    public void setList(List<CategoriesSimple> list) {
        this.list = list;
    }
}
