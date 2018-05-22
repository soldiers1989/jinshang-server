package project.jinshang.mod_admin.mod_showcategory.bean;

import java.io.Serializable;

public class ShowCatedetail implements Serializable{
    private Long id;

    private Long showid;

    private String level2category;

    private long level2id;

    private String material;

    private String categoryids;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShowid() {
        return showid;
    }

    public void setShowid(Long showid) {
        this.showid = showid;
    }

    public String getLevel2category() {
        return level2category;
    }

    public void setLevel2category(String level2category) {
        this.level2category = level2category == null ? null : level2category.trim();
    }

    public Long getLevel2id() {
        return level2id;
    }

    public void setLevel2id(Long level2id) {
        this.level2id = level2id;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material == null ? null : material.trim();
    }

    public String getCategoryids() {
        return categoryids;
    }

    public void setCategoryids(String categoryids) {
        this.categoryids = categoryids == null ? null : categoryids.trim();
    }
}