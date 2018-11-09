package project.jinshang.mod_front.bean;

import java.util.List;

/**
 * 根据产品的类别进行分类
 *
 * @author xiazy
 * @create 2018-08-01 15:05
 **/
public class ShowCateDataByType {
    private List<ShowCateFrontView> listForFastener;
    private List<ShowCateFrontView> listForOther;

    public List<ShowCateFrontView> getListForFastener() {
        return listForFastener;
    }

    public void setListForFastener(List<ShowCateFrontView> listForFastener) {
        this.listForFastener = listForFastener;
    }

    public List<ShowCateFrontView> getListForOther() {
        return listForOther;
    }

    public void setListForOther(List<ShowCateFrontView> listForOther) {
        this.listForOther = listForOther;
    }
}
