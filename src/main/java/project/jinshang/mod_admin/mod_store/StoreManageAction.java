package project.jinshang.mod_admin.mod_store;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.utils.DateUtils;
import project.jinshang.common.utils.ExcelGen;
import project.jinshang.mod_admin.mod_store.bean.StoreManageQueryDto;
import project.jinshang.mod_admin.mod_store.service.StoreManageService;
import project.jinshang.mod_company.bean.SellerCompanyInfo;
import project.jinshang.mod_company.bean.Store;
import project.jinshang.mod_company.service.SellerCompanyInfoService;
import project.jinshang.mod_company.service.StoreService;
import project.jinshang.mod_product.bean.ProductInfo;
import project.jinshang.mod_product.bean.ProductInfoExample;
import project.jinshang.mod_product.bean.ProductStoreExample;
import project.jinshang.mod_product.service.ProductInfoService;
import project.jinshang.mod_product.service.ProductStoreService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * create : wyh
 * date : 2018/1/3
 */


@RestController
@Api(tags = "后台仓库管理")
@RequestMapping("/rest/admin/storeManage")
@Transactional(rollbackFor = Exception.class)
public class StoreManageAction {

    @Autowired
    private StoreManageService storeManageService;

    @Autowired
    private SellerCompanyInfoService sellerCompanyInfoService;

    @Autowired
    private  StoreService storeService;

    @Autowired
    private ProductStoreService productStoreService;

    @PostMapping("/manageList")
    @ApiOperation("仓库列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "仓库名",name = "name",dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "id",name = "id",dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "所属商家",name = "companyname",dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "创建时间",name = "createtimeStart",dataType = "date",paramType = "query"),
            @ApiImplicitParam(value = "创建时间",name = "createtimeEnd",dataType = "date",paramType = "query"),
    })
    public PageRet manageList(StoreManageQueryDto queryDto,
                              @RequestParam(required = true,defaultValue = "1") int pageNo,
                              @RequestParam(required = true,defaultValue = "20") int pageSize){
        PageRet pageRet =  new PageRet();
        if(queryDto.getCreatetimeEnd() != null){
            queryDto.setCreatetimeEnd(DateUtils.addDays(queryDto.getCreatetimeEnd(),1));
        }

        PageInfo pageInfo =   storeManageService.searchManageList(queryDto,pageNo,pageSize);
        pageRet.data.setPageInfo(pageInfo);

        pageRet.setResult(BasicRet.SUCCESS);

        return  pageRet;
    }



    @GetMapping("/excelExport/manageList")
    @ApiOperation("仓库列表导出")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "仓库名",name = "name",dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "id",name = "id",dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "所属商家",name = "companyname",dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "创建时间",name = "createtimeStart",dataType = "date",paramType = "query"),
            @ApiImplicitParam(value = "创建时间",name = "createtimeEnd",dataType = "date",paramType = "query"),
    })
    public ResponseEntity<InputStreamResource> manageList(StoreManageQueryDto queryDto){

        if(queryDto.getCreatetimeEnd() != null){
            queryDto.setCreatetimeEnd(DateUtils.addDays(queryDto.getCreatetimeEnd(),1));
        }

        //用于存放到excel的数据
        List<Map<String,Object>> data = new ArrayList<>();

        List<Map<String,Object>> list = storeManageService.searchManageListForExcel(queryDto);

        String[] rowTitles = new String[]{"仓库编码","仓库名","所属商家","创建时间","仓库所在地"};
        for(Map<String,Object> map : list){
            Map<String,Object> m1 = new HashMap<>();
            m1.put("仓库编码",map.get("id"));
            m1.put("仓库名",map.get("name"));
            m1.put("所属商家",map.get("companyname"));
            m1.put("创建时间",map.get("createtime"));
            m1.put("仓库所在地",map.get("address"));
            data.add(m1);
        }

        XSSFWorkbook workbook = null;
        try {
            workbook = ExcelGen.common("商家仓库列表",rowTitles,data,null);
            if(workbook!=null){
                ByteArrayOutputStream baos=new   ByteArrayOutputStream();
                workbook.write(baos);
                System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String("商家仓库列表.xlsx".getBytes(),"iso-8859-1")));
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


    /*
    try{
            List<Map<String,Object>> data = new ArrayList<>();
            String[] rowTitles = new String[]{"交易时间","汇款人名称","金额"};
            Map<String,Object> m1 = new HashMap<>();
            m1.put("交易时间", new Date());
            m1.put("汇款人名称","上海辛聪不锈钢制品有限公司");
            m1.put("金额",867.34);
            data.add(m1);
            data.add(m1);
            data.add(m1);
            XSSFWorkbook workbook = ExcelGen.common("紧商科技应收帐款财务管理、作业程序",rowTitles,data,new String[]{"金额"});
            if(workbook!=null){
//                FileOutputStream fileOutputStream = new FileOutputStream(new File("tmp.xlsx"));
//                workbook.write(fileOutputStream);
//                fileOutputStream.flush();
//                fileOutputStream.close();
                ByteArrayOutputStream   baos=new   ByteArrayOutputStream();
                workbook.write(baos);
                System.out.println(baos.toByteArray().length);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", "test.xlsx"));
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                String contentType = "application/vnd.ms-excel";
                return ResponseEntity.ok()
                        .headers(headers).contentType(MediaType.parseMediaType(contentType))
                        .body(new InputStreamResource(new ByteArrayInputStream(baos.toByteArray())));
            }
            return null;
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
     */




    @PostMapping("/addStore")
    @ApiOperation("添加仓库")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "仓库名",name = "name",dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "所属商家",name = "companyname",dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "省",name = "province",dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "市",name = "city",dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "区",name = "citysmall",dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "仓库所在地",name = "address",dataType = "string",paramType = "query"),
    })
    public  BasicRet addStore(@RequestParam(required = true) String name,
                              @RequestParam(required = true) String companyname,
                              @RequestParam(required = true) String province,
                              @RequestParam(required = true) String city,
                              @RequestParam(required = true) String citysmall,
                              @RequestParam(required = true) String address){
        BasicRet basicRet =  new BasicRet();

        SellerCompanyInfo sellerCompanyInfo = sellerCompanyInfoService.getByCompanyName(companyname);

        if(sellerCompanyInfo == null){
            return  new BasicRet(BasicRet.ERR,"商家不存在");
        }

        long memberId =  sellerCompanyInfo.getMemberid();

        Store store =  storeService.getByName(memberId,name);
        if(store != null){
            return  new BasicRet(BasicRet.ERR,"该卖家已经创建过一个同名的仓库了");
        }

//        List<Store> exisStores = storeService.getByName(name);
//        if(exisStores.size()>0){
//            return  new BasicRet(BasicRet.ERR,"该仓库名称已经存在，不可添加");
//        }


        store = new Store();
        store.setMemberid(memberId);
        store.setCreatetime(new Date());
        store.setProvince(province);
        store.setCity(city);
        store.setCitysmall(citysmall);
        store.setAddress(address);
        store.setName(name);

        storeService.add(store);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("添加成功");
        return  basicRet;
    }


    @PostMapping("/updateStore")
    @ApiOperation("添加仓库")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id",name = "id",dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "仓库名",name = "name",dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "省",name = "province",dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "市",name = "city",dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "区",name = "citysmall",dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "仓库所在地",name = "address",dataType = "string",paramType = "query"),
    })
    public  BasicRet updateStore(@RequestParam(required = true) long id,
                              @RequestParam(required = true) String name,
                                 @RequestParam(required = true) String province,
                                 @RequestParam(required = true) String city,
                                 @RequestParam(required = true) String citysmall,
                              @RequestParam(required = true) String address) {
        BasicRet basicRet = new BasicRet();

        Store store =  storeService.getById(id);
        if(store == null){
            return  new BasicRet(BasicRet.ERR,"要修改的仓库不存在");
        }

//        List<Store> exisStores = storeService.getByName(name);
//
//        if(exisStores.size()==1 && !exisStores.get(0).getName().equals(name)){
//            return  new BasicRet(BasicRet.ERR,"该仓库名称已经存在，不可添加");
//        }

        Store store1 =  storeService.getByName(store.getMemberid(),name);
        if(store1 != null){
            return  new BasicRet(BasicRet.ERR,"该卖家已经创建过一个同名的仓库了");
        }


        productStoreService.updateStorenameByStoreid(store.getId(),name);



        store.setName(name);
        store.setProvince(province);
        store.setCitysmall(citysmall);
        store.setCity(city);
        store.setAddress(address);

        storeService.updateByPrimaryKeySelective(store);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        return  basicRet;
    }



    @PostMapping("/delStore")
    @ApiOperation("删除仓库")
    public  BasicRet delStore(@RequestParam(required = true) long id){
        BasicRet basicRet = new BasicRet();

        ProductStoreExample example =  new ProductStoreExample();
        ProductStoreExample.Criteria criteria = example.createCriteria();
        criteria.andStoreidEqualTo(id);
        int count =  productStoreService.countByExample(example);

        if(count>0){
            return  new BasicRet(BasicRet.ERR,"有商品存放在该仓库下，不可删除");
        }

        storeService.delById(id);

        basicRet.setMessage("删除成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return  basicRet;
    }


    @PostMapping("/getStore")
    @ApiOperation("获取仓库信息")
    public StoreRet getStore(@RequestParam(required = true) long id){
        StoreRet storeRet = new StoreRet();

        Store store =  storeService.getById(id);
        if(store == null){
            storeRet.setResult(BasicRet.ERR);
            storeRet.setMessage("仓库不存在");
            return  storeRet;
        }

        SellerCompanyInfo companyInfo = sellerCompanyInfoService.getSellerCompanyByMemberid(store.getMemberid());
        if(companyInfo == null){
            storeRet.setMessage("仓库对应的公司不存在");
            storeRet.setResult(BasicRet.ERR);
            return  storeRet;
        }

        storeRet.companyInfo = companyInfo;
        storeRet.store = store;

        storeRet.setResult(BasicRet.SUCCESS);
        storeRet.setMessage("查询成功");
        return  storeRet;
    }

    private  class  StoreRet extends  BasicRet{
        private  Store store;
        private  SellerCompanyInfo companyInfo;

        public Store getStore() {
            return store;
        }

        public void setStore(Store store) {
            this.store = store;
        }

        public SellerCompanyInfo getCompanyInfo() {
            return companyInfo;
        }

        public void setCompanyInfo(SellerCompanyInfo companyInfo) {
            this.companyInfo = companyInfo;
        }
    }
}
