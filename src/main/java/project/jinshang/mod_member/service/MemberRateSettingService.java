package project.jinshang.mod_member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.ProductCategoryUtils;
import project.jinshang.mod_member.MemberRateSettingMapper;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.bean.MemberRateSetting;
import project.jinshang.mod_member.bean.MemberRateSettingExample;
import project.jinshang.mod_product.bean.Categories;
import project.jinshang.mod_product.service.CategoriesService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * create : wyh
 * date : 2017/12/11
 */

@Service
public class MemberRateSettingService {

    @Autowired
    private MemberRateSettingMapper memberRateSettingMapper;

    @Autowired
    private CategoriesService categoriesService;


    @Autowired
    private  MemberService memberService;


    public  List<MemberRateSetting> selectByExample(MemberRateSettingExample example){
        return  memberRateSettingMapper.selectByExample(example);
    }


    public  void  add(MemberRateSetting setting){
        memberRateSettingMapper.insert(setting);
    }

    public  void  updateByPrimaryKeySelective(MemberRateSetting setting){
        memberRateSettingMapper.updateByPrimaryKeySelective(setting);
    }



    public MemberRateSetting getByMemberid_levelid_gradeid(long memberid,long levelid,long gradeid){
       return memberRateSettingMapper.getByMemberid_levelid_gradeid(memberid,levelid,gradeid);
    }


    /**
     * 查询减价率，如果该分类下没有设置这依次向父类查找
     * @param sellerid
     * @param levelid
     * @param gradeid
     * @return
     */
    public  MemberRateSetting getRecursiveSetting(long sellerid,long levelid,long gradeid){
        List<Categories> allCategories =   categoriesService.getAll();
        List<Categories> subCategory = new ArrayList<>();
        ProductCategoryUtils.get_parent_list(allCategories,levelid,subCategory);
        MemberRateSetting memberRateSetting;
        for(Categories sub : subCategory){
            memberRateSetting  = getByMemberid_levelid_gradeid(sellerid,sub.getId(),gradeid);
            if(memberRateSetting != null){
               return  memberRateSetting;
            }
        }

        memberRateSetting = new MemberRateSetting();
        memberRateSetting.setRate(new BigDecimal(1));
        return  memberRateSetting;
    }



    public  MemberRateSetting getSetting(long sellerid,long levelid,long gradeid){
        Member member = memberService.getMemberById(sellerid);
        MemberRateSetting memberRateSetting = new MemberRateSetting();
        memberRateSetting.setRate(new BigDecimal(1));
        if(member != null && member.getMembersettingstate() != null  && member.getMembersettingstate() == Quantity.STATE_1){
            memberRateSetting = this.getRecursiveSetting(sellerid,levelid,gradeid);
        }
        return  memberRateSetting;
    }



}
