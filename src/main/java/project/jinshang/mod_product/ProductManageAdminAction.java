package project.jinshang.mod_product;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.MemberLogOperator;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AdminAuthorityConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.exception.CashException;
import project.jinshang.common.utils.DateUtils;
import project.jinshang.common.utils.ExcelGen;
import project.jinshang.common.utils.JinshangUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_admin.mod_excelexport.ExcepExportAction;
import project.jinshang.mod_cash.service.SalerCapitalService;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.bean.MemberRateSetting;
import project.jinshang.mod_member.service.MemberRateSettingService;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_product.bean.*;
import project.jinshang.mod_product.bean.dto.OtherProdDetailViewDto;
import project.jinshang.mod_product.bean.dto.OtherProdStore;
import project.jinshang.mod_product.bean.dto.OtherProdStoreView;
import project.jinshang.mod_product.bean.dto.OtherProductQueryDto;
import project.jinshang.mod_product.service.*;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * create : wyh
 * date : 2017/11/14
 */
@RestController
@Api(tags = "后台商品管理接口")
@RequestMapping("/rest/admin/productmanage")
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
@Transactional(rollbackFor = Exception.class)
public class ProductManageAdminAction {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductSearchService productSearchService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private SalerCapitalService salerCapitalService;

    @Autowired
    private PdbailLogService pdbailLogService;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private MemberRateSettingService memberRateSettingService;

    @Autowired
    private ShippingTemplatesService shippingTemplatesService;

    @Autowired
    private  OtherProdService otherProdService;

    @Autowired
    private  ProductAttrService productAttrService;

    @Autowired
    protected MemberLogOperator memberLogOperator;

    @Autowired
    private MemberOperateLogService memberOperateLogService;

    @Autowired
    private  ProductStoreService productStoreService;


    @Value("${mod.middleware.serverurl}")
    private  String middleware_url;

    @RequestMapping(value =  "/getProdNotPassReason",method = RequestMethod.POST)
    @ApiOperation("查询商品未通过原因")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.COMMODITYANAGEMENT + "')")
    public NotPassReasonRet getProdNotPassReason(
            @RequestParam(required =  true) long id,Model model
    ){
        NotPassReasonRet ret = new NotPassReasonRet();

        ProductInfo productInfo = productInfoService.getById(id);

        if(productInfo == null){
            ret.setMessage("要查询的商品不存在");
            ret.setResult(BasicRet.ERR);
            return  ret;
        }


        if(productInfo.getPdstate() != 3 && productInfo.getPdstate()!=Quantity.STATE_7){
            ret.setMessage("商品状态不是未审核通过状态");
            ret.setResult(BasicRet.ERR);
            return  ret;
        }

        ret.setResult(BasicRet.SUCCESS);
        ret.setMessage("查询成功");
        ret.data.reason =  productInfo.getReason();

        return  ret;
    }



    private class NotPassReasonRet extends  BasicRet{
        private  class  NotPassReasonData{
            private  String reason;

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }
        }

        private  NotPassReasonData data =  new NotPassReasonData();

        public NotPassReasonData getData() {
            return data;
        }

        public void setData(NotPassReasonData data) {
            this.data = data;
        }
    }



    @RequestMapping(value = "/getProductInfo", method = RequestMethod.POST)
    @ApiOperation("获取紧固件商品详情接口")
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.COMMODITYANAGEMENT + "')")
    public ProductRet getProductInfo(@RequestParam(required = true) long id, Model model) {
        ProductRet productRet = new ProductRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        ProductInfo productInfo = productInfoService.getProductInfoWithStore(id);
        if (productInfo == null) {
            productRet.setMessage("未查询到该商品");
            productRet.setResult(BasicRet.ERR);
            return productRet;
        }


        ProductStore store = productInfo.getProductStore();
        if(store == null){
            productRet.setMessage("未查询到商品库存信息");
            productRet.setResult(BasicRet.ERR);
            return productRet;
        }


        //转换包装方式
        productInfo.setPackingList(JinshangUtils.toCovertPacking(productInfo.getPackagetype()));

        MemberRateSetting memberRateSetting = null;
        if(member != null){
            memberRateSetting =  memberRateSettingService.getRecursiveSetting(productInfo.getMemberid(), productInfo.getLevel3id(),member.getGradleid());
        }

        if(memberRateSetting == null){
            memberRateSetting = new MemberRateSetting();
            memberRateSetting.setRate(new BigDecimal(1));
        }

        //封装价格
        List list1 = new ArrayList();
        Map<String,Object> prodpriceMap = new HashMap<>();
        Map<String,Object> threepriceMap = new HashMap<>();
        Map<String,Object> ninetypriceMap = new HashMap<>();
        Map<String,Object> thirtypriceMap = new HashMap<>();
        Map<String,Object> sixtypriceMap = new HashMap<>();

        if(store.getProdprice() != null){
            prodpriceMap.put("type",0);
            prodpriceMap.put("name","立即发货");
            prodpriceMap.put("price",store.getProdprice().multiply(memberRateSetting.getRate()));
            list1.add(prodpriceMap);
            store.setProdprice(store.getProdprice().multiply(memberRateSetting.getRate()));
        }

        if(store.getThreeprice() != null ){
            threepriceMap.put("type",3);
            threepriceMap.put("name","3天发货");
            threepriceMap.put("price",store.getThreeprice().multiply(memberRateSetting.getRate()));
            list1.add(threepriceMap);
            store.setThreeprice(store.getThirtyprice().multiply(memberRateSetting.getRate()));
        }

        if(store.getNinetyprice() != null ){
            ninetypriceMap.put("type",90);
            ninetypriceMap.put("name","90天发货");
            ninetypriceMap.put("price",store.getNinetyprice().multiply(memberRateSetting.getRate()));
            list1.add(ninetypriceMap);
            store.setNinetyprice(store.getNinetyprice().multiply(memberRateSetting.getRate()));
        }

        if(store.getThirtyprice() != null ){
            thirtypriceMap.put("type",30);
            thirtypriceMap.put("name","30天发货");
            thirtypriceMap.put("price",store.getThirtyprice().multiply(memberRateSetting.getRate()));
            list1.add(thirtypriceMap);
            store.setThirtyprice(store.getThirtyprice().multiply(memberRateSetting.getRate()));
        }

        if(store.getSixtyprice() != null ){
            sixtypriceMap.put("type",60);
            sixtypriceMap.put("name","60天发货");
            sixtypriceMap.put("price",store.getSixtyprice().multiply(memberRateSetting.getRate()));
            list1.add(sixtypriceMap);
            store.setSixtyprice(store.getSixtyprice().multiply(memberRateSetting.getRate()));
        }
        productInfo.setPrices(list1);

//        ShippingTemplates shippingTemplates = null;

//        if(productInfo.getProductStore().getFreightmode() >0) {
//            shippingTemplates =  shippingTemplatesService.getFullTemplatesById(productInfo.getProductStore().getFreightmode());
//        }


        productRet.data.productInfo = productInfo;
        //productRet.data.shippingTemplates = shippingTemplates;

        productRet.setMessage("查询成功");
        productRet.setResult(BasicRet.SUCCESS);
        return productRet;
    }


    public class ProductRet extends BasicRet {
        public class ProductRetData{
            private ProductInfo productInfo;
            private ShippingTemplates shippingTemplates;
            public ShippingTemplates getShippingTemplates() {
                return shippingTemplates;
            }
            public ProductInfo getProductInfo() {
                return productInfo;
            }
        }
        private ProductRetData data = new ProductRetData();

        public ProductRetData getData() {
            return data;
        }

        public ProductRet setData(ProductRetData data) {
            this.data = data;
            return this;
        }
    }





    @RequestMapping(value =  "/listFastenerProduct",method = RequestMethod.POST)
    @ApiOperation("紧固件商品分页列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "第几页",name = "pageNo",paramType = "query",dataType = "int",required = true,defaultValue = "1"),
            @ApiImplicitParam(value = "每页显示的条数",name = "pageSize",paramType = "query",dataType = "int",required = true,defaultValue = "20"),
            @ApiImplicitParam(value = "商品名",name = "productname",paramType = "query",dataType = "string",required = false),
            @ApiImplicitParam(value = "品牌",name = "brand",paramType = "query",dataType = "string",required = false),
            @ApiImplicitParam(value = "分类id",name = "levelid",paramType = "query",dataType = "int",required = false,defaultValue = "0"),
            @ApiImplicitParam(value = "是否有库存,0-全部,1-有，2-没有",name = "haveStorenum",paramType = "query",dataType = "int",required = false,defaultValue = "0"),
            @ApiImplicitParam(value = "商品状态 0=放入仓库 1=待审核(立即发布) 2=审核通过 3=未通过 4=已上架(上架) 5=下架 6=删除 7-违规下架",name = "pdstate",paramType = "query",dataType = "int",required = false,defaultValue = "-1"),
            @ApiImplicitParam(value = "材质id",name = "materialid",paramType = "query",dataType = "int",required = false,defaultValue = "-1"),
            @ApiImplicitParam(value = "牌号id",name = "cardnumid",paramType = "query",dataType = "int",required = false,defaultValue = "-1"),
            @ApiImplicitParam(value = "卖家id",name = "memberid",paramType = "query",dataType = "int",required = false,defaultValue = "0"),
            @ApiImplicitParam(value = "用户名",name = "username",paramType = "query",dataType = "string",required = false,defaultValue = ""),
            @ApiImplicitParam(value = "上架时间",name = "uptimeStart",paramType = "query",dataType = "date",required = false),
            @ApiImplicitParam(value = "上架时间",name = "uptimeEnd",paramType = "query",dataType = "date",required = false),
            @ApiImplicitParam(value = "发布时间区间",name = "createStart",paramType = "query",dataType = "date",required = false),
            @ApiImplicitParam(value = "发布时间区间",name = "createEnd",paramType = "query",dataType = "date",required = false),
            @ApiImplicitParam(value = "印记",name = "mark",paramType = "query",dataType = "string",required = false),
            @ApiImplicitParam(value = "店铺名称",name = "shopname",paramType = "query",dataType = "string",required = false),
            @ApiImplicitParam(value = "规格",name = "stand",paramType = "query",dataType = "string",required = false),
            @ApiImplicitParam(value = "是否远期",name = "futurePrice",paramType = "query",dataType = "short",required = false,defaultValue = "0")
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.COMMODITYANAGEMENT + "')")
    public PageRet listFastenerProduct(@RequestParam(required = true,defaultValue = "1") int pageNo,
                               @RequestParam(required = true,defaultValue = "20") int pageSize,
                               @RequestParam(required = false) String productname,
                               @RequestParam(required = false) String brand,
                               @RequestParam(required = false,defaultValue = "0") long levelid,
                               @RequestParam(required = false,defaultValue = "0") short haveStorenum,
                               @RequestParam(required = false,defaultValue = "-1") short pdstate,
                               @RequestParam(required = false,defaultValue = "-1") long materialid,
                               @RequestParam(required = false,defaultValue = "-1") long cardnumid,
                               @RequestParam(required = false,defaultValue = "0") long memberid,
                               @RequestParam(required = false,defaultValue = "") String username,
                               @RequestParam(required = false) Date uptimeStart,
                               @RequestParam(required = false) Date uptimeEnd,
                               @RequestParam(required = false) Date createStart,
                               @RequestParam(required = false) Date createEnd,
                               @RequestParam(required = false) String mark,
                               @RequestParam(required = false) String shopname,
                               @RequestParam(required = false,defaultValue = "") String stand,
                               @RequestParam(required = false,defaultValue = "0") short futurePrice
                                ){
        PageRet pageRet =  new PageRet();
        ProductInfoQuery productInfo = new ProductInfoQuery();
        ProductStore productStore = new ProductStore();


        if(StringUtils.hasText(productname)){
            productInfo.setProductname(productname);
        }

        if(StringUtils.hasText(brand)){
            productInfo.setBrand(brand);
        }

        if(StringUtils.hasText(mark)){
            productInfo.setMark(mark);
        }

        productInfo.setFuturePrice(futurePrice);


        if(levelid >0){
            Categories productCategory =  categoriesService.getCategoryLevel(levelid);
            if(productCategory != null){
                if(productCategory.getLevel()==1){
                    productInfo.setLevel1id(levelid);
                }else if(productCategory.getLevel() == 2){
                    productInfo.setLevel2id(levelid);
                }else if(productCategory.getLevel() == 3){
                    productInfo.setLevel3id(levelid);
                }
            }
        }


        if(haveStorenum == 1 || haveStorenum == 2){
            productStore.setHaveStorenum(haveStorenum);
        }


        if(StringUtils.hasText(shopname)){
            productInfo.setShopname(shopname);
        }

        productInfo.setPdstate(pdstate);
        productInfo.setMaterialid(materialid);
        productInfo.setCardnumid(cardnumid);
        productInfo.setMemberid(memberid);
        productInfo.setUsername(username);


        if(uptimeStart!= null){
            productInfo.setUptimeStart(uptimeStart);
        }

        if(uptimeEnd != null){
            productInfo.setUptimeEnd(DateUtils.addDays(uptimeEnd,1));
        }

        if(createStart != null){
            productInfo.setCreateStart(createStart);
        }

        if(createEnd != null){
            productInfo.setCreateEnd( DateUtils.addDays(createEnd,1));
        }

        if(stand != null){
            productInfo.setStand(stand);
        }

        productInfo.setProductStore(productStore);
        PageInfo pageInfo = productInfoService.listFastenerProduct(pageNo,pageSize,productInfo);


        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);

        return  pageRet;
    }

    //static Map<String,Long> time = new HashMap();

    @RequestMapping(value =  "/excelExport/otherProduct",method = RequestMethod.GET)
    @ApiOperation("非紧固件商品Excel导出")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "商品名",name = "productname",paramType = "query",dataType = "string",required = false),
            @ApiImplicitParam(value = "品牌",name = "brand",paramType = "query",dataType = "string",required = false),
            @ApiImplicitParam(value = "分类id",name = "levelid",paramType = "query",dataType = "int",required = false,defaultValue = "0"),
            @ApiImplicitParam(value = "商品状态 0=放入仓库 1=待审核(立即发布) 2=审核通过 3=未通过 4=已上架(上架) 5=下架 6=删除 7-违规下架",name = "pdstate",paramType = "query",dataType = "int",required = false,defaultValue = "-1"),
            @ApiImplicitParam(value = "卖家id",name = "memberid",paramType = "query",dataType = "int",required = false,defaultValue = "0"),
            @ApiImplicitParam(value = "用户名",name = "username",paramType = "query",dataType = "string",required = false,defaultValue = ""),
            @ApiImplicitParam(value = "上架时间",name = "uptimeStart",paramType = "query",dataType = "date",required = false),
            @ApiImplicitParam(value = "上架时间",name = "uptimeEnd",paramType = "query",dataType = "date",required = false),
            @ApiImplicitParam(value = "发布时间区间",name = "createStart",paramType = "query",dataType = "date",required = false),
            @ApiImplicitParam(value = "发布时间区间",name = "createEnd",paramType = "query",dataType = "date",required = false),
            @ApiImplicitParam(value = "店铺名称",name = "shopname",paramType = "query",dataType = "string",required = false),
            @ApiImplicitParam(value = "是否远期",name = "futurePrice",paramType = "query",dataType = "short",required = false,defaultValue = "0")
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.COMMODITYANAGEMENT + "')")
    public ResponseEntity<InputStreamResource> listOtherProductForExportExcel(OtherProductQueryDto queryDto){

        ExcepExportAction eea = new ExcepExportAction();
        long time = System.currentTimeMillis();
        eea.map.put("otherproduct",time);

        if(queryDto.getLevelid() != null && queryDto.getLevelid()>0){
            Categories productCategory =  categoriesService.getCategoryLevel(queryDto.getLevelid());
            if(productCategory != null){
                if(productCategory.getLevel()==1){
                    queryDto.setLevel1id(queryDto.getLevelid());
                }else if(productCategory.getLevel() == 2){
                    queryDto.setLevel2id(queryDto.getLevelid());
                }else if(productCategory.getLevel() == 3){
                    queryDto.setLevel3id(queryDto.getLevelid());
                }
            }
        }


        if(queryDto.getUptimeEnd() != null){
            queryDto.setUptimeEnd(DateUtils.addDays(queryDto.getUptimeEnd(),1));
        }

        if(queryDto.getCreateEnd() != null){
            queryDto.setCreateEnd(DateUtils.addDays(queryDto.getCreateEnd(),1));
        }

        List<Map<String,Object>> resList =  otherProdService.listOtherProdForAdminExcle(queryDto);


        String[] rowTitles = new String[]{"卖家公司名称","商品名称","别名","规格","挂牌价(3天价)",	"30天价格",
                "60天价格","90天价格",	"市场价",	"库存",	"商品编码",
                "品牌","起订量",	"重量（KG）","订货区间一","折扣一","订货区间二","折扣二",
                "订货区间三","折扣三","仓库名称",	"仓库地址","运费方式","SEO标题","SEO关键字","SEO描述"};

        XSSFWorkbook workbook = null;
        try {
            workbook = ExcelGen.common("非紧固件商品列表",rowTitles,resList,null);
            if(workbook!=null){
                ByteArrayOutputStream baos=new   ByteArrayOutputStream();
                workbook.write(baos);
                //System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("非紧固件商品列表.xlsx".getBytes(),"iso-8859-1")));
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                String contentType = "application/vnd.ms-excel";
                //time.clear();
                return ResponseEntity.ok()
                        .headers(headers).contentType(MediaType.parseMediaType(contentType))
                        .body(new InputStreamResource(new ByteArrayInputStream(baos.toByteArray())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            eea.map.remove("otherproduct");
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





    @RequestMapping(value =  "/excelExport/listFastenerProduct",method = RequestMethod.GET)
    @ApiOperation("excel导出紧固件商品")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "商品名",name = "productname",paramType = "query",dataType = "string",required = false),
            @ApiImplicitParam(value = "品牌",name = "brand",paramType = "query",dataType = "string",required = false),
            @ApiImplicitParam(value = "分类id",name = "levelid",paramType = "query",dataType = "int",required = false,defaultValue = "0"),
            @ApiImplicitParam(value = "规格",name = "stand",paramType = "query",dataType = "string",required = false),
            @ApiImplicitParam(value = "是否有库存,0-全部,1-有，2-没有",name = "haveStorenum",paramType = "query",dataType = "int",required = false,defaultValue = "0"),
            @ApiImplicitParam(value = "商品状态 0=放入仓库 1=待审核(立即发布) 2=审核通过 3=未通过 4=已上架(上架) 5=下架 6=删除 7-违规下架",name = "pdstate",paramType = "query",dataType = "int",required = false,defaultValue = "-1"),
            @ApiImplicitParam(value = "材质id",name = "materialid",paramType = "query",dataType = "int",required = false,defaultValue = "-1"),
            @ApiImplicitParam(value = "牌号id",name = "cardnumid",paramType = "query",dataType = "int",required = false,defaultValue = "-1"),
            @ApiImplicitParam(value = "卖家id",name = "memberid",paramType = "query",dataType = "int",required = false,defaultValue = "0"),
            @ApiImplicitParam(value = "用户名",name = "username",paramType = "query",dataType = "string",required = false,defaultValue = ""),
            @ApiImplicitParam(value = "上架时间",name = "uptimeStart",paramType = "query",dataType = "date",required = false),
            @ApiImplicitParam(value = "上架时间",name = "uptimeEnd",paramType = "query",dataType = "date",required = false),
            @ApiImplicitParam(value = "发布时间区间",name = "createStart",paramType = "query",dataType = "date",required = false),
            @ApiImplicitParam(value = "发布时间区间",name = "createEnd",paramType = "query",dataType = "date",required = false),
            @ApiImplicitParam(value = "印记",name = "mark",paramType = "query",dataType = "string",required = false),
            @ApiImplicitParam(value = "店铺名称",name = "shopname",paramType = "query",dataType = "string",required = false),
            @ApiImplicitParam(value = "是否远期",name = "futurePrice",paramType = "query",dataType = "short",required = false,defaultValue = "0")
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.COMMODITYANAGEMENT + "')")
    public ResponseEntity<InputStreamResource> listFastenerProductForExportExcel(
                                       @RequestParam(required = false) String productname,
                                       @RequestParam(required = false) String brand,
                                       @RequestParam(required = false,defaultValue = "0") long levelid,
                                       @RequestParam(required = false) String stand,
                                       @RequestParam(required = false,defaultValue = "0") short haveStorenum,
                                       @RequestParam(required = false,defaultValue = "-1") short pdstate,
                                       @RequestParam(required = false,defaultValue = "-1") long materialid,
                                       @RequestParam(required = false,defaultValue = "-1") long cardnumid,
                                       @RequestParam(required = false,defaultValue = "0") long memberid,
                                       @RequestParam(required = false,defaultValue = "") String username,
                                       @RequestParam(required = false) Date uptimeStart,
                                       @RequestParam(required = false) Date uptimeEnd,
                                       @RequestParam(required = false) Date createStart,
                                       @RequestParam(required = false) Date createEnd,
                                       @RequestParam(required = false) String mark,
                                       @RequestParam(required = false) String shopname,
                                       @RequestParam(required = false,defaultValue = "0") short futurePrice
    ){

        ExcepExportAction eea = new ExcepExportAction();
        long time = System.currentTimeMillis();
        eea.map.put("product",time);


        ProductInfoQuery productInfo = new ProductInfoQuery();
        ProductStore productStore = new ProductStore();

        if(StringUtils.hasText(productname)){
            productInfo.setProductname(productname);
        }

        if(StringUtils.hasText(brand)){
            productInfo.setBrand(brand);
        }

        if(StringUtils.hasText(mark)){
            productInfo.setMark(mark);
        }

        if(levelid >0){
            Categories productCategory =  categoriesService.getCategoryLevel(levelid);
            if(productCategory != null){
                if(productCategory.getLevel()==1){
                    productInfo.setLevel1id(levelid);
                }else if(productCategory.getLevel() == 2){
                    productInfo.setLevel2id(levelid);
                }else if(productCategory.getLevel() == 3){
                    productInfo.setLevel3id(levelid);
                }
            }
        }

        if(StringUtils.hasText(stand)){
            productInfo.setStand(stand);
        }

        if(haveStorenum == 1 || haveStorenum == 2){
            productStore.setHaveStorenum(haveStorenum);
        }

        if(StringUtils.hasText(shopname)){
            productInfo.setShopname(shopname);
        }

        productInfo.setPdstate(pdstate);
        productInfo.setMaterialid(materialid);
        productInfo.setCardnumid(cardnumid);
        productInfo.setMemberid(memberid);
        productInfo.setUsername(username);
        productInfo.setFuturePrice(futurePrice);

        if(uptimeStart!= null){
            productInfo.setUptimeStart(uptimeStart);
        }

        if(uptimeEnd != null){
            productInfo.setUptimeEnd(DateUtils.addDays(uptimeEnd,1));
        }

        if(createStart != null){
            productInfo.setCreateStart(createStart);
        }

        if(createEnd != null){
            productInfo.setCreateEnd( DateUtils.addDays(createEnd,1));
        }

        productInfo.setProductStore(productStore);
        List<Map<String,Object>> resList  = productInfoService.listFastenerProductForExcelByAdmin(productInfo);

        String[] rowTitles = new String[]{"卖家公司名称","商品名称","别名","第三级分类名",
                "材质","牌号","规格","品牌","印记","表面处理",
                "包装方式","单位","重量(KG)","商品货号","挂牌价","起订量","30天价格",	"60天价格",
                "90天价格","市场价","成本价","订货区间一","折扣一","订货区间二","折扣二","订货区间三",
                "折扣三"	,"仓库名称",	"仓库地址","运费方式","SEO标题","SEO关键字","SEO描述"};
        XSSFWorkbook workbook = null;
        try {
            workbook = ExcelGen.common("紧固件商品列表",rowTitles,resList,null);
            if(workbook!=null){
                ByteArrayOutputStream baos=new   ByteArrayOutputStream();
                workbook.write(baos);
                System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("紧固件商品列表.xlsx".getBytes(),"iso-8859-1")));
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                String contentType = "application/vnd.ms-excel";
                //time.clear();
                return ResponseEntity.ok()
                        .headers(headers).contentType(MediaType.parseMediaType(contentType))
                        .body(new InputStreamResource(new ByteArrayInputStream(baos.toByteArray())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            eea.map.remove("product");
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







    @PostMapping("/othereProduct/detail")
    @ApiOperation("其他类商品详情")
    public  OtherProductDetailRet detailOtherProduct(@RequestParam(required = true) long id,Model model){
        OtherProductDetailRet detailRet =  new OtherProductDetailRet();

        ProductInfo productInfo =  productInfoService.getById(id);
        if(productInfo == null){
            detailRet.setMessage("该商品不存在");
            detailRet.setResult(BasicRet.ERR);
            return  detailRet;
        }

        if(!productInfo.getProducttype().equals(AppConstant.OTHER_PRO_TYPE)){
            detailRet.setMessage("该商品不属于非紧固件类商品");
            detailRet.setResult(BasicRet.ERR);
            return  detailRet;
        }

        OtherProdDetailViewDto viewDto = new OtherProdDetailViewDto();
        BeanUtils.copyProperties(productInfo,viewDto);

        List<OtherProdStore> prodStoreList = otherProdService.getOtherProdStore(productInfo.getId());

        List<OtherProdStoreView> prodStoreViewList = new ArrayList<>();

        String intervalprice = "";

        for(OtherProdStore pdStore : prodStoreList){
            viewDto.setStoreid(pdStore.getStoreid());
            viewDto.setStorename(pdStore.getStorename());
            List<ProductAttr> attrList = productAttrService.getListByPidAndPdno(productInfo.getId(),pdStore.getPdno());
            pdStore.setProductAttrList(attrList);


           // viewDto.setFreightmode(pdStore.getFreightmode());
            viewDto.setCostprice(pdStore.getCostprice());
            viewDto.setStepwiseprice(pdStore.isStepwiseprice());
            viewDto.setMarketprice(pdStore.getMarketprice());
            intervalprice =  pdStore.getIntervalprice();
            OtherProdStoreView otherProdStoreView = new OtherProdStoreView();
            BeanUtils.copyProperties(pdStore,otherProdStoreView);

            prodStoreViewList.add(otherProdStoreView);
        }

        //运费模版
        ShippingTemplates shippingTemplates = null;

//        if (viewDto.getFreightmode() > 0) {
//            shippingTemplates = shippingTemplatesService.getFullTemplatesById(viewDto.getFreightmode());
//        }



//        List<Attributetbl> attributetblList =  attributetblService.getAttributeWithValue(productInfo.getProductnameid());

        detailRet.viewDto =  viewDto;
        detailRet.prodStoreList =  prodStoreViewList;
//        detailRet.attributetblList = attributetblList;
        detailRet.intervalprice = intervalprice;
        //detailRet.shippingTemplates = shippingTemplates;

        detailRet.setResult(BasicRet.SUCCESS);
        return  detailRet;
    }



    private  class OtherProductDetailRet extends  BasicRet{
        private OtherProdDetailViewDto viewDto;

        private List<OtherProdStoreView> prodStoreList;

        // private List<Attributetbl> attributetblList;

        private  String intervalprice;

        private  ShippingTemplates shippingTemplates;

        public String getIntervalprice() {
            return intervalprice;
        }

        public void setIntervalprice(String intervalprice) {
            this.intervalprice = intervalprice;
        }

        public OtherProdDetailViewDto getViewDto() {
            return viewDto;
        }

        public void setViewDto(OtherProdDetailViewDto viewDto) {
            this.viewDto = viewDto;
        }

        public List<OtherProdStoreView> getProdStoreList() {
            return prodStoreList;
        }

        public void setProdStoreList(List<OtherProdStoreView> prodStoreList) {
            this.prodStoreList = prodStoreList;
        }

        public ShippingTemplates getShippingTemplates() {
            return shippingTemplates;
        }

        public void setShippingTemplates(ShippingTemplates shippingTemplates) {
            this.shippingTemplates = shippingTemplates;
        }


        //        public List<Attributetbl> getAttributetblList() {
//            return attributetblList;
//        }
//
//        public void setAttributetblList(List<Attributetbl> attributetblList) {
//            this.attributetblList = attributetblList;
//        }
    }









    @RequestMapping(value =  "/listOtherProduct",method = RequestMethod.POST)
    @ApiOperation("非紧固件商品分页列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "第几页",name = "pageNo",paramType = "query",dataType = "int",required = true,defaultValue = "1"),
            @ApiImplicitParam(value = "每页显示的条数",name = "pageSize",paramType = "query",dataType = "int",required = true,defaultValue = "20"),
            @ApiImplicitParam(value = "商品名",name = "productname",paramType = "query",dataType = "string",required = false),
            @ApiImplicitParam(value = "品牌",name = "brand",paramType = "query",dataType = "string",required = false),
            @ApiImplicitParam(value = "分类id",name = "levelid",paramType = "query",dataType = "int",required = false,defaultValue = "0"),
            @ApiImplicitParam(value = "商品状态 0=放入仓库 1=待审核(立即发布) 2=审核通过 3=未通过 4=已上架(上架) 5=下架 6=删除 7-违规下架",name = "pdstate",paramType = "query",dataType = "int",required = false,defaultValue = "-1"),
            @ApiImplicitParam(value = "卖家id",name = "memberid",paramType = "query",dataType = "int",required = false,defaultValue = "0"),
            @ApiImplicitParam(value = "用户名",name = "username",paramType = "query",dataType = "string",required = false,defaultValue = ""),
            @ApiImplicitParam(value = "上架时间",name = "uptimeStart",paramType = "query",dataType = "date",required = false),
            @ApiImplicitParam(value = "上架时间",name = "uptimeEnd",paramType = "query",dataType = "date",required = false),
            @ApiImplicitParam(value = "发布时间区间",name = "createStart",paramType = "query",dataType = "date",required = false),
            @ApiImplicitParam(value = "发布时间区间",name = "createEnd",paramType = "query",dataType = "date",required = false),
            @ApiImplicitParam(value = "店铺名称",name = "shopname",paramType = "query",dataType = "string",required = false),
            @ApiImplicitParam(value = "是否远期",name = "futurePrice",paramType = "query",dataType = "short",required = false,defaultValue = "0")
    })
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.COMMODITYANAGEMENT + "')")
    public  PageRet listOtherProduct(OtherProductQueryDto queryDto, int pageNo, int pageSize, Model model){
        PageRet pageRet =  new PageRet();

        if(queryDto.getLevelid() != null && queryDto.getLevelid()>0){
            Categories productCategory =  categoriesService.getCategoryLevel(queryDto.getLevelid());
            if(productCategory != null){
                if(productCategory.getLevel()==1){
                    queryDto.setLevel1id(queryDto.getLevelid());
                }else if(productCategory.getLevel() == 2){
                    queryDto.setLevel2id(queryDto.getLevelid());
                }else if(productCategory.getLevel() == 3){
                    queryDto.setLevel3id(queryDto.getLevelid());
                }
            }
        }


        if(queryDto.getUptimeEnd() != null){
            queryDto.setUptimeEnd(DateUtils.addDays(queryDto.getUptimeEnd(),1));
        }

        if(queryDto.getCreateEnd() != null){
            queryDto.setCreateEnd(DateUtils.addDays(queryDto.getCreateEnd(),1));
        }

        PageInfo pageInfo =  otherProdService.listOtherProd(queryDto,pageNo,pageSize);

        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);

        return  pageRet;
    }



    @RequestMapping(value =  "/checkProductInfo",method = RequestMethod.POST)
    @ApiOperation("产品状态修改")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "产品id",name = "id",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(value = "商品状态0=放入仓库1=待审核2=审核通过3=未通过4=已上架5=下架6=删除7=违规下架",name = "pdstate",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(value = "审核未通过原因",name = "reason",required = false,paramType = "query",dataType = "string")
    })
    //商品状态 0=放入仓库 1=待审核(立即发布) 2=审核通过 3=未通过 4=已上架(上架) 5=下架 6=删除 7-违规下架
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.COMMODITYANAGEMENT + "')")
    public BasicRet updatePdstate(@RequestParam(required = true) long id,
                                  @RequestParam(required = true) short pdstate,
                                  String reason, Model model, HttpServletRequest request) throws CashException {
        BasicRet basicRet = new BasicRet();

        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);

        ProductInfo productInfo = productInfoService.getById(id);
        if(productInfo == null){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("要修改的产品不存在");
            return basicRet;
        }

        //后台只可操作 2=审核通过 3=未通过 7-违规下架
        if(pdstate != 2 && pdstate !=3 && pdstate != 7){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("后台只能对产品操作：2=审核通过 3=未通过 7-违规下架");
            return  basicRet;
        }

        Member member =  memberService.getMemberById(productInfo.getMemberid());

        boolean canUpdate =  false;


        if(pdstate==2 && (productInfo.getPdstate()==1 || productInfo.getPdstate()==3 )){
            canUpdate = true;
        }

        if(pdstate==3 && (productInfo.getPdstate()==1 || productInfo.getPdstate()==2 )){
            canUpdate = true;
        }


        if(pdstate ==7 && productInfo.getPdstate()==4){
            canUpdate = true;
            //违规下架，退还保证金
            if(productInfo.getProducttype().equals(AppConstant.FASTENER_PRO_TYPE)) {
                salerCapitalService.backBail(productInfo);
            }

            productSearchService.delIndex(productInfo.getId());
        }


        if(canUpdate){
            ProductInfo info = new ProductInfo();
            info.setId(id);
            info.setPdstate(pdstate);
            info.setAuditname(admin.getRealname());
            info.setAudittime(new Date());
            info.setReason(reason);
            productInfoService.updateByPrimaryKeySelective(info);


            //2=审核通过 3=未通过 7-违规下架
            String tag = "审核通过";
            if(pdstate==3){
                tag ="审核未通过";
            }else if(pdstate==7){
                tag = "违规下架";
            }

            //保存日志
            memberLogOperator.saveMemberLog(null,admin,"后台将商品:"+productInfo.getId()+"设置为"+tag,request,memberOperateLogService);

            //同步到中间件
//            if (pdstate == 2 && (member.getUsername().equals("jskj") || member.getUsername().equals("jskjjgj") || member.getUsername().equals("jskjkxp"))) {
//                List<ProductStore> storeList = productStoreService.getByProductid(info.getId());
//                for (ProductStore ps : storeList) {
//                    NameValuePair[] pairs = new NameValuePair[]{
//                            new NameValuePair("sku", ps.getPdno()),
//                            new NameValuePair("goodsName", productInfo.getProductname()),
//                            new NameValuePair("mark",productInfo.getMark()),
//                            new NameValuePair("surfacetreatment",productInfo.getSurfacetreatment())
//                    };
//                    try {
//                        HttpClientUtils.post(middleware_url + MiddleWareUrl.PRODUCT_URL, pairs);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }




            basicRet.setMessage("修改成功");
            basicRet.setResult(BasicRet.SUCCESS);
        }else{
            basicRet.setMessage("要修改的状态不对");
            basicRet.setResult(BasicRet.ERR);
        }

        return  basicRet;
    }



    @RequestMapping(value =  "/batch/checkProductInfo",method = RequestMethod.POST)
    @ApiOperation("产品状态批量修改")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "产品状态",name = "pdstate",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(value = "审核未通过原因",name = "reason",required = false,paramType = "query",dataType = "string")
    })
    //商品状态 0=放入仓库 1=待审核(立即发布) 2=审核通过 3=未通过 4=已上架(上架) 5=下架 6=删除 7-违规下架
    @PreAuthorize("hasAuthority('" + AdminAuthorityConst.COMMODITYANAGEMENT + "')")
    public BasicRet batchUpdatePdstate(@RequestParam(required = true) Long[] ids,
                                       @RequestParam(required = true) short pdstate,
                                       String reason, Model model,HttpServletRequest request) throws CashException {
        BasicRet basicRet = new BasicRet();

        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);

        ProductInfoExample productInfoExample = new ProductInfoExample();
        ProductInfoExample.Criteria criteria  = productInfoExample.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));


        List<ProductInfo>  list =   productInfoService.listProductByExample(productInfoExample);

        if(list == null || list.size()==0){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("未查询到所选的商品");
            return  basicRet;
        }


        int successCount = 0;
        for(ProductInfo productInfo : list){
            boolean canUpdate =  false;

            if(pdstate==2 && (productInfo.getPdstate()==1)){
                canUpdate = true;
            }

            if(pdstate==3 && (productInfo.getPdstate()==1 || productInfo.getPdstate() == 2)){
                canUpdate = true;
            }


            if(pdstate ==7 && productInfo.getPdstate()==4){
                canUpdate = true;
                if(AppConstant.FASTENER_PRO_TYPE.equals(productInfo.getProducttype())){
                    //紧固件 违规下架，退还保证金
                    salerCapitalService.backBail(productInfo);

                }else{
                    canUpdate = true;
                }

            }

            if(canUpdate){
                ProductInfo info = new ProductInfo();
                info.setId(productInfo.getId());
                info.setPdstate(pdstate);
                info.setAuditname(admin.getRealname());
                info.setAudittime(new Date());
                info.setReason(reason);
                productInfoService.updateByPrimaryKeySelective(info);

                //2=审核通过 3=未通过 7-违规下架
                String tag = "审核通过";
                if(pdstate==3){
                    tag ="审核未通过";
                }else if(pdstate==7){
                    tag = "违规下架";
                }


//                if (pdstate == 2) {
//                    Member member = memberService.getMemberById(info.getMemberid());
//                    if (member.getUsername().equals("jskj") || member.getUsername().equals("jskjjgj") || member.getUsername().equals("jskjkxp")) {
//                        List<ProductStore> storeList = productStoreService.getByProductid(info.getId());
//                        for (ProductStore ps : storeList) {
//                            NameValuePair[] pairs = new NameValuePair[]{
//                                    new NameValuePair("sku", ps.getPdno()),
//                                    new NameValuePair("goodsName", info.getProductname()),
//                                    new NameValuePair("mark",productInfo.getMark()),
//                                    new NameValuePair("surfacetreatment",productInfo.getSurfacetreatment())
//                            };
//                            try {
//                                HttpClientUtils.post(MiddleWareUrl.PRODUCT_URL, pairs);
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }


                //保存日志
                memberLogOperator.saveMemberLog(null,admin,"后台将商品:"+productInfo.getId()+"设置为"+tag,request,memberOperateLogService);

                successCount++;
            }
        }

        basicRet.setMessage("修改成功"+successCount+"条");
        basicRet.setResult(BasicRet.SUCCESS);

        return  basicRet;
    }






    @RequestMapping(value =  "/getProductInfoWithRate",method = RequestMethod.POST)
    @ApiOperation("供货价格（运营）")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "第几页",name = "pageNo",required = true,paramType = "query",dataType = "int",defaultValue = "1"),
            @ApiImplicitParam(value = "每页多少条",name = "pageSize",required = true,paramType = "query",dataType = "int",defaultValue = "20"),
            @ApiImplicitParam(value = "品名",name = "productname",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "品牌",name = "brand",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "一级分类id",name = "level1id",required = false,paramType = "query",dataType = "int",defaultValue = "0"),
            @ApiImplicitParam(value = "二级分类id",name = "level2id",required = false,paramType = "query",dataType = "int",defaultValue = "0"),
            @ApiImplicitParam(value = "三级分类id",name = "level3id",required = false,paramType = "query",dataType = "int",defaultValue = "0"),
            @ApiImplicitParam(value = "材质",name = "material",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "牌号",name = "cardnum",required = false,paramType = "query",dataType = "string",defaultValue = ""),
            @ApiImplicitParam(value = "印记",name = "mark",required = false,paramType = "query",dataType = "string",defaultValue = ""),
    })
    public PageInfo getProductInfoWithRate(
                                            @RequestParam(required = true,defaultValue = "1") int pageNo,
                                            @RequestParam(required = true,defaultValue = "20") int pageSize,
                                             String productname,
                                             String brand,
                                            @RequestParam(required = true,defaultValue = "0") int level1id,
                                            @RequestParam(required = true,defaultValue = "0") int level2id,
                                            @RequestParam(required = true,defaultValue = "0") int level3id,
                                             String material,
                                             String cardnum,
                                             String mark){
        return  productInfoService.getProductInfoWithRate(pageNo,pageSize,productname,brand,level1id,level2id,level3id,material,cardnum,mark);
    }

}
