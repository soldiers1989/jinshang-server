package project.jinshang.mod_company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.jinshang.mod_company.BuyerCompanyInfoMapper;
import project.jinshang.mod_company.bean.BuyerCompanyInfo;
import project.jinshang.mod_company.bean.BuyerCompanyInfoExample;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.service.MemberService;

import java.util.Date;
import java.util.List;

/**
 * create : wyh
 * date : 2017/11/4
 */

@Service
public class BuyerCompanyService {

    @Autowired
    private BuyerCompanyInfoMapper buyerCompanyInfoMapper;

    @Autowired
    private MemberService memberService;


    /**
     * 申请成为公司帐号
     * @param info
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public  void applyForCompany(BuyerCompanyInfo info){
       info.setCreatedate(new Date());
       int c = buyerCompanyInfoMapper.insert(info);
       if(c>0){
        Member member =  new Member();
        member.setId(new Long(info.getMemberid()));
        member.setCompany(true);
        memberService.updateMember(member);
       }
    }


    public  BuyerCompanyInfo getBuyerCompanyInfoByMemberId(long memberId){
//        BuyerCompanyInfoExample buyerCompanyInfoExample=new BuyerCompanyInfoExample();
//        buyerCompanyInfoExample.createCriteria().andMemberidEqualTo(memberId);
//        List<BuyerCompanyInfo> list=buyerCompanyInfoMapper.selectByExample(buyerCompanyInfoExample);
//        if(list != null && list.size()>0){
//            return  list.get(0);
//        }
//        return  null;
        return  buyerCompanyInfoMapper.getBuyerCompanyInfoByMemberId(memberId);
    }



    public  BuyerCompanyInfo getById(long id){
        return buyerCompanyInfoMapper.selectByPrimaryKey(id);
    }

    public  void updateByPrimaryKeySelective(BuyerCompanyInfo info){
        buyerCompanyInfoMapper.updateByPrimaryKeySelective(info);
    }

    public void addBuyerCompanyInfo(BuyerCompanyInfo buyerCompanyInfo){
        buyerCompanyInfoMapper.insertSelective(buyerCompanyInfo);
    }

}
