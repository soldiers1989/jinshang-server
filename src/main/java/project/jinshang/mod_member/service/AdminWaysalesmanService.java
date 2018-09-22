package project.jinshang.mod_member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_member.AdminWaysalesmanMapper;
import project.jinshang.mod_member.bean.AdminWaysalesman;

import java.util.Date;


@Service
public class AdminWaysalesmanService {

    @Autowired
    private AdminWaysalesmanMapper adminWaysalesmanMapper;

    /**
     * 查询当前管理员是否已关联业务员
     * @param adminid
     * @param userid
     * @return
     */
    public AdminWaysalesman getAdminUserByAdminAndUserid(Long adminid, Long userid) {
        return  adminWaysalesmanMapper.getAdminUserByAdminAndUserid(adminid,userid);

    }
    /**
     *
     * 添加关联关系
     * @param adminWaysalesman
     * @return
     */
    public int addManageUsersBylist(AdminWaysalesman adminWaysalesman) {
        adminWaysalesman.setCreatedate(new Date());
        adminWaysalesman.setDisable(false);
        return adminWaysalesmanMapper.insertSelective(adminWaysalesman);
    }

    public void delAdminUserByid(Long id) {
        adminWaysalesmanMapper.deleteByPrimaryKey(id);
    }

    public void deleteByUserid(Long userid) {
        adminWaysalesmanMapper.deleteByUserid(userid);
    }
}
