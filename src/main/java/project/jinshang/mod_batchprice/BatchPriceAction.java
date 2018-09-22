package project.jinshang.mod_batchprice;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import project.jinshang.common.bean.MemberLogOperator;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.*;
import project.jinshang.mod_admin.mod_upload.ProductBatchImport;
import project.jinshang.mod_batchprice.bean.ProductQueryParam;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_product.bean.Categories;
import project.jinshang.mod_product.bean.ProductInfo;
import project.jinshang.mod_product.bean.ProductInfoQuery;
import project.jinshang.mod_product.bean.ProductStore;
import project.jinshang.mod_product.bean.dto.OtherProductQueryDto;
import project.jinshang.mod_product.service.CategoriesService;
import project.jinshang.mod_product.service.MemberOperateLogService;
import project.jinshang.mod_product.service.OrdersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/seller/batchprice")
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME})
@Api(tags = "批量改价格模块",description = "批量改价格模块")
@Transactional(rollbackFor =Exception.class)
public class BatchPriceAction {

    @Autowired
    private BatchPriceService batchPriceService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private MemberOperateLogService memberOperateLogService;

    @Value("${upload.dir.moduleIcon}")
    private  String uploadPath;


    @Autowired
    private ProductBatchImport productBatchImport;

    @Autowired
    private  Gson gson;

    MemberLogOperator memberLogOperator = new MemberLogOperator();

    @RequestMapping(value = "/pageBatchUpdatePrice",method = RequestMethod.POST)
    @ApiOperation(value = "页面批量修改价格")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pdids",value = "商品id数组",required = true,paramType = "query",dataType = "array"),
            @ApiImplicitParam(name = "shiftType",value = "调价依据1=挂牌价2=市场价3=折扣比例",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "shiftPriceType",value = "调价方式1=百分比2=固定金额",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "saleType",value = "销售类型1=现货销售2=远期销售",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "shiftCate",value = "调价类型1=上调2=下调",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "shiftNum",value = "调价幅度",required = true,paramType = "query",dataType = "double"),
            @ApiImplicitParam(name = "shiftNum1",value = "30调价幅度",required = true,paramType = "query",dataType = "double"),
            @ApiImplicitParam(name = "shiftNum2",value = "60调价幅度",required = true,paramType = "query",dataType = "double"),
            @ApiImplicitParam(name = "shiftNum3",value = "90调价幅度",required = true,paramType = "query",dataType = "double"),

    })
    public BasicRet pageBatchUpdatePrice(Model model,String pdids, Short shiftType, Short shiftPriceType, Short saleType, Short shiftCate, BigDecimal shiftNum,BigDecimal shiftNum1,BigDecimal shiftNum2,BigDecimal shiftNum3){
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        //验证是否有非紧固件
        List<String> list = batchPriceService.getProductInfoByIds(pdids,member.getId());
        if(list.size()>0){
            boolean flag = false;
            for(String productType:list){
                if(productType.equals("其它")){
                    flag = true;
                    break;
                }
            }
            if(flag){
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("有非紧固件产品，不可修改");
                return basicRet;
            }
            List<ProductInfo> infos = batchPriceService.getProductInfoList(pdids);

            for(ProductInfo info:infos){
                info.setPdstate(Quantity.STATE_1);
                batchPriceService.updateProductInfo(info);
            }

            List<ProductStore> productStores = batchPriceService.getProductStoreByPdids(pdids);
            if(productStores.size()>0){
                for(ProductStore productStore:productStores){
                    BigDecimal price = productStore.getProdprice();
                    BigDecimal marketPrice = productStore.getMarketprice();
                    BigDecimal discountratio = productStore.getProdprice().divide(productStore.getMarketprice(),5,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
                    //挂牌价
                    if(shiftType==1){
                        //百分比调价
                        if(shiftPriceType==1){
                            //现货
                            if(saleType==1){
                                //上调
                                if(shiftCate==1){
                                    price = price.multiply(new BigDecimal(1).add(shiftNum.multiply(new BigDecimal(0.01))));
                                    productStore.setProdprice(price);
                                    ordersService.updateProductStore(productStore);
                                    //下调
                                }else {
                                    price = price.multiply(new BigDecimal(1).subtract(shiftNum.multiply(new BigDecimal(0.01))));
                                    productStore.setProdprice(price);
                                    ordersService.updateProductStore(productStore);
                                }
                                //远期
                            }else {
                                //上调
                                if(shiftCate==1){
                                    if(shiftNum1!=null&&shiftNum2!=null&&shiftNum3!=null){
                                        BigDecimal thirtyPrice = price.multiply(new BigDecimal(1).add(shiftNum1.multiply(new BigDecimal(0.01))));
                                        BigDecimal sixtyPrice = price.multiply(new BigDecimal(1).add(shiftNum2.multiply(new BigDecimal(0.01))));
                                        BigDecimal nintyPrice = price.multiply(new BigDecimal(1).add(shiftNum3.multiply(new BigDecimal(0.01))));

                                        productStore.setThirtyprice(thirtyPrice);
                                        productStore.setSixtyprice(sixtyPrice);
                                        productStore.setNinetyprice(nintyPrice);

                                        ordersService.updateProductStore(productStore);
                                    }
                                    //下调
                                }else {
                                    if(shiftNum1!=null&&shiftNum2!=null&&shiftNum3!=null){
                                        BigDecimal thirtyPrice = price.multiply(new BigDecimal(1).subtract(shiftNum1.multiply(new BigDecimal(0.01))));
                                        BigDecimal sixtyPrice = price.multiply(new BigDecimal(1).subtract(shiftNum2.multiply(new BigDecimal(0.01))));
                                        BigDecimal nintyPrice = price.multiply(new BigDecimal(1).subtract(shiftNum3.multiply(new BigDecimal(0.01))));

                                        productStore.setThirtyprice(thirtyPrice);
                                        productStore.setSixtyprice(sixtyPrice);
                                        productStore.setNinetyprice(nintyPrice);

                                        ordersService.updateProductStore(productStore);
                                    }

                                }
                            }

                            //固定金额
                        }else {
                            //现货
                            if(saleType==1){
                                //上调
                                if(shiftCate==1){
                                    price = price.add(shiftNum);
                                    productStore.setProdprice(price);
                                    ordersService.updateProductStore(productStore);
                                    //下调
                                }else {
                                    price = price.subtract(shiftNum);
                                    productStore.setProdprice(price);
                                    ordersService.updateProductStore(productStore);
                                }
                                //远期
                            }else {
                                //上调
                                if(shiftCate==1){
                                    if(shiftNum1!=null&&shiftNum2!=null&&shiftNum3!=null){

                                        BigDecimal thirtyPrice = price.add(shiftNum1);
                                        BigDecimal sixtyPrice = price.add(shiftNum2);
                                        BigDecimal nintyPrice = price.add(shiftNum3);

                                        productStore.setThirtyprice(thirtyPrice);
                                        productStore.setSixtyprice(sixtyPrice);
                                        productStore.setNinetyprice(nintyPrice);

                                        ordersService.updateProductStore(productStore);
                                    }
                                    //下调
                                }else {
                                    if(shiftNum1!=null&&shiftNum2!=null&&shiftNum3!=null){
                                        BigDecimal thirtyPrice = price.subtract(shiftNum1);
                                        BigDecimal sixtyPrice = price.subtract(shiftNum2);
                                        BigDecimal nintyPrice = price.subtract(shiftNum3);

                                        productStore.setThirtyprice(thirtyPrice);
                                        productStore.setSixtyprice(sixtyPrice);
                                        productStore.setNinetyprice(nintyPrice);

                                        ordersService.updateProductStore(productStore);
                                    }
                                }
                            }
                        }
                        //市场价
                    }else if(shiftType==2){
                        //百分比调价
                        if(shiftPriceType==1){
                            //现货
                            if(saleType==1){
                                //上调
                                if(shiftCate==1){
                                    marketPrice = marketPrice.multiply(new BigDecimal(1).add(shiftNum.multiply(new BigDecimal(0.01))));
                                    productStore.setProdprice(marketPrice);
                                    ordersService.updateProductStore(productStore);
                                    //下调
                                }else {
                                    marketPrice = marketPrice.multiply(new BigDecimal(1).subtract(shiftNum.multiply(new BigDecimal(0.01))));
                                    productStore.setProdprice(marketPrice);
                                    ordersService.updateProductStore(productStore);
                                }
                            }
                            //固定金额
                        }else {
                            //现货
                            if(saleType==1){
                                //上调
                                if(shiftCate==1){
                                    marketPrice = marketPrice.add(shiftNum);
                                    productStore.setProdprice(marketPrice);
                                    ordersService.updateProductStore(productStore);
                                    //下调
                                }else {
                                    marketPrice = marketPrice.subtract(shiftNum);
                                    productStore.setProdprice(marketPrice);
                                    ordersService.updateProductStore(productStore);
                                }
                            }
                        }
                        //折扣比例
                    }else{
                        //百分比调价
                        if(shiftPriceType==1) {
                            //现货
                            if (saleType == 1) {
                                //上调
                                if (shiftCate == 1) {
                                    discountratio = discountratio.add(shiftNum);
                                    price = discountratio.multiply(marketPrice).divide(new BigDecimal(100));
                                    productStore.setDiscountratio(discountratio);
                                    productStore.setProdprice(price);
                                    ordersService.updateProductStore(productStore);
                                    //下调
                                } else {
                                    discountratio = discountratio.subtract(shiftNum);
                                    price = discountratio.multiply(marketPrice).divide(new BigDecimal(100));
                                    productStore.setDiscountratio(discountratio);
                                    productStore.setProdprice(price);
                                    ordersService.updateProductStore(productStore);
                                }
                            }
                        }
                    }
                }
            }else {
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("没有商品");
                return basicRet;
            }
        }else {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("没有商品");
            return basicRet;
        }

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        return basicRet;
    }


    @RequestMapping(value = "/exportProductList",method = RequestMethod.GET)
    @ApiOperation(value = "导出下架产品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "商品名", name = "productname", paramType = "query", dataType = "string", required = false),
            @ApiImplicitParam(value = "品牌", name = "brand", paramType = "query", dataType = "string", required = false),
            @ApiImplicitParam(value = "分类id", name = "levelid", paramType = "query", dataType = "int", required = false, defaultValue = "0"),
            @ApiImplicitParam(value = "规格", name = "stand", paramType = "query", dataType = "string", required = false),
            @ApiImplicitParam(value = "是否有库存,0-全部,1-有，2-没有", name = "haveStorenum", paramType = "query", dataType = "int", required = false, defaultValue = "0"),
            @ApiImplicitParam(value = "商品状态0=放入仓库1=待审核2=审核通过3=未通过4=已上架5=下架6=删除7=违规下架", name = "pdstate", paramType = "query", dataType = "int", required = false, defaultValue = "-1"),
            @ApiImplicitParam(value = "商品id，多个商品id用英文逗号隔开", name = "pdids", paramType = "query", dataType = "String", required = false),
            @ApiImplicitParam(value = "商品编号", name = "pdno", paramType = "query", dataType = "int", required = false),
            @ApiImplicitParam(value = "材质id", name = "materialid", paramType = "query", dataType = "int", required = false, defaultValue = "-1"),
            @ApiImplicitParam(value = "牌号id", name = "cardnumid", paramType = "query", dataType = "int", required = false, defaultValue = "-1"),
            @ApiImplicitParam(value = "上架时间", name = "uptimeStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "上架时间", name = "uptimeEnd", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "下架时间", name = "downtimeStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "下架时间", name = "downtimeEnd", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "最后修改价格时间", name = "updatetimeStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "最后修改价格时间", name = "updatetimeEnd", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "发布时间区间", name = "createStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "发布时间区间", name = "createEnd", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "多规格属性  {\"DIN933\":[\"M16\",\"M3\"],\"GB5783\":[\"M16\",\"M3\"]}", name = "standJson", paramType = "query", dataType = "string", required = false),
    })
    public ResponseEntity<InputStreamResource> exportProductList(Model model, @RequestParam(required = false) String productname,
                                                                 @RequestParam(required = false) String brand,
                                                                 @RequestParam(required = false, defaultValue = "0") long levelid,
                                                                 @RequestParam(required = false) String stand,
                                                                 @RequestParam(required = false, defaultValue = "0") short haveStorenum,
                                                                 @RequestParam(required = false, defaultValue = "-1") short pdstate,
                                                                 @RequestParam(required = false) String pdids,
                                                                 @RequestParam(required = false) String pdno,
                                                                 @RequestParam(required = false, defaultValue = "-1") long materialid,
                                                                 @RequestParam(required = true, defaultValue = "-1") long cardnumid,
                                                                 @RequestParam(required = false) Date uptimeStart,
                                                                 @RequestParam(required = false) Date uptimeEnd,
                                                                 @RequestParam(required = false) Date downtimeStart,
                                                                 @RequestParam(required = false) Date downtimeEnd,
                                                                 @RequestParam(required = false) Date updatetimeStart,
                                                                 @RequestParam(required = false) Date updatetimeEnd,
                                                                 @RequestParam(required = false) Date createStart,
                                                                 @RequestParam(required = false) Date createEnd,
                                                                 @RequestParam(required = false) String standJson){
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        ProductInfoQuery productInfo = new ProductInfoQuery();
        ProductStore productStore = new ProductStore();

        productInfo.setMemberid(member.getId());

        if (StringUtils.hasText(productname)) {
            productInfo.setProductname(productname);
        }

        if (StringUtils.hasText(pdno)) {
            productInfo.setPdno(pdno);
        }

        if (StringUtils.hasText(brand)) {
            productInfo.setBrand(brand);
        }

        if (StringUtils.hasText(standJson)) {
            if (!CommonUtils.isGoodJson(standJson)) {
                return null;
            }
            Map<String,List> standMap = gson.fromJson(standJson, new TypeToken<Map<String,List>>() {
            }.getType());
            productInfo.setStandMap(standMap);
        }


        if (levelid > 0) {
            Categories productCategory = categoriesService.getCategoryLevel(levelid);
            if (productCategory != null) {
                if (productCategory.getLevel() == 1) {
                    productInfo.setLevel1id(levelid);
                } else if (productCategory.getLevel() == 2) {
                    productInfo.setLevel2id(levelid);
                } else if (productCategory.getLevel() == 3) {
                    productInfo.setLevel3id(levelid);
                }
            }
        }

        if (StringUtils.hasText(stand)) {
            productInfo.setStand(stand);
        }

        if (haveStorenum == 1 || haveStorenum == 2) {
            productStore.setHaveStorenum(haveStorenum);
        }

        productInfo.setCardnumid(cardnumid);
        productInfo.setMaterialid(materialid);
        productInfo.setPdstate(pdstate);

        if (uptimeStart != null) {
            productInfo.setUptimeStart(uptimeStart);
        }

        if (uptimeEnd != null) {
            productInfo.setUptimeEnd(DateUtils.addDays(uptimeEnd, 1));
        }

        if(pdids != null && pdids !="") {
            /*String[] str = pdids.split(",");
            int[] intTemp = new int[str.length];
            for (int i = 0; i <str.length; i++) {
                intTemp[i] = Integer.parseInt(str[i]);
            }*/
            try {
                List<Integer> PdidList = Arrays.asList(pdids.split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
                productInfo.setPdids(PdidList);
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }

        if (downtimeStart != null) {
            productInfo.setDowntimeStart(downtimeStart);
        }

        if (downtimeEnd != null) {
            productInfo.setDowntimeEnd(DateUtils.addDays(downtimeEnd, 1));
        }

        if (updatetimeStart != null) {
            productInfo.setUpdatetimeStart(updatetimeStart);
        }

        if (updatetimeEnd != null) {
            productInfo.setUpdatetimeEnd(DateUtils.addDays(updatetimeEnd, 1));
        }

        if (createStart != null) {
            productInfo.setCreateStart(createStart);
        }

        if (createEnd != null) {
            productInfo.setCreateEnd(DateUtils.addDays(createEnd, 1));
        }

        productInfo.setProductStore(productStore);


        XSSFWorkbook workbook = null;
        try {
            String[] rowTitles = new String[]{"商品库存id","商品id","紧固件编码", "品名", "规格", "商品价格", "30天发货价格", "60天发货价格", "90天发货价格", "仓库名称"};

            List<Map<String, Object>> list = batchPriceService.getExcelProduct(productInfo);
            workbook = ExcelGen.common("产品列表", rowTitles, list, null);
            if (workbook != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);
                System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("产品列表.xlsx".getBytes(), "iso-8859-1")));
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                String contentType = "application/vnd.ms-excel";
                return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType(contentType))
                        .body(new InputStreamResource(new ByteArrayInputStream(baos.toByteArray())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }


    @PostMapping("/importProductPrice")
    @ApiOperation("excel导入紧固件商品")
    public  BasicRet addFastenerProductByExcel(@RequestParam("file") CommonsMultipartFile file, Model model, HttpServletRequest request) throws Exception {

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        if(!file.getOriginalFilename().endsWith(".xlsx") && !file.getOriginalFilename().endsWith(".xls")){
            return  new BasicRet(BasicRet.ERR,"请上传Excel文件");
        }

        String fileName= GenerateNo.getUUID()+file.getOriginalFilename();
        File dir =  new File(uploadPath);
        if(!dir.exists()){
            dir.mkdirs();
        }

        File newFile= new File(dir,fileName);
        Gson gson = new Gson();

        List<ProductInfo> infoList = new ArrayList<>();
        try {
            file.transferTo(newFile);
            List<ProductStore> list = productBatchImport.excelDownProductTo(newFile.getAbsolutePath());
            StringBuffer sb = new StringBuffer();
            if(list.size()>0){
                for(ProductStore store:list){
                    ordersService.updateProductStore(store);
                    sb.append(store.getPdid()).append(",");
                }
                sb.deleteCharAt(sb.length() - 1);
                List<ProductInfo> infos = batchPriceService.getProductInfoList(sb.toString());
                for(ProductInfo info:infos){
                    info.setPdstate(Quantity.STATE_1);
                    info.setUpdatetime(new Date());
                    batchPriceService.updateProductInfo(info);
                }
            }
            memberLogOperator.saveMemberLog(member, null, "excel批量修改下架商品价格：","/rest/seller/batchprice/importProductPrice", request, memberOperateLogService);
        } catch (Exception e) {
            throw  e;
        } finally {
            newFile.delete();
        }
        return  new BasicRet(BasicRet.SUCCESS,"修改成功");
    }

    @RequestMapping(value = "/exportOtherProductList",method = RequestMethod.GET)
    @ApiOperation(value = "导出下架产品列表(other)")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "商品名", name = "productname", paramType = "query", dataType = "string", required = false),
            @ApiImplicitParam(value = "品牌", name = "brand", paramType = "query", dataType = "string", required = false),
            @ApiImplicitParam(value = "分类id", name = "levelid", paramType = "query", dataType = "int", required = false, defaultValue = "0"),
            @ApiImplicitParam(value = "商品状态 0=放入仓库 1=待审核(立即发布) 2=审核通过 3=未通过 4=已上架(上架) 5=下架 6=删除 7-违规下架", name = "pdstate", paramType = "query", dataType = "int", required = false, defaultValue = "-1"),
            @ApiImplicitParam(value = "卖家id", name = "memberid", paramType = "query", dataType = "int", required = false, defaultValue = "0"),
            @ApiImplicitParam(value = "用户名", name = "username", paramType = "query", dataType = "string", required = false, defaultValue = ""),
            @ApiImplicitParam(value = "商品编号", name = "pdno", paramType = "query", dataType = "int", required = false),
            @ApiImplicitParam(value = "商品id，多个商品id用英文逗号隔开", name = "pdids", paramType = "query", dataType = "String", required = false),
            @ApiImplicitParam(value = "下架时间", name = "downtimeStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "下架时间", name = "downtimeEnd", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "最后修改价格时间", name = "updatetimeStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "最后修改价格时间", name = "updatetimeEnd", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "上架时间", name = "uptimeStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "上架时间", name = "uptimeEnd", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "发布时间区间", name = "createStart", paramType = "query", dataType = "date", required = false),
            @ApiImplicitParam(value = "发布时间区间", name = "createEnd", paramType = "query", dataType = "date", required = false),
    })
    public ResponseEntity<InputStreamResource> exportOtherProductList(Model model, OtherProductQueryDto queryDto){
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        queryDto.setMemberid(member.getId());
        if (queryDto.getLevelid() != null && queryDto.getLevelid() > 0) {
            Categories productCategory = categoriesService.getCategoryLevel(queryDto.getLevelid());
            if (productCategory != null) {
                if (productCategory.getLevel() == 1) {
                    queryDto.setLevel1id(queryDto.getLevelid());
                } else if (productCategory.getLevel() == 2) {
                    queryDto.setLevel2id(queryDto.getLevelid());
                } else if (productCategory.getLevel() == 3) {
                    queryDto.setLevel3id(queryDto.getLevelid());
                }
            }
        }

        if (queryDto.getUptimeEnd() != null) {
            queryDto.setUptimeEnd(DateUtils.addDays(queryDto.getUptimeEnd(), 1));
        }

        if (queryDto.getCreateEnd() != null) {
            queryDto.setCreateEnd(DateUtils.addDays(queryDto.getCreateEnd(), 1));
        }

        if (queryDto.getDowntimeEnd() != null) {
            queryDto.setDowntimeEnd(DateUtils.addDays(queryDto.getDowntimeEnd(), 1));
        }

        if (queryDto.getUpdatetimeEnd() != null) {
            queryDto.setUpdatetimeEnd(DateUtils.addDays(queryDto.getUpdatetimeEnd(), 1));
        }

        if(queryDto.getPdids() != null && queryDto.getPdids()!="") {

            try {
                List<Integer> PdidList = Arrays.asList(queryDto.getPdids().split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
                queryDto.setPdid(PdidList);
                String pdidStr = org.apache.commons.lang3.StringUtils.join(PdidList, ",");
                queryDto.setPdids(pdidStr);
            }catch (NumberFormatException e){
                return null;
            }
        }


        XSSFWorkbook workbook = null;
        try {
            String[] rowTitles = new String[]{"商品库存id","商品id","商品编码",  "规格", "商品价格", "30天发货价格", "60天发货价格", "90天发货价格", "仓库名称"};

            List<Map<String, Object>> list = batchPriceService.getExcelOtherProduct(queryDto);
            workbook = ExcelGen.common("产品列表", rowTitles, list, null);
            if (workbook != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);
                System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("产品列表.xlsx".getBytes(), "iso-8859-1")));
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                String contentType = "application/vnd.ms-excel";
                return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType(contentType))
                        .body(new InputStreamResource(new ByteArrayInputStream(baos.toByteArray())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }


    @PostMapping("/importOtherProductPrice")
    @ApiOperation("excel导入非紧固件商品")
    public  BasicRet addFastenerOtherProductByExcel(@RequestParam("file") CommonsMultipartFile file, Model model, HttpServletRequest request) throws Exception {

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        if(!file.getOriginalFilename().endsWith(".xlsx") && !file.getOriginalFilename().endsWith(".xls")){
            return  new BasicRet(BasicRet.ERR,"请上传Excel文件");
        }

        String fileName= GenerateNo.getUUID()+file.getOriginalFilename();
        File dir =  new File(uploadPath);
        if(!dir.exists()){
            dir.mkdirs();
        }

        File newFile= new File(dir,fileName);
        Gson gson = new Gson();

        List<ProductInfo> infoList = new ArrayList<>();
        try {
            file.transferTo(newFile);
            List<ProductStore> list = productBatchImport.excelDownOtherProductTo(newFile.getAbsolutePath());
            StringBuffer sb = new StringBuffer();
            if(list.size()>0){
                if(list.size()>1000){
                    return  new BasicRet(BasicRet.ERR,"单次导入记录限1000条，请分成2个文件导入！");
                }
                for(ProductStore store:list){
                    ordersService.updateProductStore(store);
                    sb.append(store.getPdid()).append(",");
                }
                sb.deleteCharAt(sb.length() - 1);
                List<ProductInfo> infos = batchPriceService.getProductInfoList(sb.toString());
                for(ProductInfo info:infos){
                    for(ProductStore store:list){
                        if(info.getId().equals(store.getPdid())){
                            info.setHeightprice(store.getProdprice());
                            info.setMinprice(store.getProdprice());
                        }
                    }
                    info.setPdstate(Quantity.STATE_1);
                    info.setUpdatetime(new Date());
                    batchPriceService.updateProductInfo(info);
                }
            }
            memberLogOperator.saveMemberLog(member, null, "excel批量修改下架商品价格：","/rest/seller/batchprice/importOtherProductPrice", request, memberOperateLogService);
        } catch (Exception e) {
            throw  e;
        } finally {
            newFile.delete();
        }
        return  new BasicRet(BasicRet.SUCCESS,"修改成功");
    }
}
