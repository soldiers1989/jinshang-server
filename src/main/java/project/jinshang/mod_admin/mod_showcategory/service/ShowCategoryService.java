package project.jinshang.mod_admin.mod_showcategory.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_admin.mod_showcategory.ShowCateMapper;
import project.jinshang.mod_admin.mod_showcategory.bean.ShowCate;
import project.jinshang.mod_admin.mod_showcategory.bean.ShowCateExample;
import project.jinshang.mod_admin.mod_showcategory.bean.ShowCatedetail;
import project.jinshang.mod_admin.mod_showcategory.bean.dto.ShowCategory;
import project.jinshang.mod_front.bean.ShowCateFastenerDetail;
import project.jinshang.mod_front.bean.ShowCateFastenerLevel2;
import project.jinshang.mod_front.bean.ShowCateFrontView;
import project.jinshang.mod_front.bean.ShowCateOtherDetail;
import project.jinshang.mod_product.bean.Categories;
import project.jinshang.mod_product.bean.Material;
import project.jinshang.mod_product.service.CategoriesService;
import project.jinshang.mod_product.service.MaterialService;

import java.util.ArrayList;
import java.util.List;

/**
 * create : wyh
 * date : 2018/2/1
 */

@Service
public class ShowCategoryService {

    @Autowired
    private ShowCateMapper showCateMapper;


    /**
     * 根据分类名查询
     * @param maincategory
     * @return
     */
    public  ShowCate getByMainCategory(String maincategory){
        ShowCateExample example =  new ShowCateExample();
        ShowCateExample.Criteria criteria = example.createCriteria();
        criteria.andMaincategoryEqualTo(maincategory);
        List<ShowCate> list =  showCateMapper.selectByExample(example);
        if(list.size() !=0){
            return  list.get(0);
        }

        return  null;

    }


    /**
     * 添加
     * @param showCate
     */
    public  void addCate(ShowCate showCate){
        showCateMapper.insertSelective(showCate);
    }


    public  void  delCate(Long id){
        showCateMapper.deleteByPrimaryKey(id);
    }


    public  ShowCate getById(Long id){
        return  showCateMapper.selectByPrimaryKey(id);
    }


    public  void updateByPrimaryKey(ShowCate showCate){
        showCateMapper.updateByPrimaryKey(showCate);
    }


    /**
     * 查询所有
     * @return
     */
    public List<ShowCate> listAll(){
        return  showCateMapper.listAll();
    }





}
