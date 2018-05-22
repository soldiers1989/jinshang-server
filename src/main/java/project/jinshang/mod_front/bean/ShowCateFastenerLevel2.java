package project.jinshang.mod_front.bean;

import io.swagger.annotations.ApiModelProperty;
import project.jinshang.mod_admin.mod_showcategory.bean.dto.ShowCategory;

import java.util.List;

/**
 * create : wyh
 * date : 2018/2/1
 */
public class ShowCateFastenerLevel2 {

    @ApiModelProperty(notes = "2级分类id")
    private  Long level2id;

    @ApiModelProperty(notes = "2级分类名称")
    private  String level2category;



    @ApiModelProperty(notes = "该2级分类下的3级分类")
    private List<ShowCategory> showCategoryList;

    public Long getLevel2id() {
        return level2id;
    }

    public void setLevel2id(Long level2id) {
        this.level2id = level2id;
    }

    public String getLevel2category() {
        return level2category;
    }

    public void setLevel2category(String level2category) {
        this.level2category = level2category;
    }

    public List<ShowCategory> getShowCategoryList() {
        return showCategoryList;
    }

    public void setShowCategoryList(List<ShowCategory> showCategoryList) {
        this.showCategoryList = showCategoryList;
    }
}
