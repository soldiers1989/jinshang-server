package project.jinshang.mod_sellerbill.bean;

import java.util.List;

public class SellerBillDto extends SellerBill {

    private List<SellerBillBreak> sellerBillBreakList;

    public List<SellerBillBreak> getSellerBillBreakList() {
        return sellerBillBreakList;
    }

    public void setSellerBillBreakList(List<SellerBillBreak> sellerBillBreakList) {
        this.sellerBillBreakList = sellerBillBreakList;
    }
}
