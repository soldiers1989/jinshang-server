package project.jinshang.mod_member.bean;

import java.util.List;

public class SellerProductCategory extends SellerCategory{

    private List<SellerProductCategory> list;

    public List<SellerProductCategory> getList() {
        return list;
    }

    public void setList(List<SellerProductCategory> list) {
        this.list = list;
    }
}
