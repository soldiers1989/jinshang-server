package project.jinshang.mod_member.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.utils.CommonUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_member.AdminGroupMapper;
import project.jinshang.mod_member.AdminMapper;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_member.bean.AdminExample;
import project.jinshang.mod_member.bean.AdminGroup;

import java.util.Date;
import java.util.List;

/**
 * create : wyh
 * date : 2017/11/1
 */

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;


    public  Admin getById(long id){
        return  adminMapper.selectByPrimaryKey(id);
    }


    public  void  deleteById(long id){
        adminMapper.deleteByPrimaryKey(id);
    }

    /**
     * 新增管理员
     * @param admin
     * @return
     */
    public  int addAdmin(Admin  admin){
        admin.setCreatedate(new Date());
        admin.setDisable(false);
        admin.setPasswordsalt(CommonUtils.genSalt());
        admin.setPassword(CommonUtils.genMd5Password(admin.getPassword(),admin.getPasswordsalt()));
       return adminMapper.insert(admin);
    }

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    public  Admin getByUserName(String username){
        AdminExample example = new AdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<Admin> list = adminMapper.selectByExample(example);
        if(list != null && list.size()==1){
            return  list.get(0);
        }

        return  null;
    }


    /**
     * 根据groupid查询指定权限组下有没有存在账号
     * @param groupid
     */
    public int selectCountByGroupId(int groupid){
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andGroupidEqualTo(groupid);
        int count= adminMapper.countByExample(example);
        return count;
    }


    public PageInfo listByPage(int pageNo,int pageSize,Admin admin){
        PageHelper.startPage(pageNo,pageSize);
        AdminExample example =  new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        if(admin != null){
            if(StringUtils.hasText(admin.getRealname())){
                criteria.andRealnameLike("%"+admin.getRealname()+"%");
            }
        }

        List<Admin> list =  adminMapper.selectByExample(example);
        return  new PageInfo(list);
    }


    public  void updateByPrimaryKeySelective(Admin admin){
        adminMapper.updateByPrimaryKeySelective(admin);
    }


    public  List<Admin> getAdminByRoles(String roles){
        return  adminMapper.getAdminByRoles(roles);
    }

}
