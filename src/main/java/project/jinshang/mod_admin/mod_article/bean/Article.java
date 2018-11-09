package project.jinshang.mod_admin.mod_article.bean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

public class Article {
    private Long id;

    @ApiModelProperty(notes = "关联文章类型表")
    private Long docid;

    @ApiModelProperty(notes = "文章标题")
    private String doctitle;

    @ApiModelProperty(notes = "文章内容")
    private String doccontent;

    @ApiModelProperty(notes = "文章排序")
    private Integer docorder;

    @ApiModelProperty(notes = "文章链接地址")
    private String docaddress;

    @ApiModelProperty(notes = "文章是否显示{0不显示 1显示}")
    private Short docshow;

    @ApiModelProperty(notes = "文章创建时间")
    private Date creattime;

    @ApiModelProperty(notes = "文章修改时间")
    private Date updatetime;

    @ApiModelProperty(notes = "文章是否已经删除{0未删除 1删除}")
    private Short docstatus;

    @ApiModelProperty(notes = "文章简述")
    private String sketch;

    @ApiModelProperty(notes = "文章图片")
    private String pic;

    @ApiModelProperty(notes = "文章类型的标题")
    private String docName;

    private Short type;

    private Short iscarousel;

    @ApiModelProperty(notes = "文章图片json字符串")
    private String picjson;

    private List<ArticlePic> picList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDocid() {
        return docid;
    }

    public void setDocid(Long docid) {
        this.docid = docid;
    }

    public String getDoctitle() {
        return doctitle;
    }

    public void setDoctitle(String doctitle) {
        this.doctitle = doctitle == null ? null : doctitle.trim();
    }

    public String getDoccontent() {
        return doccontent;
    }

    public void setDoccontent(String doccontent) {
        this.doccontent = doccontent == null ? null : doccontent.trim();
    }

    public Integer getDocorder() {
        return docorder;
    }

    public void setDocorder(Integer docorder) {
        this.docorder = docorder;
    }

    public String getDocaddress() {
        return docaddress;
    }

    public void setDocaddress(String docaddress) {
        this.docaddress = docaddress == null ? null : docaddress.trim();
    }

    public Short getDocshow() {
        return docshow;
    }

    public void setDocshow(Short docshow) {
        this.docshow = docshow;
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Short getDocstatus() {
        return docstatus;
    }

    public void setDocstatus(Short docstatus) {
        this.docstatus = docstatus;
    }

    public String getSketch() {
        return sketch;
    }

    public void setSketch(String sketch) {
        this.sketch = sketch;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Short getIscarousel() {
        return iscarousel;
    }

    public void setIscarousel(Short iscarousel) {
        this.iscarousel = iscarousel;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getPicjson() {
        return picjson;
    }

    public void setPicjson(String picjson) {
        this.picjson = picjson;
    }

    public List<ArticlePic> getPicList() {
        return picList;
    }

    public void setPicList(List<ArticlePic> picList) {
        this.picList = picList;
    }
}