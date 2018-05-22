package project.jinshang.mod_admin.mod_activity;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.DateUtils;
import project.jinshang.common.utils.ExcelGen;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_activity.bean.*;
import project.jinshang.mod_activity.bean.dto.LimitTimeProdQuery;
import project.jinshang.mod_activity.service.*;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_product.bean.ProductAttr;
import project.jinshang.mod_product.bean.ProductInfo;
import project.jinshang.mod_product.bean.ProductStore;
import project.jinshang.mod_product.service.ProductAttrService;
import project.jinshang.mod_product.service.ProductInfoService;
import project.jinshang.mod_product.service.ProductStoreService;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * create : wyh
 * date : 2018/1/9
 */

@RestController
@Api(tags = "后台活动-限时购")
@RequestMapping("/rest/admin/limitbuy")
@Transactional(rollbackFor = Exception.class)
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
public class LimitTimeBuyManageAction {

    @Resource
    private LimitTimeCategoryService limitTimeCategoryService;

    @Resource
    private LimitTimeSettingService limitTimeSettingService;

    @Resource
    private LimitTimeProdService limitTimeProdService;

    @Resource
    private LimitTimeStoreService limitTimeStoreService;

    @Autowired
    private ProductAttrService productAttrService;

    @Autowired
    private ProductStoreService productStoreService;

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private LimitTimeActivityService limitTimeActivityService;

    @PostMapping("/listLimitTimeProd")
    @ApiOperation("活动列表")
    public PageRet listLimitTimeProd(LimitTimeProdQuery query, @RequestParam(required = true,defaultValue = "1") int pageNo,
                                     @RequestParam(required = true,defaultValue = "10") int pageSize){
        PageRet pageRet =  new PageRet();

        if(query.getEndtime() != null){
            query.setEndtime(DateUtils.addDays(query.getEndtime(),1));
        }

        PageInfo pageInfo = limitTimeProdService.listBuyPageForAdmin(query,pageNo,pageSize);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        return  pageRet;
    }



    @GetMapping("/excelExport/listLimitTimeProd")
    @ApiOperation("excel导出活动列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "状态",name = "state",required = false,paramType = "query"),
            @ApiImplicitParam(value = "活动标题",name = "activitytitle",required = false,paramType = "query"),
            @ApiImplicitParam(value = "活动商品类目",name = "activitycateid",required = false,paramType = "query"),
    })
    public ResponseEntity<InputStreamResource> listLimitTimeProdForExprotExcel(LimitTimeProdQuery query, Model model){

        if(query.getEndtime() != null){
            query.setEndtime(DateUtils.addDays(query.getEndtime(),1));
        }

        String[] rowTitles = new String[]{"活动名称","卖家","商品名称","品牌","单位"
              ,"商品类目","开始时间","结束时间","单个ID购买限制","销售量","销售金额","状态"};


        List<Map<String,Object>> resList =  limitTimeProdService.listBuyPageForAdminExportExcel(query);

        XSSFWorkbook workbook = null;
        try {
            workbook = ExcelGen.common("限时购活动列表",rowTitles,resList,null);
            if(workbook!=null){
                ByteArrayOutputStream baos=new   ByteArrayOutputStream();
                workbook.write(baos);
                System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("限时购活动列表.xlsx".getBytes(),"iso-8859-1")));
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                String contentType = "application/vnd.ms-excel";
                return ResponseEntity.ok()
                        .headers(headers).contentType(MediaType.parseMediaType(contentType))
                        .body(new InputStreamResource(new ByteArrayInputStream(baos.toByteArray())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(workbook != null){
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return  null;
    }




    @PostMapping("/limitTimeDetail")
    @ApiOperation("查看限时购活动详情")
    public  LimitTimeDetailRet  limitTimeDetail(@RequestParam(required = true) long id){
        LimitTimeDetailRet detailRet = new LimitTimeDetailRet();

        LimitTimeProd limitTimeProd = limitTimeProdService.getById(id);
        if(limitTimeProd == null){
            detailRet.setResult(BasicRet.ERR);
            detailRet.setMessage("活动不存在");
            return  detailRet;
        }

        ProductInfo productInfo =  productInfoService.getById(limitTimeProd.getPdid());
        if(productInfo == null){
            detailRet.setResult(BasicRet.ERR);
            detailRet.setMessage("参与活动的商品不存在");
            return  detailRet;
        }

        LimitTimeStoreExample example =  new LimitTimeStoreExample();
        LimitTimeStoreExample.Criteria criteria = example.createCriteria();
        criteria.andLimittimeidEqualTo(id);
        List<LimitTimeStore> limitTimeStoreList =  limitTimeStoreService.selectByExample(example);

        List<LimitTimeStoreView> limitTimeStoreViewList = new ArrayList<>();

        for(LimitTimeStore limitTimeStore : limitTimeStoreList){
            LimitTimeStoreView limitTimeStoreView = new LimitTimeStoreView();
            BeanUtils.copyProperties(limitTimeStore,limitTimeStoreView);

            ProductStore productStore = productStoreService.getByPdidAndPdno(limitTimeStore.getPdid(),limitTimeStore.getPdno());
            if(productStore != null) {
                limitTimeStoreView.setPdstorenum(productStore.getPdstorenum());
            }

            //查询该商品的属性信息
            List<ProductAttr> productAttrList =  productAttrService.getListByPidAndPdno(limitTimeStore.getPdid(),limitTimeStore.getPdno());
            limitTimeStoreView.setLimitTimeProdAttrList( project.jinshang.common.utils.BeanUtils.objectsToMaps(productAttrList));

            limitTimeStoreViewList.add(limitTimeStoreView);
        }


        detailRet.limitTimeProd = limitTimeProd;
        detailRet.limitTimeStoreList = limitTimeStoreViewList;
        detailRet.productInfo =  productInfo;

        detailRet.setResult(BasicRet.SUCCESS);
        detailRet.setMessage("查询成功");

        return  detailRet;
    }


    private  class  LimitTimeDetailRet extends  BasicRet{
        private  ProductInfo productInfo;

        private  LimitTimeProd limitTimeProd;

        private  List<LimitTimeStoreView> limitTimeStoreList;

        public LimitTimeProd getLimitTimeProd() {
            return limitTimeProd;
        }

        public void setLimitTimeProd(LimitTimeProd limitTimeProd) {
            this.limitTimeProd = limitTimeProd;
        }

        public List<LimitTimeStoreView> getLimitTimeStoreList() {
            return limitTimeStoreList;
        }

        public void setLimitTimeStoreList(List<LimitTimeStoreView> limitTimeStoreList) {
            this.limitTimeStoreList = limitTimeStoreList;
        }

        public ProductInfo getProductInfo() {
            return productInfo;
        }

        public void setProductInfo(ProductInfo productInfo) {
            this.productInfo = productInfo;
        }
    }




    @PostMapping("/validateLimitTimeActivity")
    @ApiOperation("审核限时购活动")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "状态 1=审核通过，2=审核未通过,",name = "state",paramType = "query")
    })
    public  BasicRet validateLimitTimeActivity(@RequestParam(required = true) long id,
                                               @RequestParam(required = true) Short state,
                                               @RequestParam(required = false,defaultValue = "")String remark){


        if(state != 1 && state != 2){
            return  new BasicRet(BasicRet.ERR,"状态不合法");
        }

        LimitTimeProd limitTimeProd =  limitTimeProdService.getById(id);
        if(limitTimeProd == null){
            return  new BasicRet(BasicRet.ERR,"该活动不存在");
        }

        if(!limitTimeProd.getState().equals(Quantity.STATE_0)){
            return  new BasicRet(BasicRet.ERR,"不是待审核状态");
        }

        ProductInfo productInfo =  productInfoService.getGroundingById(limitTimeProd.getMemberid(),limitTimeProd.getPdid());

        if(productInfo == null){
            return  new BasicRet(BasicRet.ERR,"参加活动的商品不存在");
        }


        if(state == 1){ //审核通过
            //扣库存(从商品库存中扣除库存)
            String err =  limitTimeActivityService.decrLimitTimeStoreNum(limitTimeProd) ;
            if(err != null){
                return  new BasicRet(BasicRet.ERR,err);
            }
        }else if(state == 2){ //审核不通过

        }

        LimitTimeProd updateLimit =  new LimitTimeProd();
        updateLimit.setId(id);
        updateLimit.setState(state);
        updateLimit.setRemark(remark);
        limitTimeProdService.updateByPrimaryKeySelective(updateLimit);

        return  new BasicRet(BasicRet.SUCCESS,"审核成功");
    }



    @PostMapping("/stopLimitTimeActivity")
    @ApiOperation("限时购活动终止")
    public  BasicRet stopTimeActivity(@RequestParam(required = true) long id){

        LimitTimeProd limitTimeProd =  limitTimeProdService.getById(id);
        if(limitTimeProd == null){
            return  new BasicRet(BasicRet.ERR,"该活动不存在");
        }

        if(!limitTimeProd.getState().equals(Quantity.STATE_4)){
            return  new BasicRet(BasicRet.ERR,"不是活动中状态,不可终止");
        }


        //加库存,将活动没有卖完的商品添加到库存中
        String err = limitTimeActivityService.addLimitTimeStoreNum(limitTimeProd);
        if(err != null){
            return  new BasicRet(BasicRet.ERR,err);
        }


        LimitTimeProd updateLimit =  new LimitTimeProd();
        updateLimit.setId(id);
        updateLimit.setState(Quantity.STATE_3);
        limitTimeProdService.updateByPrimaryKeySelective(updateLimit);

        return  new BasicRet(BasicRet.SUCCESS,"终止成功");
    }




    @PostMapping("/set/limitSetting")
    @ApiOperation("活动参数设置")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "预热时间",name = "preheattime",required = true,defaultValue = "24",paramType = "query"),
            @ApiImplicitParam(value = "预热阶段是否可以购买 0=不可以购买，1=可以购买",name = "canbuy",required = true,defaultValue = "1",paramType = "query"),
    })
    public BasicRet setLimitSetting(LimitTimeSetting setting){
        limitTimeSettingService.insertOrReplace(setting);
        return  new BasicRet(BasicRet.SUCCESS,"设置成功");
    }


    @PostMapping("/addCategory")
    @ApiOperation("添加分类")
    public BasicRet addCategory(@RequestParam(required = true) String name){
        LimitTimeCategory limitTimeCategory =  limitTimeCategoryService.getByName(name);
        if(limitTimeCategory != null){
            return  new BasicRet(BasicRet.ERR,"分类已存在，不可重复添加");
        }

        limitTimeCategory = new LimitTimeCategory();
        limitTimeCategory.setName(name);

        limitTimeCategoryService.insertSelective(limitTimeCategory);
        return  new BasicRet(BasicRet.SUCCESS,"添加成功");
    }


    @PostMapping("/deleteCategory")
    @ApiOperation("删除分类")
    public  BasicRet deleteCategory(long id){

        LimitTimeProdExample example = new LimitTimeProdExample();
        LimitTimeProdExample.Criteria criteria = example.createCriteria();
        criteria.andStateNotEqualTo(Quantity.STATE_7); //状态不为删除状态的
        criteria.andCategoryidEqualTo(id);
        int count = limitTimeProdService.countByExample(example);
        if(count>0){
            return new BasicRet(BasicRet.ERR,"该分类下还有活动，不可删除");
        }

        limitTimeCategoryService.delById(id);
        return  new BasicRet(BasicRet.SUCCESS,"删除成功");
    }



}
