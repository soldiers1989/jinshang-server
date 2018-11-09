package project.jinshang.mod_coupon.bean;

import io.swagger.annotations.ApiModelProperty;

/**
 * null
 * 
 * @author zzy
 * 
 * @date 2018-05-26
 */
public class YhqPlan {
    @ApiModelProperty("ID自增")
    private Long id;

    @ApiModelProperty("名称")
    private String name;

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
}