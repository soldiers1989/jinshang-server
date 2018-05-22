package project.jinshang.mod_front.bean;

import io.swagger.annotations.ApiModelProperty;
import project.jinshang.mod_admin.mod_showcategory.bean.ShowCate;

import java.io.Serializable;
import java.util.List;

/**
 * create : wyh
 * date : 2018/2/1
 */
public class ShowCateFrontView implements Serializable{

    private  ShowCate showCate;

    @ApiModelProperty(notes = "其他类别的")
    private List<ShowCateOtherDetail> showCateOtherDetailList;

    @ApiModelProperty(notes = "紧固件类的")
    private  List<ShowCateFastenerDetail> showCateFastenerDetailList;


    public List<ShowCateOtherDetail> getShowCateOtherDetailList() {
        return showCateOtherDetailList;
    }

    public void setShowCateOtherDetailList(List<ShowCateOtherDetail> showCateOtherDetailList) {
        this.showCateOtherDetailList = showCateOtherDetailList;
    }

    public ShowCate getShowCate() {
        return showCate;
    }

    public void setShowCate(ShowCate showCate) {
        this.showCate = showCate;
    }

    public List<ShowCateFastenerDetail> getShowCateFastenerDetailList() {
        return showCateFastenerDetailList;
    }

    public void setShowCateFastenerDetailList(List<ShowCateFastenerDetail> showCateFastenerDetailList) {
        this.showCateFastenerDetailList = showCateFastenerDetailList;
    }
}
