package project.jinshang.mod_product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import project.jinshang.common.utils.RedisUtils;
import project.jinshang.mod_product.ProductAttrMapper;
import project.jinshang.mod_product.bean.ProductAttr;
import project.jinshang.mod_product.bean.ProductAttrExample;
import project.jinshang.mod_system.mod_redis.service.RedisCacheService;

import java.util.List;
import java.util.Set;

/**
 * create : wyh
 * date : 2017/11/11
 */

@Service
public class ProductAttrService {

    @Autowired
    private ProductAttrMapper productAttrMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    public void  add(ProductAttr attr){
        productAttrMapper.insert(attr);
    }


    /**
     * 获取该产品下的所有属性信息
     * @param productid
     * @return
     */
    public List<ProductAttr> getByProductid(long productid){
        List<ProductAttr> list = productAttrMapper.getListByPid(productid);
        return  list;
    }

    /**
     * 删除该产品下的所有属性信息
     * @param productid
     */
    public  void  deleteByProductid(long productid){

        Set<String> keys =redisTemplate.keys("getListByPidAndPdno:"+productid+":"+"*");
        redisTemplate.delete(keys);

        redisTemplate.delete("getListByPid:"+productid);

//        redisTemplate.delete("getListByPidAndPdno-1234-20180306142320829298");
        productAttrMapper.deleteByPid(productid);
    }



    public List<ProductAttr> getListByPidAndPdno(Long productid, String pdno){
        return  productAttrMapper.getListByPidAndPdno(productid,pdno);
    }

}
