package project.jinshang.mod_admin.mod_showcategory.bean.dto;

import io.swagger.annotations.ApiModelProperty;
import project.jinshang.mod_admin.mod_showcategory.bean.ShowCate;
import project.jinshang.mod_admin.mod_showcategory.bean.ShowCatedetail;

import java.util.List;

/**
 * create : wyh
 * date : 2018/2/1
 */
public class ShowCateView extends ShowCatedetail{

    @ApiModelProperty(notes = "分类列表")
    private List<ShowCategory> cateList;


    private  Long level1id;

    public List<ShowCategory> getCateList() {
        return cateList;
    }

    public void setCateList(List<ShowCategory> cateList) {
        this.cateList = cateList;
    }

    public Long getLevel1id() {
        return level1id;
    }

    public void setLevel1id(Long level1id) {
        this.level1id = level1id;
    }
}
