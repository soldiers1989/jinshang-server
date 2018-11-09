package project.jinshang.mod_front;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.constant.RedisCacheKey;
import project.jinshang.common.utils.*;
import project.jinshang.mod_admin.mod_floor.bean.Floor;
import project.jinshang.mod_admin.mod_floor.bean.FloorProd;
import project.jinshang.mod_admin.mod_floor.bean.FloorViewDto;
import project.jinshang.mod_admin.mod_floor.service.FloorService;
import project.jinshang.mod_admin.mod_showcategory.bean.ShowCate;
import project.jinshang.mod_admin.mod_showcategory.bean.ShowCatedetail;
import project.jinshang.mod_admin.mod_showcategory.bean.dto.ShowCategory;
import project.jinshang.mod_admin.mod_showcategory.service.ShowCategoryDetailService;
import project.jinshang.mod_admin.mod_showcategory.service.ShowCategoryService;
import project.jinshang.mod_front.bean.*;
import project.jinshang.mod_front.service.IndexService;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_product.bean.Categories;
import project.jinshang.mod_product.bean.Material;
import project.jinshang.mod_product.service.CategoriesService;
import project.jinshang.mod_product.service.MaterialService;
import project.jinshang.mod_system.mod_redis.service.RedisCacheService;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;

/**
 * create : wyh
 * date : 2017/12/28
 */
@RestController
@RequestMapping("/rest/front/index")
@Api(tags = "前台pc首页接口")
@SessionAttributes(value = {AppConstant.MEMBER_SESSION_NAME})
public class IndexPcFrontAction {

    @Autowired
    private RedisCacheService redisCacheService;


    @PostMapping("/listFloor")
    @ApiOperation("首页楼层")
    public  FloorListRet listFloor(Model model){
        FloorListRet floorListRet  = new FloorListRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        if(member == null){
            floorListRet.data.showPrice = false;
        }else{
            floorListRet.data.showPrice = true;
        }

//        List<FloorViewDto> dtoList =  floorService.getFloor();

        List<FloorViewDto> dtoList = redisCacheService.getIndexFloor();
        //dtoList = null;
        if(dtoList == null){
            dtoList = redisCacheService.addIndexFloor();
        }

        floorListRet.data.floorList =  dtoList;
        floorListRet.setMessage("查询成功");
        floorListRet.setResult(BasicRet.SUCCESS);
        return  floorListRet;
    }


    @PostMapping("/listShowCate")
    @ApiOperation("展示类目")
    public ShowCateRet listShowCate(){
        ShowCateRet showCateRet = new ShowCateRet();

        List<ShowCateFrontView> list = redisCacheService.getShowCate();
        //list=null;
        if(list == null){
            list = redisCacheService.addShowCate();
        }

        ShowCateData showCateData =  new ShowCateData();
        showCateData.setList(list);
        showCateRet.setData(showCateData);
        showCateRet.setResult(BasicRet.SUCCESS);
        return  showCateRet;
    }

    @PostMapping("/listShowCateForTinyPro")
    @ApiOperation("展示类目")
    public ShowCateRetByType listShowCateForTinyPro(){
        ShowCateRetByType showCateRetByType = new ShowCateRetByType();

        List<ShowCateFrontView> list = redisCacheService.getShowCate();
        //list=null;
        if(list == null){
            list = redisCacheService.addShowCate();
        }

        ShowCateDataByType showCateDataByType =  new ShowCateDataByType();
        List<ShowCateFrontView> listForFastener=new ArrayList<>();
        List<ShowCateFrontView> listForOther=new ArrayList<>();
        list.stream().forEach(showCateFrontView->{
            if("紧固件".equals(showCateFrontView.getShowCate().getType())){
                listForFastener.add(showCateFrontView);
            }else if ("工业品".equals(showCateFrontView.getShowCate().getType())){
                listForOther.add(showCateFrontView);
            }
        });
        showCateDataByType.setListForFastener(listForFastener);
        showCateDataByType.setListForOther(listForOther);
        showCateRetByType.setData(showCateDataByType);
        showCateRetByType.setResult(BasicRet.SUCCESS);
        return  showCateRetByType;
    }





    private  class  FloorListRet extends BasicRet {

        private  class  FloorData{
            private List<FloorViewDto> floorList;

            private  boolean showPrice;

            public List<FloorViewDto> getFloorList() {
                return floorList;
            }

            public void setFloorList(List<FloorViewDto> floorList) {
                this.floorList = floorList;
            }

            public boolean isShowPrice() {
                return showPrice;
            }

            public void setShowPrice(boolean showPrice) {
                this.showPrice = showPrice;
            }
        }


        private  FloorData data = new FloorData();

        public FloorData getData() {
            return data;
        }

        public void setData(FloorData data) {
            this.data = data;
        }
    }


}
