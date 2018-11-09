package project.jinshang.mod_coupon;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_coupon.bean.YhqProject;
import project.jinshang.mod_coupon.bean.YhqProjectExample;

import java.util.List;
import java.util.Map;

public interface YhqProjectMapper {
    int countByExample(YhqProjectExample example);

    int deleteByExample(YhqProjectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(YhqProject record);

    int insertSelective(YhqProject record);

    List<YhqProject> selectByExample(YhqProjectExample example);

    YhqProject selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") YhqProject record, @Param("example") YhqProjectExample example);

    int updateByExample(@Param("record") YhqProject record, @Param("example") YhqProjectExample example);

    int updateByPrimaryKeySelective(YhqProject record);

    int updateByPrimaryKey(YhqProject record);

    @Select("<script>select p.id,p.no,p.name,p.type,p.status " +
            "from yhq_project p " +
            "<where> 1=1 " +
            "<if test=\"yhqProject.name != null \">and p.name LIKE '%${yhqProject.name}%'</if>" +
            "<if test=\"yhqProject.status != null \">and p.status = #{yhqProject.status}</if>" +
            "</where> order by p.id desc " +
            "</script>")
    List<Map<String,Object>> selectObject(@Param("yhqProject") YhqProject yhqProject);

   /* @Select("<script>select , " +
            "from yhq_project p " +
            "left join yhq_ticket t on t.projectid = p.id " +
            "<where> 1=1 " +
            "<if test=\"yhqProject.name != null \">and p.name = #{yhqProject.name}</if>" +
            "<if test=\"yhqProject.type != null \">and p.type = #{yhqProject.type}</if>" +
            "<if test=\"yhqProject.status != null \">and p.status = #{yhqProject.status}</if>" +
            "</where> order by p.id desc " +
            "</script>")
    List<Map<String,Object>> selectObject1(@Param("yhqProject") YhqProject yhqProject);*/
}