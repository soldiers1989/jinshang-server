package project.jinshang.mod_admin.mod_commondata.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_admin.mod_commondata.CommonDataMapper;
import project.jinshang.mod_admin.mod_commondata.bean.CommonData;
import project.jinshang.mod_admin.mod_commondata.bean.CommonDataExample;

import java.util.List;

/**
 * create : wyh
 * date : 2017/12/26
 */

@Service
public class CommonDataService {

    @Autowired
    private CommonDataMapper commonDataMapper;

    public  CommonData getById(long id){
        return  commonDataMapper.selectByPrimaryKey(id);
    }

    public CommonData getByName(String name){
        return  commonDataMapper.getByName(name);
    }

    public  void  insertSelective(CommonData data){
        commonDataMapper.insertSelective(data);
    }


    public PageInfo listByPage(String name,int pageNo,int pageSize){
        CommonDataExample example =  new CommonDataExample();
        CommonDataExample.Criteria criteria = example.createCriteria();
        if(StringUtils.hasText(name)){
            criteria.andNameLike("%"+name+"%");
        }

        PageHelper.startPage(pageNo,pageSize);
        List<CommonData> list =  commonDataMapper.selectByExample(example);
        PageInfo pageInfo =  new PageInfo(list);
        return  pageInfo;
    }


    public  void deleteById(long id){
        commonDataMapper.deleteByPrimaryKey(id);
    }


    public  void updateByPrimaryKeySelective(CommonData data){
        commonDataMapper.updateByPrimaryKeySelective(data);
    }

}
