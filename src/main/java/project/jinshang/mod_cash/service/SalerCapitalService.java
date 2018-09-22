package project.jinshang.mod_cash.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mizuki.project.core.restserver.config.BasicRet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.exception.CashException;
import project.jinshang.common.utils.JinshangUtils;
import project.jinshang.mod_admin.mod_cash.dto.AdvanceCapitalSellerQueryDto;
import project.jinshang.mod_cash.BuyerCapitalMapper;
import project.jinshang.mod_cash.SalerCapitalMapper;
import project.jinshang.mod_cash.bean.SalerCapital;
import project.jinshang.mod_cash.bean.SalerCapitalExample;
import project.jinshang.mod_cash.bean.dto.SalerCapitalQueryDto;
import project.jinshang.mod_cash.bean.dto.SalerCapitalSellerExportExcel;
import project.jinshang.mod_cash.bean.dto.SalerCapitalViewDto;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_product.bean.PdbailLog;
import project.jinshang.mod_product.bean.ProductInfo;
import project.jinshang.mod_product.service.PdbailLogService;
import project.jinshang.mod_product.service.ProductSearchService;
import project.jinshang.mod_sellerbill.bean.SellerBillOrder;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service
public class SalerCapitalService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SalerCapitalMapper salerCapitalMapper;

    @Resource
    private BuyerCapitalMapper buyerCapitalMapper;


    @Autowired
    private PdbailLogService pdbailLogService;


    @Autowired
    private MemberService memberService;

    @Autowired
    private ProductSearchService productSearchService;



    public  SalerCapital getById(long id){
        return  salerCapitalMapper.selectByPrimaryKey(id);
    }


    public  void  addSalerCapital(SalerCapital salerCapital){
         salerCapitalMapper.insertSelective(salerCapital);
    }





    public PageInfo listCash(long memberid,Date starttime, Date endtime, short capitaltype, short rechargeperform,short rechargestate, int pageNo, int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        SalerCapitalQueryDto salerCapital=new SalerCapitalQueryDto();

        salerCapital.setMemberid(memberid);
        salerCapital.setTradetimeStart(starttime);
        salerCapital.setTradetimeEnd(endtime);
        salerCapital.setCapitaltype(capitaltype);
        salerCapital.setRechargeperform(rechargeperform);
        salerCapital.setRechargestate(rechargestate);

        return  listCash(salerCapital,pageNo,pageSize);
    }



    public PageInfo listCash(SalerCapitalQueryDto queryDto,int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<SalerCapital> list=salerCapitalMapper.selectSalerCapitalByConsume(queryDto);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }


    /**
     * 后台卖家明细导出 for excel
     * @param queryDto
     * @return
     */
    public List<Map<String,Object>> listCashForAdminExcel(SalerCapitalQueryDto queryDto) {

        List<Map<String,Object>> list=salerCapitalMapper.selectSalerCapitalByConsumeForAdminExcel(queryDto);

        List<Map<String,Object>> data =  new ArrayList<>();
        // String[] rowTitles =  new String[]{"时间","买家编号","公司名称","会员名称","类型","金额","支付方式","单号","状态"};
        Map capitaltype = new HashMap();
        capitaltype.put("0","消费");
        capitaltype.put("1","充值");
        capitaltype.put("2","退款");
        capitaltype.put("3","提现");
        capitaltype.put("4","授信");
        capitaltype.put("5","授信还款");
        capitaltype.put("6","违约金");
        capitaltype.put("7","远期定金");
        capitaltype.put("8","远期余款");
        capitaltype.put("9","远期全款");
        capitaltype.put("10","卖家违约金");
        capitaltype.put("11","授信未出账单还款");

        Map isonline = new HashMap();
        isonline.put("0","线上");
        isonline.put("1","线下");
        isonline.put("2","限时购");

        Map isbilling = new HashMap();
        isbilling.put("0","不需要");
        isbilling.put("1","需要");

        Map orderstatus = new HashMap();
        orderstatus.put("0","待付款");
        orderstatus.put("1","待发货");
        orderstatus.put("3","待收货");
        orderstatus.put("4","待验货");
        orderstatus.put("5","已完成");
        orderstatus.put("7","已关闭");
        orderstatus.put("8","备货中");
        orderstatus.put("9","备货完成");

        Map paytype = new HashMap();
        paytype.put("0","支付宝");
        paytype.put("1","微信");
        paytype.put("2","银行卡");
        paytype.put("3","余额");
        paytype.put("4","授信");
        for(Map<String,Object> map  : list) {

            Map<String, Object> resMap = new HashMap<>();
            resMap.put("下单时间", map.get("createtime"));
            resMap.put("合同号", map.get("code"));
            resMap.put("订单号", map.get("orderno"));
            resMap.put("交易号", map.get("transactionnumber"));
            resMap.put("买家", map.get("username"));
            if (map.get("realname") != null && map.get("realname") != "") {
                resMap.put("买家", map.get("realname"));
            }
            resMap.put("卖家", map.get("sellername"));
            if (map.get("companyname") != null && map.get("companyname") != "") {
                resMap.put("卖家", map.get("companyname"));
            }
            if(map.get("capitaltype")!=null){
                resMap.put("订单类型", capitaltype.get(map.get("capitaltype").toString()));
            }else{
                resMap.put("订单类型","");
            }
            if(map.get("isonline")!=null) {
                resMap.put("订单来源", isonline.get(map.get("isonline").toString()));
            }else{
                resMap.put("订单来源","");
            }
            resMap.put("货款金额", map.get("capital"));
            if(map.get("memberid")!=null){
                List<Map<String,Object>> bill = new ArrayList<Map<String,Object>>();

                bill = buyerCapitalMapper.getBillingRecordByMemberid(Long.parseLong(map.get("memberid").toString()));
                if(bill!=null) {
                    for (int i=0;i<bill.size();i++) {
                        resMap.put("开票抬头", bill.get(0).get("invoiceheadup"));
                        resMap.put("税号", bill.get(0).get("texno"));
                        resMap.put("开户行", bill.get(0).get("bankofaccounts"));
                        resMap.put("开户账号", bill.get(0).get("account"));
                        resMap.put("开票地址", bill.get(0).get("address"));
                        resMap.put("电话", bill.get(0).get("phone"));
                    }
                }else{
                    resMap.put("开票抬头", "");
                    resMap.put("税号", "");
                    resMap.put("开户行", "");
                    resMap.put("开户账号", "");
                    resMap.put("开票地址", "");
                    resMap.put("电话", "");
                }
            }else{
                resMap.put("开票抬头", "");
                resMap.put("税号", "");
                resMap.put("开户行", "");
                resMap.put("开户账号", "");
                resMap.put("开票地址", "");
                resMap.put("电话", "");
            }
            if(map.get("isbilling")!=null) {
                resMap.put("是否开票", isbilling.get(map.get("isbilling").toString()));
            }else{
                resMap.put("是否开票","");
            }
            if(map.get("orderstatus")!=null) {
                resMap.put("订单状态", orderstatus.get(map.get("orderstatus").toString()));
            }else{
                resMap.put("订单状态","");
            }
            resMap.put("项目", "");
            resMap.put("收件人", map.get("shipto"));
            String address = map.get("province") + "" + map.get("city") + "" + map.get("area") + "" + map.get("receivingaddress");
            if(address.equals("nullnullnullnull")){
                address="";
            }
            resMap.put("收货地址",address);
            if(map.get("paytype")!=null) {
                resMap.put("付款方式", paytype.get(map.get("paytype").toString()));
            }else{
                resMap.put("付款方式","");
            }
            resMap.put("物流公司", map.get("logisticscompany"));
            resMap.put("快递单号", map.get("couriernumber"));
            resMap.put("业务员", map.get("operation"));
            resMap.put("第三方支付号", map.get("transactionid"));
            resMap.put("业务单号", map.get("uuid"));
            if(map.get("orderno")!=null){
                List<Map<String, Object>> orderList = buyerCapitalMapper.getOrderProducts(map.get("orderno").toString());
                for (Map<String, Object> m : orderList) {
                    resMap.put("商品名称", m.get("pdname"));
                    resMap.put("规格", m.get("standard"));
                    resMap.put("材质", m.get("material"));
                    resMap.put("牌号", m.get("gradeno"));
                    resMap.put("品牌", m.get("brand"));
                    resMap.put("印记", m.get("mark"));
                    resMap.put("表面处理", m.get("surfacetreatment"));
                    resMap.put("包装方式", m.get("packagetype"));
                    resMap.put("单位", m.get("unit"));
                    resMap.put("单价", m.get("price"));
                    resMap.put("订购量", m.get("num"));

                    data.add(resMap);

                }
            }else{
                resMap.put("商品名称", "");
                resMap.put("规格", "");
                resMap.put("材质", "");
                resMap.put("牌号", "");
                resMap.put("品牌", "");
                resMap.put("印记", "");
                resMap.put("表面处理", "");
                resMap.put("包装方式", "");
                resMap.put("单位", "");
                resMap.put("单价", "");
                resMap.put("订购量", "");
                data.add(resMap);
            }
        }
        return data;
    }



    public  void  insertSelective(SalerCapital salerCapital){
        salerCapitalMapper.insertSelective(salerCapital);
    }


    public  int countByExample(SalerCapitalExample example){
        return  salerCapitalMapper.countByExample(example);
    }


    /**
     * 根据充值单号查询
     * @param rechargenumber
     * @return
     */
    public  SalerCapital getSalerCapitalByRechargenumber(String rechargenumber){
        return  salerCapitalMapper.getSalerCapitalByRechargenumber(rechargenumber);
    }


    public  void  updateByPrimaryKeySelective(SalerCapital salerCapital){
        salerCapitalMapper.updateByPrimaryKeySelective(salerCapital);
    }


    /**
     * 列出会员的可用金额、冻结金额和历史充值总金额
     * @param pageNo
     * @param PageSize
     * @return
     */
    public PageInfo advanceCapitalList(int pageNo, int PageSize, AdvanceCapitalSellerQueryDto queryDto){

        PageHelper.startPage(pageNo,PageSize);

        List<Map<String,Object>> list = salerCapitalMapper.advanceCapitalList(queryDto);

        PageInfo pageInfo =  new PageInfo(list);
        return  pageInfo;

    }


    /**
     * 列表查询卖家资金明细
     * @param pageNo
     * @param pageSize
     * @param dto
     * @return
     */
    public  PageInfo list(int pageNo, int pageSize, SalerCapitalQueryDto dto){
        PageHelper.startPage(pageNo,pageSize);
        List<SalerCapitalViewDto> list =  salerCapitalMapper.list(dto);
        PageInfo pageInfo = new PageInfo(list);
        return  pageInfo;
    }


    /**
     * 卖家导出资金明细
     * @param dto
     * @return
     */
    public  List<Map<String,Object>> listForSellerExportExcel(SalerCapitalQueryDto dto){

        List<SalerCapitalSellerExportExcel> list =  salerCapitalMapper.listForSellerExportExcel(dto);

        List<Map<String,Object>> data =  new ArrayList<>();

       // String[] rowTitles =  new String[]{"时间","类型","订单号","交易号","订单金额","保证金","违约金","退款","状态"};

        for(SalerCapitalSellerExportExcel cap : list){
            Map<String,Object> resMap = new HashMap<>();

            resMap.put("时间",cap.getTradetime());
            resMap.put("类型",JinshangUtils.sellerCapitalType(cap.getCapitaltype()));
            resMap.put("订单号",cap.getOrderno());
            resMap.put("交易号",cap.getTradeno());
            resMap.put("订单金额",cap.getOrdercapital());
            resMap.put("保证金",cap.getBail());
            resMap.put("违约金",cap.getPenalty());
            resMap.put("退款",cap.getRefundamount());
            resMap.put("状态",JinshangUtils.sellerCapitalState(cap.getRechargestate()));

            data.add(resMap);
        }

        return  data;
    }




    /**
     * 退还保证金
     * @param productInfo
     * @return
     */
    public   void backBail(ProductInfo productInfo) throws CashException {
        BasicRet basicRet = new BasicRet();
        //查询出上架交了多少保证金
        PdbailLog pdbailLog =  pdbailLogService.getLastLogByPdidType(productInfo.getId(),(short)0,productInfo.getMemberid());
        if(pdbailLog == null){
            throw  new CashException("未查询到商品缴纳的保证金额");
        }

        Member member =  memberService.getMemberById(productInfo.getMemberid());

        if(member == null){
            throw  new CashException("未查询到该商品所属的用户");
        }

        if(member.getSellerfreezebanlance().subtract(pdbailLog.getCash()).doubleValue() <0){
            throw  new CashException("帐户出现错误，冻结金额小于退回金额");
        }


        //1.帐号可用余额增加保证金，冻结金额扣除保证金
        //BigDecimal sellerBalance = member.getSellerbanlance() == null ? pdbailLog.getCash() : member.getSellerbanlance().add(pdbailLog.getCash());
        //BigDecimal sellerfreezebanlance = member.getSellerfreezebanlance().subtract(pdbailLog.getCash());
        //memberService.updateSellerMemberBalance(member.getId(),sellerBalance,sellerfreezebanlance);
        memberService.updateSellerMemberBalanceInDb(member.getId(),pdbailLog.getCash(),pdbailLog.getCash().multiply(Quantity.BIG_DECIMAL_MINUS_1));


        //2.记录到卖家资金明细表
        SalerCapital salerCapital =  new SalerCapital();
        salerCapital.setMemberid(member.getId());
        salerCapital.setTradeno("");
        salerCapital.setOrderno("");
        salerCapital.setTradetime(new Date());
        salerCapital.setOrdercapital(new BigDecimal(0));
        salerCapital.setBail(pdbailLog.getCash());
        salerCapital.setPenalty(new BigDecimal(0));
        salerCapital.setRefundamount(new BigDecimal(0));
        salerCapital.setRemark("产品下架退回保证金");
        salerCapital.setCapitaltype(Quantity.STATE_2);
        salerCapital.setRechargenumber("");
        salerCapital.setRechargeperform((short)-1);
        salerCapital.setPresentationnumber("");
        salerCapital.setRechargestate(Quantity.STATE_1);
        addSalerCapital(salerCapital);

        //3.添加记录到商品保证金流水表
        pdbailLog.setPdid(productInfo.getId());
        pdbailLog.setPdname(productInfo.getProductname());
        pdbailLog.setCaptialid(salerCapital.getId());
        pdbailLog.setSellerid(member.getId());
        pdbailLog.setCreatetime(new Date());
        pdbailLog.setType(Quantity.STATE_1);
        pdbailLogService.add(pdbailLog);

        productSearchService.delIndex(productInfo.getId());
    }

    public PageInfo getWaitPenaltyOpenBillList(Long salerid, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String, Object>> list = salerCapitalMapper.getWaitPenaltyOpenBillList(salerid);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;

    }

    public List<SalerCapital> getSalerCapitalByIds(Long[] ids) {
        return salerCapitalMapper.getSalerCapitalByIds(ids);
    }


    /**
     * 导出商家的已添加的违约金开票列表
     * @param sellerid
     * @param salercapitalids
     * @return
     */
     public List<Map<String,Object>> getExcelSellerBillid(Long sellerid, Long[] salercapitalids) {
         List<Map<String, Object>> list =  salerCapitalMapper.getExcelSellerBillid(sellerid,salercapitalids);
         List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
             for (Map<String, Object> map : list) {
                 Map<String, Object> maptemp = new HashMap<String, Object>();
                 maptemp.put("违约订单号", map.get("orderno"));
                 maptemp.put("完成时间", map.get("tradetime"));
                 maptemp.put("交易号", map.get("tradeno"));
                 maptemp.put("违约类型", (Integer) map.get("capitaltype") == 0 ? "订单金额":(Integer) map.get("capitaltype") == 1 ? "上架保证金":(Integer) map.get("capitaltype") == 2 ? "下架保证金":(Integer) map.get("capitaltype") == 3 ? "退款金额":(Integer) map.get("capitaltype") == 4 ?"充值":(Integer) map.get("capitaltype") == 5 ?"余额提现":(Integer) map.get("capitaltype") == 6 ?"买家违约金":(Integer) map.get("capitaltype") == 7 ?"卖家违约金":(Integer) map.get("capitaltype") == 8 ?"余款":(Integer) map.get("capitaltype") == 9 ?"全款":(Integer) map.get("capitaltype") == 10?"定金":"货款提现");
                 maptemp.put("违约金额",map.get("penalty"));
                 if(map.get("paytype") != null && !"".equals(map.get("paytype"))) {
                     maptemp.put("付款方式", (Integer) map.get("paytype") == 0 ? "支付宝" : (Integer) map.get("paytype") == 1 ? "微信" : (Integer) map.get("paytype") == 2 ? "银行卡" : (Integer) map.get("paytype") == 3 ? "余额" : (Integer) map.get("paytype") == 4 ? "授信" : "");
                 }else{
                     maptemp.put("付款方式","");
                 }
                 list2.add(maptemp);
             }
         return list2;
     }
}
