package project.jinshang.mod_sellerbill.bean;

import mizuki.project.core.restserver.config.BasicRet;

import java.util.List;

public  class  SellerBillDetailRet extends BasicRet {
    public   class SellerBillDetailData {
        private  SellerBill sellerBill;
        private List<SellerBillOrderView> sellerBillOrderViewList;

        public SellerBill getSellerBill() {
            return sellerBill;
        }

        public void setSellerBill(SellerBill sellerBill) {
            this.sellerBill = sellerBill;
        }

        public List<SellerBillOrderView> getSellerBillOrderViewList() {
            return sellerBillOrderViewList;
        }

        public void setSellerBillOrderViewList(List<SellerBillOrderView> sellerBillOrderViewList) {
            this.sellerBillOrderViewList = sellerBillOrderViewList;
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