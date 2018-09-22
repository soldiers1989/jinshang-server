package project.jinshang.mod_member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_member.bean.Registersource;
import project.jinshang.mod_member.bean.RegistersourceExample;

public interface RegistersourceMapper {
    int countByExample(RegistersourceExample example);

    int deleteByExample(RegistersourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Registersource record);

    int insertSelective(Registersource record);

    List<Registersource> selectByExample(RegistersourceExample example);

    Registersource selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Registersource record, @Param("example") RegistersourceExample example);

    int updateByExample(@Param("record") Registersource record, @Param("example") RegistersourceExample example);

    int updateByPrimaryKeySelective(Registersource record);

    int updateByPrimaryKey(Registersource record);

    @Select("<script>select  r.* " +
            "from registersource r " +
            "<where> 1=1 " +
            "<if test=\"registersource.label != null  \">and r.label like '%${registersource.label}%' </if>" +
            "<if test=\"registersource.labelname != null  \">and r.labelname like '%${registersource.labelname}%' </if>" +
            "<if test=\"registersource.type != null  \">and r.type = #{registersource.type} </if>" +
            "</where> order by r.id desc" +
            "</script>")
    List<Map<String,Object>> selectAll(@Param("registersource") Registersource registersource);

    @Select("<script>select  r.* " +
            "from registersource r " +
            "<where> 1=1 " +
            "<if test=\"registersource.label != null  \">and r.label like '%${registersource.label}%' </if>" +
            "<if test=\"registersource.labelname != null  \">and r.labelname like '%${registersource.labelname}%' </if>" +
            "<if test=\"registersource.type != null  \">and r.type = #{registersource.type} </if>" +
            "</where> order by r.id desc" +
            "</script>")
    List<Registersource> selectRegistersourceByType(@Param("registersource") Registersource registersource);
}