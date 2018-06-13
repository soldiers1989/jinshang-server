package project.jinshang.mod_techplatform;

import com.github.pagehelper.PageInfo;
import com.netflix.discovery.converters.Auto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import mizuki.project.core.restserver.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.Page;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.MD5Tools;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_company.bean.SellerCompanyInfo;
import project.jinshang.mod_company.bean.SellerCompanyInfoExample;
import project.jinshang.mod_company.bean.Store;
import project.jinshang.mod_company.service.SellerCompanyInfoService;
import project.jinshang.mod_company.service.StoreService;
import project.jinshang.mod_product.bean.*;
import project.jinshang.mod_product.service.OrdersService;
import project.jinshang.mod_product.service.ProductStoreService;

import javax.persistence.criteria.Order;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 紧商网接受中间件管理平台调用
 *
 * @author xiazy
 * @create 2018-06-02 16:16
 **/
@RestController
@RequestMapping("/api")
@Api(tags = "紧商网接受中间件管理平台调用")
@Transactional(rollbackFor = Exception.class)
public class ApiTecPlatformAction {
    @Autowired
    public OrdersService ordersService;
    @Autowired
    public SellerCompanyInfoService sellerCompanyInfoService;
    @Autowired
    public ProductStoreService productStoreService;
    @Autowired
    public StoreService storeService;

    @RequestMapping(value = "/jinshang",method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation(value = "调用主入口")
    @ApiImplicitParam(name = "middleManaCallbackJson",value = "中间件传入的参数",required = true,paramType = "query",dataType = "string")
    public ApiTecRet remoteInvoke(@RequestParam(value = "middleManaCallbackJson") String middleManaCallbackJson){
        ApiTecRet apiTecRet=new ApiTecRet();
        Map<String,Object> paraMap= JsonUtil.toMap(middleManaCallbackJson);
        String type=paraMap.get("type").toString();
        apiTecRet.setType(type);
        BasicRet basicRet=verification(paraMap);
        if (basicRet.getResult()!=1){
            apiTecRet.setStatus("err");
            apiTecRet.setErrcode(basicRet.getResult());
            apiTecRet.setErrdesc("接口的校验码检验失败!");
            //不清楚这个timestamp的值怎么获取
            apiTecRet.setTimestamp(System.currentTimeMillis());
            return apiTecRet;
        }
        if (DockType.JS002.getType().equals(type)){
            return reWriteOrderBack(paraMap);
        }else if (DockType.JS005.getType().equals(type)){
            String appId= (String) paraMap.get("appid");
            SellerCompanyInfoExample example=new SellerCompanyInfoExample();
            SellerCompanyInfoExample.Criteria criteria=example.createCriteria();
            criteria.andAppidEqualTo(appId);
//            List<SellerCompanyInfo> sellerCompanyInfoList=sellerCompanyInfoService.selectByExample(example);
//            开始调用库存同步共用方法
//            BasicRet basicRet1=productStoreService.stockSynCom(sellerCompanyInfoList.get(0).getMemberid());
            apiTecRet.setStatus("success");
            apiTecRet.setType(DockType.JS005.getType());
//            apiTecRet.setErrcode(basicRet1.getResult());
//            apiTecRet.setErrdesc(basicRet1.getMessage());
            //不清楚这个timestamp的值怎么获取
            apiTecRet.setTimestamp(System.currentTimeMillis());
            return apiTecRet;
        }
        return apiTecRet;
    }



    /**
     *处理订单状态回写
     * @author xiazy
     * @date  2018/6/6 9:30
     * @param paraMap 中间件管理平台post数据
     * @return project.jinshang.mod_techplatform.ApiTecPlatformAction.ApiTecRet
     */
    private ApiTecRet reWriteOrderBack(Map paraMap){
        ApiTecRet apiTecRet=new ApiTecRet();
        OrdersExample ordersExample=new OrdersExample();
        OrdersExample.Criteria criteria=ordersExample.createCriteria();
        String orderNo= (String) paraMap.get("orderno");
        criteria.andOrdernoEqualTo(orderNo);
        Orders order=new Orders();
        String orderstatus=(String) paraMap.get("orderstatus");
        Short transorderstatus= DockType.OrderStatusType.transOrderStatus(orderstatus);
        String logisticscompany= (String) paraMap.get("logisticscompany");
        String couriernumber= (String) paraMap.get("couriernumber");
        order.setOrderstatus(transorderstatus);
        order.setLogisticscompany(logisticscompany);
        order.setCouriernumber(couriernumber);
        ordersService.updateByExampleSelective(order,ordersExample);
        apiTecRet.setStatus("success");
        apiTecRet.setType(DockType.JS002.getType());
        apiTecRet.setErrdesc(null);
        apiTecRet.setTimestamp(System.currentTimeMillis());
        return apiTecRet;
    }


    /**
     *校验传入的JSON头部信息
     * @author xiazy
     * @date  2018/6/6 11:25
     * @param param 传入的map参数
     * @return mizuki.project.core.restserver.config.BasicRet
     */
    public BasicRet verification(Map param){
        BasicRet basicRet=new BasicRet();
        basicRet.setMessage("校验通过");
        basicRet.setResult(BasicRet.SUCCESS);
        String appId= (String) param.get("appid");
        Long timeStamp= Long.parseLong(param.get("timestamp").toString());
        SellerCompanyInfoExample example=new SellerCompanyInfoExample();
        SellerCompanyInfoExample.Criteria criteria=example.createCriteria();
        criteria.andAppidEqualTo(appId);
//        List<SellerCompanyInfo> sellerCompanyInfoList=sellerCompanyInfoService.selectByExample(example);
//        String appSecret=sellerCompanyInfoList.get(0).getAppsecret();
//        String checkNotify=MD5Tools.MD5(appId+appSecret+timeStamp);
        String notify=(String)param.get("notify");
//        if (StringUtils.hasText(checkNotify)&&StringUtils.hasText(notify)&&checkNotify.equals(notify)){
            return basicRet;
//        }else{
//            basicRet.setMessage("校验不通过！");
//            basicRet.setResult(BasicRet.TOKEN_ERR);
//            return basicRet;
//        }
    }


//    /**
//     *库存同步(主动)/库存同步(被动)的共方法
//     * @author xiazy
//     * @date  2018/6/6 17:59
//     * @param sellerId 卖家的id
//     * @return mizuki.project.core.restserver.config.BasicRet
//     */
//    public BasicRet stockSynCom(Long sellerId){
//        BasicRet basicRet=new BasicRet();
//        basicRet.setResult(BasicRet.SUCCESS);
//        basicRet.setMessage("库存同步主体方法调用成功");
//        List<Store> storeList=storeService.getAllByMember(sellerId);
//        Map<String,Object> retmap=ordersService.verification(sellerId);
//        if (((BasicRet)retmap.get("basicRet")).getResult()!=1){
//            basicRet= (BasicRet)retmap.get("basicRet");
//            return basicRet;
//        }
////        PageInfo pageInfo = productStoreService.getCreditRecordList(member.getId(), creditApplyRecord, pageNo, pageSize);
////        buyerCreditRet.data.pageInfo = pageInfo;
////        buyerCreditRet.setMessage("返回成功");
////        buyerCreditRet.setResult(BasicRet.SUCCESS);
////        return buyerCreditRet;
//        Map<String,Object> sysParam=new HashMap<>();
//        String pdnoStr="";
//        String jsonParam="";
//        SellerCompanyInfo sellerCompanyInfo= (SellerCompanyInfo) retmap.get("sellerCompanyInfo");
//        String appId=sellerCompanyInfo.getAppid();
//        String appSecret=sellerCompanyInfo.getAppsecret();
//        String appUrl=sellerCompanyInfo.getAppurl();
//        Long timestamp=System.currentTimeMillis();
//        String notify=MD5Tools.MD5(appId+appSecret+timestamp);
//        Js006 js006=new Js006();
//        js006.setAppid(appId);
//        js006.setNotify(notify);
//        js006.setType(DockType.JS006.getType());
//        js006.setExtendjson(null);
//        js006.setTimestamp(String.valueOf(timestamp));
//        if (storeList!=null&&storeList.size()>0){
//            for (Store store:storeList) {
//                Long storeId=store.getId();
//                js006.setStore(String.valueOf(storeId));
//                PageInfo<Products> productsPageInfo=productStoreService.selectProductStoreForSyn(0,0,storeId,sellerId,ONSHEWLF);
//                int totalPageSize=productsPageInfo.getPages();
//                if (totalPageSize>0){
//                    for(int i=1;i<totalPageSize;i++){
//                        PageInfo<Products> currentPageInfo=productStoreService.selectProductStoreForSyn(i,PAGESIZE,storeId,sellerId,ONSHEWLF);
//                        int size=currentPageInfo.getSize();
//                        //计算出商品明细JSON
//                        pdnoStr="";
//                        for (Products product:currentPageInfo.getList()) {
//                            pdnoStr=pdnoStr+product.getProductno()+",";
//                        }
//                        Map<String,Object> pdnoMap=new HashMap<>();
//                        pdnoMap.put("pdno",pdnoStr);
//                        js006.setPdjson(JsonUtil.toJson(pdnoMap));
//                        Page page=new Page();
//                        page.setPageNo(i);
//                        page.setPageSize(size);
//                        js006.setPagejson(JsonUtil.toJson(page));
//                        jsonParam=JsonUtil.toJson(js006);
//                        ordersService.sendToMiddleManage(appUrl,JsonUtil.toMap(jsonParam));
//                    }
//                }
//            }
//            return basicRet;
//        }else{
//            basicRet.setMessage("没有获取对应卖家的所属仓库");
//            basicRet.setResult(BasicRet.ERR);
//            return basicRet;
//        }
//    }



    private static  class ApiTecRet extends BasicRet{
        private String status;
        private String type;
        private int errcode;
        private String errdesc;
        private Long timestamp;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getErrcode() {
            return errcode;
        }

        public void setErrcode(int errcode) {
            this.errcode = errcode;
        }

        public String getErrdesc() {
            return errdesc;
        }

        public void setErrdesc(String errdesc) {
            this.errdesc = errdesc;
        }

        public void setTimestamp(Long timestamp) {
            this.timestamp = timestamp;
        }

        public Long getTimestamp() {
            return timestamp;
        }
    }

    private static class StockRet extends BasicRet{
        private class Data{
            private PageInfo pageInfo;

            public PageInfo getPageInfo() {
                return pageInfo;
            }

            public void setPageInfo(PageInfo pageInfo) {
                this.pageInfo = pageInfo;
            }
        }
        private Data data;

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }
    }


}
