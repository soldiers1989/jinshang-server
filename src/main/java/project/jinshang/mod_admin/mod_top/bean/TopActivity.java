package project.jinshang.mod_admin.mod_top.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class TopActivity {

    private Long id;

    @ApiModelProperty(notes = "商品栏位名称")
    private String name;

    @ApiModelProperty(notes = "推荐商品栏位描述")
    private String description;

    @ApiModelProperty(notes = "是否启用1=启用2=停用")
    private Short isavailable;

    @ApiModelProperty(notes = "创建时间")
    private Date createtime;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Short getIsavailable() {
        return isavailable;
    }

    public void setIsavailable(Short isavailable) {
        this.isavailable = isavailable;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}