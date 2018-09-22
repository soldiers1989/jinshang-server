package project.jinshang.mod_admin.mod_returnreason.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * null
 * 
 * @author zzy
 * 
 * @date 2018-08-02
 */
public class ReturnReason {
    private Long id;

    @ApiModelProperty(notes = "退货原因")
    private String returnreason;

    @ApiModelProperty(notes = "责任方1.买方 2.卖方")
    private Short responsibility;

    @ApiModelProperty(notes = "违约金比例")
    private BigDecimal penalty;

    @ApiModelProperty(notes = "创建时间")
    private Date createtime;

    @ApiModelProperty(notes = "修改时间")
    private Date updatetime;

    @ApiModelProperty(notes = "后台管理员的id")
    private Long adminid;

    @ApiModelProperty(notes = "备注")
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReturnreason() {
        return returnreason;
    }

    public void setReturnreason(String returnreason) {
        this.returnreason = returnreason == null ? null : returnreason.trim();
    }

    public Short getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(Short responsibility) {
        this.responsibility = responsibility;
    }

    public BigDecimal getPenalty() {
        return penalty;
    }

    public void setPenalty(BigDecimal penalty) {
        this.penalty = penalty;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Long getAdminid() {
        return adminid;
    }

    public void setAdminid(Long adminid) {
        this.adminid = adminid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}