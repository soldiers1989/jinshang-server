package project.jinshang.mod_product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_product.BillingRecordMapper;
import project.jinshang.mod_product.bean.BillingRecordExample;

import java.math.BigDecimal;

/**
 * create : wyh
 * date : 2018/3/8
 */

@Service
public class BillingRecordService {


    @Autowired
    private BillingRecordMapper billingRecordMapper;


    public  int countByExample(BillingRecordExample example){
        return  billingRecordMapper.countByExample(example);
    }


    /**
     * 订单完成后，将提交订单时就申请开票的设置为待开票状态
     * @param orderid
     * @param memberid
     * @param billcash (增加的开票金额)
     * @return
     */
    public void updateForwordBillingState(String orderid,Long memberid,BigDecimal billcash){
        billingRecordMapper.updateForwordBillingState(orderid,memberid, billcash);
    }

    /**
     * 后台对订单商品修改，对开票金额要同步修改
     * @param orderid
     * @param memberid
     * @param billcash
     */
    public  void  updateAdminDecOrderProductnum(String orderid,Long memberid,BigDecimal billcash){
        billingRecordMapper.updateAdminDecOrderProductnum(orderid,memberid, billcash);
    }

    /**
     * 根据订单id 和用户id 修改开票金额
     * @param orderid
     * @param memberid
     * @param billcash
     */
    public  void  updateBillcashByMemberAndOrderid(String orderid,Long memberid,BigDecimal billcash){
        billingRecordMapper.updateBillcashByMemberAndOrderid(orderid,memberid, billcash);
    }


}
