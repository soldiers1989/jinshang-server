package project.jinshang.mod_admin.mod_station.bean;

import io.swagger.annotations.ApiModelProperty;

public class Station {
    private Long id;

    @ApiModelProperty(notes = "网站名称")
    private String stname;

    @ApiModelProperty(notes = "网站标题")
    private String sttitle;

    @ApiModelProperty(notes = "关键词")
    private String stkeyword;

    @ApiModelProperty(notes = "描述")
    private String stdescribe;

    @ApiModelProperty(notes = "Logo")
    private String stlogo;
    @ApiModelProperty(notes = "售后时间")
    private Integer aftertime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStname() {
        return stname;
    }

    public void setStname(String stname) {
        this.stname = stname == null ? null : stname.trim();
    }

    public String getSttitle() {
        return sttitle;
    }

    public void setSttitle(String sttitle) {
        this.sttitle = sttitle == null ? null : sttitle.trim();
    }

    public String getStkeyword() {
        return stkeyword;
    }

    public void setStkeyword(String stkeyword) {
        this.stkeyword = stkeyword == null ? null : stkeyword.trim();
    }

    public String getStdescribe() {
        return stdescribe;
    }

    public void setStdescribe(String stdescribe) {
        this.stdescribe = stdescribe == null ? null : stdescribe.trim();
    }

    public String getStlogo() {
        return stlogo;
    }

    public void setStlogo(String stlogo) {
        this.stlogo = stlogo == null ? null : stlogo.trim();
    }

    public Integer getAftertime() {
        return aftertime;
    }

    public void setAftertime(Integer aftertime) {
        this.aftertime = aftertime;
    }
}