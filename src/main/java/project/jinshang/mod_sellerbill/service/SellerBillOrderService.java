package project.jinshang.mod_sellerbill.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_sellerbill.bean.SellerBillOrder;
import project.jinshang.mod_sellerbill.bean.SellerBillOrderExample;
import project.jinshang.mod_sellerbill.mapper.SellerBillOrderMapper;

import java.util.List;

@Service
public class SellerBillOrderService {

    @Autowired
    private SellerBillOrderMapper sellerBillOrderMapper;

    public  void  insert(SellerBillOrder sellerBillOrder){
        sellerBillOrderMapper.insertSelective(sellerBillOrder);
    }

    public  void  deleteBySellerbillid(long sellerbillid){
        SellerBillOrderExample example = new SellerBillOrderExample();
        SellerBillOrderExample.Criteria criteria = example.createCriteria();
        criteria.andSellerbillidEqualTo(sellerbillid);
        sellerBillOrderMapper.deleteByExample(example);
    }


    public int batchInsert(List<SellerBillOrder> list){
        return  sellerBillOrderMapper.batchInsert(list);
    }


    public List<SellerBillOrder> getBySellerbillid(long sellerbillid){
        SellerBillOrderExample example = new SellerBillOrderExample();
        example.setOrderByClause("id desc");
        SellerBillOrderExample.Criteria criteria = example.createCriteria();
        criteria.andSellerbillidEqualTo(sellerbillid);
        return sellerBillOrderMapper.selectByExample(example);
    }
}
