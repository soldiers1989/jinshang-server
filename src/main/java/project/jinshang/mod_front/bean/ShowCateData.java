package project.jinshang.mod_front.bean;

import java.io.Serializable;
import java.util.List;

/**
 * create : wyh
 * date : 2018/2/8
 */
public class ShowCateData implements Serializable {

    private List<ShowCateFrontView> list;

    public List<ShowCateFrontView> getList() {
        return list;
    }

    public void setList(List<ShowCateFrontView> list) {
        this.list = list;
    }
}
