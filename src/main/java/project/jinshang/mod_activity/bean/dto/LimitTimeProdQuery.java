package project.jinshang.mod_activity.bean.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * create : wyh
 * date : 2018/1/11
 */
public class LimitTimeProdQuery {

   private  Short state;

   private  Long memberid;

   private  String  activitytitle;

   @ApiModelProperty(notes = "活动商品类目")
   private  Long activitycateid;

    @ApiModelProperty(notes = "活动开始时间")
   private Date begintime;

    @ApiModelProperty(notes = "活动结束时间")
   private  Date endtime;




    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public String getActivitytitle() {
        return activitytitle;
    }

    public void setActivitytitle(String activitytitle) {
        this.activitytitle = activitytitle;
    }

    public Long getActivitycateid() {
        return activitycateid;
    }

    public void setActivitycateid(Long activitycateid) {
        this.activitycateid = activitycateid;
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
}
