package project.jinshang.mod_admin.mod_count.bean;

import io.swagger.annotations.ApiModelProperty;

public class MemberStatistcModel {

    @ApiModelProperty(notes = "统计时间")
    private String createtime;

    @ApiModelProperty(notes = "注册数量")
    private Integer registernum;

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Integer getRegisternum() {
        return registernum;
    }

    public void setRegisternum(Integer registernum) {
        this.registernum = registernum;
    }
}
