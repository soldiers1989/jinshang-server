package project.jinshang.mod_shippingaddress.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_member.MemberMapper;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.bean.MemberGrade;
import project.jinshang.mod_member.bean.MemberGradeExample;
import project.jinshang.mod_shippingaddress.ShippingAddressMapper;
import project.jinshang.mod_shippingaddress.bean.ShippingAddress;
import project.jinshang.mod_shippingaddress.bean.ShippingAddressExample;

import java.util.List;

/**
 * create : wyh
 * date : 2017/11/3
 */

@Service
public class ShippingAddressService {

    @Autowired
    private ShippingAddressMapper shippingAddressMapper;


    public  ShippingAddress selectByidAndType(long id,short type){
        return shippingAddressMapper.selectByidAndType(id,type);
    }

    public void  addShippingAddress(ShippingAddress shippingAddress){
        shippingAddressMapper.insert(shippingAddress);
    }

    public void deleteShippingAddress(long id){
       shippingAddressMapper.deleteByPrimaryKey(id);
   }


    public  void  updateByPrimaryKey(ShippingAddress address){
        shippingAddressMapper.updateByPrimaryKey(address);
    }


    public  void  updateByPrimaryKeySelective(ShippingAddress address){
        shippingAddressMapper.updateByPrimaryKeySelective(address);
    }

    public PageInfo listAllShippingAddress(int pageNo,int pageSize,long memberid,short type){
        //PageHelper.startPage(pageNo,pageSize);
        ShippingAddressExample shippingAddressExample=new ShippingAddressExample();
        ShippingAddressExample.Criteria criteria= shippingAddressExample.createCriteria();
        criteria.andMemberidEqualTo(memberid).andTypeEqualTo(type);
        shippingAddressExample.setOrderByClause(" isdefault desc ");
        List<ShippingAddress> list =  shippingAddressMapper.selectByExample(shippingAddressExample);
        PageInfo pageInfo = new PageInfo(list);
        return  pageInfo;
    }

    public ShippingAddress getById(long id){
        return  shippingAddressMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取默认的收获地址信息
     * @return
     */
    public ShippingAddress getDefaultShippingAddress(long memberid,short type){
        return  shippingAddressMapper.getDefaultShippingAddress(memberid,type);
    }


    public  void upateAllToNotDefault(long memberid,short type){
        shippingAddressMapper.upateAllToNotDefault(memberid,type);
    }


    public  int countByExample(ShippingAddressExample example){
        return  shippingAddressMapper.countByExample(example);
    }


}
