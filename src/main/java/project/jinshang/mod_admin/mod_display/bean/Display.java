package project.jinshang.mod_admin.mod_display.bean;

import io.swagger.annotations.ApiModelProperty;

public class Display {
    private Long id;
    @ApiModelProperty(notes = "父级ID")
    private Long praentid;

    @ApiModelProperty(notes = "分类管理")
    private String dpclass;

    @ApiModelProperty(notes = "链接地址")
    private String dpurl;

    @ApiModelProperty(notes = "上级分类")
    private String dpsuperior;

    @ApiModelProperty(notes = "选择广告")
    private String adadvert;

    @ApiModelProperty(notes = "是否显示{0：不显示1显示}")
    private Integer isshow;

    @ApiModelProperty(notes = "分类图标")
    private String dpicon;

    @ApiModelProperty(notes = "排序")
    private Integer dpsort;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPraentid() {
        return praentid;
    }

    public void setPraentid(Long praentid) {
        this.praentid = praentid;
    }

    public String getDpclass() {
        return dpclass;
    }

    public void setDpclass(String dpclass) {
        this.dpclass = dpclass == null ? null : dpclass.trim();
    }

    public String getDpurl() {
        return dpurl;
    }

    public void setDpurl(String dpurl) {
        this.dpurl = dpurl == null ? null : dpurl.trim();
    }

    public String getDpsuperior() {
        return dpsuperior;
    }

    public void setDpsuperior(String dpsuperior) {
        this.dpsuperior = dpsuperior == null ? null : dpsuperior.trim();
    }

    public String getAdadvert() {
        return adadvert;
    }

    public void setAdadvert(String adadvert) {
        this.adadvert = adadvert == null ? null : adadvert.trim();
    }

    public Integer getIsshow() {
        return isshow;
    }

    public void setIsshow(Integer isshow) {
        this.isshow = isshow;
    }

    public String getDpicon() {
        return dpicon;
    }

    public void setDpicon(String dpicon) {
        this.dpicon = dpicon == null ? null : dpicon.trim();
    }

    public Integer getDpsort() {
        return dpsort;
    }

    public void setDpsort(Integer dpsort) {
        this.dpsort = dpsort;
    }
}