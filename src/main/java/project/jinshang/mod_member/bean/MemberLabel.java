package project.jinshang.mod_member.bean;

import io.swagger.annotations.ApiModelProperty;

/**
 * null
 * 
 * @author zzy
 * 
 * @date 2018-07-09
 */
public class MemberLabel {
    private Long id;

    @ApiModelProperty(notes = "标签名称")
    private String labelname;

    @ApiModelProperty(notes = "备注")
    private String remarks;

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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}