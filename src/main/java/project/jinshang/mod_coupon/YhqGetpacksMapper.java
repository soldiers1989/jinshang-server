package project.jinshang.mod_coupon;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_coupon.bean.YhqGetpacks;
import project.jinshang.mod_coupon.bean.YhqGetpacksExample;

import java.util.List;
import java.util.Map;

public interface YhqGetpacksMapper {
    int countByExample(YhqGetpacksExample example);

    int deleteByExample(YhqGetpacksExample example);

    int deleteByPrimaryKey(Long id);

    int insert(YhqGetpacks record);

    int insertSelective(YhqGetpacks record);

    List<YhqGetpacks> selectByExample(YhqGetpacksExample example);

    YhqGetpacks selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") YhqGetpacks record, @Param("example") YhqGetpacksExample example);

    int updateByExample(@Param("record") YhqGetpacks record, @Param("example") YhqGetpacksExample example);

    int updateByPrimaryKeySelective(YhqGetpacks record);

    int updateByPrimaryKey(YhqGetpacks record);

    @Select("<script>select p.*,m.username,t.name " +
            "from yhq_getpacks p " +
            "left join yhq_ticketpacks t on t.id = p.packsid  " +
            "left join member m on m.id = p.memberid  "+
            "<where> 1=1 " +
            "<if test=\"yhqGetpacks.id != null \">and p.id = #{yhqGetpacks.id}</if>" +
            "<if test=\"yhqGetpacks.packsid != null \">and p.packsid = #{yhqGetpacks.packsid}</if>" +
            "<if test=\"membername != null \">and m.username like '%${membername}%'</if>" +
            "</where> order by p.id desc" +
            "</script>")
    List<Map<String,Object>> selectByObject(@Param("yhqGetpacks") YhqGetpacks yhqGetpacks, @Param("membername") String membername);

    @Select("<script>select p.*,m.username,t.name " +
            "from yhq_getpacks p " +
            "left join yhq_ticketpacks t on t.id = p.packsid " +
            "left join member m on m.id = p.memberid " +
            "<where> 1=1 " +
            "<if test=\"yhqGetpacks.id != null \">and p.id = #{yhqGetpacks.id}</if>" +
            "<if test=\"yhqGetpacks.packsid != null \">and p.packsid = #{yhqGetpacks.packsid}</if>" +
            "</where> order by p.id desc" +
            "</script>")
    List<Map<String,Object>> selectByObject1(@Param("yhqGetpacks") YhqGetpacks yhqGetpacks);
}