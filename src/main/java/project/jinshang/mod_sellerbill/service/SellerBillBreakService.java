package project.jinshang.mod_sellerbill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_sellerbill.bean.SellerBillBreak;
import project.jinshang.mod_sellerbill.bean.SellerBillBreakExample;
import project.jinshang.mod_sellerbill.mapper.SellerBillBreakMapper;

import java.util.List;

@Service
public class SellerBillBreakService {
    @Autowired
    private SellerBillBreakMapper sellerBillBreakMapper;

    public void insertSellerBillBreak(SellerBillBreak sellerBillBreak) {
        sellerBillBreakMapper.insertSelective(sellerBillBreak);
    }

    public List<SellerBillBreak> getBySellerbillid(Long sellerbillid) {
        SellerBillBreakExample example = new SellerBillBreakExample();
        example.setOrderByClause("id desc");
        SellerBillBreakExample.Criteria criteria = example.createCriteria();
        criteria.andSellerbillidEqualTo(sellerbillid);
        return sellerBillBreakMapper.selectByExample(example);
    }

    public void deleteById(Long id) {
        sellerBillBreakMapper.deleteByPrimaryKey(id);
    }

}
