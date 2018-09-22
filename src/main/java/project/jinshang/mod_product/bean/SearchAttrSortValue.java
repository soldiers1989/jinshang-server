package project.jinshang.mod_product.bean;

public class SearchAttrSortValue {
    private Long id;

    private Long searchAttrId;

    private String key;

    private Integer weight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSearchAttrId() {
        return searchAttrId;
    }

    public void setSearchAttrId(Long searchAttrId) {
        this.searchAttrId = searchAttrId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}