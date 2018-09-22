package project.jinshang.mod_member.bean;

import java.util.Date;

/**
 *
 * 
 * @author zzy
 * 
 * @date 2018-07-12
 */
public class AdminWaysalesman {
    /**
     * id自增
     */
    private Long id;

    /**
     * 管理员id
     */
    private Long adminid;

    /**
     * 用户id
     */
    private Long userid;

    /**
     * 是否添加业务员管理 f 是 t 否
     */
    private Boolean disable;

    /**
     * 创建时间
     */
    private Date createdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdminid() {
        return adminid;
    }

    public void setAdminid(Long adminid) {
        this.adminid = adminid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}