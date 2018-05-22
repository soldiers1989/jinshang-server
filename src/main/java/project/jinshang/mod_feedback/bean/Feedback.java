package project.jinshang.mod_feedback.bean;

import java.util.Date;

public class Feedback {
    private Integer id;

    private Integer feedbackTypeId;

    private String feedbackContent;

    private String phone;

    private Long memberId;

    private Short dispose;

    private Long disposePeopleId;

    private String disposeContent;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFeedbackTypeId() {
        return feedbackTypeId;
    }

    public void setFeedbackTypeId(Integer feedbackTypeId) {
        this.feedbackTypeId = feedbackTypeId;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Short getDispose() {
        return dispose;
    }

    public void setDispose(Short dispose) {
        this.dispose = dispose;
    }

    public Long getDisposePeopleId() {
        return disposePeopleId;
    }

    public void setDisposePeopleId(Long disposePeopleId) {
        this.disposePeopleId = disposePeopleId;
    }

    public String getDisposeContent() {
        return disposeContent;
    }

    public void setDisposeContent(String disposeContent) {
        this.disposeContent = disposeContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}