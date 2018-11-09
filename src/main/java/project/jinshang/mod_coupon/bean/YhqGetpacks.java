package project.jinshang.mod_coupon.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * null
 * 
 * @author zzy
 * 
 * @date 2018-05-26
 */
public class YhqGetpacks {

    @ApiModelProperty("ID自增")
    private Long id;

    @ApiModelProperty("礼包ID")
    private Long packsid;

    @ApiModelProperty("礼包编码")
    private String packsno;

    @ApiModelProperty("领取用户")
    private Long memberid;

    @ApiModelProperty("领取时间")
    private Date gettime;

    @ApiModelProperty("发放优惠券列表")
    private String ticketlist;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPacksid() {
        return packsid;
    }

    public void setPacksid(Long packsid) {
        this.packsid = packsid;
    }

    public String getPacksno() {
        return packsno;
    }

    public void setPacksno(String packsno) {
        this.packsno = packsno == null ? null : packsno.trim();
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public Date getGettime() {
        return gettime;
    }

    public void setGettime(Date gettime) {
        this.gettime = gettime;
    }

    public String getTicketlist() {
        return ticketlist;
    }

    public void setTicketlist(String ticketlist) {
        this.ticketlist = ticketlist == null ? null : ticketlist.trim();
    }
}