package project.jinshang.mod_member.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_member.SellerGroupMapper;
import project.jinshang.mod_member.bean.SellerGroup;

import java.util.List;

/**
 * create : wyh
 * date : 2017/11/7
 */

@Service
public class SellerGroupService {

    @Autowired
    private SellerGroupMapper sellerGroupMapper;

    public  void addSellerGroup(SellerGroup sellerGroup){
        sellerGroupMapper.insert(sellerGroup);
    }

    public PageInfo listsellergroup(Long id,int pageNo,int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        SellerGroup sellerGroup=new SellerGroup();
        sellerGroup.setMemberid(id);
        List<SellerGroup> list= sellerGroupMapper.listsellergroup(sellerGroup);
        PageInfo pageInfo = new PageInfo(list);
        return  pageInfo;
    }


    public  SellerGroup getById(long id){
        return  sellerGroupMapper.selectByPrimaryKey(id);
    }


    public  void updateByPrimaryKeySelective(SellerGroup sellerGroup){
        sellerGroupMapper.updateByPrimaryKeySelective(sellerGroup);
    }


    public  void  deleteById(long id){
        sellerGroupMapper.deleteByPrimaryKey(id);
    }

}
