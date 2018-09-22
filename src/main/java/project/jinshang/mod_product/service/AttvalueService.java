package project.jinshang.mod_product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_product.AttvalueMapper;
import project.jinshang.mod_product.bean.Attvalue;
import project.jinshang.mod_product.bean.AttvalueExample;

import java.util.List;

/**
 * create : wyh
 * date : 2017/11/10
 */

@Service
public class AttvalueService {

    @Autowired
    private AttvalueMapper attvalueMapper;


    public  void add(Attvalue attvalue){
        attvalueMapper.insert(attvalue);
    }


    public  void delById(long id){
        attvalueMapper.deleteByPrimaryKey(id);
    }


    public  Attvalue  getById(long id){
        return attvalueMapper.selectByPrimaryKey(id);
    }



    public  void updateById(Attvalue attvalue){
        attvalueMapper.updateByPrimaryKeySelective(attvalue);
    }


    /**
     * 根据属性id 和属性值查询
     * @param attid
     * @param value
     * @return
     */
    public Attvalue getByAttidAndValue(long attid,String value ){
        return  attvalueMapper.getByAttidAndValue(attid,value);
    }


    /**
     * 查询该属性id下的所有属性值
     * @param attid
     * @return
     */
    public List<Attvalue> getListByAttid(long attid){
        AttvalueExample example = new AttvalueExample();
        AttvalueExample.Criteria criteria = example.createCriteria();
        criteria.andAttidEqualTo(attid);
        example.setOrderByClause(" sort asc ");

        List<Attvalue> list = attvalueMapper.selectByExample(example);
        return  list;
    }

    /**
     * 根据属性id删除属性值
     * @param attid
     */
    public  int  deleteByAttid(long attid){
        AttvalueExample example = new AttvalueExample();
        AttvalueExample.Criteria criteria = example.createCriteria();
        criteria.andAttidEqualTo(attid);
        return  attvalueMapper.deleteByExample(example);
    }


    /**
     * 根据品名id删除属性值
     * @param productnameid
     */
    public  void  deleteAttvalueByProductnameid(long productnameid){
        attvalueMapper.deleteAttvalueByProductnameid(productnameid);
    }


    /**
     *
     * @param productnameid
     * @param name
     * @return
     */
    public  List<Attvalue> listByProductnameidAndName(long productnameid,String name){
       return attvalueMapper.listByProductnameidAndName(productnameid,name);
    }

}
