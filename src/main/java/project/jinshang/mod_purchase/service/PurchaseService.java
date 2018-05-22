package project.jinshang.mod_purchase.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_purchase.*;
import project.jinshang.mod_purchase.bean.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseBillMapper purchaseBillMapper;

    @Autowired
    private PurchaseCapitalMapper purchaseCapitalMapper;

    @Autowired
    private PurchaseProductMapper purchaseProductMapper;

    @Autowired
    private PurchaseRecordMapper purchaseRecordMapper;

    @Autowired
    private PurchaseStoreRecordMapper purchaseStoreRecordMapper;

    @Autowired
    private PurchaseSupplyMapper purchaseSupplyMapper;

    /**
     * 保存开票
     * @param purchaseBill
     */
    public void savePurchaseBill(PurchaseBill purchaseBill){
        purchaseBillMapper.insertSelective(purchaseBill);
    }

    /**
     * 根据用户id获取供应商列表
     * @param memberid
     * @return
     */
    public List<PurchaseSupply> getPurchaseSupplyByMemberId(Long memberid){
        PurchaseSupplyExample purchaseSupplyExample = new PurchaseSupplyExample();
        purchaseSupplyExample.createCriteria().andMemberidEqualTo(memberid);
        return purchaseSupplyMapper.selectByExample(purchaseSupplyExample);
    }

    /**
     * 获取已开发票
     * @param param
     * @return
     */
    public PageInfo getPurchaseBillList(PurchaseQueryParam param){
        PageHelper.startPage(param.getPageNo(),param.getPageSize()).setOrderBy("createtime desc");
        PurchaseBillExample purchaseBillExample = new PurchaseBillExample();
        PurchaseBillExample.Criteria criteria = purchaseBillExample.createCriteria();

        if(StringUtils.hasText(param.getSupplier())){
            String supplier = "%"+param.getSupplier()+"%";
            criteria.andSupplierLike(supplier);
        }
        if(param.getSupplyid()!=null){
            criteria.andSupplyidEqualTo(param.getSupplyid());
        }
        if(StringUtils.hasText(param.getBillno())){
            String billNo = "%"+param.getBillno()+"%";
            criteria.andBillnoLike(billNo);
        }
        if(StringUtils.hasText(param.getExpressno())){
            String expressNo = "%"+param.getExpressno()+"%";
            criteria.andExpressnoLike(expressNo);
        }
        if(param.getBillstart()!=null){
            criteria.andBillstartGreaterThanOrEqualTo(param.getBillstart());
        }
        if(param.getBillend()!=null){
            Date endTime = param.getBillend();
            Calendar c = Calendar.getInstance();
            c.setTime(endTime);
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            Date tomorrow = c.getTime();
            criteria.andBillendLessThanOrEqualTo(tomorrow);
        }

        List<PurchaseBill> list = purchaseBillMapper.selectByExample(purchaseBillExample);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 保存供应商
     * @param purchaseSupply
     */
    public void savePurchaseSupply(PurchaseSupply purchaseSupply){
        purchaseSupplyMapper.insertSelective(purchaseSupply);
    }

}

