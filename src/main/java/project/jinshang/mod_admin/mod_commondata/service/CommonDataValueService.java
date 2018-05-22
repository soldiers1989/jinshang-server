package project.jinshang.mod_admin.mod_commondata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_admin.mod_commondata.CommonDataValueMapper;
import project.jinshang.mod_admin.mod_commondata.bean.CommonDataValue;
import project.jinshang.mod_admin.mod_commondata.bean.CommonDataValueExample;

import java.util.List;

/**
 * create : wyh
 * date : 2017/12/26
 */

@Service
public class CommonDataValueService {


    @Autowired
    private CommonDataValueMapper commonDataValueMapper;


    public  void  insertSelective(CommonDataValue value){
        commonDataValueMapper.insertSelective(value);
    }


    public  void deleteById(long id){
        commonDataValueMapper.deleteByPrimaryKey(id);
    }

    public  CommonDataValue getByValueAndDataId(String value,long dataid){
        return  commonDataValueMapper.getByValueAndDataId(value,dataid);
    }


    public  void deleteByDataId(long dataid){
        CommonDataValueExample example = new CommonDataValueExample();
        CommonDataValueExample.Criteria criteria = example.createCriteria();
        criteria.andDataidEqualTo(dataid);
        commonDataValueMapper.deleteByExample(example);
    }


    public List<CommonDataValue> getValueByDataid(long dataid){
        CommonDataValueExample example = new CommonDataValueExample();
        CommonDataValueExample.Criteria criteria = example.createCriteria();
        criteria.andDataidEqualTo(dataid);
        return  commonDataValueMapper.selectByExample(example);
    }


    public  void updateByPrimaryKey(CommonDataValue commonDataValue){
        commonDataValueMapper.updateByPrimaryKey(commonDataValue);
    }


    public  CommonDataValue getById(long id){
      return  commonDataValueMapper.selectByPrimaryKey(id);
    }


    public  List<String> getcommonDataValue(String name){
        return commonDataValueMapper.getcommonDataValue(name);
    }
}
