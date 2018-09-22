package project.jinshang.mod_admin.mod_shopgroup.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_admin.mod_shopgroup.ShopGroupMapper;
import project.jinshang.mod_admin.mod_shopgroup.bean.ShopGroup;
import project.jinshang.mod_admin.mod_shopgroup.bean.dto.ShopGroupAndStoreView;
import project.jinshang.mod_company.StoreMapper;
import project.jinshang.mod_company.bean.Store;
import project.jinshang.mod_company.bean.StoreExample;

import java.util.List;

/**
 * 商家合单集合
 *
 * @author xiazy
 * @create 2018-06-28 14:51
 **/
@Service
public class ShopGroupService {

    @Autowired
    private ShopGroupMapper shopGroupMapper;
    @Autowired
    private StoreMapper storeMapper;

    public ShopGroup getByPrimaryKey(Long id){
        return shopGroupMapper.selectByPrimaryKey(id);
    }

    public PageInfo getShopGroupList(int pageNo, int pageSize, ShopGroup shopGroup){
        PageHelper.startPage(pageNo,pageSize);
        List<ShopGroup> shopGroupList=shopGroupMapper.selectShopGroup(shopGroup);
        PageInfo pageInfo = new PageInfo(shopGroupList);
        return  pageInfo;
    }
    public void updateStoreShopGroup(Long storeId,Long shopgroupid){
        Store store=new Store();
        store.setId(storeId);
        store.setShopgroupid(shopgroupid);
        storeMapper.updateByPrimaryKeySelective(store);
    }
    public boolean checkShopGroupName(ShopGroup shopGroup){
        ShopGroup shopGroupnew=new ShopGroup();
        shopGroupnew.setName(shopGroup.getName());
        List<ShopGroup> shopGroupList=shopGroupMapper.selectShopGroup(shopGroupnew);
        if (shopGroupList!=null&&shopGroupList.size()>0){
            if(shopGroup.getId()!=null&&shopGroupList.get(0).getId().compareTo(shopGroup.getId())==0){
                return false;
            }else {
                return true;
            }
        }
        return false;
    }

    public boolean checkStoreExit(String storename){
        StoreExample example=new StoreExample();
        StoreExample.Criteria criteria=example.createCriteria();
        criteria.andNameEqualTo(storename);
        List<Store>storeList= storeMapper.selectByExample(example);
        if (storeList!=null&&storeList.size()>0){
            return true;
        }else {
            return false;
        }
    }

    public void addShopGroup(ShopGroup shopGroup){
        shopGroupMapper.insertSelective(shopGroup);
    }

    public void updateShopGroup(ShopGroup shopGroup){
        shopGroupMapper.updateByPrimaryKeySelective(shopGroup);
    }


    public void deleteShopGroup(Long id){
        shopGroupMapper.deleteByPrimaryKey(id);
    }


    public  void resertStoreShopGroup(Long shopGroupId){
        storeMapper.resertStoreShopGroup(shopGroupId);
    }
    public List<ShopGroupAndStoreView> getShopGroupSellerList(Store store){
        List<ShopGroupAndStoreView> storeList=storeMapper.getByShopGroupIdAndName(store);
        return storeList;
    }

}
