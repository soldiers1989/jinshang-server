package project.jinshang.mod_front;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.BeanUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_activity.bean.*;
import project.jinshang.mod_activity.bean.dto.LimitTimeProdQuery;
import project.jinshang.mod_activity.service.LimitTimeCategoryService;
import project.jinshang.mod_activity.service.LimitTimeProdService;
import project.jinshang.mod_activity.service.LimitTimeSettingService;
import project.jinshang.mod_activity.service.LimitTimeStoreService;
import project.jinshang.mod_admin.mod_transet.bean.TransactionSetting;
import project.jinshang.mod_admin.mod_transet.service.TransactionSettingService;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_product.bean.ProductAttr;
import project.jinshang.mod_product.bean.ProductInfo;
import project.jinshang.mod_product.bean.ProductStore;
import project.jinshang.mod_product.service.ProductAttrService;
import project.jinshang.mod_product.service.ProductInfoService;
import project.jinshang.mod_product.service.ProductStoreService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * create : wyh
 * date : 2018/1/11
 */

@RestController
@RequestMapping("/rest/front/activity/limittime")
@Api(tags = "前台限时购")
@SessionAttributes(AppConstant.MEMBER_SESSION_NAME)
public class LimitTimeActivityFrontAction {

    @Resource
    private LimitTimeCategoryService limitTimeCategoryService;

    @Resource
    private LimitTimeSettingService limitTimeSettingService;

    @Resource
    private TransactionSettingService transactionSettingService;

    @Resource
    private LimitTimeProdService limitTimeProdService;

    @Resource
    private ProductInfoService productInfoService;

    @Resource
    private LimitTimeStoreService limitTimeStoreService;

    @Resource
    private ProductAttrService productAttrService;


    @Resource
    private ProductStoreService productStoreService;

    @PostMapping("/list")
    @ApiOperation("限时购列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "状态 0=所有活动 4=活动中 9=预热活动",name = "state",required = true,paramType = "query")
    })
    public PageRet list(LimitTimeProdQuery query, @RequestParam(required = true,defaultValue = "1") int pageNo,
                        @RequestParam(required = true,defaultValue = "20") int pageSize, Model model){

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        PageRet pageRet = new PageRet();

        if(query.getState() != Quantity.STATE_0 && query.getState() != Quantity.STATE_4 && query.getState() != Quantity.STATE_9){
            pageRet.setResult(BasicRet.ERR);
            pageRet.setMessage("状态不正确");
            return  pageRet;
        }

        TransactionSetting transactionSetting = transactionSettingService.getTransactionSetting();

        int befoBuytime = 0;
        if(transactionSetting != null){
           befoBuytime = transactionSetting.getBuytimeaheadtime().intValue();
        }


        limitTimeProdService.setToStart();
        limitTimeProdService.setToEnd();


            if(member == null || !"8".equals(member.getRegistertypelabel()) || !"9".equals(member.getRegisterchannellabel())){
                query.setNotInIds("207,208,209,210");
            }


        PageInfo pageInfo =  limitTimeProdService.listBuyPageForFront(query,befoBuytime,pageNo,pageSize);

        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        return  pageRet;
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

        if(limitTimeProd.getState()== Quantity.STATE_0 || limitTimeProd.getState()==Quantity.STATE_3){
            detailRet.setResult(BasicRet.ERR);
            detailRet.setMessage("活动状态不合法");
            return  detailRet;
        }

        //修改活动状态
        long nowTime = System.currentTimeMillis();

        if(limitTimeProd.getBegintime().getTime() < nowTime &&
                limitTimeProd.getEndtime().getTime()> nowTime &&
                limitTimeProd.getState()== Quantity.STATE_1){
            //修改活动为进行中状态
            LimitTimeProd updateLimitTimeProd =  new LimitTimeProd();
            updateLimitTimeProd.setId(id);
            updateLimitTimeProd.setState(Quantity.STATE_4);
            limitTimeProdService.updateByPrimaryKeySelective(updateLimitTimeProd);
            limitTimeProd.setState(Quantity.STATE_4);
        }

        if(limitTimeProd.getEndtime().getTime() < nowTime && limitTimeProd.getState()==Quantity.STATE_4){
            //修改活动状态为活动结束状态
            LimitTimeProd updateLimitTimeProd =  new LimitTimeProd();
            updateLimitTimeProd.setId(id);
            updateLimitTimeProd.setState(Quantity.STATE_5);
            limitTimeProdService.updateByPrimaryKeySelective(updateLimitTimeProd);
            limitTimeProd.setState(Quantity.STATE_5);
        }


        ProductInfo productInfo =  productInfoService.getById(limitTimeProd.getPdid());
        if(productInfo == null){
            detailRet.setResult(BasicRet.ERR);
            detailRet.setMessage("参与活动的商品不存在");
            return  detailRet;
        }

        //前端只差一个仓库地址，所以只取一条仓库信息即可
        ProductStore store =  productStoreService.selectByProductidForFastener(limitTimeProd.getPdid());
        productInfo.setProductStore(store);


        List<LimitTimeStore> limitTimeStoreList =  limitTimeStoreService.getStoreListByLimitid(id);

        List<LimitTimeStoreView> limitTimeStoreViewList = new ArrayList<>();

        List<ProdAttrName> publishAttrs = new ArrayList<>();

        for(LimitTimeStore limitTimeStore : limitTimeStoreList){
            LimitTimeStoreView limitTimeStoreView = new LimitTimeStoreView();
            BeanUtils.copyProperties(limitTimeStore,limitTimeStoreView);

            limitTimeStoreView.setTotalstorenum(limitTimeStore.getStorenum().add(limitTimeStore.getSalesnum()==null ? new BigDecimal(0) : limitTimeStore.getSalesnum()));

            //查询该商品的属性信息
            List<ProductAttr> productAttrList =  productAttrService.getListByPidAndPdno(limitTimeStore.getPdid(),limitTimeStore.getPdno());
            limitTimeStoreView.setLimitTimeProdAttrList( project.jinshang.common.utils.BeanUtils.objectsToMaps(productAttrList));


            StringBuilder attrStr = new StringBuilder();
            for(ProductAttr attr : productAttrList){
                attrStr.append(attr.getAttribute()).append(":").append(attr.getValue()).append(";");
                ProdAttrName prodAttrName =  conKey(publishAttrs,attr.getAttribute());
                if(prodAttrName == null){
                    prodAttrName = new ProdAttrName();
                    prodAttrName.setName(attr.getAttribute());
                    Set<String> set = new HashSet<>();
                    set.add(attr.getValue());
                    prodAttrName.setValue(set);
                    publishAttrs.add(prodAttrName);
                }else{
                    Set<String> set = prodAttrName.getValue();
                    if(!set.contains(attr.getValue())){
                        set.add(attr.getValue());
                    }
                }
            }

            limitTimeStoreView.setAttrStr(attrStr.toString());

            limitTimeStoreViewList.add(limitTimeStoreView);
        }

        detailRet.data.limitTimeProd = limitTimeProd;
        detailRet.data.limitTimeStoreList = limitTimeStoreViewList;
        detailRet.data.productInfo =  productInfo;
        detailRet.data.systemTime = System.currentTimeMillis();
        detailRet.data.publishAttrs = publishAttrs;

        detailRet.setResult(BasicRet.SUCCESS);
        detailRet.setMessage("查询成功");

        return  detailRet;
    }



    private  ProdAttrName conKey(List<ProdAttrName> list,String key){
        for(ProdAttrName attrName : list){
            if(attrName.getName().equals(key)){
                return  attrName;
            }
        }
        return  null;
    }

    private  class  ProdAttrName {
        private  String name;
        private  Set<String> value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Set<String> getValue() {
            return value;
        }

        public void setValue(Set<String> value) {
            this.value = value;
        }
    }




    private  class  LimitTimeDetailRet extends  BasicRet{
       private  class  LimitTimeDetailData{
           private ProductInfo productInfo;

           private LimitTimeProd limitTimeProd;

           private  List<LimitTimeStoreView> limitTimeStoreList;

           private  long systemTime;


           private  List<ProdAttrName> publishAttrs;

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

           public long getSystemTime() {
               return systemTime;
           }

           public void setSystemTime(long systemTime) {
               this.systemTime = systemTime;
           }

           public List<ProdAttrName> getPublishAttrs() {
               return publishAttrs;
           }

           public void setPublishAttrs(List<ProdAttrName> publishAttrs) {
               this.publishAttrs = publishAttrs;
           }
       }

        private  LimitTimeDetailData data = new LimitTimeDetailData();

        public LimitTimeDetailData getData() {
            return data;
        }

        public void setData(LimitTimeDetailData data) {
            this.data = data;
        }
    }


    @PostMapping("/listCategory")
    @ApiOperation("分类列表-前台用-只显示没有隐藏的分类-该分类下没有活动商品也不显示")
    private CategoryListRet listCategory(){
        CategoryListRet listRet = new CategoryListRet();
        listRet.setData(limitTimeCategoryService.selectActivity());
        listRet.setResult(BasicRet.SUCCESS);
        return  listRet;
    }

    @PostMapping("/listCategory2")
    @ApiOperation("分类列表-商家用")
    public CategoryListRet listCategory2(){
        CategoryListRet listRet = new CategoryListRet();
        listRet.setData(limitTimeCategoryService.selectAll(0));
        listRet.setResult(BasicRet.SUCCESS);
        return  listRet;
    }

    @PostMapping("/listCategory3")
    @ApiOperation("分类列表-后台用")
    public CategoryListRet listCategory3(){
        CategoryListRet listRet = new CategoryListRet();
        listRet.setData(limitTimeCategoryService.selectAll());
        listRet.setResult(BasicRet.SUCCESS);
        return  listRet;
    }

    @PostMapping("/isShow")
    @ApiOperation("选择某个分类是否显示")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "ID",name = "id",required = true,paramType = "query"),
            @ApiImplicitParam(value = "是否开启 0=开启 1=隐藏",name = "type",required = true,paramType = "query")
    })
    private PageRet isShow(int id,int type){
        PageRet pageRet = new PageRet();
        int result =limitTimeCategoryService.updateTypeById(id,type);
        if(result>0){
            pageRet.setResult(BasicRet.SUCCESS);
            pageRet.setMessage("修改成功");
        }else{
            pageRet.setResult(BasicRet.ERR);
            pageRet.setMessage("修改失败");
        }
        return  pageRet;
    }

    private  class  CategoryListRet extends  BasicRet{
        private List<LimitTimeCategory> data;

        public List<LimitTimeCategory> getData() {
            return data;
        }

        public void setData(List<LimitTimeCategory> data) {
            this.data = data;
        }
    }


}
