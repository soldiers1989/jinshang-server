package project.jinshang.mod_member.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_member.AdminGroupMapper;
import project.jinshang.mod_member.bean.AdminGroup;
import project.jinshang.mod_member.bean.AdminGroupExample;

import java.util.Date;
import java.util.List;

/**
 * create : wyh
 * date : 2017/11/1
 */

@Service
public class AdminGroupService {

    @Autowired
    private AdminGroupMapper adminGroupMapper;


    public void addGroup(AdminGroup adminGroup){
        adminGroup.setCreatedate(new Date());
        adminGroupMapper.insert(adminGroup);

    }

    public void  deleteGroup(long  id){
        adminGroupMapper.deleteByPrimaryKey(id);
    }

    public void updateGroup(AdminGroup adminGroup){
        adminGroupMapper.updateByPrimaryKey(adminGroup);
    }

    public List<AdminGroup> listAll(){
        AdminGroupExample example = new AdminGroupExample();
        return   adminGroupMapper.selectByExample(example);
    }



    public PageInfo listByPage(int pageNo,int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        AdminGroupExample example = new AdminGroupExample();
        List<AdminGroup> list =  adminGroupMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(list);
        return  pageInfo;

    }

    public  AdminGroup getById(Long id){
        return  adminGroupMapper.selectByPrimaryKey(id);
    }

}
