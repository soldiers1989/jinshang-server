package project.jinshang.mod_company;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.constant.SellerAuthorityConst;
import project.jinshang.common.utils.CommonUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_company.bean.SellerCompanyInfo;
import project.jinshang.mod_company.bean.Store;
import project.jinshang.mod_company.service.BuyerCompanyService;
import project.jinshang.mod_company.service.SellerCompanyInfoService;
import project.jinshang.mod_company.service.StoreService;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.bean.SellerCategory;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_member.service.SellerCategoryService;
import project.jinshang.mod_product.bean.Categories;
import project.jinshang.mod_product.service.CategoriesService;
import project.jinshang.mod_shop.bean.ShopGrade;
import project.jinshang.mod_shop.service.ShopGradeService;

import java.util.*;

/**
 * create : wyh
 * date : 2017/11/4
 */
@RestController
@RequestMapping("/rest/seller")
@Api(tags = "卖家公司信息",description = "卖家公司信息相关接口")
@SessionAttributes(AppConstant.MEMBER_SESSION_NAME)
@Transactional(rollbackFor = Exception.class)
public class SellerCompanyAction {

    @Autowired
    private MemberService memberService;

    @Autowired
    private SellerCompanyInfoService sellerCompanyInfoService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private BuyerCompanyService buyerCompanyService;


    @Autowired
    private ShopGradeService shopGradeService;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private SellerCategoryService sellerCategoryService;


    @RequestMapping(value = "/applyToShop",method = RequestMethod.POST)
    @ApiOperation(value = "申请开店")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyname",value = "公司名称",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "province",value = "省",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "city",value = "市",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "citysmall",value = "区",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "address",value = "公司详细地址",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "email",value = "mail",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "companytel",value = "公司电话",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "linkman",value = "联系人",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "linkmantel",value = "联系人电话",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "employeenum",value = "员工人数",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "regfound",value = "注册资本（万元）",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "businesslicencenumber",value = "营业执照",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "businesslicencenumberphoto",value = "营业执照图片地址",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "businesslicencestart",value = "营业执照开始时间",required = true,paramType = "query",dataType = "date"),
            @ApiImplicitParam(name = "businesslicenceend",value = "营业执照结束时间",required = true,paramType = "query",dataType = "date"),
            @ApiImplicitParam(name = "businessscope",value = "经营范围",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "bankname",value = "银行开户行",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "bankaccount",value = "公司银行帐号",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "bankbrachname",value = "开户行支行名称",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "bankbrachaccount",value = "支行联行号",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "bankprovince",value = "开会行所在地(省)",required = false,paramType = "query",dataType = "string" ),
            @ApiImplicitParam(name = "bankcity",value = "开会行所在地(市)",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "bankcitysmall",value = "开会行所在地(区)",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "bankorgnumpic",value = "开户银行许可证图片",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "alipayname",value = "支付宝姓名",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "alipayno",value = "支付宝帐号",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "wxname",value = "微信姓名",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "wxno",value = "微信帐号",required = false,paramType = "query",dataType = "string"),

            //@ApiImplicitParam(name = "taxregistrationno",value = "税务登记证",required = true,paramType = "query",dataType = "string"),
            //@ApiImplicitParam(name = "taxregistrationcertificate",value = "纳税人识别号",required = true,paramType = "query",dataType = "string"),
            //@ApiImplicitParam(name = "taxregistrationnopic",value = "税务登记证电子图片",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "shopname",value = "店铺名称",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "storeJson",value = "仓库json:[{\"name\":\"杭州仓\",\"province\": \"浙江\",\"city\": \"杭州\",\"citysmall\": \"下沙\",\"address\": \"科技孵化园\"}]",required = true,paramType = "query",dataType = "string"),
    })
    public BasicRet applyToShop(SellerCompanyInfo info,String[] businessCategory,@RequestParam(required = true) String storeJson,Model model){
        BasicRet basicRet =  new BasicRet();

        Member memberSession= (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        long memberId =  memberSession.getId();

        if(!CommonUtils.isGoodJson(storeJson)){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("storeJson请封装成json格式");
            return  basicRet;
        }


        ShopGrade shopGrade =  shopGradeService.getDefaultShopGroup();
        if(shopGrade != null){
            info.setShopgrade(shopGrade.getGradename());
            info.setShopgradeid(shopGrade.getId().intValue());
        }


        SellerCompanyInfo sellerCompanyInfo = sellerCompanyInfoService.getSellerCompanyByMemberid(memberId);
        if(sellerCompanyInfo != null && sellerCompanyInfo.getValidate() == Quantity.STATE_1){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("您已经申请过开店了，不可重复申请");
            return  basicRet;
        }


        Gson gson = new Gson();
        List<Store> storeList =  gson.fromJson(storeJson,new TypeToken<List<Store>>() {}.getType());
        List<Store> realStoreList = new ArrayList<>();
        Set<String> storeNameSet = new HashSet<>();
        //判断提交过来的仓库是否有重名的
        if(storeList != null && storeList.size()>0){
            for(Store store : storeList){
                if(store.getName() != null && !store.getName().equals("")){
                    storeNameSet.add(store.getName());
                    realStoreList.add(store);
                }
            }
            if(realStoreList.size() != storeNameSet.size()){
                return  new BasicRet(BasicRet.ERR,"仓库名称不可重复");
            }
        }

        if(realStoreList .size()==0){
            return  new BasicRet(BasicRet.ERR,"请添加仓库");
        }

        //验证数据库中是否已经存在该仓库名
        /*
        for(Store store : realStoreList){
            List<Store> dbStoreList = storeService.getByName(store.getName());
            if(dbStoreList.size()>0){
                return  new BasicRet(BasicRet.ERR,store.getName()+"名称的仓库已经存在，请更换名称");
            }
        }
        */

        info.setMemberid(memberId);
        info.setShopstate(Quantity.STATE_0);
        info.setBusinesscategory(StringUtils.strArrToLongArr(businessCategory));

        if(sellerCompanyInfo == null) {
            sellerCompanyInfoService.applyToShop(info);
        }else{
            info.setId(sellerCompanyInfo.getId());
            info.setValidate(Quantity.STATE_0);
            sellerCompanyInfoService.updateByPrimaryKeySelective(info);

            storeService.delByMemberid(sellerCompanyInfo.getMemberid());
        }


        //仓库
        if(storeList != null && storeList.size()>0){
            for(Store store:storeList){
                if(store.getName() == null || store.getName().equals("")) continue;
                store.setCreatetime(new Date());
                store.setMemberid(memberId);
                storeService.add(store);
            }
        }


        memberService.fillMember(memberSession);
        model.addAttribute(AppConstant.MEMBER_SESSION_NAME,memberSession);

        //复制分类
        List<Categories> list =  categoriesService.getAll();

        List<SellerCategory> sellerCategories = new ArrayList<>();

        for(Categories categories:list){
            SellerCategory sellerCategory = new SellerCategory();
            sellerCategory.setCategoryid(categories.getId());
            sellerCategory.setBrokeragerate(categories.getBrokeragerate());
            if(StringUtils.hasText(categories.getDescription())){
                sellerCategory.setDescription("");
            }else {
                sellerCategory.setDescription(categories.getDescription());
            }
            if(StringUtils.hasText(categories.getImg())){
                sellerCategory.setImg("");
            }else {
                sellerCategory.setImg(categories.getImg());
            }
            if(StringUtils.hasText(categories.getKeywords())){
                sellerCategory.setKeywords("");
            }else {
                sellerCategory.setKeywords(categories.getKeywords());
            }
            sellerCategory.setName(categories.getName());
            sellerCategory.setParentid(categories.getParentid());
            sellerCategory.setSellerid(memberId);
            sellerCategory.setSort(categories.getSort());
            if(StringUtils.hasText(categories.getTitle())){
                sellerCategory.setTitle("");
            }else {
                sellerCategory.setTitle(categories.getTitle());
            }
            sellerCategories.add(sellerCategory);
        }
        sellerCategoryService.insertAll(sellerCategories);
        basicRet.setMessage("申请成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return  basicRet;
    }

    @RequestMapping(value = "/updateShopComapnyInfo",method = RequestMethod.POST)
    @ApiOperation(value = "修改店铺信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "alipayname",value = "支付宝姓名",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "alipayno",value = "支付宝帐号",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "wxname",value = "微信姓名",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "wxno",value = "微信帐号",required = true,paramType = "query",dataType = "string"),
    })
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.SHIP+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public  BasicRet updateComapnyInfo( String alipayname,String alipayno,String wxname,String wxno){
        BasicRet basicRet =new BasicRet();
       sellerCompanyInfoService.updateSellerCompanyInfo(alipayname,alipayno,wxname, wxno);
       basicRet.setResult(BasicRet.SUCCESS);
       basicRet.setMessage("修改成功");
        return  basicRet;
    }


    @RequestMapping(value =  "/sellerComapnyInfo",method = RequestMethod.POST)
    @ApiOperation(value = "获取卖家公司信息")
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.SHIP+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public SellerCompanyRet sellerComapnyInfo(Model model){
        SellerCompanyRet sellerCompanyRet = new SellerCompanyRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        long memberid = member.getId();

        SellerCompanyInfo sellerCompanyInfo = sellerCompanyInfoService.getSellerCompanyByMemberid(memberid);

        if(sellerCompanyInfo == null){
            sellerCompanyRet.setResult(BasicRet.ERR);
            sellerCompanyRet.setMessage("没有您的卖家公司信息");
            return  sellerCompanyRet;
        }

        if(sellerCompanyInfo.getShopgradeid() >0){
           ShopGrade shopGrade = shopGradeService.selectByPrimaryKey(sellerCompanyInfo.getShopgradeid());
           sellerCompanyInfo.setShopgrade(shopGrade.getGradename());
        }


        sellerCompanyRet.sellerCompanyInfo = sellerCompanyInfo;
        sellerCompanyRet.setMessage("查询成功");
        sellerCompanyRet.setResult(BasicRet.SUCCESS);
        return  sellerCompanyRet;
    }


    @RequestMapping(value =  "/company/listMyStore",method = RequestMethod.POST)
    @ApiOperation("列出我的所有仓库信息")
    public  StoreRet  listMyStore(Model model){

        StoreRet ret =  new StoreRet();

        Member member= (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        List<Store> list = storeService.getAllByMember(member.getId());

        ret.data.list = list;
        ret.setResult(BasicRet.SUCCESS);

        return  ret;
    }



    private  class  SellerCompanyRet extends  BasicRet{
        private  SellerCompanyInfo sellerCompanyInfo;


        public void setSellerCompanyInfo(SellerCompanyInfo sellerCompanyInfo) {
            this.sellerCompanyInfo = sellerCompanyInfo;
        }



        public SellerCompanyInfo getSellerCompanyInfo() {
            return sellerCompanyInfo;
        }
    }


    private  class  StoreRet extends  BasicRet{
        private  class StoreData{
            private  List<Store> list ;

            public List<Store> getList() {
                return list;
            }

            public void setList(List<Store> list) {
                this.list = list;
            }
        }

        private  StoreData data = new StoreData();

        public StoreData getData() {
            return data;
        }

        public void setData(StoreData data) {
            this.data = data;
        }
    }

}
