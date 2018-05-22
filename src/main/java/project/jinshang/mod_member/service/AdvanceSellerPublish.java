package project.jinshang.mod_member.service;

import project.jinshang.mod_company.service.SellerCompanyCacheService;

/**
 * create : wyh
 * date : 2018/3/13
 */


public  class  AdvanceSellerPublish implements Runnable{

    private  SellerCompanyCacheService sellerCompanyCacheService;
    private  Long memberid;

    public  AdvanceSellerPublish(SellerCompanyCacheService sellerCompanyCacheService, Long memberid){
        this.sellerCompanyCacheService = sellerCompanyCacheService;
        this.memberid =  memberid;
    }

    @Override
    public void run() {
        sellerCompanyCacheService.getPushCategory(memberid);
    }
}

