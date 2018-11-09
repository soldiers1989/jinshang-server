package project.jinshang.mod_coupon;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import project.jinshang.mod_coupon.bean.YhqTicketpacks;
import project.jinshang.mod_coupon.bean.YhqTicketpacksExample;

import java.util.List;
import java.util.Map;

public interface YhqTicketpacksMapper {
    int countByExample(YhqTicketpacksExample example);

    int deleteByExample(YhqTicketpacksExample example);

    int deleteByPrimaryKey(Long id);

    int insert(YhqTicketpacks record);

    int insertSelective(YhqTicketpacks record);

    List<YhqTicketpacks> selectByExample(YhqTicketpacksExample example);

    YhqTicketpacks selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") YhqTicketpacks record, @Param("example") YhqTicketpacksExample example);

    int updateByExample(@Param("record") YhqTicketpacks record, @Param("example") YhqTicketpacksExample example);

    int updateByPrimaryKeySelective(YhqTicketpacks record);

    int updateByPrimaryKey(YhqTicketpacks record);

    @Select("<script>select p.id,p.no,p.name,p.starttime,p.endtime,p.packscount,p.status " +
            "from yhq_ticketpacks p " +
            "<where> 1=1 " +
            "<if test=\"yhqTicketpacks.name != null \">and p.name LIKE '%${yhqTicketpacks.name}%' </if>" +
            "<if test=\"yhqTicketpacks.status != null \">and p.status = #{yhqTicketpacks.status} </if>" +
            "</where> order by p.id desc" +
            "</script>")
    List<Map<String,Object>> selectByObject(@Param("yhqTicketpacks") YhqTicketpacks yhqTicketpacks);

    @Select("<script>select p.id,p.no,p.name,p.starttime,p.endtime,p.packscount,p.status,t.name,t.ticketcount " +
            "from yhq_ticketpacks p " +
            "<if test=\"ticketid != null \">left join yhq_ticketset t on t.id = #{ticketid} </if>" +
            "<where> 1=1 " +
            "<if test=\"id != null \">and p.id = #{id} </if>" +
            "</where> order by p.id desc" +
            "</script>")
    List<Map<String,Object>> selectById(@Param("id") Long id, @Param("ticketid") Long ticketid);

    @Select("<script>select p.id,p.no,p.name,p.starttime,p.endtime,p.packscount,p.status,p.surpluscount,p.ticketlist " +
            "from yhq_ticketpacks p " +
            "<where> 1=1 " +
            "<if test=\"no != null \">and p.no = #{no} </if>" +
            "</where> and surpluscount  &gt; 0 order by p.id desc" +
            "</script>")
    List<YhqTicketpacks> selectByNoAndSurplusCount(@Param("no") String no);

    @Select("select p.* from yhq_ticketpacks p where p.status = 99 order by p.id desc ")
    List<YhqTicketpacks> getAllYhqTicketpacks();

    @Update("update yhq_ticketpacks set surpluscount = #{surpluscount} where id = #{id} ")
    void updateSurpluscount(@Param("id") Long id, @Param("surpluscount") Long surpluscount);

    @Select("select * from yhq_ticketpacks order by createtime DESC limit 1")
    List<Map<String,Object>> selectByObject1(YhqTicketpacks yhqTicketpacks);

    @Select("select * from yhq_ticketpacks where name = #{name} order by createtime DESC limit 1 ")
    YhqTicketpacks getYhqTicketpacksInfoByName(@Param("name") String name);
}