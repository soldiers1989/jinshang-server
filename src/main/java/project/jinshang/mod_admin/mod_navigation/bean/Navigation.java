package project.jinshang.mod_admin.mod_navigation.bean;

import io.swagger.annotations.ApiModelProperty;

public class Navigation {
    private Long id;
    @ApiModelProperty(notes = "导航类型")
    private String natype;

    @ApiModelProperty(notes = "标题")
    private String natitle;

    @ApiModelProperty(notes = "链接")
    private String nalink;

    @ApiModelProperty(notes = "所在位置")
    private String nalocation;

    @ApiModelProperty(notes = "平台标识")
    private String naterrace;

    @ApiModelProperty(notes = "图标")
    private String naicon;

    @ApiModelProperty(notes = "是否新窗口打开")
    private Integer isnew;

    @ApiModelProperty(notes = "排序")
    private Integer nasort;

    @ApiModelProperty(notes = "前台是否显示0：不显示 1：显示")
    private Integer isShow;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNatype() {
        return natype;
    }

    public void setNatype(String natype) {
        this.natype = natype == null ? null : natype.trim();
    }

    public String getNatitle() {
        return natitle;
    }

    public void setNatitle(String natitle) {
        this.natitle = natitle == null ? null : natitle.trim();
    }

    public String getNalink() {
        return nalink;
    }

    public void setNalink(String nalink) {
        this.nalink = nalink == null ? null : nalink.trim();
    }

    public String getNalocation() {
        return nalocation;
    }

    public void setNalocation(String nalocation) {
        this.nalocation = nalocation == null ? null : nalocation.trim();
    }

    public String getNaterrace() {
        return naterrace;
    }

    public void setNaterrace(String naterrace) {
        this.naterrace = naterrace == null ? null : naterrace.trim();
    }

    public String getNaicon() {
        return naicon;
    }

    public void setNaicon(String naicon) {
        this.naicon = naicon == null ? null : naicon.trim();
    }

    public Integer getIsnew() {
        return isnew;
    }

    public void setIsnew(Integer isnew) {
        this.isnew = isnew;
    }

    public Integer getNasort() {
        return nasort;
    }

    public void setNasort(Integer nasort) {
        this.nasort = nasort;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }
}