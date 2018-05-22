package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

public class CardNum {
    private Long id;

    private String name;

    @ApiModelProperty(notes = "材质ID")
    private Long matialid;
    @ApiModelProperty(notes = "编号")
    private Integer number;

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

    public Long getMatialid() {
        return matialid;
    }

    public void setMatialid(Long matialid) {
        this.matialid = matialid;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}