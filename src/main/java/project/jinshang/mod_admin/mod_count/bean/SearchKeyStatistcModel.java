package project.jinshang.mod_admin.mod_count.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class SearchKeyStatistcModel {

    private Long id;

    @ApiModelProperty(notes = "搜索关键词")
    private String searchkey;

    @ApiModelProperty(notes = "统计数量")
    private Integer countnum;

    public String getSearchkey() {
        return searchkey;
    }

    public void setSearchkey(String searchkey) {
        this.searchkey = searchkey;
    }

    public Integer getCountnum() {
        return countnum;
    }

    public void setCountnum(Integer countnum) {
        this.countnum = countnum;
    }
}
