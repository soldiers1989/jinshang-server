package project.jinshang.mod_member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_member.AdminUserMapper;
import project.jinshang.mod_member.bean.AdminUser;

import java.util.Date;
import java.util.List;

/**
 * @author Godlf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/309:11
 */
@Service
public class AdminUserService {
    @Autowired
    private AdminUserMapper adminUserMapper;


    /**
     *
     * 添加关联关系
     * @param adminUser
     * @return
     */
    public  int addManageUsersBylist(AdminUser adminUser){
        adminUser.setCreatedate(new Date());
        adminUser.setDisable(false);
        return adminUserMapper.insertSelective(adminUser);
    }

    /**
     * 查询当前管理员是否已关联会员
     * @param adminid
     * @param userid
     * @return
     */
    public  AdminUser getAdminUserByAdminAndUserid(Long adminid,Long userid){
        return adminUserMapper.getAdminUserByAdminAndUserid(adminid, userid);
    }

    /**
     * 根據id刪除數據
     * @param id
     * @return
     */
    public  int delAdminUserByid(Long id){
        return adminUserMapper.deleteByPrimaryKey(id);
    }

    public int delAdminUserByuserid(long id) {
        return adminUserMapper.delAdminUserByuserid(id);
    }



    public AdminUser getAdminUserByUserid(long userid) {
        return adminUserMapper.getAdminUserByUserid(userid);
    }

    public List<AdminUser> findAdminUserByAdminid(long adminid) {
        return adminUserMapper.findAdminUserByAdminid(adminid);
    }
}
