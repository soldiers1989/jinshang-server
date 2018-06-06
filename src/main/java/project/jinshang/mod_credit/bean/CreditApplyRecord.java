package project.jinshang.mod_credit.bean;

import java.math.BigDecimal;
import java.util.Date;

public class CreditApplyRecord {
    private Long id;

    private Long memberid;

    private BigDecimal limitmoney;

    private String company;

    private String address;

    private String contract;

    private String phone;

    private String description;

    private String license;

    private Short state;

    private String auditname;

    private Long auditid;

    private String officer;

    private Long officerid;

    private String reviewer;

    private Long reviewerid;

    private Date audittime;

    private Date confirmofficetime;

    private Date officetime;

    private Date reviewtime;

    private String mainserver;

    private String assistserver;

    private Long mainserverid;

    private Long assistserverid;

    private Date createtime;

    private Date expectarrivaltime;

    private Integer acceptdays;

    private String entering;

    private Long enteringid;

    private String agreementno;

    private Date signtime;

    private String signaddr;

    private String agreementfile;

    private BigDecimal applymoney;

    private String cancelreason;

    private String reviewnotes;

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
        this.entering = entering == null ? null : entering.trim();
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
        this.agreementno = agreementno == null ? null : agreementno.trim();
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
        this.signaddr = signaddr == null ? null : signaddr.trim();
    }

    public String getAgreementfile() {
        return agreementfile;
    }

    public void setAgreementfile(String agreementfile) {
        this.agreementfile = agreementfile == null ? null : agreementfile.trim();
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
        this.cancelreason = cancelreason == null ? null : cancelreason.trim();
    }

    public String getReviewnotes() {
        return reviewnotes;
    }

    public void setReviewnotes(String reviewnotes) {
        this.reviewnotes = reviewnotes == null ? null : reviewnotes.trim();
    }
}