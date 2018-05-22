package project.jinshang.mod_member;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_member.bean.AdminExample;

public interface AdminMapper {
    int countByExample(AdminExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);



    @Select("select * from admin where  to_tsvector(array_to_string(roles, ' ')) @@ to_tsquery('${roles}')")
    @Results({
            @Result(column = "roles",property = "roles",typeHandler =project.jinshang.typeHandler.ArrayTypeHandler.class )
    })
    List<Admin> getAdminByRoles(@Param("roles") String roles);

}