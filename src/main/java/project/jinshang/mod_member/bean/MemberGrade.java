package project.jinshang.mod_member.bean;

public class MemberGrade {
    private Integer id;

    private Integer iserial;

    private String gradename;

    private String remark;

    private Integer idefault;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIserial() {
        return iserial;
    }

    public void setIserial(Integer iserial) {
        this.iserial = iserial;
    }

    public String getGradename() {
        return gradename;
    }

    public void setGradename(String gradename) {
        this.gradename = gradename == null ? null : gradename.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getIdefault() {
        return idefault;
    }

    public void setIdefault(Integer idefault) {
        this.idefault = idefault;
    }
}