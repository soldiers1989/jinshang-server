package project.jinshang.mod_admin.mod_upload;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_product.bean.ProductStore;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 商品批量上传数据
 */

@Component
public class ProductBatchImport {

    /**
     * 卖家发布商品
     * @param URL
     * @return
     * @throws IOException
     */
    public ArrayList<ProductImportModel> excelToProductinfo(String URL) throws Exception{
        //创建list集合存放对象
        ArrayList<ProductImportModel> list = new ArrayList<ProductImportModel>();

        File file = new File(URL);

        Workbook workbook=null;
        try {
            workbook = WorkbookFactory.create(file);
            //读取默认第一个工作表sheet
            Sheet sheet =  workbook.getSheetAt(0);
            //获取sheet中最后一行行号
            int lastRowNum = sheet.getLastRowNum();
            if(lastRowNum<1){
                throw  new RuntimeException("模版不正确");
            }

            Map<String,Integer> map = new HashMap<>();
            Row titleRow =  sheet.getRow(0);
            int cellNum = titleRow.getLastCellNum();
            for(int i=0;i<cellNum;i++){
                map.put(titleRow.getCell(i).getStringCellValue(),i);
            }

            //紧固件编码	商品副标题	商品价格	起订量		30天发货价格	60天发货价格	90天发货价格	阶梯数量区间一
            // 折扣一	阶梯数量区间二	折扣二	阶梯数量区间三
            // 	折扣三	市场价	成本价	商品库存	商品货号	仓库名称	运输方式	商品标签	SEO标题	SEO关键字	SEO描述
            Set<String> keySet = map.keySet();
            if(!keySet.contains("编码") ||
                    !keySet.contains("商品副标题") ||
                    !keySet.contains("商品价格") ||
                    !keySet.contains("起订量") ||
                    !keySet.contains("加购量") ||
                    !keySet.contains("30天发货价格") ||
                    !keySet.contains("60天发货价格") ||
                    !keySet.contains("90天发货价格") ||
                    !keySet.contains("阶梯数量区间一") ||
                    !keySet.contains("折扣一") ||
                    !keySet.contains("阶梯数量区间二") ||
                    !keySet.contains("折扣二") ||
                    !keySet.contains("阶梯数量区间三") ||
                    !keySet.contains("折扣三") ||
                    !keySet.contains("市场价") ||
                    !keySet.contains("成本价") ||
                    !keySet.contains("商品库存") ||
                    !keySet.contains("商品编码") ||
                    !keySet.contains("仓库名称") ||
                    !keySet.contains("运费集合名称") ||
                    !keySet.contains("商品标签") ||
                    !keySet.contains("SEO标题") ||
                    !keySet.contains("SEO关键字") ||
                    !keySet.contains("SEO描述")){
                throw  new Exception("模版不正确");
            }


            //循环所有行
            for (int i = 1; i <= lastRowNum; i++) {
                //获取当前行中的内容
                Row row = sheet.getRow(i);
                short cell = row.getLastCellNum();
                ProductImportModel productImportModel=new ProductImportModel();
                if(row !=null && cell!=0){
                    //@ApiModelProperty(notes = "紧固件编码")
                    String pdno=this.getValue(row.getCell(map.get("编码")));

                    if(pdno == null){
                        throw  new RuntimeException("第"+(i+1)+"行紧固件编码必须填写");
                    }

                    productImportModel.setPdno(pdno);

                    //@ApiModelProperty(notes = "商品副标题")
                    String pdname=this.getValue(row.getCell(map.get("商品副标题")));
                    productImportModel.setPdname(pdname);

                    //@ApiModelProperty(notes = "商品价格")
                    BigDecimal price = null;
                    try {
                        price = new BigDecimal(this.getValue(row.getCell(map.get("商品价格"))));
                    }catch (Exception e){
                        throw  new RuntimeException("第"+(i+1)+"商品价格必须填写");
                    }

                    if(price == null){
                        throw  new RuntimeException("第"+(i+1)+"商品价格必须填写");
                    }

                    productImportModel.setPrice(price);



                    //@ApiModelProperty(notes = "加购量")
                    String minplusStr = this.getValue(row.getCell(map.get("加购量")));

                    if(minplusStr != null && StringUtils.isNumeric(minplusStr)){
                        productImportModel.setMinplus(new BigDecimal(minplusStr));
                    }




                    BigDecimal num = new BigDecimal(this.getValue(row.getCell(map.get("起订量"))));

                    if(num == null || num.compareTo(new BigDecimal(0)) <=0){
                        throw  new RuntimeException("第"+(i+1)+"行起订量填写不正确");
                    }

                    productImportModel.setNum(num);


                    //@ApiModelProperty(notes = "3天发货价格")
//                    BigDecimal threePrice = new BigDecimal(this.getValue(row.getCell(map.get())));
//                    productImportModel.setThreePrice(threePrice);

                    //@ApiModelProperty(notes = "30天发货价格")
                    String thirtyPriceString = this.getValue(row.getCell(map.get("30天发货价格")));
                    if(StringUtils.hasText(thirtyPriceString)) {
                        BigDecimal thirtyPrice = new BigDecimal(thirtyPriceString);
                        productImportModel.setThirtyPrice(thirtyPrice);
                    }

                    //@ApiModelProperty(notes = "60天发货价格")
                    String sixtyPriceString = this.getValue(row.getCell(map.get("60天发货价格")));
                    if(StringUtils.hasText(sixtyPriceString)) {
                        BigDecimal sixtyPrice = new BigDecimal(sixtyPriceString);
                        productImportModel.setSixtyPrice(sixtyPrice);
                    }

                    //@ApiModelProperty(notes = "90天发货价格")
                    String nintyPriceString = this.getValue(row.getCell(map.get("90天发货价格")));
                    if(StringUtils.hasText(nintyPriceString)) {
                        BigDecimal nintyPrice = new BigDecimal(nintyPriceString);
                        productImportModel.setNintyPrice(nintyPrice);
                    }

                    //@ApiModelProperty(notes = "价格数量区间一")
                    String interval1 = this.getValue(row.getCell(map.get("阶梯数量区间一")));
                    productImportModel.setInterval1(interval1);

                    //@ApiModelProperty(notes = "折扣一")
                    String sale1 = this.getValue(row.getCell(map.get("折扣一")));
                    if(sale1 != null && !sale1.equals("") &&  !StringUtils.isNumeric(sale1)){
                        throw  new RuntimeException("第"+(i+1)+"折扣一填写不正确");
                    }

                    productImportModel.setSale1(sale1);

                    //@ApiModelProperty(notes = "价格数量区间二")
                    String interval2 = this.getValue(row.getCell(map.get("阶梯数量区间二")));
                    productImportModel.setInterval2(interval2);

                    //@ApiModelProperty(notes = "折扣二")
                    String sale2 = this.getValue(row.getCell(map.get("折扣二")));
                    if(sale2 != null && !sale2.equals("") && !StringUtils.isNumeric(sale2)){
                        throw  new RuntimeException("第"+(i+1)+"行折扣二填写不正确");
                    }
                    productImportModel.setSale2(sale2);

                    //@ApiModelProperty(notes = "价格数量区间三")
                    String interval3 = this.getValue(row.getCell(map.get("阶梯数量区间三")));
                    productImportModel.setInterval3(interval3);


                    //@ApiModelProperty(notes = "折扣三")
                    String sale3 = this.getValue(row.getCell(map.get("折扣三")));
                    if(sale3 != null && !sale3.equals("") && !StringUtils.isNumeric(sale3)){
                        throw  new RuntimeException("第"+(i+1)+"折扣三填写不正确");
                    }
                    productImportModel.setSale3(sale3);

                    //@ApiModelProperty(notes = "市场价")
                    BigDecimal marketPrice = new BigDecimal(this.getValue(row.getCell(map.get("市场价"))));
                    if(marketPrice == null || marketPrice.compareTo(new BigDecimal(0)) <=0){
                        throw  new RuntimeException("第"+(i+1)+"行市场价填写不正确");
                    }
                    productImportModel.setMarketPrice(marketPrice);

                    //@ApiModelProperty(notes = "成本价")
                    String CostPrice_s = this.getValue(row.getCell(map.get("成本价")));
                    if(CostPrice_s != null && !"".equals(CostPrice_s) ) {
                        BigDecimal costPrice = new BigDecimal(this.getValue(row.getCell(map.get("成本价"))));
                        productImportModel.setCostPrice(costPrice);
                    }

                    //@ApiModelProperty(notes = "商品库存")
                    String storeStirng =  this.getValue(row.getCell(map.get("商品库存")));
                    if(storeStirng == null || !StringUtils.isNumeric(storeStirng)){
                        throw  new RuntimeException("第"+(i+1)+"行商品库存填写不正确");
                    }
                    BigDecimal storeNum = new BigDecimal(storeStirng);
                    productImportModel.setStoreNum(storeNum);



                    //@ApiModelProperty(notes = "商品货号")
                    String goodsNum = this.getValue(row.getCell(map.get("商品编码")));
                    if(!StringUtils.hasText(goodsNum)){
                        throw  new RuntimeException("第"+(i+1)+"行商品编码填写不正确");
                    }
                    productImportModel.setGoodsNum(goodsNum);

                    //@ApiModelProperty(notes = "仓库名称")
                    String storeName = this.getValue(row.getCell(map.get("仓库名称")));
                    if(storeName == null){
                        throw  new RuntimeException("第"+(i+1)+"行市场价填写不正确");
                    }
                    productImportModel.setStoreName(storeName);

                    //@ApiModelProperty(notes = "运输方式")
                    String deliveryType = this.getValue(row.getCell(map.get("运费集合名称")));
                    if(deliveryType == null){
                        throw  new RuntimeException("第"+(i+1)+"行运费集合名称填写不正确");
                    }

                    productImportModel.setDeliveryType(deliveryType);

                    //@ApiModelProperty(notes = "商品标签")
                    String pdtag = this.getValue(row.getCell(map.get("商品标签")));
                    productImportModel.setPdtag(pdtag);

                    //@ApiModelProperty(notes = "SEO标题")
                    String seoTitle = this.getValue(row.getCell(map.get("SEO标题")));
                    productImportModel.setSeoTitle(seoTitle);

                    //@ApiModelProperty(notes = "SEO关键字")
                    String seoKey = this.getValue(row.getCell(map.get("SEO关键字")));
                    productImportModel.setSeoKey(seoKey);

                    //@ApiModelProperty(notes = "SEO描述")
                    String seoDescription = this.getValue(row.getCell(map.get("SEO描述")));
                    productImportModel.setSeoDescription(seoDescription);

                    list.add(productImportModel);
                }
            }
            return list;
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }finally {
            if(workbook != null){
                workbook.close();
            }
        }
        return list;
    }

    /**
     * 后台发布商品库
     * @param URL
     * @return
     * @throws IOException
     */
    public ArrayList<ProductStoreModel> excelProductTo(String URL) throws Exception {
        //创建list集合存放对象
        ArrayList<ProductStoreModel> list = new ArrayList<ProductStoreModel>();

        File file = new File(URL);

        Workbook workbook=null;
        try {
            workbook = WorkbookFactory.create(file);
            //读取默认第一个工作表sheet
            Sheet sheet =  workbook.getSheetAt(0);
            //获取sheet中最后一行行号
            int lastRowNum = sheet.getLastRowNum();

            if(lastRowNum<1){
                throw  new RuntimeException("模版不正确");
            }

            //紧固件分类	材质	牌号	商品名称	商品编号	商品别名	紧固件属性名和值	品牌	印记	计量单位	包装方式	表面处理	重量(kg)
            Map<String,Integer> map = new HashMap<>();

            Row titleRow =  sheet.getRow(0);
            int cellNum = titleRow.getLastCellNum();
            for(int i=0;i<cellNum;i++){
               map.put(titleRow.getCell(i).getStringCellValue(),i);
            }

            Set<String> keySet = map.keySet();
            if(!keySet.contains("紧固件分类") ||
                    !keySet.contains("材质") ||
                    !keySet.contains("牌号") ||
                    !keySet.contains("商品名称") ||
                    !keySet.contains("商品别名") ||
                    !keySet.contains("紧固件属性名和值") ||
                    !keySet.contains("品牌") ||
                    !keySet.contains("印记") ||
                    !keySet.contains("计量单位") ||
                    !keySet.contains("包装方式") ||
                    !keySet.contains("表面处理") ||
                    !keySet.contains("重量(kg)")){
                throw  new Exception("模版不正确，紧固件分类、材质、牌号、商品名称、商品编号、商品别名、紧固件属性名和值、品牌、印记、计量单位、包装方式、表面处理、重量(kg)");
            }

            //循环所有行
            for (int i = 1; i <= lastRowNum; i++) {
                //获取当前行中的内容
                Row row = sheet.getRow(i);
                short cell = row.getLastCellNum();
                ProductStoreModel productStoreModel=new ProductStoreModel();
                if(row !=null && cell!=0){
                    //@ApiModelProperty(notes = "分类")
                    String category=this.getValue(row.getCell(map.get("紧固件分类")));
                    if(category == null){
                        throw  new RuntimeException("第"+ (i+1) +"紧固件分类必须填写");
                    }
                    productStoreModel.setLevel(category.trim());

                    //@ApiModelProperty(notes = "材质")
                    String material=row.getCell(map.get("材质")).getStringCellValue();
                    if(material == null){
                        throw  new RuntimeException("第"+ (i+1) +"材质必须填写");
                    }
                    productStoreModel.setMaterial(material.trim());


                    //@ApiModelProperty(notes = "牌号")
                    String cardNum = this.getValue(row.getCell(map.get("牌号")));
                    if(cardNum == null){
                        throw  new RuntimeException("第"+ (i+1) +"牌号必须填写");
                    }else if(StringUtils.isNumeric(cardNum) && cardNum.endsWith(".0")){
                        cardNum =  cardNum.substring(0,cardNum.length()-2);
                    }
                    productStoreModel.setCardnum(cardNum.trim());

                    //@ApiModelProperty(notes = "名称")
                    String pdName = this.getValue(row.getCell(map.get("商品名称")));
                    if(pdName == null){
                        throw  new RuntimeException("第"+ (i+1) +"商品名称必须填写");
                    }

                    productStoreModel.setProductname(pdName.trim());

                    //@ApiModelProperty(notes = "别名")

                        String pdotherName = this.getValue(row.getCell(map.get("商品别名")));
                        productStoreModel.setAlias(pdotherName);



                    //@ApiModelProperty(notes = "属性名和值")
                    String attrNameValue = this.getValue(row.getCell(map.get("紧固件属性名和值")));
                    productStoreModel.setAttr(attrNameValue);


                    //@ApiModelProperty(notes = "品牌")
                    String brand = row.getCell(map.get("品牌")).getStringCellValue();

                    if(brand == null){
                        throw  new RuntimeException("第"+ (i+1) +"品牌必须填写");
                    }

                    productStoreModel.setBrand(brand.trim());
                    //@ApiModelProperty(notes = "印记")
                    String mark = getValue(row.getCell(map.get("印记")));
                    productStoreModel.setMark(mark);

                    //@ApiModelProperty(notes = "计量单位")
                    String unit = this.getValue(row.getCell(map.get("计量单位")));
                    if(unit == null){
                        throw  new RuntimeException("第"+ (i+1) +"计量单位必须填写");
                    }
                    productStoreModel.setUnit(unit.trim());

                    //@ApiModelProperty(notes = "包装方式")
                    String packageType = this.getValue(row.getCell(map.get("包装方式")));
                    productStoreModel.setPackagetype(packageType.trim());

                    //@ApiModelProperty(notes = "表面处理")
                    String surface = this.getValue(row.getCell(map.get("表面处理")));
                    productStoreModel.setSuffer(surface.trim());

                    //@ApiModelProperty(notes = "重量")
                    String weightString = this.getValue(row.getCell(map.get("重量(kg)")));
                    if(weightString == null ){
                        throw  new Exception("第"+ (i+1) +"行重量必须填写");
                    }

                    if(!StringUtils.isNumeric(weightString)){
                        throw  new Exception("第"+ (i+1) +"行 重量请填写数字");
                    }
                    productStoreModel.setWeight(weightString.trim());

                    list.add(productStoreModel);
                }
            }
            return list;
        } catch (InvalidFormatException e) {
            e.printStackTrace();
            throw e;
        }finally {
            if(workbook != null){
                workbook.close();
            }
        }
       // return null;
    }

    //取单元格中的值
    public String getValue(Cell cell){
        String result="";

        if(cell == null){
            return  null;
        }

        if(cell.getCellType() == cell.CELL_TYPE_BOOLEAN){
            //返回布尔类型的值
            result = cell.getBooleanCellValue() +"";
        }else if(cell.getCellType() == cell.CELL_TYPE_NUMERIC){
            //返回数值类型的值
            if(HSSFDateUtil.isCellDateFormatted(cell)){
                result = DateUtil.getJavaDate(cell.getNumericCellValue()).toString();
            }else{
                result = cell.getNumericCellValue()+"";
            }
            return String.valueOf(cell.getNumericCellValue());
        }else if(cell.getCellType() == cell.CELL_TYPE_FORMULA){
            result = cell.getCellFormula();
        }else if(cell.getCellType() == cell.CELL_TYPE_STRING){
            result = cell.getStringCellValue();
        }else{
            //返回字符口串类型的值
            result = cell.getStringCellValue();
        }
        return result;
    }


    /**
     * 后台发布商品库
     * @param URL
     * @return
     * @throws IOException
     */
    public List<ProductStore> excelDownProductTo(String URL) throws Exception {
        //创建list集合存放对象
        ArrayList<ProductStore> list = new ArrayList<ProductStore>();

        File file = new File(URL);

        Workbook workbook=null;
        try {
            workbook = WorkbookFactory.create(file);
            //读取默认第一个工作表sheet
            Sheet sheet =  workbook.getSheetAt(0);
            //获取sheet中最后一行行号
            int lastRowNum = sheet.getLastRowNum();

            if(lastRowNum<2){
                throw  new RuntimeException("模版不正确");
            }

            //紧固件分类	材质	牌号	商品名称	商品编号	商品别名	紧固件属性名和值	品牌	印记	计量单位	包装方式	表面处理	重量(kg)
            Map<String,Integer> map = new HashMap<>();

            Row titleRow =  sheet.getRow(1);
            int cellNum = titleRow.getLastCellNum();
            for(int i=0;i<cellNum;i++){
                map.put(titleRow.getCell(i).getStringCellValue(),i);
            }

            Set<String> keySet = map.keySet();
            if(!keySet.contains("商品库存id") ||
                    !keySet.contains("商品id") ||
                    !keySet.contains("紧固件编码") ||
                    !keySet.contains("品名") ||
                    !keySet.contains("规格") ||
                    !keySet.contains("商品价格") ||
                    !keySet.contains("30天发货价格") ||
                    !keySet.contains("60天发货价格") ||
                    !keySet.contains("90天发货价格") ||
                    !keySet.contains("仓库名称")){
                throw  new Exception("模版不正确，商品库存id、商品id、紧固件编码、品名、规格、商品价格、30天发货价格、60天发货价格、90天发货价格、仓库名称");
            }

            //循环所有行
            for (int i = 2; i <= lastRowNum; i++) {
                //获取当前行中的内容
                Row row = sheet.getRow(i);
                short cell = row.getLastCellNum();
                ProductStore productStore=new ProductStore();
                if(row !=null && cell!=0){
                    //@ApiModelProperty(notes = "分类")
                    String pdstoreid=this.getValue(row.getCell(map.get("商品库存id")));
                    if(pdstoreid == null){
                        throw  new RuntimeException("第"+ (i+1) +"商品库存id");
                    }
                    String pdid = this.getValue(row.getCell(map.get("商品id")));
                    if(pdid==null){
                        throw  new RuntimeException("第"+ (i+1) +"商品id");
                    }
                    if(isInteger(pdstoreid)){
                        productStore.setId(new BigDecimal(pdstoreid).longValue());
                    }
                    if(isInteger(pdid)){
                        productStore.setPdid(new BigDecimal(pdid).longValue());
                    }

                    String pdno=this.getValue(row.getCell(map.get("紧固件编码")));
                    if(StringUtils.hasText(pdno)){
                        productStore.setPdno(pdno);
                    }

                    String price = this.getValue(row.getCell(map.get("商品价格")));
                    if(isInteger(price)){
                        productStore.setProdprice(new BigDecimal(price));

                    }

                    String thirtyPrice = this.getValue(row.getCell(map.get("30天发货价格")));
                    if(isInteger(thirtyPrice)){
                        productStore.setThirtyprice(new BigDecimal(thirtyPrice));
                    }
                    String sixtyPrice = this.getValue(row.getCell(map.get("60天发货价格")));
                    if(isInteger(sixtyPrice)){
                        productStore.setSixtyprice(new BigDecimal(sixtyPrice));
                    }
                    String nintyPrice = this.getValue(row.getCell(map.get("90天发货价格")));
                    if(isInteger(nintyPrice)){
                        productStore.setNinetyprice(new BigDecimal(nintyPrice));
                    }
                    list.add(productStore);
                }
            }
            return list;
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }finally {
            if(workbook != null){
                workbook.close();
            }
        }
        return null;
    }

    /**
     * 后台发布商品库(其他商品)
     * @param URL
     * @return
     * @throws IOException
     */
    public List<ProductStore> excelDownOtherProductTo(String URL) throws Exception {
        //创建list集合存放对象
        ArrayList<ProductStore> list = new ArrayList<ProductStore>();

        File file = new File(URL);

        Workbook workbook=null;
        try {
            workbook = WorkbookFactory.create(file);
            //读取默认第一个工作表sheet
            Sheet sheet =  workbook.getSheetAt(0);
            //获取sheet中最后一行行号
            int lastRowNum = sheet.getLastRowNum();

            if(lastRowNum<2){
                throw  new RuntimeException("模版不正确");
            }

            Map<String,Integer> map = new HashMap<>();

            Row titleRow =  sheet.getRow(1);
            int cellNum = titleRow.getLastCellNum();
            for(int i=0;i<cellNum;i++){
                map.put(titleRow.getCell(i).getStringCellValue(),i);
            }

            Set<String> keySet = map.keySet();
            if(!keySet.contains("商品库存id") ||
                    !keySet.contains("商品id") ||
                    !keySet.contains("商品编码") ||
                    !keySet.contains("规格") ||
                    !keySet.contains("商品价格") ||
                    !keySet.contains("30天发货价格") ||
                    !keySet.contains("60天发货价格") ||
                    !keySet.contains("90天发货价格") ||
                    !keySet.contains("仓库名称")){
                throw  new Exception("模版不正确，商品库存id、商品id、商品编码、规格、商品价格、30天发货价格、60天发货价格、90天发货价格、仓库名称");
            }

            //循环所有行
            for (int i = 2; i <= lastRowNum; i++) {
                //获取当前行中的内容
                Row row = sheet.getRow(i);
                short cell = row.getLastCellNum();
                ProductStore productStore=new ProductStore();
                if(row !=null && cell!=0){
                    //@ApiModelProperty(notes = "分类")
                    String pdstoreid=this.getValue(row.getCell(map.get("商品库存id")));
                    if(pdstoreid == null){
                        throw  new RuntimeException("第"+ (i+1) +"商品库存id");
                    }
                    String pdid = this.getValue(row.getCell(map.get("商品id")));
                    if(pdid==null){
                        throw  new RuntimeException("第"+ (i+1) +"商品id");
                    }
                    if(isInteger(pdstoreid)){
                        productStore.setId(new BigDecimal(pdstoreid).longValue());
                    }
                    if(isInteger(pdid)){
                        productStore.setPdid(new BigDecimal(pdid).longValue());
                    }

                    String pdno=this.getValue(row.getCell(map.get("商品编码")));
                    if(StringUtils.hasText(pdno)){
                        productStore.setPdno(pdno);
                    }

                    String price = this.getValue(row.getCell(map.get("商品价格")));
                    if(isInteger(price)){
                        productStore.setProdprice(new BigDecimal(price));

                    }

                    String thirtyPrice = this.getValue(row.getCell(map.get("30天发货价格")));
                    if(isInteger(thirtyPrice)){
                        productStore.setThirtyprice(new BigDecimal(thirtyPrice));
                    }
                    String sixtyPrice = this.getValue(row.getCell(map.get("60天发货价格")));
                    if(isInteger(sixtyPrice)){
                        productStore.setSixtyprice(new BigDecimal(sixtyPrice));
                    }
                    String nintyPrice = this.getValue(row.getCell(map.get("90天发货价格")));
                    if(isInteger(nintyPrice)){
                        productStore.setNinetyprice(new BigDecimal(nintyPrice));
                    }
                    list.add(productStore);
                }
            }
            return list;
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }finally {
            if(workbook != null){
                workbook.close();
            }
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        ProductBatchImport excelToList=new ProductBatchImport();
        List<ProductStore> list =excelToList.excelDownProductTo("/Users/jacky/Downloads/产品列表.xlsx");
        System.out.println(list.size());
    }


    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]+)?$");
        return pattern.matcher(str).matches();
    }




}