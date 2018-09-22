package project.jinshang.mod_company;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.constant.AdminAuthorityConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.exception.CashException;
import project.jinshang.common.utils.ExcelGen;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_cash.service.SalerCapitalService;
import project.jinshang.mod_company.bean.AgentDeliveryAddress;
import project.jinshang.mod_company.bean.SellerCompanyInfo;
import project.jinshang.mod_company.bean.Store;
import project.jinshang.mod_company.service.*;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_product.bean.ProductInfo;
import project.jinshang.mod_product.bean.ProductInfoExample;
import project.jinshang.mod_product.service.ProductInfoService;
import project.jinshang.mod_shop.bean.ShopGrade;
import project.jinshang.mod_shop.service.ShopGradeService;


import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping(value = "/rest/admin")
@Api(tags = "后台商铺管理",description = "店铺查询店铺等级编辑")
@SessionAttributes(AppConstant.MEMBER_SESSION_NAME)
@Transactional(rollbackFor = Exception.class)
public class AdminShopAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminShopService adminShopService;

    @Autowired
    private SalerCapitalService salerCapitalService;

    @Autowired
    private SellerCompanyInfoService sellerCompanyInfoService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private StoreService  storeService;

    @Autowired
    private ShopGradeService shopGradeService;

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private SellerCompanyCacheService sellerCompanyCacheService;

    @Autowired
    private AgentDeliveryAddressService agentDeliveryAddressService;



    @RequestMapping(value = "/listshop",method = RequestMethod.POST)
    @ApiOperation(value = "店铺查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyname",value = "店铺名",dataType = "string",required = false,paramType = "query"),
            @ApiImplicitParam(name = "username",value = "店主账号",dataType = "string",required = false,paramType = "query"),
            @ApiImplicitParam(name = "shopgradeid",value = "店铺等级",dataType = "int",required = false,paramType = "query"),
            @ApiImplicitParam(name = "validate",value = "审核状态 0-未审核，1-通过，2-未通过，3-删除",dataType = "int",defaultValue = "-1",required = false,paramType = "query"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.BUSINESSMANAGEMENT + "')")
    public PageInfo listshop(  @RequestParam(required = false,defaultValue ="" )String companyname,
                               @RequestParam(required = false,defaultValue ="" )String username,
                               @RequestParam(required = false,defaultValue ="0" ) int shopgradeid,
                               @RequestParam(required = false,defaultValue = "-1") int validate,
                               @RequestParam(required = true,defaultValue ="1" ) int pageNo,
                               @RequestParam(required = true,defaultValue ="20" ) int pageSize){

            PageInfo pageInfo =adminShopService.listShop(companyname, username, shopgradeid,validate,pageNo, pageSize);
            return pageInfo;
    }



    @RequestMapping(value = "/selectShopInfoByid",method = RequestMethod.POST)
    @ApiOperation(value = "店铺详细信息查询")
    public SellerCompanyRet  selectShopInfoByid(@RequestParam(required = true) long id,@RequestParam(required = true) Long memberid){
           SellerCompanyRet sellerCompanyRet =  new SellerCompanyRet();
            SellerCompanyInfo sellerCompanyInfo= adminShopService.selectShopInfoByid(id);

            if(sellerCompanyInfo == null){
                sellerCompanyRet.setMessage("未查询到公司信息");
                sellerCompanyRet.setResult(BasicRet.ERR);
                return  sellerCompanyRet;
            }

            Map<String,Object> agentdeliveryaddressMap =  new HashMap<>();
            if(sellerCompanyInfo.getDeliverymode() == Quantity.STATE_1){ //发货模式
               AgentDeliveryAddress ad =  agentDeliveryAddressService.getBySellerid(sellerCompanyInfo.getMemberid());
               if(ad != null){
                   agentdeliveryaddressMap.put("agentlinkman",ad.getLinkman());
                   agentdeliveryaddressMap.put("agenttel",ad.getTel());
                   agentdeliveryaddressMap.put("agentzipcode",ad.getZipcode());
                   agentdeliveryaddressMap.put("agentprovince",ad.getProvince());
                   agentdeliveryaddressMap.put("agentcity",ad.getCity());
                   agentdeliveryaddressMap.put("agentcitysmall",ad.getCitysmall());
                   agentdeliveryaddressMap.put("agentaddress",ad.getAddress());
               }
            }


            if(sellerCompanyInfo.getShopgradeid() >0){
                ShopGrade grade =  shopGradeService.selectByPrimaryKey(sellerCompanyInfo.getShopgradeid());
                if(grade != null){
                    sellerCompanyInfo.setShopgrade(grade.getGradename());
                }
            }

            List<Store> storeList = storeService.getAllByMember(sellerCompanyInfo.getMemberid());

            BigDecimal broker = adminShopService.getSellerSumBokerBySellerId(memberid);
            sellerCompanyRet.data.broker = broker;
            sellerCompanyRet.data.storeList = storeList;
            sellerCompanyRet.data.sellerCompanyInfo =  sellerCompanyInfo;
            sellerCompanyRet.data.agentdeliveryaddress =  agentdeliveryaddressMap;

            sellerCompanyRet.setResult(BasicRet.SUCCESS);
            return sellerCompanyRet;
    }


    @RequestMapping(value = "/validateShop",method = RequestMethod.POST)
    @ApiOperation("开店审核")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "店铺id",name = "id",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(value = "审核状态 0-未审核，1-通过，2-未通过，3-删除",name = "validate",required = true,paramType = "query",dataType = "int"),
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.BUSINESSMANAGEMENT + "')")
    public  BasicRet validateShop(@RequestParam(required = true) long id,
                                  @RequestParam(required =  true) short validate){

        BasicRet basicRet = new BasicRet();

        SellerCompanyInfo sellerCompanyInfo =  sellerCompanyInfoService.getById(id);
        if(sellerCompanyInfo == null){
            basicRet.setMessage("卖家公司信息未查询到");
            basicRet.setResult(BasicRet.ERR);
            return  basicRet;
        }

        Member member = new Member();
        member.setId(sellerCompanyInfo.getMemberid());
        if(validate == Quantity.STATE_1){
            member.setType(Quantity.STATE_1);
        }else {
            member.setType(Quantity.STATE_0);
        }
        memberService.updateByPrimaryKeySelective(member);

        //如果审核不通过，直接将资料删除
        SellerCompanyInfo info = new SellerCompanyInfo();
        info.setId(id);
        info.setValidate(validate);
        sellerCompanyInfoService.updateByPrimaryKeySelective(info);


        basicRet.setMessage("修改成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return  basicRet;
    }


    @RequestMapping(value = "/updateSellerCompanyInfo",method = RequestMethod.POST)
    @ApiOperation(value = "店铺信息修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "ID",dataType = "int",required = false,paramType = "query"),
            @ApiImplicitParam(name = "companyTel",value = "公司电话",dataType = "string",required = false,paramType = "query"),
            @ApiImplicitParam(name = "province",value = "省",dataType = "string",required = false,paramType = "query"),
            @ApiImplicitParam(name = "city",value = "市",dataType = "string",required = false,paramType = "query"),
            @ApiImplicitParam(name = "citysmall",value = "区",dataType = "string",required = false,paramType = "query"),
            @ApiImplicitParam(name = "address",value = "详细地址",dataType = "string",required = false,paramType = "query"),
            @ApiImplicitParam(name = "linkmanTel",value = "联系人电话",dataType = "string",required = false,paramType = "query"),
            @ApiImplicitParam(name = "shopgradeid",value = "店铺等级",dataType = "int",required = true,paramType = "query"),
            @ApiImplicitParam(name = "shopperiod",value = "有效期",dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "shopstate",value = "店铺状态{0=开店1=关店}",dataType = "int",required = true,paramType = "query"),
//            @ApiImplicitParam(name = "isrecommend",value = "是否推荐{0=推存1=不推存}",dataType = "int",required = true,paramType = "query"),
            @ApiImplicitParam(name = "deliverymode",value = "发货模式 0-直发，1-代发",dataType = "int",required = true,paramType = "query"),
            @ApiImplicitParam(name = "agentlinkman",value = "代理发货联系人",dataType = "string",required = false,paramType = "query"),
            @ApiImplicitParam(name = "agenttel",value = "代理发货电话",dataType = "string",required = false,paramType = "query"),
            @ApiImplicitParam(name = "agentzipcode",value = "代理发货邮编",dataType = "string",required = false,paramType = "query"),
            @ApiImplicitParam(name = "agentprovince",value = "代理发货地址-省",dataType = "string",required = false,paramType = "query"),
            @ApiImplicitParam(name = "agentcity",value = "代理发货地址-市",dataType = "string",required = false,paramType = "query"),
            @ApiImplicitParam(name = "agentcitysmall",value = "代理发货地址-区",dataType = "string",required = false,paramType = "query"),
            @ApiImplicitParam(name = "agentaddress",value = "代理发货地址-详细地址",dataType = "string",required = false,paramType = "query"),

            @ApiImplicitParam(name = "employeenum",value = "员工人数",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "regfound",value = "注册资本（万元）",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "businesslicencenumber",value = "营业执照",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "businesslicencenumberphoto",value = "营业执照图片地址",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "businesslicencestart",value = "营业执照开始时间",required = true,paramType = "query",dataType = "date"),
            @ApiImplicitParam(name = "businesslicenceend",value = "营业执照结束时间",required = true,paramType = "query",dataType = "date"),
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
            @ApiImplicitParam(name = "smsnotify",value = "短信通知 0=不通知，1=通知",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "isselflifting",value = "是否可以自提",required = true,paramType = "query",dataType = "bool"),
            @ApiImplicitParam(name = "freightmode",value = "运费是否计入结算价 0-不计入，1-计入",required = true,paramType = "query",dataType = "int"),

            //公司名称companyname、电子邮箱email、联系人姓名linkman、法定经营范围businessscope、店铺名称shopname字段
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.BUSINESSMANAGEMENT + "')")
    public  BasicRet updateSellerCompanyInfo(long id ,String businesscategory[],
                                             String companyTel,String province,
                                             String city,String citysmall,
                                             String linkmanTel,String address,
                                             int shopgradeid,String shopperiod,
                                             @RequestParam(required = true,defaultValue = "0") Short shopstate,
                                             //Short isrecommend
                                             Short deliverymode,
                                             String agentlinkman,String agenttel,String agentzipcode,String agentprovince,String agentcity,
                                             String agentcitysmall,String agentaddress,Integer employeenum,
                                             Integer regfound,String businesslicencenumber,String businesslicencenumberphoto, Date businesslicencestart,
                                             Date businesslicenceend,String bankname,String bankaccount,String bankbrachname,String bankbrachaccount,String bankprovince,
                                             String bankcity,String bankcitysmall,String bankorgnumpic,String alipayname,String alipayno,String wxname,String wxno,
                                             String companyname,String email,String linkman,String businessscope,String shopname,
                                             //@RequestParam(required = true,defaultValue = "0") Short disparity
                                             @RequestParam(defaultValue = "0") Short smsnotify,@RequestParam(required = true,defaultValue = "true")boolean isselflifting,@RequestParam(defaultValue = "0") Short freightmode) throws CashException {
        BasicRet basicRet=new BasicRet();

        SellerCompanyInfo dbInfo = sellerCompanyInfoService.getById(id);
        if(dbInfo == null){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("未查询到店铺信息");
            return  basicRet;
        }

        Member member = memberService.getMemberById(dbInfo.getMemberid());

        SellerCompanyInfo sellerCompanyInfo=new SellerCompanyInfo();
        sellerCompanyInfo.setId(id);

        sellerCompanyInfo.setCompanyname(companyname);
        sellerCompanyInfo.setEmail(email);
        sellerCompanyInfo.setLinkman(linkman);
        sellerCompanyInfo.setBusinessscope(businessscope);
        sellerCompanyInfo.setShopname(shopname);

        sellerCompanyInfo.setBusinesscategory(StringUtils.strArrToLongArr(businesscategory));
        sellerCompanyInfo.setCompanytel(companyTel);
        sellerCompanyInfo.setProvince(province);
        sellerCompanyInfo.setCity(city);
        sellerCompanyInfo.setCitysmall(citysmall);
        sellerCompanyInfo.setAddress(address);
        sellerCompanyInfo.setLinkmantel(linkmanTel);
        sellerCompanyInfo.setShopgradeid(shopgradeid);
        sellerCompanyInfo.setShopperiod(shopperiod);
        sellerCompanyInfo.setShopstate(shopstate);

        sellerCompanyInfo.setEmployeenum(employeenum);
        sellerCompanyInfo.setRegfound(regfound);


        sellerCompanyInfo.setBusinesslicencenumber(businesslicencenumber);
        sellerCompanyInfo.setBusinesslicencenumberphoto(businesslicencenumberphoto);
        sellerCompanyInfo.setBusinesslicencestart(businesslicencestart);
        sellerCompanyInfo.setBusinesslicenceend(businesslicenceend);
        sellerCompanyInfo.setBankname(bankname);
        sellerCompanyInfo.setBankaccount(bankaccount);
        sellerCompanyInfo.setBankbrachname(bankbrachname);
        sellerCompanyInfo.setBankbrachaccount(bankbrachaccount);
        sellerCompanyInfo.setBankprovince(bankprovince);
        sellerCompanyInfo.setBankcity(bankcity);
        sellerCompanyInfo.setBankcitysmall(bankcitysmall);
        sellerCompanyInfo.setBankorgnumpic(bankorgnumpic);
        sellerCompanyInfo.setAlipayname(alipayname);
        sellerCompanyInfo.setAlipayno(alipayno);
        sellerCompanyInfo.setWxname(wxname);
        sellerCompanyInfo.setWxno(wxno);


        sellerCompanyInfo.setDeliverymode(deliverymode);
        sellerCompanyInfo.setSmsnotify(smsnotify);
        sellerCompanyInfo.setIsselflifting(isselflifting);
        sellerCompanyInfo.setFreightmode(freightmode);



        //发货模式  0-商家直发 1-代理发货
        if(deliverymode == Quantity.STATE_1){
            //代理发货模式，代理发货联系人信息必须填写完整
            if(!StringUtils.hasText(agentlinkman)){
                return  new BasicRet(BasicRet.ERR,"代理发货联系人必须填写");
            }

            if(!StringUtils.hasText(agenttel)){
                return  new BasicRet(BasicRet.ERR,"代理发货电话必须填写");
            }

            if(!StringUtils.hasText(agentzipcode)){
                return  new BasicRet(BasicRet.ERR,"代理发货邮编必须填写");
            }

            if(!StringUtils.hasText(agentprovince) || !StringUtils.hasText(agentcity) || !StringUtils.hasText(agentcitysmall) ||
                    !StringUtils.hasText(agentaddress)){
                return new BasicRet(BasicRet.ERR,"代理发货地址必须填写完整");
            }
        }


        if(shopstate == Quantity.STATE_1){  //关闭店铺  1.下架所有商品  2.退回所有上架产品保证金
            ProductInfoExample example = new ProductInfoExample();
            ProductInfoExample.Criteria criteria = example.createCriteria();
            criteria.andMemberidEqualTo(dbInfo.getMemberid()).andPdstateEqualTo(Quantity.STATE_4);
            List<ProductInfo> list = productInfoService.listProductByExample(example);
            for(ProductInfo productInfo : list){
               salerCapitalService.backBail(productInfo);

               ProductInfo update = new ProductInfo();
               update.setPdstate(Quantity.STATE_5);
               update.setId(productInfo.getId());
               productInfoService.updateByPrimaryKeySelective(update);
            }
        }


        AgentDeliveryAddress agentDeliveryAddress = agentDeliveryAddressService.getBySellerid(dbInfo.getMemberid());
        if(agentDeliveryAddress != null){  //已经存在
            agentDeliveryAddress.setLinkman(agentlinkman);
            agentDeliveryAddress.setZipcode(agentzipcode);
            agentDeliveryAddress.setTel(agenttel);
            agentDeliveryAddress.setProvince(agentprovince);
            agentDeliveryAddress.setCity(agentcity);
            agentDeliveryAddress.setCitysmall(agentcitysmall);
            agentDeliveryAddress.setAddress(agentaddress);
            agentDeliveryAddressService.updateByPrimaryKeySelective(agentDeliveryAddress);
        }else{ //不存在，新创建
            agentDeliveryAddress = new AgentDeliveryAddress();
            agentDeliveryAddress.setLinkman(agentlinkman);
            agentDeliveryAddress.setZipcode(agentzipcode);
            agentDeliveryAddress.setTel(agenttel);
            agentDeliveryAddress.setProvince(agentprovince);
            agentDeliveryAddress.setCity(agentcity);
            agentDeliveryAddress.setCitysmall(agentcitysmall);
            agentDeliveryAddress.setAddress(agentaddress);
            agentDeliveryAddress.setSellerid(dbInfo.getMemberid());
            agentDeliveryAddressService.insertSelective(agentDeliveryAddress);
        }


        adminShopService.updateSellerCompanyInfo(sellerCompanyInfo);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");

        //把卖家可发布的商品分类重新加载
        sellerCompanyCacheService.reloadPushCategory(dbInfo.getMemberid());

        return basicRet;
    }





    @RequestMapping(value = "/listCash",method = RequestMethod.POST)
    @ApiOperation(value = "卖家资金明细查询（capitaltype:资金类型0=订单金额1=上架保证金2=下架保证金3=退款金额4=充值5=提现6=违约金）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "starttime",value = "开始时间",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(name = "endtime",value = "结束时间",required = false,paramType = "query",dataType = "date"),
            @ApiImplicitParam(name = "capitaltype",value = "类别{0=订单金额1=上架保证金2=下架保证金3=退款金额4=充值5=提现6=违约金}",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "rechargeperform",value = "类别{0=订单金额1=上架保证金2=下架保证金3=退款金额4=充值5=提现6=违约金}",required = false,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "rechargestate",value = "状态0=待处理1=成功2=失败3=待审核4=审核通过5=审核不通过",required = false,paramType = "query",dataType = "int"),
    })
    public PageInfo selectSalerCapitalByConsume(Date starttime, Date endtime,
                                                @RequestParam(required = true)long memberid,
                                                @RequestParam(required = false,defaultValue ="-1" )short capitaltype,
                                                @RequestParam(required = false,defaultValue ="-1" )short rechargeperform,
                                                @RequestParam(required = false,defaultValue ="-1" )short rechargestate,
                                                @RequestParam(required = true)int pageNo,
                                                @RequestParam(required = true)int pageSize, Model model){

        PageInfo pageInfo = salerCapitalService.listCash(memberid,starttime,endtime,capitaltype,rechargeperform,rechargestate,pageNo,pageSize);

        return pageInfo;

    }







    private class  SellerCompanyRet extends  BasicRet{
        private class SellerCompanyData {
            private List<Store> storeList ;
            private  SellerCompanyInfo sellerCompanyInfo;

            private BigDecimal broker;

            private  Map<String,Object> agentdeliveryaddress;

            public BigDecimal getBroker() {
                return broker;
            }

            public void setBroker(BigDecimal broker) {
                this.broker = broker;
            }

            public SellerCompanyInfo getSellerCompanyInfo() {
                return sellerCompanyInfo;
            }

            public void setSellerCompanyInfo(SellerCompanyInfo sellerCompanyInfo) {
                this.sellerCompanyInfo = sellerCompanyInfo;
            }

            public List<Store> getStoreList() {
                return storeList;
            }

            public void setStoreList(List<Store> storeList) {
                this.storeList = storeList;
            }
        }

        private  SellerCompanyData data = new SellerCompanyData();

        public SellerCompanyData getData() {
            return data;
        }

        public void setData(SellerCompanyData data) {
            this.data = data;
        }
    }
}
