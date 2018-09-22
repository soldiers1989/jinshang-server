package project.jinshang.mod_member;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_member.bean.AdminWaysalesman;
import project.jinshang.mod_member.bean.AdminWaysalesmanExample;

public interface AdminWaysalesmanMapper {
    int countByExample(AdminWaysalesmanExample example);

    int deleteByExample(AdminWaysalesmanExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdminWaysalesman record);

    int insertSelective(AdminWaysalesman record);

    List<AdminWaysalesman> selectByExample(AdminWaysalesmanExample example);

    AdminWaysalesman selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdminWaysalesman record, @Param("example") AdminWaysalesmanExample example);

    int updateByExample(@Param("record") AdminWaysalesman record, @Param("example") AdminWaysalesmanExample example);

    int updateByPrimaryKeySelective(AdminWaysalesman record);

    int updateByPrimaryKey(AdminWaysalesman record);

    @Select("select * from adminwaysalesman where adminid =#{adminid} and userid=#{userid} limit 1")
    AdminWaysalesman getAdminUserByAdminAndUserid(@Param("adminid") Long adminid,@Param("userid") Long userid);

    @Delete("delete from adminwaysalesman where userid=#{userid}")
    void deleteByUserid(Long userid);
}