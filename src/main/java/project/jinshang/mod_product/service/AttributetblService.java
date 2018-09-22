package project.jinshang.mod_product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_product.AttributetblMapper;
import project.jinshang.mod_product.bean.Attribute;
import project.jinshang.mod_product.bean.Attributetbl;
import project.jinshang.mod_product.bean.AttributetblExample;
import project.jinshang.mod_product.bean.dto.AttributetblDto1;

import java.util.List;

/**
 * create : wyh
 * date : 2017/11/10
 */
@Service
public class AttributetblService {

    @Autowired
    private AttributetblMapper attributetblMapper;

    @Autowired
    private  AttvalueService attvalueService;

    public Attributetbl selectByPrimaryKey(Long id){
        return attributetblMapper.selectByPrimaryKey(id);
    }

    public  void add(Attributetbl attributetbl){
        attributetblMapper.insert(attributetbl);
    }

    public  void  delByid(long id){
        attributetblMapper.deleteByPrimaryKey(id);
    }


    public  void updateById(Attributetbl attributetbl){
        attributetblMapper.updateByPrimaryKeySelective(attributetbl);
    }


    /**
     * 根据品名id和名称查询
     * @param productnameid
     * @param name
     * @return
     */
    public  Attributetbl getByProductnameidAndName(long productnameid,String name){
       return attributetblMapper.getByProductnameidAndName(productnameid,name);
    }


    /**
     * 根据品名id查询出该品名下的所有属性
     * @param productnameid
     * @return
     */
    public List<Attributetbl> getAttributeListByProductnameid(long productnameid){
        AttributetblExample example = new AttributetblExample();
        AttributetblExample.Criteria criteria = example.createCriteria();
        criteria.andProductnameidEqualTo(productnameid);
        example.setOrderByClause("sort asc");

        List<Attributetbl> list = attributetblMapper.selectByExample(example);
        return  list;
    }


    /***
     * 根据牌名id获取属性和属性值
     * @param productnameid
     * @return
     */
    public  List<Attributetbl> getAttributeWithValue(long productnameid){
        return  attributetblMapper.getAttributeWithValue(productnameid);
    }





    /**
     * 根据品名id删除属性并且删除属性值
     * @param productnameid
     */
    public void deleteByProductnameid(long productnameid){

        attvalueService.deleteAttvalueByProductnameid(productnameid);

        attributetblMapper.deleteByProductnameid(productnameid);
    }



    public List<AttributetblDto1> getAttributeByProdnameid(Long productnameid){
        return  attributetblMapper.getAttributeByProdnameid(productnameid);
    }



}
