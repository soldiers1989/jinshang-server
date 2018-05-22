package project.jinshang.mod_credit.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class CreditApplyRecord {
    private Long id;

    @ApiModelProperty(notes = "申请人id")
    private Long memberid;

    @ApiModelProperty(notes = "申请信用额度")
    private BigDecimal limitmoney;

    @ApiModelProperty(notes = "申请单位")
    private String company;

    @ApiModelProperty(notes = "单位地址")
    private String address;

    @ApiModelProperty(notes = "联系人")
    private String contract;

    @ApiModelProperty(notes = "联系电话")
    private String phone;


    @ApiModelProperty(notes = "备注")
    private String description;

    @ApiModelProperty(notes = "营业执照")
    private String license;

    @ApiModelProperty(notes = "状态0=待审核1=待受理2=受理中3=待复核4=已开通5=已拒绝6=已撤消")
    private Short state;

    @ApiModelProperty(notes = "审核人")
    private String auditname;

    @ApiModelProperty(notes = "审核人id")
    private Long auditid;

    @ApiModelProperty(notes = "受理人")
    private String officer;

    @ApiModelProperty(notes = "受理人id")
    private Long officerid;

    @ApiModelProperty(notes = "复核人")
    private String reviewer;

    @ApiModelProperty(notes = "复核人id")
    private Long reviewerid;

    @ApiModelProperty(notes = "审核时间")
    private Date audittime;

    @ApiModelProperty(notes = "确认受理时间")
    private Date confirmofficetime;

    @ApiModelProperty(notes = "受理时间")
    private Date officetime;

    @ApiModelProperty(notes = "复核时间")
    private Date reviewtime;

    @ApiModelProperty(notes = "主业务员")
    private String mainserver;

    @ApiModelProperty(notes = "辅业务员")
    private String assistserver;

    @ApiModelProperty(notes = "主业务员id")
    private Long mainserverid;

    @ApiModelProperty(notes = "辅业务员id")
    private Long assistserverid;

    @ApiModelProperty(notes = "创建时间")
    private Date createtime;

    @ApiModelProperty(notes = "撤消原因")
    private String cancelreason;

    private Date expectarrivaltime;

    private Integer acceptdays;

    private String entering;

    private Long enteringid;

    private String agreementno;

    private Date signtime;

    private String signaddr;

    private String agreementfile;



    private BigDecimal applymoney;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public BigDecimal getLimitmoney() {
        return limitmoney;
    }

    public void setLimitmoney(BigDecimal limitmoney) {
        this.limitmoney = limitmoney;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract == null ? null : contract.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license == null ? null : license.trim();
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public String getAuditname() {
        return auditname;
    }

    public void setAuditname(String auditname) {
        this.auditname = auditname == null ? null : auditname.trim();
    }

    public Long getAuditid() {
        return auditid;
    }

    public void setAuditid(Long auditid) {
        this.auditid = auditid;
    }

    public String getOfficer() {
        return officer;
    }

    public void setOfficer(String officer) {
        this.officer = officer == null ? null : officer.trim();
    }

    public Long getOfficerid() {
        return officerid;
    }

    public void setOfficerid(Long officerid) {
        this.officerid = officerid;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer == null ? null : reviewer.trim();
    }

    public Long getReviewerid() {
        return reviewerid;
    }

    public void setReviewerid(Long reviewerid) {
        this.reviewerid = reviewerid;
    }

    public Date getAudittime() {
        return audittime;
    }

    public void setAudittime(Date audittime) {
        this.audittime = audittime;
    }

    public Date getConfirmofficetime() {
        return confirmofficetime;
    }

    public void setConfirmofficetime(Date confirmofficetime) {
        this.confirmofficetime = confirmofficetime;
    }

    public Date getOfficetime() {
        return officetime;
    }

    public void setOfficetime(Date officetime) {
        this.officetime = officetime;
    }

    public Date getReviewtime() {
        return reviewtime;
    }

    public void setReviewtime(Date reviewtime) {
        this.reviewtime = reviewtime;
    }

    public String getMainserver() {
        return mainserver;
    }

    public void setMainserver(String mainserver) {
        this.mainserver = mainserver == null ? null : mainserver.trim();
    }

    public String getAssistserver() {
        return assistserver;
    }

    public void setAssistserver(String assistserver) {
        this.assistserver = assistserver == null ? null : assistserver.trim();
    }

    public Long getMainserverid() {
        return mainserverid;
    }

    public void setMainserverid(Long mainserverid) {
        this.mainserverid = mainserverid;
    }

    public Long getAssistserverid() {
        return assistserverid;
    }

    public void setAssistserverid(Long assistserverid) {
        this.assistserverid = assistserverid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getExpectarrivaltime() {
        return expectarrivaltime;
    }

    public void setExpectarrivaltime(Date expectarrivaltime) {
        this.expectarrivaltime = expectarrivaltime;
    }

    public Integer getAcceptdays() {
        return acceptdays;
    }

    public void setAcceptdays(Integer acceptdays) {
        this.acceptdays = acceptdays;
    }

    public String getEntering() {
        return entering;
    }

    public void setEntering(String entering) {
        this.entering = entering;
    }

    public Long getEnteringid() {
        return enteringid;
    }

    public void setEnteringid(Long enteringid) {
        this.enteringid = enteringid;
    }

    public String getAgreementno() {
        return agreementno;
    }

    public void setAgreementno(String agreementno) {
        this.agreementno = agreementno;
    }

    public Date getSigntime() {
        return signtime;
    }

    public void setSigntime(Date signtime) {
        this.signtime = signtime;
    }

    public String getSignaddr() {
        return signaddr;
    }

    public void setSignaddr(String signaddr) {
        this.signaddr = signaddr;
    }

    public String getAgreementfile() {
        return agreementfile;
    }

    public void setAgreementfile(String agreementfile) {
        this.agreementfile = agreementfile;
    }

    public BigDecimal getApplymoney() {
        return applymoney;
    }

    public void setApplymoney(BigDecimal applymoney) {
        this.applymoney = applymoney;
    }

    public String getCancelreason() {
        return cancelreason;
    }

    public void setCancelreason(String cancelreason) {
        this.cancelreason = cancelreason;
    }
}