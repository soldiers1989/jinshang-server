package project.jinshang.mod_admin.mod_count.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 
 * @author zzy
 * 
 * @date 2018-09-15
 */
public class SearchKeyRecord {
    private Long id;

    @ApiModelProperty(notes = "搜索关键词")
    private String searchkey;

    @ApiModelProperty(notes = "创建时间")
    private Date createtime;

    @ApiModelProperty(notes = "0=生活类和工业品、1=工业品、2=生活类 3=紧固件")
    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSearchkey() {
        return searchkey;
    }

    public void setSearchkey(String searchkey) {
        this.searchkey = searchkey == null ? null : searchkey.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}