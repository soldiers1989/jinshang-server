package project.jinshang.mod_advertis.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class Advertisement {
    @ApiModelProperty(notes = "id")
    private Long id;

    @ApiModelProperty(notes = "广告位id")
    private Long placeid;

    @ApiModelProperty(notes = "广告标题")
    private String title;

    @ApiModelProperty(notes = "图片地址")
    private String imgs;

    @ApiModelProperty(notes = "广告关联url")
    private String url;

    @ApiModelProperty(notes = "开始时间")
    private Date starttime;

    @ApiModelProperty(notes = "结束时间")
    private Date endtime;

    @ApiModelProperty(notes = "排序")
    private Integer sort;

    @ApiModelProperty(notes = "创建时间")
    private Date createtime;

    @ApiModelProperty(notes = "添加人")
    private String adduser;

    @ApiModelProperty(notes = "更新时间")
    private Date updatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlaceid() {
        return placeid;
    }

    public void setPlaceid(Long placeid) {
        this.placeid = placeid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getAdduser() {
        return adduser;
    }

    public void setAdduser(String adduser) {
        this.adduser = adduser;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}