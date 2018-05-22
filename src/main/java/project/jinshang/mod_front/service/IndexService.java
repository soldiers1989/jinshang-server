package project.jinshang.mod_front.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.CommonUtils;
import project.jinshang.common.utils.JinshangUtils;
import project.jinshang.common.utils.ProductCategoryUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_admin.mod_floor.bean.Floor;
import project.jinshang.mod_admin.mod_floor.bean.FloorProd;
import project.jinshang.mod_admin.mod_floor.bean.FloorViewDto;
import project.jinshang.mod_admin.mod_floor.service.FloorService;
import project.jinshang.mod_admin.mod_showcategory.bean.ShowCate;
import project.jinshang.mod_admin.mod_showcategory.bean.ShowCatedetail;
import project.jinshang.mod_admin.mod_showcategory.bean.dto.ShowCategory;
import project.jinshang.mod_admin.mod_showcategory.service.ShowCategoryDetailService;
import project.jinshang.mod_admin.mod_showcategory.service.ShowCategoryService;
import project.jinshang.mod_front.bean.ShowCateFastenerDetail;
import project.jinshang.mod_front.bean.ShowCateFastenerLevel2;
import project.jinshang.mod_front.bean.ShowCateFrontView;
import project.jinshang.mod_front.bean.ShowCateOtherDetail;
import project.jinshang.mod_product.bean.Categories;
import project.jinshang.mod_product.bean.Material;
import project.jinshang.mod_product.service.CategoriesService;
import project.jinshang.mod_product.service.MaterialService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create : wyh
 * date : 2018/2/27
 */
@Service
public class IndexService {

    @Autowired
    private ShowCategoryService showCategoryService;

    @Autowired
    private ShowCategoryDetailService showCategoryDetailService;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private FloorService floorService;

    @Autowired
    private Gson gson;

    /**
     * 首页楼层
     * @return
     */
    public  List getFloor(){
        List<Floor> list =  floorService.getShowFloor();
        List<FloorViewDto> dtoList =  new ArrayList<>();

        for(Floor floor : list){
            FloorViewDto floorViewDto = new FloorViewDto();
            BeanUtils.copyProperties(floor,floorViewDto);


//            Categories floorCate =  categoriesService.getById(floor.getLevel1id());
//            if(floorCate == null) continue;
//            if(floorCate.getName().equals("紧固件")){
//                floorViewDto.setFloortype("紧固件");
//            }else{
//                floorViewDto.setFloortype("其它");
//            }

            Long[] cateArr = (Long[]) floorViewDto.getCategoryarray();

            if(cateArr == null || cateArr.length==0) continue;
            //获取顶级分类id
            Long topparentcateid= ProductCategoryUtils.get_top_parent_id(categoriesService.getAll(),cateArr[0]);
            Categories floorCate =  categoriesService.getById(topparentcateid);
            if(floorCate == null) continue;
            if(JinshangUtils.getCateType(floorCate.getName()).equals("紧固件")){
                floorViewDto.setFloortype("紧固件");
            }else{
                floorViewDto.setFloortype("其它");
            }

            List<Map<String,Object>> floorCategories = new ArrayList<>();
            for (Long cateid : cateArr){
                Categories categories =  categoriesService.getById(cateid);
                if(categories==null) continue;
                Map<String,Object> map = new HashMap<>();
                map.put("id",categories.getId());
                map.put("name",categories.getName());
                floorCategories.add(map);
            }
            floorViewDto.setFloorCategories(floorCategories);

            //查询楼层商品列表
//            List<Map<String,Object>> floorProducts =  new ArrayList<>();
//            if(StringUtils.hasText(floorViewDto.getFloorproducts())) {
//                floorProducts = floorService.getFloorproducts(floorViewDto.getFloorproducts());
//            }
//            floorViewDto.setFloorproductsList(floorProducts);

            List<FloorProd> floorPrads =  new ArrayList<>();
            if(CommonUtils.isGoodJson(floor.getFloorproducts())){
                floorPrads =gson.fromJson(floor.getFloorproducts(), new TypeToken<List<FloorProd>>() {}.getType());
            }

            for(FloorProd prod : floorPrads){
                Categories categories =  categoriesService.getById(prod.getCateid());
                if(categories == null) continue;
                prod.setType(categories.getCatetype());
            }

            floorViewDto.setFloorProds(floorPrads);

            //排行榜列表
            List<Map<String,Object>> rankingprodList = new ArrayList<>();

            if(floor.getRankingstop() == Quantity.STATE_1){  //楼层排行榜关闭，取真实数据
                rankingprodList =  floorService.getRealRankingprodList(floor.getLevel2id(),4);
            }else{
                String rankingprodJson = floorViewDto.getRankingprodjson();

                List<Map<String,String>> topList =  gson.fromJson(rankingprodJson, new TypeToken<List<Map<String,String>>>() {}.getType());

                if(topList != null){
                    for(Map<String,String> map : topList){
                        Map<String,Object> prodMap =  floorService.getRankingprod(new Long(map.get("id")));
                        if(prodMap != null){
                            prodMap.put("salenum",map.get("salenum"));
                            rankingprodList.add(prodMap);
                        }
                    }
                }
            }

            floorViewDto.setRankingprodList(rankingprodList);

            dtoList.add(floorViewDto);
        }

        return  dtoList;
    }




    /**
     * 展示类目
     * @return
     */
    public List<ShowCateFrontView> getShowCate(){
        List<ShowCateFrontView> list = new ArrayList<>();

        List<ShowCate> showCateList = showCategoryService.listAll();

        for(ShowCate showCate : showCateList){
            ShowCateFrontView showCateFrontView = new ShowCateFrontView();
            showCateFrontView.setShowCate(showCate);

            List<ShowCatedetail> showCatedetailList = showCategoryDetailService.getByShowId(showCate.getId());

            if("紧固件".equals(showCate.getType())){
                List<ShowCateFastenerDetail> showCateFastenerDetailList = new ArrayList<>();
                List<String> materialList = getMaterialList(showCatedetailList);

                //材质
                for(String material : materialList){
                    ShowCateFastenerDetail showCateFastenerDetail = new ShowCateFastenerDetail();
                    showCateFastenerDetail.setMaterial(material);

                    Material mat = materialService.getByName(material);
                    if(mat != null && StringUtils.hasText(mat.getImg())){
                        showCateFastenerDetail.setMaterialImg(mat.getImg());
                    }

                    List<ShowCatedetail> sameMateerialList = getByMaterial(showCatedetailList,material);

                    List<Long> level2List = getLevel2List(sameMateerialList);

                    List<ShowCateFastenerLevel2> showCateFastenerLevel2List = new ArrayList<>();
                    for(Long level2id : level2List ){
                        ShowCateFastenerLevel2 showCateFastenerLevel2 = new ShowCateFastenerLevel2();
                        showCateFastenerLevel2.setLevel2id(level2id);

                        Categories categories1 = categoriesService.getById(level2id);
                        if(categories1 == null) continue;
                        showCateFastenerLevel2.setLevel2category(categories1.getName());
                        List<ShowCatedetail> sameMaterialAndLevel2List = getByMaterialAndStand(showCatedetailList,material,level2id);

                        for(ShowCatedetail sameMaterialAndStand : sameMaterialAndLevel2List){
                            List<ShowCategory> showCategoryList = new ArrayList<>();
                            String[] cateidsArr = sameMaterialAndStand.getCategoryids().split(",");
                            if(cateidsArr != null && cateidsArr.length>0){
                                for(String catid : cateidsArr){
                                    if(StringUtils.hasText(catid)){
                                        Categories categories = categoriesService.getById(new Long(catid));
                                        ShowCategory showCategory = new ShowCategory();
                                        if(categories != null) {
                                            BeanUtils.copyProperties(categories, showCategory);
                                            showCategoryList.add(showCategory);
                                        }
                                    }
                                }
                            }

                            showCateFastenerLevel2.setShowCategoryList(showCategoryList);
                        }

                        showCateFastenerLevel2List.add(showCateFastenerLevel2);
                    }
                    showCateFastenerDetail.setShowCateFastenerStandList(showCateFastenerLevel2List);
                    showCateFastenerDetailList.add(showCateFastenerDetail);
                }

                showCateFrontView.setShowCateFastenerDetailList(showCateFastenerDetailList);
            }else if("其他".equals(showCate.getType())){
                List<ShowCateOtherDetail> showCateOtherDetailList = new ArrayList<>();
                for(ShowCatedetail cateDetail : showCatedetailList){
                    ShowCateOtherDetail showCateOtherDetail = new ShowCateOtherDetail();
                    showCateOtherDetail.setLevel2category(cateDetail.getLevel2category());
                    showCateOtherDetail.setLevel2id(cateDetail.getLevel2id());

                    List<ShowCategory> showCategoryList = new ArrayList<>();

                    String[] cateidsArr = cateDetail.getCategoryids().split(",");
                    if(cateidsArr != null && cateidsArr.length>0){
                        for(String catid : cateidsArr){
                            if(StringUtils.hasText(catid)){
                                Categories categories = categoriesService.getById(new Long(catid));
                                ShowCategory showCategory = new ShowCategory();
                                if(categories != null) {
                                    BeanUtils.copyProperties(categories, showCategory);
                                    showCategoryList.add(showCategory);
                                }
                            }
                        }
                    }
                    showCateOtherDetail.setShowCategoryList(showCategoryList);
                    showCateOtherDetailList.add(showCateOtherDetail);
                }

                showCateFrontView.setShowCateOtherDetailList(showCateOtherDetailList);
            }

            list.add(showCateFrontView);
        }
        return  list;
    }


    /**
     * 获取2级分类
     */
    private  List<Long> getLevel2List(List<ShowCatedetail> showCatedetailList){
        List<Long> level2List = new ArrayList<>();
        for(ShowCatedetail detail : showCatedetailList){
            if(!level2List.contains(detail.getLevel2id())){
                level2List.add(detail.getLevel2id());
            }
        }

        return  level2List;
    }



    /**
     * 获取材质集合
     * @param showCatedetailList
     * @return
     */
    private  List<String> getMaterialList(List<ShowCatedetail> showCatedetailList){
        List<String> materialList = new ArrayList<>();

        for(ShowCatedetail showCatedetail : showCatedetailList){
            if(!materialList.contains(showCatedetail.getMaterial())){
                materialList.add(showCatedetail.getMaterial());
            }
        }
        return  materialList;
    }


    /**
     *
     * @param list
     * @param material
     * @return
     */
    private  List<ShowCatedetail> getByMaterial(List<ShowCatedetail> list ,String material){
        List<ShowCatedetail>  resList = new ArrayList<>();
        for(ShowCatedetail showCatedetail : list){
            if(material.equals(showCatedetail.getMaterial())){
                resList.add(showCatedetail);
            }
        }
        return  resList;
    }



    private  List<ShowCatedetail> getByMaterialAndStand(List<ShowCatedetail> list ,String material,Long level2id){
        List<ShowCatedetail>  resList = new ArrayList<>();
        for(ShowCatedetail showCatedetail : list){
            if(material.equals(showCatedetail.getMaterial()) && level2id.equals(showCatedetail.getLevel2id())){
                resList.add(showCatedetail);
            }
        }
        return  resList;
    }


}
