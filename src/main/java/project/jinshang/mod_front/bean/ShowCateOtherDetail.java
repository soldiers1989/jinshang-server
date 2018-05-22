package project.jinshang.mod_front.bean;

import project.jinshang.mod_admin.mod_showcategory.bean.dto.ShowCategory;

import java.io.Serializable;
import java.util.List;

/**
 * create : wyh
 * date : 2018/2/1
 */
public class ShowCateOtherDetail implements Serializable{

    private  Long level2id;


    private  String level2category;


    private List<ShowCategory> showCategoryList;


    public List<ShowCategory> getShowCategoryList() {
        return showCategoryList;
    }

    public void setShowCategoryList(List<ShowCategory> showCategoryList) {
        this.showCategoryList = showCategoryList;
    }

    public String getLevel2category() {
        return level2category;
    }

    public void setLevel2category(String level2category) {
        this.level2category = level2category;
    }

    public Long getLevel2id() {
        return level2id;
    }

    public void setLevel2id(Long level2id) {
        this.level2id = level2id;
    }
}
