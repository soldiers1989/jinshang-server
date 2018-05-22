package project.jinshang.mod_company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_company.StoreMapper;
import project.jinshang.mod_company.bean.Store;
import project.jinshang.mod_company.bean.StoreExample;

import java.util.Date;
import java.util.List;

/**
 * create : wyh
 * date : 2017/11/8
 */

@Service
public class StoreService {


    @Autowired
    private StoreMapper storeMapper;

    public  List<Store> getByName(String name){
        StoreExample example = new StoreExample();
        StoreExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        return  storeMapper.selectByExample(example);
    }

    public  Store getById(long id){
        return  storeMapper.selectByPrimaryKey(id);
    }


    public  void add(Store store){
        store.setCreatetime(new Date());
        storeMapper.insert(store);
    }


    public  Store getByName(Long memberid,String name){
        return  storeMapper.getByName(memberid,name);
    }


    public List<Store> getAllByMember(Long memberid){
        StoreExample example = new StoreExample();
        example.createCriteria().andMemberidEqualTo(memberid);
        return  storeMapper.selectByExample(example);
    }


    public  Store getByIdAndMemberId(long id,long memberid){
        return  storeMapper.getByIdAndMemberId(id,memberid);
    }


    public  Store getByNameAndMemberId(String name,long memberid){
        return  storeMapper.getByNameAndMemberId(name,memberid);
    }


    public  void  updateByPrimaryKeySelective(Store store){
        storeMapper.updateByPrimaryKeySelective(store);
    }


    public  void  delById(long id){
        storeMapper.deleteByPrimaryKey(id);
    }


    /**
     * 根据用户id 删除仓库
     * @param memberid
     */
    public  void delByMemberid(long memberid){
        StoreExample example =  new StoreExample();
        StoreExample.Criteria criteria = example.createCriteria();
        criteria.andMemberidEqualTo(memberid);
        storeMapper.deleteByExample(example);
    }

}
