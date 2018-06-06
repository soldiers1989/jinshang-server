package project.jinshang.mod_activity;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.constant.SellerAuthorityConst;
import project.jinshang.common.utils.CommonUtils;
import project.jinshang.common.utils.ExcelGen;
import project.jinshang.mod_activity.bean.*;
import project.jinshang.mod_activity.bean.dto.LimitTimeProdQuery;
import project.jinshang.mod_activity.service.LimitTimeActivityService;
import project.jinshang.mod_activity.service.LimitTimeCategoryService;
import project.jinshang.mod_activity.service.LimitTimeProdService;
import project.jinshang.mod_activity.service.LimitTimeStoreService;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_product.bean.ProductAttr;
import project.jinshang.mod_product.bean.ProductInfo;
import project.jinshang.mod_product.bean.ProductStore;
import project.jinshang.mod_product.bean.dto.OtherProdStore;
import project.jinshang.mod_product.service.OtherProdService;
import project.jinshang.mod_product.service.ProductAttrService;
import project.jinshang.mod_product.service.ProductInfoService;
import project.jinshang.mod_product.service.ProductStoreService;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * create : wyh
 * date : 2018/1/9
 */
@RestController
@Api(tags = "卖家活动-限时购")
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/rest/seller/activity/limittime")
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME})
public class LimitTimeBuySellerAction {

    @Resource
    private LimitTimeProdService limitTimeProdService;

    @Resource
    private LimitTimeStoreService limitTimeStoreService;

    @Resource
    private ProductInfoService productInfoService;

    @Resource
    private ProductStoreService productStoreService;

    @Resource
    private ProductAttrService productAttrService;

    @Resource
    private LimitTimeCategoryService limitTimeCategoryService;

    @Autowired
    private LimitTimeActivityService limitTimeActivityService;

    @Autowired
    private OtherProdService otherProdService;



    @PostMapping("/setStatesLimitTimeActivity")
    @ApiOperation("修改活动状态")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "状态 0=待审核(重新发起)，1=审核通过，2=审核未通过,3=活动终止，4=活动中，5=活动结束,6=用户取消,7=删除",name = "state",required = true,paramType = "query")
    })
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.LIMITED+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public  BasicRet setStatesLimitTimeActivity(@RequestParam(required = true) long id,
                                                @RequestParam(required = true) Short state,
                                                Model model){

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        LimitTimeProd limitTimeProd =  limitTimeProdService.getById(id);
        if(limitTimeProd == null){
            return  new BasicRet(BasicRet.ERR,"该活动不存在");
        }

        if(state != 0 && state != 3 && state != 6 && state != 5 && state !=7){
            return  new BasicRet(BasicRet.ERR,"状态不合法");
        }


        if(!member.getId().equals(limitTimeProd.getMemberid())){
            return  new BasicRet(BasicRet.ERR,"该活动不是你创建的");
        }

        if(state == 0){ //重新发起活动
            if(limitTimeProd.getState() == 5 || limitTimeProd.getState() == 6 || limitTimeProd.getState() == 3){

            }else {
                return  new BasicRet(BasicRet.ERR,"不可重新发起活动");
            }
        }else if(state == 3){ // 活动终止
            if(limitTimeProd.getState() == 4){
                //将未卖完的库存添加回实际库存
                limitTimeActivityService.addLimitTimeStoreNum(limitTimeProd);
            }else{
                return  new BasicRet(BasicRet.ERR,"不可终止活动");
            }
        }else if(state == 6){ //取消活动
            if(limitTimeProd.getState() == 0 || limitTimeProd.getState() == 1){

            }else{
                return  new BasicRet(BasicRet.ERR,"不可取消活动");
            }
        }else if(state == 7){
            if(limitTimeProd.getState() == 4){
                return  new BasicRet(BasicRet.ERR,"活动中不可删除");
            }
        }

        LimitTimeProd updateLimit =  new LimitTimeProd();
        updateLimit.setId(id);
        updateLimit.setState(state);
        limitTimeProdService.updateByPrimaryKeySelective(updateLimit);

        return  new BasicRet(BasicRet.SUCCESS,"修改成功");
    }



    @PostMapping("/listLimitTimeProd")
    @ApiOperation("活动列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "状态",name = "state",required = false,paramType = "query"),
            @ApiImplicitParam(value = "活动标题",name = "activitytitle",required = false,paramType = "query"),
            @ApiImplicitParam(value = "活动商品类目",name = "activitycateid",required = false,paramType = "query"),
    })
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.LIMITED+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public  PageRet listLimitTimeProd(LimitTimeProdQuery query,@RequestParam(required = true,defaultValue = "1") int pageNo,
                                      @RequestParam(required = true,defaultValue = "10") int pageSize,
                                      Model model){
        PageRet pageRet =  new PageRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        query.setMemberid(member.getId());

        PageInfo pageInfo = limitTimeProdService.listBuyPageForSeller(query,pageNo,pageSize);
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
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.LIMITED+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public ResponseEntity<InputStreamResource> listLimitTimeProdForExprotExcel(LimitTimeProdQuery query, Model model){
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        query.setMemberid(member.getId());


        String[] rowTitles = new String[]{"活动名称","商品名称","品牌","单位","商品属性","原价","限时价","商品库存",
                "活动库存","商品类目","开始时间",	"结束时间","单个ID购买限制","销售量","状态"};


        List<Map<String,Object>> resList =  limitTimeProdService.listBuyPageForSellerExportExcel(query);

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




    @PostMapping("/selectProd")
    @ApiOperation("选择商品接口")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "level1id",name = "level1id",required = false,paramType = "query", dataType = "Long"),
            @ApiImplicitParam(value = "level2id",name = "level2id",required = false,paramType = "query", dataType = "Long"),
            @ApiImplicitParam(value = "level3id",name = "level3id",required = false,paramType = "query", dataType = "Long"),
            @ApiImplicitParam(value = "商品名称",name = "productname",required = false,paramType = "query", dataType = "String"),
            @ApiImplicitParam(value = "规格",name = "stand",required = false,paramType = "query", dataType = "String"),
            @ApiImplicitParam(value = "品牌",name = "brand",required = false,paramType = "query", dataType = "String"),
            @ApiImplicitParam(value = "材质",name = "materialid",required = false,paramType = "query", dataType = "Long"),
            @ApiImplicitParam(value = "牌号",name = "cardnumid",required = false,paramType = "query", dataType = "Long"),
            @ApiImplicitParam(value = "商品编号",name = "pdno",required = false,paramType = "query", dataType = "String"),
            @ApiImplicitParam(value = "pageNo",name = "pageNo",required = false,paramType = "query", dataType = "int"),
            @ApiImplicitParam(value = "pageSize",name = "pageSize",required = false,paramType = "query", dataType = "int")
    })
    public PageRet selectProd(Long level1id,Long level2id,Long level3id,String productname,String stand,String brand,Long materialid,Long cardnumid,String pdno,
                              @RequestParam(required = true,defaultValue = "1") int pageNo,
                              @RequestParam(required = true,defaultValue = "10") int pageSize,Model model){
        PageRet pageRet =  new PageRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        /*ProductInfoExample example = new ProductInfoExample();
        ProductInfoExample.Criteria criteria = example.createCriteria();

        criteria.andPdstateEqualTo(Quantity.STATE_4);
        criteria.andMemberidEqualTo(member.getId());

        if(level1id != null && level1id >0 ){
            criteria.andLevel1idEqualTo(level1id);
        }

        if(level2id != null && level2id >0 ){
            criteria.andLevel2idEqualTo(level2id);
        }

        if(level3id != null && level3id >0 ){
            criteria.andLevel3idEqualTo(level3id);
        }*/

        ProductInfo productInfo = new ProductInfo();
        if(member.getId() != null ){
            productInfo.setMemberid(member.getId());
        }

        if(level1id != null && level1id >0 ){
            productInfo.setLevel1id(level1id);
        }

        if(level2id != null && level2id >0 ){
            productInfo.setLevel2id(level2id);
        }

        if(level3id != null && level3id >0 ){
            productInfo.setLevel3id(level3id);
        }

        if(productname != null && productname !=""){
            productInfo.setProductname(productname);
        }

        if(stand != null && stand !=""){
            productInfo.setStand(stand);
        }

        if(brand != null && brand != ""){
            productInfo.setBrand(brand);
        }

        if(materialid != null && materialid >0){
            productInfo.setMaterialid(materialid);
        }

        if(cardnumid != null && cardnumid >0){
            productInfo.setCardnumid(cardnumid);
        }

        ProductStore productStore = new ProductStore();
        if(pdno != null && pdno != ""){
            productStore.setPdno(pdno);
        }
        productInfo.setProductStore(productStore);

        PageInfo pageInfo = productInfoService.getListProduct(productInfo, pageNo, pageSize);
        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.data.setPageInfo(pageInfo);
        return  pageRet;
    }


    @PostMapping("/updateLimitTimeActivity")
    @ApiOperation("修改限时购活动")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "商品id",name="pdid",required = true,paramType = "query"),
            @ApiImplicitParam(value = "分类id",name="categoryid",required = true,paramType = "query"),
            @ApiImplicitParam(value = "活动标题",name="activitytitle",required = true,paramType = "query"),
            @ApiImplicitParam(value = "活动开始时间",name="begintime",required = true,paramType = "query"),
            @ApiImplicitParam(value = "活动结束时间",name="endtime",required = true,paramType = "query"),
            @ApiImplicitParam(value = "购买限制",name="buylimit",required = true,paramType = "query"),
            @ApiImplicitParam(value = "库存json [{\"pdno\":\"no123\",\"originalprice\":100,\"limitprice\":88,\"storenum\":100}] ",name="storeJson",required = true,paramType = "query"),
    })
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.LIMITED+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public BasicRet updateLimitTimeActivity(
                                            @RequestParam(required = true) long id,
                                            @RequestParam(required = true) long pdid,
                                            @RequestParam(required = true) long categoryid,
                                            @RequestParam(required = false,defaultValue = "")String activitytitle,
                                            @RequestParam(required = true)Date begintime,
                                            @RequestParam(required = true) Date endtime,
                                            @RequestParam(required = true)BigDecimal buylimit,
                                            @RequestParam(required = true) String storeJson,
                                            Model model){
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        LimitTimeProd limitTimeProd = limitTimeProdService.getById(id);
        if(limitTimeProd == null){
            return  new BasicRet(BasicRet.ERR,"该活动不存在");
        }

        if(!limitTimeProd.getMemberid().equals(limitTimeProd.getMemberid())){
            return  new BasicRet(BasicRet.ERR,"该活动不属于你");
        }

        if(limitTimeProd.getState() != Quantity.STATE_0 && limitTimeProd.getState() != Quantity.STATE_2){
            return  new BasicRet(BasicRet.ERR,"活动只有在待审核和审核未通过状态下可修改");
        }

        long currentTime = System.currentTimeMillis();
        if(begintime.getTime() - currentTime <=0){
            return  new BasicRet(BasicRet.ERR,"开始时间不可比当前时间早");
        }

        if(begintime.getTime() - endtime.getTime() >=0){
            return  new BasicRet(BasicRet.ERR,"开始时间不可比结束时间晚");
        }

        LimitTimeCategory limitTimeCategory = limitTimeCategoryService.getById(categoryid);
        if(limitTimeCategory ==null){
            return  new BasicRet(BasicRet.ERR,"分类不存在");
        }

        ProductInfo productInfo = productInfoService.getGroundingById(member.getId(),pdid);
        if(productInfo == null ){
            return  new BasicRet(BasicRet.ERR,"要添加限时购的商品不存在");
        }

        if(!CommonUtils.isGoodJson(storeJson)){
            return  new BasicRet(BasicRet.ERR,"库存json不正确");
        }


        Gson gson = new Gson();
        List<LimitTimeStore> limitTimeStoreList = gson.fromJson(storeJson,new TypeToken<List<LimitTimeStore>>() {}.getType());


        BigDecimal minprice = new BigDecimal(0);
        BigDecimal normalprice = new BigDecimal(0);

        for(LimitTimeStore limitTimeStore : limitTimeStoreList){
            if(limitTimeStore.getPdno() == null) return  new BasicRet(BasicRet.ERR,"商品编码不正确");

            ProductStore productStore =  productStoreService.getByPdidAndPdno(pdid,limitTimeStore.getPdno());
            if(productStore == null) return  new BasicRet(BasicRet.ERR,"库存信息不正确");

            if(productStore.getPdstorenum().compareTo(limitTimeStore.getStorenum()) == Quantity.STATE_){
                return  new BasicRet(BasicRet.ERR,"实际库存小于活动库存");
            }
            limitTimeStore.setPdid(pdid);
            limitTimeStore.setSalesnum(new BigDecimal(0));

            if(minprice.compareTo(new BigDecimal(0)) == Quantity.STATE_0){
                minprice = limitTimeStore.getLimitprice();

                normalprice = limitTimeStore.getOriginalprice();
            }else{
                if(minprice.compareTo(limitTimeStore.getLimitprice()) == Quantity.STATE_1){
                    minprice = limitTimeStore.getLimitprice();
                    normalprice = limitTimeStore.getOriginalprice();
                }
            }
        }


        // 清除老的仓库数据
        limitTimeStoreService.deleteByLimitid(limitTimeProd.getId());

        limitTimeProd.setPdid(pdid);
        limitTimeProd.setActivitytitle(activitytitle);
        limitTimeProd.setBegintime(begintime);
        limitTimeProd.setEndtime(endtime);
        limitTimeProd.setSalestotalnum(new BigDecimal(0));
        limitTimeProd.setState(Quantity.STATE_0);
        limitTimeProd.setBuylimit(buylimit);
        limitTimeProd.setCategory(limitTimeCategory.getName());
        limitTimeProd.setCategoryid(limitTimeCategory.getId());
        limitTimeProd.setMinprice(minprice);
        limitTimeProd.setNormalprice(normalprice);

        limitTimeProdService.updateByPrimaryKeySelective(limitTimeProd);

        for(LimitTimeStore store : limitTimeStoreList){
            store.setLimittimeid(limitTimeProd.getId());
            limitTimeStoreService.insertSelective(store);
        }

        return  new BasicRet(BasicRet.SUCCESS,"修改成功");
    }



    @PostMapping("/addLimitTimeActivity")
    @ApiOperation("添加限时购活动")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "商品id",name="pdid",required = true,paramType = "query"),
            @ApiImplicitParam(value = "分类id",name="categoryid",required = true,paramType = "query"),
            @ApiImplicitParam(value = "活动标题",name="activitytitle",required = true,paramType = "query"),
            @ApiImplicitParam(value = "活动开始时间",name="begintime",required = true,paramType = "query"),
            @ApiImplicitParam(value = "活动结束时间",name="endtime",required = true,paramType = "query"),
            @ApiImplicitParam(value = "购买限制",name="buylimit",required = true,paramType = "query"),
            @ApiImplicitParam(value = "库存json [{\"pdno\":\"no123\",\"originalprice\":100,\"limitprice\":88,\"storenum\":100}] ",name="storeJson",required = true,paramType = "query"),
    })
    @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.LIMITED+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
    public  BasicRet addLimitTimeActivity(@RequestParam(required = true) long pdid,
                                          @RequestParam(required = true) long categoryid,
                                          @RequestParam(required = false,defaultValue = "")String activitytitle,
                                          @RequestParam(required = true)Date begintime,
                                          @RequestParam(required = true) Date endtime,
                                          @RequestParam(required = true)BigDecimal buylimit,
                                          @RequestParam(required = true) String storeJson,
                                          Model model){

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        long currentTime = System.currentTimeMillis();
        if(begintime.getTime() - currentTime <=0){
            return  new BasicRet(BasicRet.ERR,"开始时间不可比当前时间早");
        }

        if(begintime.getTime() - endtime.getTime() >=0){
            return  new BasicRet(BasicRet.ERR,"开始时间不可比结束时间晚");
        }

        LimitTimeCategory limitTimeCategory = limitTimeCategoryService.getById(categoryid);
        if(limitTimeCategory ==null){
            return  new BasicRet(BasicRet.ERR,"分类不存在");
        }


        ProductInfo productInfo = productInfoService.getGroundingById(member.getId(),pdid);
        if(productInfo == null ){
            return  new BasicRet(BasicRet.ERR,"要添加限时购的商品不存在");
        }

        if(!CommonUtils.isGoodJson(storeJson)){
            return  new BasicRet(BasicRet.ERR,"库存json不正确");
        }

        Gson gson = new Gson();
        List<LimitTimeStore> limitTimeStoreList = gson.fromJson(storeJson,new TypeToken<List<LimitTimeStore>>() {}.getType());


        BigDecimal minprice = new BigDecimal(0);
        BigDecimal normalprice = new BigDecimal(0);

        for(LimitTimeStore limitTimeStore : limitTimeStoreList){
            if(limitTimeStore.getPdno() == null) return  new BasicRet(BasicRet.ERR,"商品编码不正确");

            ProductStore productStore =  productStoreService.getByPdidAndPdno(pdid,limitTimeStore.getPdno());
            if(productStore == null) return  new BasicRet(BasicRet.ERR,"库存信息不正确");

            if(productStore.getPdstorenum().compareTo(limitTimeStore.getStorenum()) == Quantity.STATE_){
                return  new BasicRet(BasicRet.ERR,"实际库存小于活动库存");
            }
            limitTimeStore.setPdid(pdid);
            limitTimeStore.setSalesnum(new BigDecimal(0));


            if(minprice.compareTo(new BigDecimal(0)) == Quantity.STATE_0){
                minprice = limitTimeStore.getLimitprice();
                normalprice = limitTimeStore.getOriginalprice();
            }else{
                if(minprice.compareTo(limitTimeStore.getLimitprice()) == Quantity.STATE_1){
                    minprice = limitTimeStore.getLimitprice();
                    normalprice = limitTimeStore.getOriginalprice();
                }
            }


        }

        LimitTimeProd limitTimeProd = new LimitTimeProd();
        limitTimeProd.setPdid(pdid);
        limitTimeProd.setMemberid(member.getId());
        limitTimeProd.setUsername(member.getUsername());
        limitTimeProd.setActivitytitle(activitytitle);
        limitTimeProd.setBegintime(begintime);
        limitTimeProd.setEndtime(endtime);
        limitTimeProd.setSalestotalnum(new BigDecimal(0));
        limitTimeProd.setState(Quantity.STATE_0);
        limitTimeProd.setBuylimit(buylimit);
        limitTimeProd.setCategory(limitTimeCategory.getName());
        limitTimeProd.setCategoryid(limitTimeCategory.getId());
        limitTimeProd.setMinprice(minprice);
        limitTimeProd.setNormalprice(normalprice);


        limitTimeProdService.insertSelective(limitTimeProd);

        for(LimitTimeStore store : limitTimeStoreList){
            store.setLimittimeid(limitTimeProd.getId());
            limitTimeStoreService.insertSelective(store);
        }

        return   new BasicRet(BasicRet.SUCCESS,"添加成功");
    }



   @PostMapping("/limitTimeDetail")
   @ApiOperation("查看限时购活动详情")
   @PreAuthorize("hasAuthority('"+ SellerAuthorityConst.LIMITED+"') || hasAuthority('"+ SellerAuthorityConst.ALL+"')")
   public  LimitTimeDetailRet  limitTimeDetail(@RequestParam(required = true) long id,Model model){
       LimitTimeDetailRet detailRet = new LimitTimeDetailRet();

       Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

       LimitTimeProd limitTimeProd = limitTimeProdService.getById(id);
       if(limitTimeProd == null){
           detailRet.setResult(BasicRet.ERR);
           detailRet.setMessage("活动不存在");
           return  detailRet;
       }

       if(!limitTimeProd.getMemberid().equals(member.getId())){
           detailRet.setResult(BasicRet.ERR);
           detailRet.setMessage("该活动不是你创建的");
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



    @PostMapping("/product/detail")
    @ApiOperation("商品详情")
    public  LimitTimeDetailRet detailOtherProduct(@RequestParam(required = true) long id,Model model){
        LimitTimeDetailRet detailRet =  new LimitTimeDetailRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        ProductInfo productInfo =  productInfoService.getById(id);
        if(productInfo == null){
            detailRet.setMessage("该商品不存在");
            detailRet.setResult(BasicRet.ERR);
            return  detailRet;
        }

        List<OtherProdStore> prodStoreList = otherProdService.getOtherProdStore(productInfo.getId());

        List<LimitTimeStoreView> limitTimeStoreViewList = new ArrayList<>();

        for(OtherProdStore pdStore : prodStoreList){

            LimitTimeStoreView limitTimeStoreView =  new LimitTimeStoreView();
            limitTimeStoreView.setPdstorenum(pdStore.getPdstorenum());
            limitTimeStoreView.setOriginalprice(pdStore.getProdprice());

            limitTimeStoreView.setPdid(pdStore.getPdid());
            limitTimeStoreView.setPdno(pdStore.getPdno());

            List<ProductAttr> attrList = productAttrService.getListByPidAndPdno(productInfo.getId(),pdStore.getPdno());

            limitTimeStoreView.setLimitTimeProdAttrList(project.jinshang.common.utils.BeanUtils.objectsToMaps(attrList));

            limitTimeStoreViewList.add(limitTimeStoreView);
        }

       detailRet.productInfo = productInfo;
//        detailRet.limitTimeProd = limitTimeProd;
        detailRet.limitTimeStoreList = limitTimeStoreViewList;

        detailRet.setResult(BasicRet.SUCCESS);
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



//    @PostMapping("/product/detail")
//    @ApiOperation("商品详情")
//    public  OtherProductDetailRet detailOtherProduct(@RequestParam(required = true) long id,Model model){
//        OtherProductDetailRet detailRet =  new OtherProductDetailRet();
//
//        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
//
//        ProductInfo productInfo =  productInfoService.getById(id);
//        if(productInfo == null){
//            detailRet.setMessage("该商品不存在");
//            detailRet.setResult(BasicRet.ERR);
//            return  detailRet;
//        }
//
//        OtherProdDetailViewDto viewDto = new OtherProdDetailViewDto();
//        BeanUtils.copyProperties(productInfo,viewDto);
//
//        List<OtherProdStore> prodStoreList = otherProdService.getOtherProdStore(productInfo.getId());
//
//        List<OtherProdStoreView> prodStoreViewList = new ArrayList<>();
//
//        String intervalprice = "";
//
//        for(OtherProdStore pdStore : prodStoreList){
//            viewDto.setStoreid(pdStore.getStoreid());
//            viewDto.setStorename(pdStore.getStorename());
//            viewDto.setStartnum(pdStore.getStartnum());
//            viewDto.setFreightmode(pdStore.getFreightmode());
//            viewDto.setCostprice(pdStore.getCostprice());
//            viewDto.setStepwiseprice(pdStore.isStepwiseprice());
//
//            List<ProductAttr> attrList = productAttrService.getListByPidAndPdno(productInfo.getId(),pdStore.getPdno());
//            pdStore.setProductAttrList(attrList);
//
//            pdStore.setProdprice(pdStore.getProdprice());
//
//            intervalprice =  pdStore.getIntervalprice();
//            OtherProdStoreView otherProdStoreView = new OtherProdStoreView();
//            BeanUtils.copyProperties(pdStore,otherProdStoreView);
//
//            prodStoreViewList.add(otherProdStoreView);
//        }
//
//
//
//        detailRet.viewDto =  viewDto;
//        detailRet.prodStoreList =  prodStoreViewList;
//
//        detailRet.setResult(BasicRet.SUCCESS);
//        return  detailRet;
//    }
//
//
//    private  class OtherProductDetailRet extends  BasicRet{
//        private OtherProdDetailViewDto viewDto;
//
//        private List<OtherProdStoreView> prodStoreList;
//
//        public OtherProdDetailViewDto getViewDto() {
//            return viewDto;
//        }
//
//        public void setViewDto(OtherProdDetailViewDto viewDto) {
//            this.viewDto = viewDto;
//        }
//
//        public List<OtherProdStoreView> getProdStoreList() {
//            return prodStoreList;
//        }
//
//        public void setProdStoreList(List<OtherProdStoreView> prodStoreList) {
//            this.prodStoreList = prodStoreList;
//        }
//
//    }


//    public static void main(String[] args) {
//        LimitTimeStore timeStore = new LimitTimeStore();
//        timeStore.setPdno("no123");
//        timeStore.setOriginalprice(new BigDecimal(100));
//        timeStore.setLimitprice(new BigDecimal(88));
//        timeStore.setStorenum(new BigDecimal(100));
//        Gson gson = new Gson();
//
//
//        System.out.println(gson.toJson(timeStore));
//    }



}
