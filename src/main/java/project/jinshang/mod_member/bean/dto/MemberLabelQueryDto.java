package project.jinshang.mod_member.bean.dto;

public class MemberLabelQueryDto {
    private Long id;

    private String labelname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabelname() {
        return labelname;
    }

    public void setLabelname(String labelname) {
        this.labelname = labelname == null ? null : labelname.trim();
    }


}