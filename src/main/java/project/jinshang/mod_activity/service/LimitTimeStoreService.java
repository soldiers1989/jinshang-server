package project.jinshang.mod_activity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_activity.LimitTimeStoreMapper;
import project.jinshang.mod_activity.bean.LimitTimeStore;
import project.jinshang.mod_activity.bean.LimitTimeStoreExample;

import java.math.BigDecimal;
import java.util.List;

/**
 * create : wyh
 * date : 2018/1/10
 */

@Service
public class LimitTimeStoreService {

    @Autowired
    private LimitTimeStoreMapper limitTimeStoreMapper;


    public  void insertSelective(LimitTimeStore store){
        limitTimeStoreMapper.insertSelective(store);
    }

    public List<LimitTimeStore> selectByExample(LimitTimeStoreExample example){
        return  limitTimeStoreMapper.selectByExample(example);
    }

    /**
     * 根据限时购活动id删除库存信息
     * @param limittimeid
     */
    public  void deleteByLimitid(Long limittimeid){
        LimitTimeStoreExample example =  new LimitTimeStoreExample();
        LimitTimeStoreExample.Criteria criteria = example.createCriteria();
        criteria.andLimittimeidEqualTo(limittimeid);
        limitTimeStoreMapper.deleteByExample(example);
    }

    /**
     * 根据限时购活动id查询库存信息
     * @param limittimeid
     * @return
     */
    public  List<LimitTimeStore> getStoreListByLimitid(long limittimeid){
        LimitTimeStoreExample example =  new LimitTimeStoreExample();
        LimitTimeStoreExample.Criteria criteria = example.createCriteria();
        criteria.andLimittimeidEqualTo(limittimeid);
        return  limitTimeStoreMapper.selectByExample(example);
    }


    /**
     * 数据库中直接更新仓库数量
     * @param id
     * @param storenum
     * @param salesnum
     */
    public  void updateLimitStoreNum(long id, BigDecimal storenum,BigDecimal salesnum){
        limitTimeStoreMapper.updateLimitStoreNum(id,storenum,salesnum);
    }

    public LimitTimeStore  selectByPrimaryKey(Long id){
        return  limitTimeStoreMapper.selectByPrimaryKey(id);
    }


    public LimitTimeStore getLimitTimeStore(Long limitId,Long pdid,String pdno){
        LimitTimeStoreExample limitTimeStoreExample = new LimitTimeStoreExample();
        limitTimeStoreExample.createCriteria().andLimittimeidEqualTo(limitId).andPdidEqualTo(pdid).andPdnoEqualTo(pdno);
        List<LimitTimeStore> list = limitTimeStoreMapper.selectByExample(limitTimeStoreExample);
        if(list.size()>0){
            return list.get(0);
        }else {
            return null;
        }
    }

}
