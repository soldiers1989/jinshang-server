package project.jinshang.mod_coupon;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import project.jinshang.mod_coupon.bean.YhqTicketset;
import project.jinshang.mod_coupon.bean.YhqTicketsetExample;

import java.util.List;
import java.util.Map;

public interface YhqTicketsetMapper {
    int countByExample(YhqTicketsetExample example);

    int deleteByExample(YhqTicketsetExample example);

    int deleteByPrimaryKey(Long id);

    int insert(YhqTicketset record);

    int insertSelective(YhqTicketset record);

    List<YhqTicketset> selectByExample(YhqTicketsetExample example);

    YhqTicketset selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") YhqTicketset record, @Param("example") YhqTicketsetExample example);

    int updateByExample(@Param("record") YhqTicketset record, @Param("example") YhqTicketsetExample example);

    int updateByPrimaryKeySelective(YhqTicketset record);

    int updateByPrimaryKey(YhqTicketset record);

    @Select("<script>select p.id,p.no,p.name,p.type,p.starttime,p.endtime,p.ticketcount,p.createtype,p.validitystarttime,p.validityendtime,p.status,p.planid  " +
            "from yhq_ticketset p " +
            "<where> 1=1 " +
            "<if test=\"yhqTicketset.name != null \">and p.name = #{yhqTicketset.name} </if>" +
            "<if test=\"yhqTicketset.planid != null \">and p.planid = #{yhqTicketset.planid} </if>" +
            "<if test=\"yhqTicketset.type != null \">and p.type = #{yhqTicketset.type} </if>" +
            "<if test=\"yhqTicketset.status != null \">and p.status = #{yhqTicketset.status} </if>" +
            "</where> order by p.id desc" +
            "</script>")
    List<Map<String,Object>> selectByObject(@Param("yhqTicketset") YhqTicketset yhqTicketset);

    @Update("update yhq_ticketset set surpluscount = #{surpluscount} where id = #{id} ")
    void updateSurpluscount(@Param("id") Long id, @Param("surpluscount") Long surpluscount);
}