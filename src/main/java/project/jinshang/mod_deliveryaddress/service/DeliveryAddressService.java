package project.jinshang.mod_deliveryaddress.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_deliveryaddress.DeliveryAddressMapper;
import project.jinshang.mod_deliveryaddress.bean.DeliveryAddress;
import project.jinshang.mod_deliveryaddress.bean.DeliveryAddressExample;

import java.util.List;

/**
 * create : wyh
 * date : 2017/11/3
 */

@Service
public class DeliveryAddressService {

    @Autowired
    private DeliveryAddressMapper deliveryAddressMapper;

    public void  addDeliveryAddress(DeliveryAddress deliveryAddress){
        deliveryAddressMapper.insert(deliveryAddress);
    }



    public  void  updateByPrimaryKeySelective(DeliveryAddress address){
        deliveryAddressMapper.updateByPrimaryKeySelective(address);
    }


   public void deleteDeliveryAddress(long id){
       deliveryAddressMapper.deleteByPrimaryKey(id);
   }

    public void  updateDeliveryAddress(DeliveryAddress deliveryAddress,DeliveryAddressExample deliveryAddressExample){
        deliveryAddressMapper.updateByPrimaryKeySelective(deliveryAddress);
    }
    public  void updatedefultAddressBymemberid(long memberid){
        deliveryAddressMapper.updatedefultAddressBymemberid(memberid);
    }

    public PageInfo listDeliveryAddressByPage(int pageNo,int pageSize,long memberid){
        PageHelper.startPage(pageNo,pageSize);
        DeliveryAddressExample deliveryAddressExample=new DeliveryAddressExample();
        DeliveryAddressExample.Criteria criteria = deliveryAddressExample.createCriteria();
        criteria.andMemberidEqualTo(memberid);
        List<DeliveryAddress> list = deliveryAddressMapper.selectByExample(deliveryAddressExample);
        return  new PageInfo(list);
    }

    public DeliveryAddress getById(long id){
        return  deliveryAddressMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据id查询该地址的memberid
     * @param id
     * @return
     */
    public  DeliveryAddress selectMemberidByid(long id){
        return deliveryAddressMapper.selectMemberidByid(id);
    }

    /**
     * 获取默认的收获地址信息
     * @return
     */
    public DeliveryAddress getDefaultDeliveryAddress(long memberid){
        return  deliveryAddressMapper.getDefaultDeliveryAddress(memberid);
    }


    public  void  updateIsdefault(long id, short isdefault){
        deliveryAddressMapper.updateIsdefault(id,isdefault);
    }



}
