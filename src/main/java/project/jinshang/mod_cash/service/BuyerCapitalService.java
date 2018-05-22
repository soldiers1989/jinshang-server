package project.jinshang.mod_cash.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.utils.DateUtils;
import project.jinshang.common.utils.JinshangUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_admin.mod_cash.dto.AdvanceCapitalQueryDto;
import project.jinshang.mod_cash.BuyerCapitalMapper;
import project.jinshang.mod_cash.bean.BuyerCapital;
import project.jinshang.mod_cash.bean.BuyerCapitalExample;
import project.jinshang.mod_cash.bean.dto.BuyerCapitalAccountQueryDto;
import project.jinshang.mod_cash.bean.dto.BuyerCapitalQueryDto;
import project.jinshang.mod_cash.bean.dto.BuyerCapitalViewDto;
import project.jinshang.mod_member.service.MemberService;


import java.util.*;

@Service
public class BuyerCapitalService {

    @Autowired
    private BuyerCapitalMapper buyerCapitalMapper;


    @Autowired
    private MemberService memberService;



    public  BuyerCapital getById(long id){
        return  buyerCapitalMapper.selectByPrimaryKey(id);
    }



    public PageInfo  listConsume(long memberId, Date starttime, Date endtime, short capitaltype, short paytype, short rechargeperform, int pageNo, int pageSize){

        BuyerCapitalQueryDto  buyerCapital=new BuyerCapitalQueryDto();

        if(memberId != 0) {
            buyerCapital.setMemberid(memberId);
        }

        if (starttime != null ){
            buyerCapital.setTradetimeStart(starttime);
        }

        if (endtime != null &&  !endtime.equals("")){
            buyerCapital.setTradetimeEnd(DateUtils.addDays(endtime,1));
        }

        if (paytype != -1){
            buyerCapital.setPaytype(paytype);
        }

        if (rechargeperform !=-1){
            buyerCapital.setRechargeperform(rechargeperform);
        }

        if (capitaltype !=-1){
            buyerCapital.setCapitaltype(capitaltype);
        }

        return  listConsume(buyerCapital,pageNo,pageSize);
    }



    public PageInfo  listConsume(BuyerCapitalQueryDto queryDto,int pageNo,int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<BuyerCapital> list=buyerCapitalMapper.selectBuyerCapitalByConsume(queryDto);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }


    /**
     * 后台导出买家资金明细 for excel
     * @param queryDto
     * @return
     */
    public List<Map<String,Object>> listConsumeForAdminExportExcel(BuyerCapitalQueryDto queryDto) {

        List<Map<String,Object>> list=buyerCapitalMapper.listForPurchaserExportExcel(queryDto);

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
                Map<String,Object> bill = new HashMap<String,Object>();
                bill = buyerCapitalMapper.getBillingRecordByMemberid(Long.parseLong(map.get("memberid").toString()));
                if(bill!=null) {
                    resMap.put("开票抬头", bill.get("invoiceheadup"));
                    resMap.put("税号", bill.get("texno"));
                    resMap.put("开户行", bill.get("bankofaccounts"));
                    resMap.put("开户账号", bill.get("account"));
                    resMap.put("开票地址", bill.get("address"));
                    resMap.put("电话", bill.get("phone"));
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
/*
        List<Map<String,Object>> newList = new ArrayList<Map<String,Object>>();
        for(int i=0;i<data.size();i++){
            Map<String,Object> newMap = new HashMap<String,Object>();
            String flag=data.get(i).get("订单号").toString();
            if(newList.size()<1){
                newMap.put("下单时间",data.get(i).get("下单时间"));
                newMap.put("合同号",data.get(i).get("合同号"));
                newMap.put("订单号",data.get(i).get("订单号"));
                newMap.put("交易号",data.get(i).get("交易号"));
                newMap.put("买家",data.get(i).get("买家"));
                newMap.put("卖家",data.get(i).get("卖家"));
                newMap.put("订单类型",data.get(i).get("订单类型"));
                newMap.put("订单来源",data.get(i).get("订单来源"));
                newMap.put("开票抬头",data.get(i).get("开票抬头"));
                newMap.put("税号",data.get(i).get("税号"));
                newMap.put("开户行",data.get(i).get("开户行"));
                newMap.put("开户账号",data.get(i).get("开户账号"));
                newMap.put("开票地址",data.get(i).get("开票地址"));
                newMap.put("电话",data.get(i).get("电话"));
                newMap.put("是否开票",data.get(i).get("是否开票"));
                newMap.put("订单状态",data.get(i).get("订单状态"));
                newMap.put("项目",data.get(i).get("项目"));
                newMap.put("收件人",data.get(i).get("收件人"));
                newMap.put("收货地址",data.get(i).get("收货地址"));
                newMap.put("付款方式",data.get(i).get("付款方式"));
                newMap.put("物流公司",data.get(i).get("物流公司"));
                newMap.put("快递单号",data.get(i).get("快递单号"));
                newMap.put("业务员",data.get(i).get("业务员"));
                newMap.put("第三方支付号",data.get(i).get("第三方支付号"));
                newMap.put("业务单号",data.get(i).get("业务单号"));
            }else if(!newList.get(i-1).get("订单号").equals(data.get(i).get("订单号")) && !data.get(i-1).get("订单号").equals(flag)){
                newMap.put("下单时间",data.get(i).get("下单时间"));
                newMap.put("合同号",data.get(i).get("合同号"));
                newMap.put("订单号",data.get(i).get("订单号"));
                newMap.put("交易号",data.get(i).get("交易号"));
                newMap.put("买家",data.get(i).get("买家"));
                newMap.put("卖家",data.get(i).get("卖家"));
                newMap.put("订单类型",data.get(i).get("订单类型"));
                newMap.put("订单来源",data.get(i).get("订单来源"));
                newMap.put("开票抬头",data.get(i).get("开票抬头"));
                newMap.put("税号",data.get(i).get("税号"));
                newMap.put("开户行",data.get(i).get("开户行"));
                newMap.put("开户账号",data.get(i).get("开户账号"));
                newMap.put("开票地址",data.get(i).get("开票地址"));
                newMap.put("电话",data.get(i).get("电话"));
                newMap.put("是否开票",data.get(i).get("是否开票"));
                newMap.put("订单状态",data.get(i).get("订单状态"));
                newMap.put("项目",data.get(i).get("项目"));
                newMap.put("收件人",data.get(i).get("收件人"));
                newMap.put("收货地址",data.get(i).get("收货地址"));
                newMap.put("付款方式",data.get(i).get("付款方式"));
                newMap.put("物流公司",data.get(i).get("物流公司"));
                newMap.put("快递单号",data.get(i).get("快递单号"));
                newMap.put("业务员",data.get(i).get("业务员"));
                newMap.put("第三方支付号",data.get(i).get("第三方支付号"));
                newMap.put("业务单号",data.get(i).get("业务单号"));
            }else if(data.get(i).get("订单号").equals(flag)){
                newMap.put("下单时间","");
                newMap.put("合同号","");
                newMap.put("订单号","");
                newMap.put("交易号","");
                newMap.put("买家","");
                newMap.put("卖家","");
                newMap.put("订单类型","");
                newMap.put("订单来源","");
                newMap.put("开票抬头","");
                newMap.put("税号","");
                newMap.put("开户行","");
                newMap.put("开户账号","");
                newMap.put("开票地址","");
                newMap.put("电话","");
                newMap.put("是否开票","");
                newMap.put("订单状态","");
                newMap.put("项目","");
                newMap.put("收件人","");
                newMap.put("收货地址","");
                newMap.put("付款方式","");
                newMap.put("物流公司","");
                newMap.put("快递单号","");
                newMap.put("业务员","");
                newMap.put("第三方支付号","");
                newMap.put("业务单号","");

            }
            newMap.put("商品名称",data.get(i).get("商品名称"));
            newMap.put("规格",data.get(i).get("规格"));
            newMap.put("材质",data.get(i).get("材质"));
            newMap.put("牌号",data.get(i).get("牌号"));
            newMap.put("品牌",data.get(i).get("品牌"));
            newMap.put("印记",data.get(i).get("印记"));
            newMap.put("表面处理",data.get(i).get("表面处理"));
            newMap.put("包装方式",data.get(i).get("包装方式"));
            newMap.put("单位",data.get(i).get("单位"));
            newMap.put("单价",data.get(i).get("单价"));
            newMap.put("订购量",data.get(i).get("订购量"));
            newMap.put("货款金额",data.get(i).get("货款金额"));
            newList.add(newMap);
        }*/
        return  data;
    }



    public  void  insertSelective(BuyerCapital buyerCapital){
        buyerCapitalMapper.insertSelective(buyerCapital);
    }


    /**
     * 根据充值单号查询
     * @param rechagernumber
     * @return
     */
    public  BuyerCapital getBuyerCapitalByRechargenumber(String rechagernumber){
        return  buyerCapitalMapper.getBuyerCapitalByRechargenumber(rechagernumber);
    }


    public  void  updateByPrimaryKeySelective(BuyerCapital buyerCapital){
        buyerCapitalMapper.updateByPrimaryKeySelective(buyerCapital);
    }

    /**
     * 列出会员的可用金额、冻结金额和历史充值总金额
     * @param pageNo
     * @param PageSize
     * @return
     */
    public PageInfo advanceCapitalList(int pageNo, int PageSize, AdvanceCapitalQueryDto queryDto){

        PageHelper.startPage(pageNo,PageSize);

        List<Map<String,Object>> list = buyerCapitalMapper.advanceCapitalList(queryDto);

        PageInfo pageInfo =  new PageInfo(list);
        return  pageInfo;
    }


    /**
     * 买家资金列表查询
     * @param pageNo
     * @param pageSize
     * @param dto
     * @return
     */
    public  PageInfo list(int pageNo, int pageSize, BuyerCapitalQueryDto dto){
        PageHelper.startPage(pageNo,pageSize);
        List<BuyerCapitalViewDto> list = buyerCapitalMapper.list(dto);
        PageInfo pageInfo =  new PageInfo(list);
        return  pageInfo;
    }

    /**
     * 买家对账单所用的资金列表查询
     * @param dto
     * @return
     */
    public List<BuyerCapitalViewDto> listForAccount(BuyerCapitalAccountQueryDto dto){
        List<BuyerCapitalViewDto> listForAccount=null;
        if (StringUtils.isEmpty(dto.getCompanyname())&&StringUtils.isEmpty(dto.getInvoicename())
                &&StringUtils.isEmpty(dto.getRealname())&&StringUtils.isEmpty(dto.getMobile())
                &&dto.getTradetimeEnd()==null &&dto.getTradetimeStart()==null&&
                (dto.getMemberid()==null||dto.getMemberid()<0)){

        }else{
//            PageHelper.startPage(pageNo,pageSize);
            listForAccount=buyerCapitalMapper.listForAccount(dto);
        }
        return listForAccount;
    }

   public void  updateBillcreateid( long billcreateid,String ids){
        buyerCapitalMapper.updateBillcreateid(billcreateid,ids);
   }


   public  List<BuyerCapital>  listByBillcreateid(long billcreateid){
       BuyerCapitalExample example =  new BuyerCapitalExample();
       BuyerCapitalExample.Criteria criteria = example.createCriteria();
       criteria.andBillcreateidEqualTo(billcreateid);

       return  buyerCapitalMapper.selectByExample(example);
   }


   public  List<BuyerCapital> selectByExample(BuyerCapitalExample example){
       return  buyerCapitalMapper.selectByExample(example);
   }


    /**
     * 违约金查询
     * @param queryMap
     * @param pageNo
     * @param pageSize
     * @return
     */
   public PageInfo breakContractListLogs(Map<String,Object> queryMap,int pageNo,int pageSize){
       PageHelper.startPage(pageNo,pageSize);
       List<Map> list = buyerCapitalMapper.breakContractListLogs(queryMap);
       return  new PageInfo(list);
   }


    /**
     * 后台违约金导出Excel
     * @param queryMap
     * @return
     */
    public List<Map<String,Object>> breakContractListLogsForAdminExcel(Map<String,Object> queryMap){

        List<Map<String,Object>> list = buyerCapitalMapper.breakContractListLogsForAdminExcel(queryMap);

        List<Map<String,Object>> data =  new ArrayList<>();

        String[] rowTitles = new String[]{"时间","客户","卖方","交易号","类型","合同金额","违约事由","违约金额"};

        for(Map<String,Object> map : list){
            Map<String,Object> resMap =  new HashMap<>();

            resMap.put("时间",map.get("tradetime"));
            resMap.put("客户",map.get("buyerusername"));
            if(map.get("buyerrealname")!="" && map.get("buyerrealname")!=null){
                resMap.put("客户",map.get("buyerrealname"));
            }
            resMap.put("卖方",map.get("username"));
            if(map.get("realname")!="" && map.get("realname")!=null){
                resMap.put("卖方",map.get("realname"));
            }
            if(map.get("companyname")!="" && map.get("companyname")!=null){
                resMap.put("卖方",map.get("companyname"));
            }
            resMap.put("交易号",map.get("tradeno"));
            Integer type = (Integer) map.get("capitaltype");

            resMap.put("类型",JinshangUtils.sellerCapitalType(new Short(String.valueOf(type))));
            resMap.put("合同金额",map.get("actualpayment"));
            resMap.put("违约事由",map.get("remark"));
            resMap.put("违约金额",map.get("penalty"));

            data.add(resMap);
        }

        return  data;
    }



    /**
     * 买家违约金导出Excel
     * @param queryMap
     * @return
     */
    public List<Map<String,Object>> breakContractListLogsForBuyerExcel(Map<String,Object> queryMap){

        List<Map<String,Object>> list = buyerCapitalMapper.breakContractListLogsForBuyerExcel(queryMap);

        List<Map<String,Object>> data =  new ArrayList<>();

        String[] rowTitles = new String[]{"时间","客户","卖方","交易号","类型","合同金额","违约事由","违约金额"};

        for(Map<String,Object> map : list){
            Map<String,Object> resMap =  new HashMap<>();

            resMap.put("时间",map.get("tradetime"));
            resMap.put("客户",map.get("buyerusername"));
            resMap.put("卖方",map.get("username"));
            resMap.put("交易号",map.get("tradeno"));
            Integer type = (Integer) map.get("capitaltype");

            resMap.put("类型",JinshangUtils.sellerCapitalType(new Short(String.valueOf(type))));
            resMap.put("合同金额",map.get("allpay"));
            resMap.put("违约事由",map.get("remark"));
            resMap.put("违约金额",map.get("penalty"));

            data.add(resMap);
        }

        return  data;
    }



    public  int countByExample(BuyerCapitalExample example){
        return  buyerCapitalMapper.countByExample(example);
    }

    /**
     * 卖家应收账款财务管理excel导出
     * @param queryDto
     * @return
     */
    public List<Map<String,Object>> ExcelExportUserCapitalManagement(BuyerCapitalQueryDto queryDto){
        List<Map<String,Object>> list=buyerCapitalMapper.ExcelExportUserCapitalManagement(queryDto);
        List<Map<String,Object>> data =  new ArrayList<>();
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
        for(Map<String,Object> map: list){
            Map<String, Object> resMap = new HashMap<>();
            if(map.get("rechargeperform")!=null && map.get("rechargeperform")!="" && Integer.valueOf(map.get("rechargeperform").toString())==0){
                resMap.put("汇入行","微信");
            }else if(Integer.valueOf(map.get("rechargeperform").toString())==1){
                resMap.put("汇入行","支付宝");
            }else{
                resMap.put("汇入行","");
            }
            resMap.put("交易时间",map.get("tradetime"));
            resMap.put("凭证号","");
            if(map.get("buyercompanyname")!=null && map.get("buyercompanyname")!=""){
                resMap.put("汇款人名称",map.get("buyercompanyname"));
            }else if(map.get("buyerrealname")!=null && map.get("buyerrealname")!=""){
                resMap.put("汇款人名称",map.get("buyerrealname"));
            }else{
                resMap.put("汇款人名称",map.get("buyerusername"));
            }
            if((map.get("capitaltype")!=null && map.get("capitaltype")!="") && Integer.valueOf(map.get("capitaltype").toString())==2){
                resMap.put("金额",Double.parseDouble("-"+map.get("capital").toString()));
            }else{
                resMap.put("金额",map.get("capital"));
            }
            resMap.put("款项归属","");
            if((map.get("isonline")!=null && map.get("isonline")!= "") && Integer.valueOf(map.get("isonline").toString())==2){
                resMap.put("备注","限时购");
            }else{
                resMap.put("备注","其它");
            }
            resMap.put("卖家","");
            if(Integer.valueOf(map.get("capitaltype").toString())==0){
                resMap.put("订单号",map.get("uuid"));
            }else{
                resMap.put("订单号","buyer-"+map.get("rechargenumber"));
            }
            if(map.get("orderno")!=null && map.get("orderno")!=""){
                List<Map<String, Object>> productList=buyerCapitalMapper.getProductList(map.get("orderno").toString());
                if(productList.size()>0) {
                    for (Map<String, Object> m : productList) {
                        if (m.get("producttype").equals("紧固件")) {
                            resMap.put("类别", m.get("producttype"));
                        } else {
                            resMap.put("类别", m.get("level1"));
                        }
                    }
                    data.add(resMap);
                }else{
                    resMap.put("类别", "");
                    data.add(resMap);
                }
            }else{
                resMap.put("类别", "");
                data.add(resMap);
            }
        }
        return data;
    }
}