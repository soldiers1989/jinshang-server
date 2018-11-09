package project.jinshang.mod_admin.mod_count;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.utils.DateUtils;
import project.jinshang.mod_admin.mod_count.bean.*;
import project.jinshang.mod_admin.mod_count.service.ProductViewService;
import project.jinshang.mod_admin.mod_count.service.SearchKeyRecordService;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_product.bean.OrderQueryParam;
import project.jinshang.mod_product.service.OrdersService;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

@RestController
@RequestMapping("/rest/admin/statistics")
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
@Api(tags = "后台统计模块",description = "后台统计模块")
@Transactional(rollbackFor =Exception.class)
public class StatisticsAction {

    @Autowired
    private MemberService memberService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private ProductViewService productViewService;

    @Autowired
    private SearchKeyRecordService searchKeyRecordService;


    private class StatisticsRet extends BasicRet{
        private class Data{
            private List<MemberStatistcModel> memberStatistcModels;

            private OrderComplex orderComplex;

            public OrderComplex getOrderComplex() {
                return orderComplex;
            }

            public void setOrderComplex(OrderComplex orderComplex) {
                this.orderComplex = orderComplex;
            }

            public List<MemberStatistcModel> getMemberStatistcModels() {
                return memberStatistcModels;
            }

            public void setMemberStatistcModels(List<MemberStatistcModel> memberStatistcModels) {
                this.memberStatistcModels = memberStatistcModels;
            }
        }
        private Data data = new Data();

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }
    }


    @RequestMapping(value = "/getMemberRegisterNumList",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户注册统计数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime",value = "开始时间",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "endTime",value = "结束时间",required = false,paramType = "query",dataType = "string"),
    })
    public StatisticsRet getMemberRegisterNumList(MemberQueryParam param){
        StatisticsRet statisticsRet = new StatisticsRet();
        List<MemberStatistcModel> list = memberService.getMemberRegisterStatistic(param);
        statisticsRet.data.memberStatistcModels = list;
        statisticsRet.setResult(BasicRet.SUCCESS);
        statisticsRet.setMessage("返回成功");
        return statisticsRet;
    }

    @RequestMapping(value = "/getOrderStatisticNumList",method = RequestMethod.POST)
    @ApiOperation(value = "获取订单统计数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime",value = "开始时间",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "endTime",value = "结束时间",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "shopname",value = "店铺名称",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "orderState",value = "订单状态",required = false,paramType = "query",dataType = "int"),
    })
    public StatisticsRet getOrderStatisticNumList(OrderQueryParam param){
        StatisticsRet statisticsRet = new StatisticsRet();
        OrderComplex orderComplex = ordersService.getOrderComplex(param);
        statisticsRet.data.orderComplex = orderComplex;
        statisticsRet.setResult(BasicRet.SUCCESS);
        statisticsRet.setMessage("返回成功");
        return statisticsRet;
    }


    @RequestMapping(value = "/getProductview",method = RequestMethod.POST)
    @ApiOperation("商品浏览量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime",value = "开始时间",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "endTime",value = "结束时间",required = false,paramType = "query",dataType = "string"),
    })
    public  ProductViewRet getProductview(Date startTime,Date endTime){

        ProductViewRet productViewRet =  new ProductViewRet();

        Calendar calendar = Calendar.getInstance();

        int yyyymmdd_start = 0;
        int yyyymmdd_end = 0;

        if(endTime != null){
            yyyymmdd_end = new Integer(DateUtils.format(endTime,"yyyyMMdd"));
        }else{
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE,-1);
            endTime = calendar.getTime();
            yyyymmdd_end= new Integer(DateUtils.format(calendar.getTime(),"yyyyMMdd"));
            calendar.clear();
        }

        if(startTime != null){
            yyyymmdd_start = new Integer(DateUtils.format(startTime,"yyyyMMdd"));
        }else{
            calendar.setTime(endTime);
            calendar.add(Calendar.DATE,-7);
            yyyymmdd_start = new Integer(DateUtils.format(calendar.getTime(),"yyyyMMdd"));
            calendar.clear();
        }

        List<Map<String,Object>> list = productViewService.getMaxCountByyyyymmdd(yyyymmdd_end,5);


        List<Integer> yyyyMMddList =  new ArrayList<>();
        List<Long> pdidList = new ArrayList<>();
        List<List<Map<String,Object>>> resList = new ArrayList<>();

        if(list != null && list.size()>0) {
            StringBuilder ids = new StringBuilder();
            list.forEach(map -> {
                ids.append(map.get("pdid")).append(",");
            });

            String ids_s =  ids.toString().substring(0,ids.length()-1);

            List<Map<String,Object>> data = productViewService.getViewList(yyyymmdd_start,yyyymmdd_end,ids_s);

            data.forEach(map->{
                if( !yyyyMMddList.contains(map.get("yyyymmdd")) ) {
                    yyyyMMddList.add((Integer) map.get("yyyymmdd"));
                }

                if(!pdidList.contains(map.get("pdid"))){
                    pdidList.add((Long) map.get("pdid"));
                }
            });


            yyyyMMddList.sort((Integer i1,Integer i2)->i1.compareTo(i2));


            for(Integer yyyymmdd : yyyyMMddList){
                List<Map<String,Object>> sameDayList = new ArrayList<>();
                for(Long pdid : pdidList){
                    Map<String,Object> map = getByYyyymmddAndPdid(data,yyyymmdd,pdid);

                    if(map == null){
                        map = new HashMap<>();
                        map.put("pdid",pdid);
                        map.put("count",0);
                        map.put("productname",getProdName(data,pdid));
                    }

                    map.put("yyyymmdd",DateUtils.format(DateUtils.StrToDate(yyyymmdd.toString(),"yyyymmdd"),"yyyy-MM-dd"));

                    sameDayList.add(map);
                }

                resList.add(sameDayList);
            }
        }

        productViewRet.data.viewList = resList;
        List<String> dateStrList = new ArrayList<>();
        yyyyMMddList.forEach(yyyymmdd->{
            dateStrList.add(DateUtils.format(DateUtils.StrToDate(yyyymmdd.toString(),"yyyymmdd"),"yyyy-MM-dd"));
        });
        productViewRet.data.dateList = dateStrList;
        productViewRet.setResult(BasicRet.SUCCESS);

        return  productViewRet;
    }



    private  class  ProductViewRet extends  BasicRet{
        private  class  ProductViewData{
            private  List<List<Map<String,Object>>> viewList;
            private  List<String> dateList;

            public List<List<Map<String, Object>>> getViewList() {
                return viewList;
            }

            public void setViewList(List<List<Map<String, Object>>> viewList) {
                this.viewList = viewList;
            }

            public List<String> getDateList() {
                return dateList;
            }

            public void setDateList(List<String> dateList) {
                this.dateList = dateList;
            }
        }

        private  ProductViewData data = new ProductViewData();

        public ProductViewData getData() {
            return data;
        }

        public void setData(ProductViewData data) {
            this.data = data;
        }
    }




    @RequestMapping(value = "/getRegisterRate",method = RequestMethod.POST)
    @ApiOperation("注册购买率")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime",value = "开始时间",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "endTime",value = "结束时间",required = true,paramType = "query",dataType = "string"),
    })
    public  RegisRateRet getRegisterRate(@RequestParam(required = true) Date startTime,
                                         @RequestParam(required = true) Date endTime){
        RegisRateRet regisRateRet =new RegisRateRet();

        List<Map<String,Object>> resList = memberService.getRegisterRate(startTime,endTime);

        List<String> timeList = new ArrayList<>();
        List<BigDecimal> rateList = new ArrayList<>();

        DecimalFormat df = new DecimalFormat("#.000");

        for(Map<String,Object> map : resList){
            timeList.add((String) map.get("registerday"));

            Long registerCount = (Long) map.get("registercount");
            Long buyCount = (Long) map.get("buycount");

            if(buyCount == 0){
                rateList.add(new BigDecimal(0));
            }else{
                rateList.add( new BigDecimal(buyCount).divide(new BigDecimal(registerCount),BigDecimal.ROUND_HALF_UP,2).multiply(new BigDecimal(100)));
            }
        }

        regisRateRet.data.timeList =  timeList;
        regisRateRet.data.rateList =  rateList;
        regisRateRet.setResult(BasicRet.SUCCESS);
        return  regisRateRet;
    }




    private class RegisRateRet extends  BasicRet{
        private class  RegisRateData {
            private  List<String> timeList;
            private  List<BigDecimal> rateList;

            public List<String> getTimeList() {
                return timeList;
            }

            public void setTimeList(List<String> timeList) {
                this.timeList = timeList;
            }

            public List<BigDecimal> getRateList() {
                return rateList;
            }

            public void setRateList(List<BigDecimal> rateList) {
                this.rateList = rateList;
            }
        }

        private  RegisRateData data =  new RegisRateData();

        public RegisRateData getData() {
            return data;
        }

        public void setData(RegisRateData data) {
            this.data = data;
        }
    }



    private  String getProdName(List<Map<String,Object>> list,Long pdid){
        for(Map<String,Object> map : list){
            if(pdid.equals(map.get("pdid"))){
                return (String) map.get("productname");
            }
        }

        return  null;
    }


    private  Map<String,Object> getByYyyymmddAndPdid(List<Map<String,Object>> list,Integer yyyymmdd,Long pdid){
        for(Map<String,Object> map : list){
            if(yyyymmdd.equals(map.get("yyyymmdd")) && pdid.equals(map.get("pdid"))){
                return  map;
            }
        }

        return  null;
    }

    @PostMapping(value = "/searchkeycount")
    @ApiOperation(value = "获取搜索统计数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime",value = "开始时间",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "endTime",value = "结束时间",required = false,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "pageNo", value = "页码", required = false, paramType = "query", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, paramType = "query", dataType = "int", defaultValue = "20"),
    })
    public PageRet searchcount(int pageNo, int pageSize,SearchKeyQueryParam searchKeyQueryParam){
        Date endTime = searchKeyQueryParam.getEndTime();
        if(endTime!=null){
            Calendar c = Calendar.getInstance();
            c.setTime(endTime);
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
            Date tomorrow = c.getTime();
            searchKeyQueryParam.setEndTime(tomorrow);
        }
        PageInfo pageInfo = searchKeyRecordService.getListByPage(pageNo,pageSize,searchKeyQueryParam);
        PageRet pageRet = new PageRet();
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("返回成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }


}
