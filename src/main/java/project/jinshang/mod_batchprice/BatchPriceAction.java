package project.jinshang.mod_batchprice;

import com.google.gson.Gson;
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
import project.jinshang.common.utils.ExcelGen;
import project.jinshang.common.utils.GenerateNo;
import project.jinshang.mod_admin.mod_upload.ProductBatchImport;
import project.jinshang.mod_batchprice.bean.ProductQueryParam;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_product.bean.Categories;
import project.jinshang.mod_product.bean.ProductInfo;
import project.jinshang.mod_product.bean.ProductStore;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    MemberLogOperator memberLogOperator = new MemberLogOperator();

    @RequestMapping(value = "/pageBatchUpdatePrice",method = RequestMethod.POST)
    @ApiOperation(value = "页面批量修改价格")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pdids",value = "商品id数组",required = true,paramType = "query",dataType = "array"),
            @ApiImplicitParam(name = "shiftType",value = "调价依据1=挂牌价2=市场价",required = true,paramType = "query",dataType = "int"),
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
                    }else {
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
    public ResponseEntity<InputStreamResource> exportProductList(Model model,HttpServletResponse response, ProductQueryParam productQueryParam){
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        productQueryParam.setSellerId(member.getId());
        if(productQueryParam.getLevel()!=null&&productQueryParam.getLevel()>0){

            Categories productCategory = categoriesService.getCategoryLevel(productQueryParam.getLevel());
            if (productCategory != null) {
                if (productCategory.getLevel() == 1) {
                    productQueryParam.setLevel1(productQueryParam.getLevel());
                } else if (productCategory.getLevel() == 2) {
                    productQueryParam.setLevel2(productQueryParam.getLevel());
                } else if (productCategory.getLevel() == 3) {
                    productQueryParam.setLevel3(productQueryParam.getLevel());
                }
            }

        }


        XSSFWorkbook workbook = null;
        try {
            String[] rowTitles = new String[]{"商品库存id","商品id","紧固件编码", "品名", "规格", "商品价格", "30天发货价格", "60天发货价格", "90天发货价格", "仓库名称"};

            List<Map<String, Object>> list = batchPriceService.getExcelProduct(productQueryParam);
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
}
