package project.jinshang.mod_sellerbill.bean;

import mizuki.project.core.restserver.config.BasicRet;

import java.util.List;

public  class SellerPenaltyBillDetailRet extends BasicRet {
    public   class SellerBillDetailData {

       private SellerBill sellerBill;

       private  List<SellerBillBreakView> sellerBillBreakList;

        public SellerBill getSellerBill() {
            return sellerBill;
        }

        public void setSellerBill(SellerBill sellerBill) {
            this.sellerBill = sellerBill;
        }

        public List<SellerBillBreakView> getSellerBillBreakList() {
            return sellerBillBreakList;
        }

        public void setSellerBillBreakList(List<SellerBillBreakView> sellerBillBreakList) {
            this.sellerBillBreakList = sellerBillBreakList;
        }


    }


    private  SellerBillDetailData data = new SellerBillDetailData();

    public SellerBillDetailData getData() {
        return data;
    }

    public void setData(SellerBillDetailData data) {
        this.data = data;
    }
}