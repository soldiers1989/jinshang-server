package project.jinshang.mod_admin.mod_count.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_admin.mod_count.ProductViewMapper;
import project.jinshang.mod_admin.mod_count.bean.ProductView;

import java.util.List;
import java.util.Map;

/**
 * create : wyh
 * date : 2018/1/30
 */

@Service
public class ProductViewService {

    @Autowired
    private ProductViewMapper productViewMapper;


    public void insertSelective(ProductView view){
        productViewMapper.insertSelective(view);
    }

    /**
     * 添加访问次数
     * @param id
     * @param count
     */
    public  void addCount(Long id,int count){
        productViewMapper.addCount(id,count);
    }


    public  ProductView  selectByPdidAndYyyymmdd(long pdid,int yyyymmdd){
        return  productViewMapper.selectByPdidAndYyyymmdd(pdid,yyyymmdd);
    }


    public List<Map<String,Object>> getMaxCountByyyyymmdd(int yyyymmdd,int count){
        return  productViewMapper.getMaxCountByyyyymmdd(yyyymmdd,count);
    }


    public List<Map<String,Object>> getViewList(int yyyymmdd_start,int yyyymmdd_end,String ids){
        return  productViewMapper.getViewList(yyyymmdd_start,yyyymmdd_end,ids);
    }


}
