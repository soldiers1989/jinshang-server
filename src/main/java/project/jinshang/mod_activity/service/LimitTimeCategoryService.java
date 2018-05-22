package project.jinshang.mod_activity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_activity.LimitTimeCategoryMapper;
import project.jinshang.mod_activity.bean.LimitTimeCategory;
import project.jinshang.mod_activity.bean.LimitTimeCategoryExample;

import java.util.List;

/**
 * create : wyh
 * date : 2018/1/10
 */

@Service
public class LimitTimeCategoryService {

    @Autowired
    private LimitTimeCategoryMapper limitTimeCategoryMapper;

    public void  insertSelective(LimitTimeCategory category){
        limitTimeCategoryMapper.insertSelective(category);
    }


    public LimitTimeCategory getById(long id){
        return  limitTimeCategoryMapper.selectByPrimaryKey(id);
    }

    public  LimitTimeCategory getByName(String name){
        return  limitTimeCategoryMapper.getByName(name);
    }

    public  void  delById(long id){
        limitTimeCategoryMapper.deleteByPrimaryKey(id);
    }


    public List<LimitTimeCategory> selectAll() {
        LimitTimeCategoryExample example = new LimitTimeCategoryExample();
        return limitTimeCategoryMapper.selectByExample(example);
    }

    public List<LimitTimeCategory> selectAll(int type) {
        return limitTimeCategoryMapper.getByType(type);
    }

    public List<LimitTimeCategory> selectActivity() {
        return limitTimeCategoryMapper.selectActivity();
    }

    public int updateTypeById(int id,int type){
        return limitTimeCategoryMapper.updateTypeById(id,type);
    }

}
