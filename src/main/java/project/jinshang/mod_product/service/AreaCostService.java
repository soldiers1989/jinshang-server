package project.jinshang.mod_product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_product.AreaCostMapper;
import project.jinshang.mod_product.bean.AreaCost;
import project.jinshang.mod_product.bean.AreaCostExample;

import java.util.List;

/**
 * create : wyh
 * date : 2017/11/13
 */
@Service
public class AreaCostService {

    @Autowired
    private AreaCostMapper areaCostMapper;

    public  void  add(AreaCost areaCost){
        areaCostMapper.insert(areaCost);
    }


    public  void  deleteById(long id){
        areaCostMapper.deleteByPrimaryKey(id);
    }

    public  void  deleteByTemid(long temid){
        AreaCostExample example = new AreaCostExample();
        AreaCostExample.Criteria criteria = example.createCriteria();
        criteria.andTemidEqualTo(temid);
        areaCostMapper.deleteByExample(example);
    }


    public List<AreaCost> getByTempid(long tempid){
        AreaCostExample example = new AreaCostExample();
        AreaCostExample.Criteria criteria = example.createCriteria();
        criteria.andTemidEqualTo(tempid);
        return  areaCostMapper.selectByExample(example);
    }


    public List<AreaCost> getAreaCostByTemid(long id){
        AreaCostExample areaCostExample  = new AreaCostExample();
        areaCostExample.createCriteria().andTemidEqualTo(id);
        List<AreaCost> list = areaCostMapper.selectByExample(areaCostExample);
        return  list;
    }


}
