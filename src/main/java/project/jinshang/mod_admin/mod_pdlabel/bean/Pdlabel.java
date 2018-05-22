package project.jinshang.mod_admin.mod_pdlabel.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class Pdlabel {
    private Long id;

    private Long pdlableid;

    @ApiModelProperty(notes = "商品栏位名称")
    private String labelname;

    @ApiModelProperty(notes = "推荐商品栏目描述")
    private String recommend;

    @ApiModelProperty(notes = "是否启用")
    private Integer isuse;

    @ApiModelProperty(notes = "创建时间")
    private Date creattime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPdlableid() {
        return pdlableid;
    }

    public void setPdlableid(Long pdlableid) {
        this.pdlableid = pdlableid;
    }

    public String getLabelname() {
        return labelname;
    }

    public void setLabelname(String labelname) {
        this.labelname = labelname == null ? null : labelname.trim();
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend == null ? null : recommend.trim();
    }

    public Integer getIsuse() {
        return isuse;
    }

    public void setIsuse(Integer isuse) {
        this.isuse = isuse;
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }
}