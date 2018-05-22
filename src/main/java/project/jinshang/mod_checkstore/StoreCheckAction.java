package project.jinshang.mod_checkstore;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.*;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.jinshang.common.bean.Page;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.*;
import project.jinshang.mod_checkstore.bean.StoreCheck;
import project.jinshang.mod_front.service.ProductFrontService;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.bean.MemberRateSetting;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_checkstore.service.StoreCheckService;
import project.jinshang.mod_checkstore.bean.ProductStoreCheck;
import project.jinshang.mod_product.bean.OrderQueryParam;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Transactional(rollbackFor = Exception.class)
@Api(tags = "盘库小插件", description = "紧固件搜索接口")
@RequestMapping("/rest/admin/checkStore")
public class StoreCheckAction {

    @Autowired
    private MemberService memberService;

    @Autowired
    private StoreCheckService storeCheckService;

    @Autowired
    private ProductFrontService productFrontService;

    @Value("${shop.productstore-check.id}")
    private String memberids;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation("紧固件产品搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "搜索词", name = "searchKey", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "1级分类", name = "level1", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "2级分类", name = "level2", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "3级分类", name = "level3", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "品名", name = "productname", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "品牌", name = "brand", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "牌号", name = "cardnum", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "材质", name = "material", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "表面处理", name = "surfacetreatment", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "属性搜索json [{\"attr\":\"长度\",\"value\":\"10\"}]", name = "searchJson", paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "排序方式 默认按规格降序 0=按规格升序，1=按规格降序", name = "sorttype", paramType = "query", dataType = "int"),
           /* @ApiImplicitParam(value = "是否自营 0=全部，1=自营", name = "selfsupport", paramType = "query", dataType = "int"),
            @ApiImplicitParam(value = "有库存 0=全部，1=有库存", name = "havestore", paramType = "query", dataType = "int"),
            @ApiImplicitParam(value = "是否有远期 0=全部，1=有远期", name = "forwardtime", paramType = "query", dataType = "int"),*/
    })
    public ProductListRet list(
            @RequestParam(required = true, defaultValue = "1") int pageNo,
            @RequestParam(required = true, defaultValue = "20") int pageSize,
            @RequestParam(required = false,defaultValue = "") String searchKey,
            @RequestParam(required = false) String level1,
            @RequestParam(required = false) String level2,
            @RequestParam(required = false) String level3,
            @RequestParam(required = false) String productname,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String cardnum,
            @RequestParam(required = false) String material,
            @RequestParam(required = false) String searchJson,
//            @RequestParam(required = false) String diameter,
            @RequestParam(required = false) String surfacetreatment,
            @RequestParam(required = false) Integer sorttype,
            /*@RequestParam(required = false,defaultValue = "0") Integer selfsupport,
            @RequestParam(required = false,defaultValue = "0") Integer havestore,
            @RequestParam(required = false,defaultValue = "0") Integer forwardtime,*/
            Model model) {
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        searchKey = searchKey.toLowerCase();

        if(member != null){
            member =  memberService.getMemberById(member.getId());
        }

        ProductListRet productListRet = new ProductListRet();

        int start = (pageNo - 1) * pageSize;


        if(!StringUtils.hasText(searchKey) && sorttype == null){
            sorttype = 0;
        }


        Map<String, Object> attrs = new HashMap<>();

        if (StringUtils.hasText(searchJson)) {
            Gson gson = new Gson();
            if (!CommonUtils.isGoodJson(searchJson)) {
                productListRet.setResult(BasicRet.ERR);
                productListRet.setMessage("searchJson格式不对");
                return productListRet;
            }

            List<Map<String, String>> attrsList = gson.fromJson(searchJson, new TypeToken<List<Map<String, String>>>() {
            }.getType());

            if (attrsList != null) {
                for (Map<String, String> jsonMap : attrsList) {
//                    String name = ProductSearchUtils.getName(jsonMap.get("attr"));
                    String name = jsonMap.get("attr");
                    if (name != null) {
                        attrs.put(name, jsonMap.get("value"));
                    }
                }
            }
        }

        int count = storeCheckService.countSearchWithKeys(StringUtils.nvl(searchKey), level1, level2, level3, productname, brand, cardnum, material,surfacetreatment, attrs,memberids);

        List<Map> list = null;
//        List<List<Map>> resultList = null;
        Map<String, Set> resultGroupAttr = null;
        Map<String, Set> resultGroupAttr2 = null;

        List<KeyValue> keyValues = new ArrayList<>();
        if (count > 0) {
            list = storeCheckService.searchWithKeys(StringUtils.nvl(searchKey), level1, level2, level3, productname, brand, cardnum, material, surfacetreatment, attrs, start, pageSize,memberids);
            if (list != null && list.size() > 0) {

                for (Map<String, Object> map : list) {
                    String pdno=map.get("pdno").toString();
                    int pdid= Integer.parseInt(map.get("id").toString());
                    List<ProductStoreCheck> checklist = storeCheckService.getProductStoreCheck(pdno,pdid);
                    map.put("checklist",checklist);
                    //System.out.println("checklist-------------------------:"+checklist);
                  /* for(String key:set){
                        Integer value=map.get(key);
                        System.out.println(key+"___"+value);
                    }*/

                  /*  for(ProductStoreCheck check: checklist){
                        map.put("check":check);
                    }*/
                   /* for (Map.Entry<String, Object> entry : map.entrySet()) {
                        System.out.println(entry.getKey() + ":" + entry.getValue());
                    }*/



                    /*MemberRateSetting memberRateSetting = null;
                    if (member != null && map.get("membersettingstate") != null && map.get("membersettingstate").equals(1) && member.getGradleid() != null) {
                        memberRateSetting = memberRateSettingService.getRecursiveSetting((Long) map.get("memberid"), (Long) map.get("level3id"), member.getGradleid());
                    }

                    if (memberRateSetting == null) {
                        memberRateSetting = new MemberRateSetting();
                        memberRateSetting.setRate(new BigDecimal(1));
                    }


                    List list1 = new ArrayList();
                    Map<String, Object> prodpriceMap = new HashMap<>();
                    Map<String, Object> threepriceMap = new HashMap<>();
                    Map<String, Object> ninetypriceMap = new HashMap<>();
                    Map<String, Object> thirtypriceMap = new HashMap<>();
                    Map<String, Object> sixtypriceMap = new HashMap<>();

                    if (map.get("prodprice") != null && ((BigDecimal)map.get("prodprice")).compareTo(Quantity.BIG_DECIMAL_0) >0 ) {
                        prodpriceMap.put("type", 0);
                        prodpriceMap.put("name", Quantity.LIJIFAHUO);
                        if(member != null) {
                            prodpriceMap.put("price", ((BigDecimal) map.get("prodprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                        }else{
                            prodpriceMap.put("price", ((BigDecimal) map.get("prodprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                        }
                        list1.add(prodpriceMap);
                    }


                    if (map.get("thirtyprice") != null && ((BigDecimal)map.get("thirtyprice")).compareTo(Quantity.BIG_DECIMAL_0) >0) {
                        thirtypriceMap.put("type", 30);
                        thirtypriceMap.put("name", Quantity.SANSHITIANFAHUO);
                        if(member != null) {
                            thirtypriceMap.put("price", ((BigDecimal) map.get("thirtyprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                        }else{
                            thirtypriceMap.put("price", ((BigDecimal) map.get("thirtyprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                        }
                        list1.add(thirtypriceMap);
                    }


                    if (map.get("sixtyprice") != null && ((BigDecimal)map.get("sixtyprice")).compareTo(Quantity.BIG_DECIMAL_0) >0) {
                        sixtypriceMap.put("type", 60);
                        sixtypriceMap.put("name", Quantity.LIUSHITIANFAHUO);
                        if(member != null) {
                            sixtypriceMap.put("price", ((BigDecimal) map.get("sixtyprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                        }else{
                            sixtypriceMap.put("price", ((BigDecimal) map.get("sixtyprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                        }

                        list1.add(sixtypriceMap);
                    }

                    if (map.get("ninetyprice") != null && ((BigDecimal)map.get("ninetyprice")).compareTo(Quantity.BIG_DECIMAL_0) >0) {
                        ninetypriceMap.put("type", 90);
                        ninetypriceMap.put("name", Quantity.JIUSHITIANFAHUO);
                        if(member != null) {
                            ninetypriceMap.put("price", ((BigDecimal) map.get("ninetyprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                        }else{
                            ninetypriceMap.put("price", ((BigDecimal) map.get("ninetyprice")).multiply(memberRateSetting.getRate()).setScale(5, BigDecimal.ROUND_HALF_UP));
                        }
                        list1.add(ninetypriceMap);
                    }

                    map.remove("threeprice");
                    map.remove("thirtyprice");
                    map.remove("sixtyprice");
                    map.remove("ninetyprice");


                    if(member != null) {
                        map.put("prices", list1);
                        map.put("showprice",true);
                    }else{
                        map.put("prices", list1);
                        map.put("showprice",false);
                    }

                    map.put("brandpic", map.get("pic"));
                    map.remove("pic");

                    //转换包装方式
                    List packageList = JinshangUtils.toCovertPacking(StringUtils.nvl(map.get("packagetype")));
                    map.put("packages", packageList);

                    map.put("packageStr",JinshangUtils.packageToString((String)map.get("packagetype"),(BigDecimal)map.get("startnum"),(String) map.get("unit")));*/
                }
                }

//            ProductGroup sort = new ProductGroup();
//            resultList = sort.group(list);
                List<Map> groupAttr = storeCheckService.fetchSearchKeys(StringUtils.nvl(searchKey), level1, level2, level3, productname, brand, cardnum, material, surfacetreatment, attrs);


                List<Map> groupAttr1 = new ArrayList<>();
                if (attrs == null || attrs.size() == 0) {
                    groupAttr1 = storeCheckService.fetchSearchAttrKeys(StringUtils.nvl(searchKey), level1, level2, level3, productname, brand, cardnum, material, surfacetreatment, attrs);
                } else {
                    groupAttr1 = storeCheckService.fetchSearchAttrKeysHashAttr(StringUtils.nvl(searchKey), level1, level2, level3, productname, brand, cardnum, material, surfacetreatment, attrs);
                }

                resultGroupAttr = productFrontService.groupAttr(groupAttr);

                //在选择品名后,需要返回对应品名的所有属性条件作为筛选项
                if (!StringUtils.hasText(productname)) {
                    resultGroupAttr2 = productFrontService.groupAttrInAttr(groupAttr1);
                } else {
                    resultGroupAttr2 = productFrontService.groupAttrInAttrbyProductname(groupAttr1);
                }


                resultGroupAttr.putAll(resultGroupAttr2);

                Set<String> keySet = resultGroupAttr.keySet();
                for (String key : keySet) {
                    KeyValue keyValue = new KeyValue();
                    keyValue.setKey(key);
                    keyValue.setValue(new ArrayList(resultGroupAttr.get(key)));
                    keyValue.setName(ProductSearchUtils.getName(key).replace("一级分类", "大类")
                            .replace("二级分类", "分类").replace("三级分类", "标准"));
                    keyValue.setSort(ProductSearchUtils.getSort(key));

                    if (key.equals("长度")) {
                        keyValue.value.sort((String v1, String v2) ->
                                StringUtils.floatValue(v1) > StringUtils.floatValue(v2) ? 1 : -1
                        );
                    }

                    if (key.equals("公称直径")) {
                        keyValue.value.sort((String::compareTo));
                    }


                    if (key.equals("level1")) {
                        keyValue.value.sort((String v1, String v2) -> {
                            return ProductSearchUtils.getBigSort(v1).compareTo(ProductSearchUtils.getBigSort(v2));
                        });
                    }

                    keyValues.add(keyValue);


                }
            }

        keyValues.sort((KeyValue kv1,KeyValue kv2)->kv1.getSort().compareTo(kv2.getSort()));
        Page page = new Page();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotalRows(count);
        page.setList(list);
        page.setTotalPages(count > 0 ? (count - 1) / pageSize + 1 : 0);

        productListRet.data.pageInfo = page;
        productListRet.data.keyValues = keyValues;

        productListRet.setResult(BasicRet.SUCCESS);

        return productListRet;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ApiOperation(value = "删除盘库信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "int")
    })
    public BasicRet delete(int id){
        BasicRet basicRet = new BasicRet();
        ProductStoreCheck psCheck = storeCheckService.getProductStoreCheckById(id);
        if(psCheck==null){
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该条信息");
            return basicRet;
        }
        storeCheckService.deleteProductStoreCheckById(id);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("删除成功");
        return basicRet;
    }

    @RequestMapping(value = "/addAndUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "添加、修改盘库信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pdid", value = "商品ID", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pdno", value = "商品编码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "storesite", value = "库位", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "unit", value = "单位", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "storenum", value = "库存", required = true,  dataType = "double", paramType = "query"),
            @ApiImplicitParam(name = "checkuser", value = "盘点人", required = false, dataType = "String", paramType = "query"),
            //@ApiImplicitParam(name = "checktime", value = "盘点时间", required = false, dataType = "Date", paramType = "query"),
            @ApiImplicitParam(name = "validateuser", value = "审核人", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "validatetime", value = "审核时间", required = false, dataType = "Date", paramType = "query"),
    })
    public BasicRet addAndUpdate(ProductStoreCheck psCheck){
        BasicRet basicRet = new BasicRet();
        Date d = new Date();
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        psCheck.setChecktime(d);
        int result = storeCheckService.addAndUpdateProductStoreCheck(psCheck);
        if(result>0) {
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("添加、修改成功");
        }else{
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("添加、修改失败");
        }
        return basicRet;
    }

    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    @ApiOperation(value = "导出盘库信息")
    public ResponseEntity<InputStreamResource> exportExcel(HttpServletResponse response, StoreCheck storeCheck, Model model) {

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        int pageNo = storeCheck.getPageNo();
        int pageSize = storeCheck.getPageSize();
        String searchKey = storeCheck.getSearchKey();
        String level1 = storeCheck.getLevel1();
        String level2 = storeCheck.getLevel2();
        String level3 = storeCheck.getLevel3();
        String productname = storeCheck.getProductname();
        String brand = storeCheck.getBrand();
        String cardnum = storeCheck.getCardnum();
        String material = storeCheck.getMaterial();
        String searchJson = storeCheck.getSearchJson();
        String surfacetreatment = storeCheck.getSurfacetreatment();

        searchKey = searchKey.toLowerCase();

        if (member != null) {
            member = memberService.getMemberById(member.getId());
        }

        int start = (pageNo - 1) * pageSize;


      /*  if (!StringUtils.hasText(searchKey) && sorttype == null) {
            sorttype = 0;
        }*/

        Map<String, Object> attrs = new HashMap<>();

        if (StringUtils.hasText(storeCheck.getSearchJson())) {
            Gson gson = new Gson();
            /*if (!CommonUtils.isGoodJson(searchJson)) {
                productListRet.setResult(BasicRet.ERR);
                productListRet.setMessage("searchJson格式不对");
                return productListRet;
            }*/

            List<Map<String, String>> attrsList = gson.fromJson(searchJson, new TypeToken<List<Map<String, String>>>() {
            }.getType());

            if (attrsList != null) {
                for (Map<String, String> jsonMap : attrsList) {
//                    String name = ProductSearchUtils.getName(jsonMap.get("attr"));
                    String name = jsonMap.get("attr");
                    if (name != null) {
                        attrs.put(name, jsonMap.get("value"));
                    }
                }
            }
        }

        int count = storeCheckService.countSearchWithKeys(StringUtils.nvl(searchKey), level1, level2, level3, productname, brand, cardnum, material, surfacetreatment, attrs, memberids);

        List<Map> list = null;
//        List<List<Map>> resultList = null;
        Map<String, Set> resultGroupAttr = null;
        Map<String, Set> resultGroupAttr2 = null;

        List<KeyValue> keyValues = new ArrayList<>();
        if (count > 0) {
            list = storeCheckService.searchWithKeys(StringUtils.nvl(searchKey), level1, level2, level3, productname, brand, cardnum, material, surfacetreatment, attrs, start, pageSize, memberids);
            if (list != null && list.size() > 0) {

                for (Map<String, Object> map : list) {
                    String pdno = map.get("pdno").toString();
                    int pdid = Integer.parseInt(map.get("id").toString());
                    List<ProductStoreCheck> checklist = storeCheckService.getProductStoreCheck(pdno, pdid);
                    map.put("checklist", checklist);
                }
            }
        }
        //System.out.println(list);
        XSSFWorkbook workbook = null;
        try {
        List<Map<String,Object>> allData = new ArrayList();
            if(list==null){
                return null;
            }
        for (Map<String,Object> mapdata: list){
            List<ProductStoreCheck> checkList = (List<ProductStoreCheck>)mapdata.get("checklist");
           if(checkList.size()>0) {
               for (ProductStoreCheck checkMap : checkList) {
                   Map map1 = new HashMap();
                   String pname = mapdata.get("brand") + "" + mapdata.get("level2") + "/" + mapdata.get("material") + "-" + mapdata.get("cardnum") + "/" + mapdata.get("level1") + "" + mapdata.get("level3") + "/" + mapdata.get("stand") + "/" + mapdata.get("surfacetreatment");
                   if (checkMap!=null) {

                       map1.put("品名", pname);

                       String py = mapdata.get("brand") + "" + mapdata.get("mark");

                       map1.put("品牌印记", py);

                       map1.put("包装方式", mapdata.get("packagetype"));

                       map1.put("商品编码", mapdata.get("pdno"));

                       map1.put("库位", checkMap.getStoresite());
                       map1.put("数量", checkMap.getStorenum());
                       map1.put("单位", checkMap.getUnit());
                       map1.put("盘点人", checkMap.getCheckuser());
                       map1.put("盘点时间", checkMap.getChecktime());
                       map1.put("审核人", checkMap.getValidateuser());
                       map1.put("审核时间", checkMap.getValidatetime());
                   }
                   allData.add(map1);
               }
           }else{
               Map map1 = new HashMap();
               map1.put("品名", mapdata.get("brand") + "" + mapdata.get("level2") + "/" + mapdata.get("material") + "-" + mapdata.get("cardnum") + "/" + mapdata.get("level1") + "" + mapdata.get("level3") + "/" + mapdata.get("stand") + "/" + mapdata.get("surfacetreatment"));
               map1.put("品牌印记", mapdata.get("brand") + "" + mapdata.get("mark"));
               map1.put("包装方式", mapdata.get("brand"));
               map1.put("商品编码", mapdata.get("pdno"));
               map1.put("库位", "");
               map1.put("数量", "");
               map1.put("单位", "");
               map1.put("盘点人", "");
               map1.put("盘点时间", "");
               map1.put("审核人", "");
               map1.put("审核时间", "");
               allData.add(map1);
           }
        }

            List<Map<String,Object>> newList = new ArrayList<Map<String,Object>>();
            for(int i=0;i<allData.size();i++){
                Map<String,Object> newMap = new HashMap<String,Object>();
                String flag=allData.get(i).get("品名").toString();
                if(newList.size()<1){
                    newMap.put("品名",allData.get(i).get("品名"));
                    newMap.put("品牌印记",allData.get(i).get("品牌印记"));
                    newMap.put("包装方式",allData.get(i).get("包装方式"));
                    newMap.put("商品编码",allData.get(i).get("商品编码"));
                }else if(!newList.get(i-1).get("品名").equals(allData.get(i).get("品名")) && !allData.get(i-1).get("品名").equals(flag)){
                    newMap.put("品名",allData.get(i).get("品名"));
                    newMap.put("品牌印记",allData.get(i).get("品牌印记"));
                    newMap.put("包装方式",allData.get(i).get("包装方式"));
                    newMap.put("商品编码",allData.get(i).get("商品编码"));
                }else if(allData.get(i).get("品名").equals(flag)){
                    newMap.put("品名","");
                    newMap.put("品牌印记","");
                    newMap.put("包装方式","");
                    newMap.put("商品编码","");

                }
                newMap.put("库位",allData.get(i).get("库位"));
                newMap.put("数量",allData.get(i).get("数量"));
                newMap.put("单位",allData.get(i).get("单位"));
                newMap.put("盘点人",allData.get(i).get("盘点人"));
                newMap.put("盘点时间",allData.get(i).get("盘点时间"));
                newMap.put("审核人",allData.get(i).get("审核人"));
                newMap.put("审核时间",allData.get(i).get("审核时间"));
                newList.add(newMap);
            }

            String[] rowTitles = new String[]{"品名", "品牌印记", "包装方式", "商品编码", "库位", "数量","单位", "盘点人", "盘点时间", "审核人", "审核时间" };
            String[] sumCols = new String[]{""};

            //List<Map<String, Object>> list = ordersService.getExcelOrders(param);
            workbook = ExcelGen.common("盘库列表", rowTitles, newList, sumCols);
            if (workbook != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);
                System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("盘库列表.xlsx".getBytes(), "iso-8859-1")));
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

    private class ProductListRet extends BasicRet {
        private class ProductListRetData {
            private Page pageInfo;
            private Map<String, Set> resultAttrGroup;

            public Page getPageInfo() {
                return pageInfo;
            }

//            public Map<String,Set> getResultAttrGroup() {
//                return resultAttrGroup;
//            }

            public Map<String,Object> searchMap;


            public List<KeyValue> keyValues;

            public void setPageInfo(Page pageInfo) {
                this.pageInfo = pageInfo;
            }

            public Map<String, Set> getResultAttrGroup() {
                return resultAttrGroup;
            }

            public void setResultAttrGroup(Map<String, Set> resultAttrGroup) {
                this.resultAttrGroup = resultAttrGroup;
            }

            public Map<String, Object> getSearchMap() {
                return searchMap;
            }

            public void setSearchMap(Map<String, Object> searchMap) {
                this.searchMap = searchMap;
            }

            public List<KeyValue> getKeyValues() {
                return keyValues;
            }

            public void setKeyValues(List<KeyValue> keyValues) {
                this.keyValues = keyValues;
            }
        }


        private ProductListRetData data = new ProductListRetData();

        public ProductListRetData getData() {
            return data;
        }

        public void setData(ProductListRetData data) {
            this.data = data;
        }
    }

    public class KeyValue {
        private String key;
        private List<String> value;
        private String name;
        private  Integer sort;

        public Integer getSort() {
            return sort;
        }

        public void setSort(Integer sort) {
            this.sort = sort;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List getValue() {
            return value;
        }

        public void setValue(List value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    }