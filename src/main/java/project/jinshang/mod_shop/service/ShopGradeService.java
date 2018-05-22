package project.jinshang.mod_shop.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_shop.ShopGradeMapper;
import project.jinshang.mod_shop.bean.ShopGrade;
import project.jinshang.mod_shop.bean.ShopGradeExample;


import java.util.List;

/**
 * create : wyh
 * date : 2017/11/4
 */
@Service
public class ShopGradeService {

    @Autowired
    private ShopGradeMapper shopGradeMapper;

    /**
     * 添加
     * @param sg
     */
    public  void addGrade(ShopGrade sg){
        shopGradeMapper.insert(sg);
    }

    public  void  deleteShopGroup(long id){
        shopGradeMapper.deleteByPrimaryKey(id);
    }

    public  void  updateShopGroup(ShopGrade sg){
        shopGradeMapper.updateByPrimaryKeySelective(sg);
    }

    public  List<ShopGrade> listAllShopGroup(ShopGradeExample example){
        return  shopGradeMapper.selectByExample(example);
    }
    public  void  updateByExampleSelective(ShopGrade shopGrade,ShopGradeExample example){
        shopGradeMapper.updateByExampleSelective(shopGrade,example);
    }
    public ShopGrade  selectByPrimaryKey(long id){
       return  shopGradeMapper.selectByPrimaryKey(id);
    }



    public PageInfo listShopGroupBuyPage(ShopGradeExample example, int pageNo, int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        return  new PageInfo( shopGradeMapper.selectByExample(example));
    }




    /**
     * 获取默认的级别信息
     * @return ppppppp
     */
    public  ShopGrade getDefaultShopGroup(){
        ShopGradeExample example = new ShopGradeExample();
        example.createCriteria().andIdefaultEqualTo(1);
        List<ShopGrade> list =shopGradeMapper.selectByExample(example);
        if(list != null && list.size()>0){
            return  list.get(0);
        }
        return  null;
    }
}


