package project.jinshang.mod_admin.mod_common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.DateUtils;
import project.jinshang.mod_activity.bean.LimitTimeProdExample;
import project.jinshang.mod_activity.service.LimitTimeProdService;
import project.jinshang.mod_admin.mod_creditapplyrecord.service.AdminCreditapplyService;
import project.jinshang.mod_cash.bean.BuyerCapitalExample;
import project.jinshang.mod_cash.bean.SalerCapitalExample;
import project.jinshang.mod_cash.service.BuyerCapitalService;
import project.jinshang.mod_cash.service.SalerCapitalService;
import project.jinshang.mod_company.bean.SellerCompanyInfoExample;
import project.jinshang.mod_company.service.SellerCompanyInfoService;
import project.jinshang.mod_member.bean.MemberExample;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_product.bean.BillingRecordExample;
import project.jinshang.mod_product.bean.BrandExample;
import project.jinshang.mod_product.bean.ProductInfoExample;
import project.jinshang.mod_product.service.BillingRecordService;
import project.jinshang.mod_product.service.BrandService;
import project.jinshang.mod_product.service.OrdersService;
import project.jinshang.mod_product.service.ProductInfoService;

import java.util.*;

/**
 * create : wyh
 * date : 2018/1/31
 */

@RestController
@RequestMapping("/rest/admin/common")
@Api(tags = "后台通用模块")
public class AdminCommonAction {

    @Autowired
    private MemberService memberService;

    @Autowired
    private AdminCreditapplyService adminCreditapplyService;

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private LimitTimeProdService limitTimeProdService;

    @Autowired
    private SellerCompanyInfoService sellerCompanyInfoService;

    @Autowired
    private BuyerCapitalService buyerCapitalService;

    @Autowired
    private SalerCapitalService salerCapitalService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    BillingRecordService billingRecordService;


    @PostMapping("/indexCount")
    @ApiOperation("首页统计")
    public IndexCountRet indexCount(){
        IndexCountRet indexCountRet = new IndexCountRet();

        Map<String,Object> dataMap = new HashMap<>();

        Date tody = DateUtils.StrToDate(DateUtils.format(new Date(),"yyyy-MM-dd"));

        dataMap.put("今日销量统计",ordersService.getCurrentOrdersSumPay());

        //今日新增会员
        MemberExample memberExample =  new MemberExample();
        MemberExample.Criteria memberCriteria = memberExample.createCriteria();
        memberCriteria.andCreatedateGreaterThanOrEqualTo(tody);
        dataMap.put("今日新增会员",memberService.countByExample(memberExample));

        //待审核授信用户
        dataMap.put("待审核授信用户",adminCreditapplyService.getCountByStates(Quantity.STATE_0));

        //待审核商品
        ProductInfoExample productInfoExample = new ProductInfoExample();
        ProductInfoExample.Criteria criteria = productInfoExample.createCriteria();
        criteria.andPdstateEqualTo(Quantity.STATE_1);
        dataMap.put("待审核商品",productInfoService.countByExample(productInfoExample));

        //待审核活动
        LimitTimeProdExample limitTimeProdExample = new LimitTimeProdExample();
        LimitTimeProdExample.Criteria criteria1 = limitTimeProdExample.createCriteria();
        criteria1.andStateEqualTo(Quantity.STATE_0);
        dataMap.put("待审核活动",limitTimeProdService.countByExample(limitTimeProdExample));

        //待审核品牌
        BrandExample brandExample = new BrandExample();
        BrandExample.Criteria criteria2 = brandExample.createCriteria();
        criteria2.andAuditstateEqualTo(Quantity.STATE_0);
        dataMap.put("待审核品牌",brandService.countByExample(brandExample));


        //买家待审核提现
        BuyerCapitalExample buyerCapitalExample = new BuyerCapitalExample();
        BuyerCapitalExample.Criteria criteria3 = buyerCapitalExample.createCriteria();
        criteria3.andCapitaltypeEqualTo(Quantity.STATE_3);
        criteria3.andRechargestateEqualTo(Quantity.STATE_3);
        dataMap.put("买家待审核提现",buyerCapitalService.countByExample(buyerCapitalExample));


        //卖家待审核提现
        SalerCapitalExample salerCapitalExample =  new SalerCapitalExample();
        SalerCapitalExample.Criteria criteria4 = salerCapitalExample.createCriteria();
        List<Short> capitalList = new ArrayList<>();
        capitalList.add(Quantity.STATE_5);
        capitalList.add(Quantity.STATE_11);
        criteria4.andCapitaltypeIn(capitalList);
        dataMap.put("商家待审核提现",salerCapitalService.countByExample(salerCapitalExample));

        //待审核开店
        SellerCompanyInfoExample sellerCompanyInfoExample = new SellerCompanyInfoExample();
        SellerCompanyInfoExample.Criteria criteria5 = sellerCompanyInfoExample.createCriteria();
        criteria5.andValidateEqualTo(Quantity.STATE_0);
        dataMap.put("待审核开店",sellerCompanyInfoService.countByExample(sellerCompanyInfoExample));


        //待开票统计
        BillingRecordExample billingRecordExample = new BillingRecordExample();
        BillingRecordExample.Criteria criteria6 = billingRecordExample.createCriteria();
        criteria6.andStateEqualTo(Quantity.STATE_0);
        dataMap.put("待开票",billingRecordService.countByExample(billingRecordExample));


        //当日卖家违约订单数
        dataMap.put("今日卖家违约订单数",ordersService.getSellerBreachOrders());


        indexCountRet.data.dataMap = dataMap;
        indexCountRet.setResult(BasicRet.SUCCESS);

        return  indexCountRet;
    }




    private  class IndexCountRet extends BasicRet{
        private  class IndexData{
            private  Map dataMap;

            public Map getDataMap() {
                return dataMap;
            }

            public void setDataMap(Map dataMap) {
                this.dataMap = dataMap;
            }
        }


        private  IndexData data = new IndexData();

        public IndexData getData() {
            return data;
        }

        public void setData(IndexData data) {
            this.data = data;
        }
    }

}
