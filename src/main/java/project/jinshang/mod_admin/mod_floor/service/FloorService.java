package project.jinshang.mod_admin.mod_floor.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.CommonUtils;
import project.jinshang.common.utils.JinshangUtils;
import project.jinshang.common.utils.ProductCategoryUtils;
import project.jinshang.mod_admin.mod_floor.FloorMapper;
import project.jinshang.mod_admin.mod_floor.bean.Floor;
import project.jinshang.mod_admin.mod_floor.bean.FloorExample;
import project.jinshang.mod_admin.mod_floor.bean.FloorProd;
import project.jinshang.mod_admin.mod_floor.bean.FloorViewDto;
import project.jinshang.mod_product.bean.Categories;
import project.jinshang.mod_product.service.CategoriesService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create : wyh
 * date : 2017/12/28
 */

@Service
public class FloorService {

    @Autowired
    private FloorMapper floorMapper;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private  Gson gson;

    public void insertSelective(Floor floor){
        floorMapper.insertSelective(floor);
    }


    public  Floor getById(long id){
        return  floorMapper.selectByPrimaryKey(id);
    }


    public  void deleteById(long id){
        floorMapper.deleteByPrimaryKey(id);
    }

    public  void  updateByPrimaryKeySelective(Floor floor){
        floorMapper.updateByPrimaryKeySelective(floor);
    }


    public PageInfo listByPage(int pageNo,int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        FloorExample example = new FloorExample();
        List<Floor> list = floorMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(list);
        return  pageInfo;
    }


    /**
     * 查询楼层商品列表
     * @param ids
     * @return
     */
    public List<Map<String,Object>> getFloorproducts(String ids){
       return floorMapper.getFloorproducts(ids);
    }

    /**
     * 获取排行榜数据
     * @param id
     * @return
     */
    public Map<String,Object> getRankingprod(Long id){
        return  floorMapper.getRankingprod(id);
    }


    public  List<Floor> getShowFloor(){
       return  floorMapper.getShowFloor();
    }

    /**
     * 查询真实楼层排行榜数据
     * @param
     * @param count
     * @return
     */
    public List<Map<String,Object>> getRealRankingprodList(long level2id,int count){
        return  floorMapper.getRealRankingprodList(level2id,count);
    }




}
