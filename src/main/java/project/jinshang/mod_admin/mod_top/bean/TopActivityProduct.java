package project.jinshang.mod_admin.mod_top.bean;

public class TopActivityProduct {
    private Long id;

    private Long activityid;

    private Long topid;

    private Short activitytype;

    private Long pdid;

    private String pdno;

    public Long getPdid() {
        return pdid;
    }

    public void setPdid(Long pdid) {
        this.pdid = pdid;
    }

    public String getPdno() {
        return pdno;
    }

    public void setPdno(String pdno) {
        this.pdno = pdno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActivityid() {
        return activityid;
    }

    public void setActivityid(Long activityid) {
        this.activityid = activityid;
    }

    public Long getTopid() {
        return topid;
    }

    public void setTopid(Long topid) {
        this.topid = topid;
    }

    public Short getActivitytype() {
        return activitytype;
    }

    public void setActivitytype(Short activitytype) {
        this.activitytype = activitytype;
    }
}