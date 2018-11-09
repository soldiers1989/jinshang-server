package project.jinshang.mod_admin.mod_overtimeorders.dto;


import io.swagger.annotations.ApiModelProperty;

public class OverTimeView{

    @ApiModelProperty(notes = "公司名/店铺名")
    private String companyname;

    @ApiModelProperty(notes = "超时发货总数")
    private Integer overtimenum;

    @ApiModelProperty(notes = "超时1天订单数量")
    private Integer overtime1daynum;

    @ApiModelProperty(notes = "超时2天订单数量")
    private Integer overtime2daynum;

    @ApiModelProperty(notes = "超时3天及以上订单数量")
    private Integer overtime3daynum;

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public Integer getOvertimenum() {
        return overtimenum;
    }

    public void setOvertimenum(Integer overtimenum) {
        this.overtimenum = overtimenum;
    }

    public Integer getOvertime1daynum() {
        return overtime1daynum;
    }

    public void setOvertime1daynum(Integer overtime1daynum) {
        this.overtime1daynum = overtime1daynum;
    }

    public Integer getOvertime2daynum() {
        return overtime2daynum;
    }

    public void setOvertime2daynum(Integer overtime2daynum) {
        this.overtime2daynum = overtime2daynum;
    }

    public Integer getOvertime3daynum() {
        return overtime3daynum;
    }

    public void setOvertime3daynum(Integer overtime3daynum) {
        this.overtime3daynum = overtime3daynum;
    }


}
