package project.jinshang.mod_member.bean;

import java.util.Date;

public class ApiRecord {
    private Long id;

    private String appid;

    private String appurl;

    private String apicode;

    private String content;

    private String returnjson;

    private Date createtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    public String getAppurl() {
        return appurl;
    }

    public void setAppurl(String appurl) {
        this.appurl = appurl == null ? null : appurl.trim();
    }

    public String getApicode() {
        return apicode;
    }

    public void setApicode(String apicode) {
        this.apicode = apicode == null ? null : apicode.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getReturnjson() {
        return returnjson;
    }

    public void setReturnjson(String returnjson) {
        this.returnjson = returnjson == null ? null : returnjson.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}