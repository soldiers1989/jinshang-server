package project.jinshang.mod_member;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.MemberLogOperator;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.constant.SellerAuthorityConst;
import project.jinshang.common.utils.CommonUtils;
import project.jinshang.common.utils.ProductCategoryUtils;
import project.jinshang.mod_company.bean.SellerCompanyInfo;
import project.jinshang.mod_company.service.SellerCompanyInfoService;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.bean.MemberGrade;
import project.jinshang.mod_member.bean.MemberRateSetting;
import project.jinshang.mod_member.bean.MemberRateSettingExample;
import project.jinshang.mod_member.bean.dto.MemberRateSettingViewDto;
import project.jinshang.mod_member.service.MemberGradeService;
import project.jinshang.mod_member.service.MemberRateSettingService;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_product.bean.Categories;
import project.jinshang.mod_product.bean.MemberOperateLog;
import project.jinshang.mod_product.service.CategoriesService;
import project.jinshang.mod_product.service.MemberOperateLogService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * create : wyh
 * date : 2017/12/11
 */
@RestController
@RequestMapping("/rest/seller/memberRateSetting")
@Api(tags = "卖家减价率设置")
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME})
@Transactional(rollbackFor = Exception.class)
public class MemberRateSettingSellerAction {

    @Autowired
    private SellerCompanyInfoService sellerCompanyInfoService;
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private MemberRateSettingService memberRateSettingService;
    @Autowired
    private MemberGradeService memberGradeService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberLogOperator memberLogOperator;
    @Autowired
    private MemberOperateLogService memberOperateLogService;




    @PostMapping("/list")
    @ApiOperation("减价率列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentcateid",value = "父级id",paramType = "query",dataType = "int",required = true),
            @ApiImplicitParam(name = "gradeid",value = "会员等级id",paramType = "query",dataType = "int",required = true),
    })
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.PRICESETTING+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public RateSettingListRet list(@RequestParam(required = true,defaultValue = "0") long parentcateid,
                                        @RequestParam(required = true) long gradeid, Model model) {
        RateSettingListRet rateSettingListRet = new RateSettingListRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        SellerCompanyInfo sellerCompanyInfo = sellerCompanyInfoService.getSellerCompanyByMemberid(member.getId());

        if (sellerCompanyInfo == null) {
            rateSettingListRet.setMessage("未查询到卖家公司信息");
            rateSettingListRet.setResult(BasicRet.ERR);
            return rateSettingListRet;
        }

        Long[] cateArr = (Long[]) sellerCompanyInfo.getBusinesscategory();
        if (cateArr == null || cateArr.length == 0) {
            rateSettingListRet.setMessage("未查询到卖家公司可经营的分类信息");
            rateSettingListRet.setResult(BasicRet.ERR);
            return rateSettingListRet;
        }

        List<Categories> list = categoriesService.getCategoryByParentid(parentcateid);

        if(list == null || list.size()==0){
            rateSettingListRet.setMessage("没有子分类了");
            rateSettingListRet.setResult(BasicRet.ERR);
            return rateSettingListRet;
        }


        List<Categories> canPushCateList  =  new ArrayList<>();
        if(parentcateid == 0){  //如果是一级类要筛选出可经营的类别
            for(Categories categories : list){
                for(Long cateid : cateArr){
                    if(cateid.equals(categories.getId())) canPushCateList.add(categories);
                }
            }
        }else{
            canPushCateList = list;
        }

        if(canPushCateList.size()==0){
            rateSettingListRet.setResult(BasicRet.ERR);
            rateSettingListRet.setMessage("没有可发布的分类");
            return  rateSettingListRet;
        }

        MemberRateSettingExample memberRateSettingExample = new MemberRateSettingExample();
        MemberRateSettingExample.Criteria criteria = memberRateSettingExample.createCriteria();
        criteria.andMembergradeidEqualTo(gradeid).andMemberidEqualTo(member.getId()).andParentlevelidEqualTo(parentcateid);
        List<MemberRateSetting> rateSettingList = memberRateSettingService.selectByExample(memberRateSettingExample);

        List<MemberRateSettingViewDto> resSettList = new ArrayList<>();



        MemberRateSetting parentRateSetting = null;

        List<Categories> allCategories =   categoriesService.getAll();
        List<Categories> subCategory = new ArrayList<>();
        ProductCategoryUtils.get_parent_list(allCategories,parentcateid,subCategory);


        for(Categories sub : subCategory){
           parentRateSetting = memberRateSettingService.getByMemberid_levelid_gradeid(member.getId(),sub.getId(),gradeid);
           if(parentRateSetting != null){
               break;
           }
        }



        for(Categories categories : canPushCateList){
            MemberRateSettingViewDto memberRateSettingViewDto =  new MemberRateSettingViewDto();
            boolean b = false;
            for(MemberRateSetting setting : rateSettingList){
                if(categories.getId().equals(setting.getLevelid())){
                    memberRateSettingViewDto.setParentlevelid(setting.getParentlevelid());
                    memberRateSettingViewDto.setLevelid(setting.getLevelid());
                    memberRateSettingViewDto.setRate(setting.getRate());
                    memberRateSettingViewDto.setMembergradeid(setting.getMembergradeid());
                    b = true;
                }
            }

            if(!b){
                memberRateSettingViewDto.setLevelid(categories.getId());
                memberRateSettingViewDto.setParentlevelid(categories.getParentid());
                memberRateSettingViewDto.setRate(parentRateSetting == null ? new BigDecimal(1) : parentRateSetting.getRate());
                memberRateSettingViewDto.setMembergradeid(gradeid);
            }

            memberRateSettingViewDto.setCategoryname(categories.getName());
            memberRateSettingViewDto.setMembergradeid(gradeid);
            memberRateSettingViewDto.setMemberid(member.getId());
            resSettList.add(memberRateSettingViewDto);
        }

        rateSettingListRet.data.list = resSettList;
        rateSettingListRet.setMessage("查询成功");
        rateSettingListRet.setResult(BasicRet.SUCCESS);

        return  rateSettingListRet;

    }


    @PostMapping("/setting")
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.PRICESETTING+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public  BasicRet setting(@RequestParam(required = true) long levelid,
                             @RequestParam(required = true) long membergradeid,
                             @RequestParam(required = true)BigDecimal rate,Model model,HttpServletRequest request){
        BasicRet basicRet = new BasicRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        Categories categories = categoriesService.getById(levelid);
        if(categories == null){
            basicRet.setMessage("类别不正确");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }


        MemberRateSettingExample memberRateSettingExample = new MemberRateSettingExample();
        MemberRateSettingExample.Criteria criteria = memberRateSettingExample.createCriteria();
        criteria.andMembergradeidEqualTo(membergradeid).andMemberidEqualTo(member.getId()).andLevelidEqualTo(levelid);
        List<MemberRateSetting> rateSettingList = memberRateSettingService.selectByExample(memberRateSettingExample);

        MemberRateSetting setting = new MemberRateSetting();
        setting.setLevelid(levelid);
        setting.setMembergradeid(membergradeid);
        setting.setMemberid(member.getId());
        setting.setRate(rate);
        setting.setUpdatetime(new Date());
        setting.setParentlevelid(categories.getParentid());
        if(rateSettingList.size()==1){ //更新
            setting.setId(rateSettingList.get(0).getId());
            memberRateSettingService.updateByPrimaryKeySelective(setting);
        }else{ //插入
            memberRateSettingService.add(setting);
        }

        memberLogOperator.saveMemberLog(member,null,"更新"+categories.getName()+"的减价率","",request,memberOperateLogService);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("设置成功");
        return  basicRet;
    }




    @RequestMapping(value = "/grade/listAll",method = RequestMethod.POST)
    @ApiOperation("会员等级列表")
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.PRICESETTING+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public MemberGradeListRet listAll(){
        MemberGradeListRet memberGradeListRet = new MemberGradeListRet();

        memberGradeListRet.data.list =  memberGradeService.list();
        memberGradeListRet.setMessage("查询成功");
        memberGradeListRet.setResult(BasicRet.SUCCESS);
        return  memberGradeListRet;
    }




    @PostMapping("/set/state")
    @ApiOperation("减价率状态开启、关闭")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "状态 1=开启 0=关闭",name ="state",paramType = "query",dataType = "int")
    })
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.PRICESETTING+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public  BasicRet setState(@RequestParam(required = true) short state, Model model, HttpServletRequest request){
        BasicRet basicRet = new BasicRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        if(state != Quantity.STATE_1 && state != Quantity.STATE_0){
            basicRet.setMessage("状态不合法");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }
        Member updateMember = new Member();
        updateMember.setId(member.getId());
        updateMember.setMembersettingstate(state);
        memberService.updateByPrimaryKeySelective(updateMember);

        //更新session
        member.setMembersettingstate(state);
        model.asMap().put(AppConstant.MEMBER_SESSION_NAME,member);

        String opt = state == Quantity.STATE_1 ? "开启" : "关闭";
        memberLogOperator.saveMemberLog(member,null,opt+ "减价设置","",request,memberOperateLogService);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        return basicRet;
    }



    private  class  MemberGradeListRet extends  BasicRet{
        private class MemberGradeData {
            private  List<MemberGrade> list;

            public List<MemberGrade> getList() {
                return list;
            }

            public void setList(List<MemberGrade> list) {
                this.list = list;
            }
        }

        private  MemberGradeData data = new MemberGradeData();

        public MemberGradeData getData() {
            return data;
        }

        public void setData(MemberGradeData data) {
            this.data = data;
        }
    }



    private  class  RateSettingListRet extends BasicRet{
        private  class  RateSettingData{
            private  List<MemberRateSettingViewDto> list;

            public List<MemberRateSettingViewDto> getList() {
                return list;
            }

            public void setList(List<MemberRateSettingViewDto> list) {
                this.list = list;
            }
        }

        private  RateSettingData data=new RateSettingData();

        public RateSettingData getData() {
            return data;
        }

        public void setData(RateSettingData data) {
            this.data = data;
        }
    }


}
