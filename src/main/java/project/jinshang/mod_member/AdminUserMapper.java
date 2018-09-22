package project.jinshang.mod_member;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_member.bean.AdminUser;
import project.jinshang.mod_member.bean.AdminUserExample;


public interface AdminUserMapper {
    int countByExample(AdminUserExample example);

    int deleteByExample(AdminUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    List<AdminUser> selectByExample(AdminUserExample example);

    AdminUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdminUser record, @Param("example") AdminUserExample example);

    int updateByExample(@Param("record") AdminUser record, @Param("example") AdminUserExample example);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);

     @Select("select * from adminuser where adminid =#{adminid} and userid=#{userid} limit 1")
    AdminUser getAdminUserByAdminAndUserid(@Param("adminid") Long adminid,@Param("userid")Long userid);

    @Select("select * from adminuser where userid=#{userid} limit 1")
    AdminUser getAdminUserByUserid(@Param("userid")Long userid);

    @Delete("DELETE FROM adminuser WHERE userid = #{userid}")
    int delAdminUserByuserid(@Param("userid")Long userid);

    @Select("select * from adminuser where adminid=#{adminid}")
    List<AdminUser> findAdminUserByAdminid(@Param("adminid")Long adminid);


}