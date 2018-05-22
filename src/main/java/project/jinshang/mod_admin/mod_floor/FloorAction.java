package project.jinshang.mod_admin.mod_floor;


import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.MemberLogOperator;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.utils.CommonUtils;
import project.jinshang.common.utils.ProductCategoryUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_admin.mod_floor.bean.Floor;
import project.jinshang.mod_admin.mod_floor.bean.FloorProd;
import project.jinshang.mod_admin.mod_floor.bean.FloorProdCate;
import project.jinshang.mod_admin.mod_floor.bean.FloorViewDto;
import project.jinshang.mod_admin.mod_floor.service.FloorService;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_product.bean.Categories;
import project.jinshang.mod_product.service.CategoriesService;
import project.jinshang.mod_product.service.MemberOperateLogService;
import project.jinshang.mod_product.service.ProductInfoService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.*;

@RestController
@RequestMapping(value = "/rest/admin/floor")
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
@Api(tags = "后台楼层设置", description = "首页楼层设置，商品排序设置")
public class FloorAction {

    @Resource
    private FloorService floorService;

    @Resource
    private CategoriesService categoriesService;

    @Autowired
    private ProductInfoService productInfoService;


    @Autowired
    protected MemberLogOperator memberLogOperator;

    @Autowired
    private MemberOperateLogService memberOperateLogService;


    @PostMapping("/add")
    @ApiOperation("添加楼层")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "楼层名称", name = "floorname", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "楼层分类1id", name = "level1id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(value = "楼层分类2id", name = "level2id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(value = "楼层副标题名称", name = "floorsubname", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "楼层主题图片", name = "subjectimg", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "楼层主题图片链接", name = "subjectimgurl", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "楼层主题分类 二级分类id数组", name = "categoryarrays", required = true, paramType = "query", dataType = "array"),
            @ApiImplicitParam(value = "楼层商品列表 Json [{\"img\":\"http://www.baidu.com/1.png\",\"catename\":\"螺母\",\"cateid\":100,\"type\":\"紧固件\"}]", name = "floorproducts", required = true, paramType = "query", dataType = "string"),
            //@ApiImplicitParam(value = "楼层商品列表url",name ="floorproductsurl",required =true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(value = "楼层排行榜名称", name = "rankingname", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "楼层排行榜是否开启 0=开启，1=停止", name = "rankingstop", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(value = "楼层排行榜商品json [{\"id\":23,\"salenum\":111}]", name = "rankingprodjson", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "楼层颜色", name = "floorcolor", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "是否显示 0=不显示，1=显示", name = "isshow", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(value = "排序", name = "sort", required = true, paramType = "query", dataType = "string"),
    })
    public BasicRet add(Floor floor, @RequestParam(required = true) Long[] categoryarrays, Model model, HttpServletRequest request) {
        BasicRet basicRet = new BasicRet();
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        floor.setCategoryarray(categoryarrays);

        if (!StringUtils.hasText(floor.getFloorproducts())) {
            return new BasicRet(BasicRet.ERR, "楼层商品不可为空");
        }

        if (!CommonUtils.isGoodJson(floor.getFloorproducts())) {
            return new BasicRet(BasicRet.ERR, "楼层商品json格式不对");
        }

        Gson gson = new Gson();
        List<FloorProdCate> floorProdCates = gson.fromJson(floor.getFloorproducts(), new TypeToken<List<FloorProdCate>>() {
        }.getType());
        for (FloorProdCate cate : floorProdCates) {

            Categories categories = categoriesService.getById(cate.getCateid());
            if (categories == null) {
                return new BasicRet(BasicRet.ERR, "楼层分类不存在");
            }
            cate.setCatename(categories.getName());

            if (!StringUtils.hasText(cate.getImg()) || cate.getCateid() == null) {
                return new BasicRet(BasicRet.ERR, "楼层商品必须上传图片和选择分类");
            }
        }

        floor.setFloorproducts(gson.toJson(floorProdCates));

        floorService.insertSelective(floor);


        //保存日志
        memberLogOperator.saveMemberLog(null, admin, "添加首页楼层:" + floor.getFloorname(), request, memberOperateLogService);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("添加成功");
        return basicRet;
    }


    @PostMapping("/update")
    @ApiOperation("修改楼层")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id", name = "id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(value = "楼层名称", name = "floorname", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "楼层分类1id", name = "level1id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(value = "楼层分类2id", name = "level2id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(value = "楼层副标题名称", name = "floorsubname", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "楼层主题图片", name = "subjectimg", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "楼层主题图片链接", name = "subjectimgurl", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "楼层主题分类 二级分类id数组", name = "categoryarrays", required = true, paramType = "query", dataType = "array"),
            @ApiImplicitParam(value = "楼层商品列表 Json [{\"img\":\"http://www.baidu.com/1.png\",\"catename\":\"\",\"cateid\":100,\"type\":\"紧固件\"}]", name = "floorproducts", required = true, paramType = "query", dataType = "string"),
            //@ApiImplicitParam(value = "楼层商品列表url",name ="floorproductsurl",required =true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(value = "楼层排行榜名称", name = "rankingname", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "楼层排行榜是否开启 0=开启，1=停止", name = "rankingstop", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(value = "楼层排行榜商品json [{\"id\":23,\"salenum\":111}]", name = "rankingprodjson", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "楼层颜色", name = "floorcolor", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(value = "是否显示 0=不显示，1=显示", name = "isshow", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(value = "排序", name = "sort", required = true, paramType = "query", dataType = "string"),
    })
    public BasicRet update(Floor floor, @RequestParam(required = true) Long[] categoryarrays, Model model, HttpServletRequest request) {
        BasicRet basicRet = new BasicRet();
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        floor.setCategoryarray(categoryarrays);

        if (!StringUtils.hasText(floor.getFloorproducts())) {
            return new BasicRet(BasicRet.ERR, "楼层商品不可为空");
        }

        if (!CommonUtils.isGoodJson(floor.getFloorproducts())) {
            return new BasicRet(BasicRet.ERR, "楼层商品json格式不对");
        }

        Gson gson = new Gson();
        List<FloorProdCate> floorProdCates = gson.fromJson(floor.getFloorproducts(), new TypeToken<List<FloorProdCate>>() {
        }.getType());
        for (FloorProdCate cate : floorProdCates) {

            Categories categories = categoriesService.getById(cate.getCateid());
            if (categories == null) {
                return new BasicRet(BasicRet.ERR, "楼层分类不存在");
            }
            cate.setCatename(categories.getName());

            if (!StringUtils.hasText(cate.getImg()) || cate.getCateid() == null) {
                return new BasicRet(BasicRet.ERR, "楼层商品必须上传图片和选择分类");
            }
        }

        floor.setFloorproducts(gson.toJson(floorProdCates));

        floorService.updateByPrimaryKeySelective(floor);


        //保存日志
        memberLogOperator.saveMemberLog(null, admin, "修改首页楼层:" + floor.getFloorname(), request, memberOperateLogService);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("修改成功");
        return basicRet;
    }


    @PostMapping("/delete")
    @ApiOperation("删除楼层")
    public BasicRet delete(@RequestParam(required = true) long id, Model model, HttpServletRequest request) {
        BasicRet basicRet = new BasicRet();
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);

        Floor floor = floorService.getById(id);
        if (floor == null) {
            return new BasicRet(BasicRet.ERR, "楼层不存在");
        }

        floorService.deleteById(id);

        //保存日志
        memberLogOperator.saveMemberLog(null, admin, "删除首页楼层:" + floor.getFloorname(), request, memberOperateLogService);

        basicRet.setMessage("删除成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }


    @PostMapping("/list")
    @ApiOperation("楼层列表")
    public PageRet list(@RequestParam(required = true, defaultValue = "1") int pageNo,
                        @RequestParam(required = true, defaultValue = "10") int pageSize) {
        PageRet pageRet = new PageRet();
        PageInfo pageInfo = floorService.listByPage(pageNo, pageSize);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }


    @PostMapping("/detail")
    @ApiOperation("楼层详细信息")
    public FloorDetailRet detail(@RequestParam(required = true) long id) {
        FloorDetailRet floorDetailRet = new FloorDetailRet();
        Floor floor = floorService.getById(id);

        if (floor == null) {
            floorDetailRet.setResult(BasicRet.ERR);
            floorDetailRet.setMessage("楼层不存在");
            return floorDetailRet;
        }

        FloorViewDto floorViewDto = new FloorViewDto();
        BeanUtils.copyProperties(floor, floorViewDto);


        Long[] cateArr = (Long[]) floorViewDto.getCategoryarray();
        List<Map<String, Object>> floorCategories = new ArrayList<>();
        for (Long cateid : cateArr) {
            Categories categories = categoriesService.getById(cateid);
            if (categories == null) continue;
            Map<String, Object> map = new HashMap<>();
            map.put("id", categories.getId());
            map.put("name", categories.getName());
            floorCategories.add(map);
        }
        floorViewDto.setFloorCategories(floorCategories);


        //查询楼层商品列表
//        List<Map<String,Object>> floorProducts =  new ArrayList<>();
//        if(floorViewDto.getFloorproducts() != null && !"".equals(floorViewDto.getFloorproducts())) {
//            floorProducts =  floorService.getFloorproducts(floorViewDto.getFloorproducts());
//        }
//        floorViewDto.setFloorproductsList(floorProducts);


        Gson gson = new Gson();
        List<FloorProd> floorPrads = new ArrayList<>();
        if (CommonUtils.isGoodJson(floor.getFloorproducts())) {
            floorPrads = gson.fromJson(floor.getFloorproducts(), new TypeToken<List<FloorProd>>() {
            }.getType());
        }

        List<Categories> productCategoryList = categoriesService.getAll();
        if (floorPrads != null) {
            for (FloorProd prod : floorPrads) {
                List<Categories> resultList = new ArrayList<>();
                ProductCategoryUtils.get_parent_list(productCategoryList, prod.getCateid(), resultList);
                List<Long> cateidpath = new ArrayList<>();
                for (Categories cate : resultList) {
                    cateidpath.add(cate.getId());
                }
                Collections.reverse(cateidpath);
                prod.setCateidpath(cateidpath.toArray());

            }
        }



        floorViewDto.setFloorProds(floorPrads);


        //排行榜列表
        List<Map<String, Object>> rankingprodList = new ArrayList<>();
        String rankingprodJson = floorViewDto.getRankingprodjson();

        List<Map<String, String>> topList = gson.fromJson(rankingprodJson, new TypeToken<List<Map<String, String>>>() {
        }.getType());

        if (topList != null) {
            for (Map<String, String> map : topList) {

                Map<String, Object> prodMap = floorService.getRankingprod(new Long(map.get("id")));
                if (prodMap != null) {
                    prodMap.put("salenum", map.get("salenum"));
                    rankingprodList.add(prodMap);
                }

            }
        }

        floorViewDto.setRankingprodList(rankingprodList);


        floorDetailRet.floor = floorViewDto;
        floorDetailRet.setResult(BasicRet.SUCCESS);

        return floorDetailRet;
    }


    @PostMapping("/getFloorProducts")
    @ApiOperation("查询楼层排行榜商品")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "商品编码", name = "pdno", paramType = "query"),
            @ApiImplicitParam(value = "商机用户名", name = "username", paramType = "query"),
            @ApiImplicitParam(value = "品牌", name = "brand", paramType = "query"),
            @ApiImplicitParam(value = "品名", name = "productname", paramType = "query"),
            @ApiImplicitParam(value = "规格", name = "stand", paramType = "query"),
            @ApiImplicitParam(value = "仓库", name = "store", paramType = "query"),
    })
    public ShopSelfProdRet getFloorProducts(@RequestParam(required = false) String pdno,
                                            @RequestParam(required = false) String username,
                                            @RequestParam(required = false) String brand,
                                            @RequestParam(required = false) String productname,
                                            @RequestParam(required = false) String stand,
                                            @RequestParam(required = false) String store) {
        ShopSelfProdRet shopSelfProdRet = new ShopSelfProdRet();

        List<Map<String, Object>> prodInfo = productInfoService.getFloorProducts(pdno, username, brand, productname, stand, store);

        if (prodInfo == null) {
            shopSelfProdRet.setResult(BasicRet.ERR);
            shopSelfProdRet.setMessage("未查询到商品");
            return shopSelfProdRet;
        }

        shopSelfProdRet.data.prodlist = prodInfo;

        shopSelfProdRet.setResult(BasicRet.SUCCESS);

        return shopSelfProdRet;
    }



/*
    @PostMapping("/getShopSelfProd")
    @ApiOperation("根据产品编号获取自营店产品信息")
    public  ShopSelfProdRet getShopSelfProd(@RequestParam(required = true) String pdno){
        ShopSelfProdRet shopSelfProdRet =  new ShopSelfProdRet();

        Map<String,Object> prodInfo = productInfoService.getShopSelfProdByPdno(pdno,new Long(shopSelfSupportid));

        if(prodInfo == null){
            shopSelfProdRet.setResult(BasicRet.ERR);
            shopSelfProdRet.setMessage("未查询到商品");
            return  shopSelfProdRet;
        }

        shopSelfProdRet.data = prodInfo;

        shopSelfProdRet.setResult(BasicRet.SUCCESS);

        return  shopSelfProdRet;
    }
*/

    private class ShopSelfProdRet extends BasicRet {
        private class ShopSelfProdData {
            private List<Map<String, Object>> prodlist;

            public List<Map<String, Object>> getProdlist() {
                return prodlist;
            }

            public void setProdlist(List<Map<String, Object>> prodlist) {
                this.prodlist = prodlist;
            }
        }

        private ShopSelfProdData data = new ShopSelfProdData();

        public ShopSelfProdData getData() {
            return data;
        }

        public void setData(ShopSelfProdData data) {
            this.data = data;
        }
    }

    private class FloorDetailRet extends BasicRet {
        private FloorViewDto floor;

        public FloorViewDto getFloor() {
            return floor;
        }

        public void setFloor(FloorViewDto floor) {
            this.floor = floor;
        }
    }

}
