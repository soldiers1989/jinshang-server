package project.jinshang.mod_coupon;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import project.jinshang.mod_coupon.bean.YhqTicket;
import project.jinshang.mod_coupon.bean.YhqTicketExample;
import project.jinshang.mod_coupon.dto.YhqCheckParamDto;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface YhqTicketMapper {
    int countByExample(YhqTicketExample example);

    int deleteByExample(YhqTicketExample example);

    int deleteByPrimaryKey(Long id);

    int insert(YhqTicket record);

    int insertSelective(YhqTicket record);

    List<YhqTicket> selectByExample(YhqTicketExample example);

    YhqTicket selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") YhqTicket record, @Param("example") YhqTicketExample example);

    int updateByExample(@Param("record") YhqTicket record, @Param("example") YhqTicketExample example);

    int updateByPrimaryKeySelective(YhqTicket record);

    int updateByPrimaryKey(YhqTicket record);
    @Select("<script>select t.*  " +
            "from yhq_ticket t " +
            "<where> 1=1 " +
            "<if test=\"yhqTicket.name != null  \">and t.name LIKE '%${yhqTicket.name}%' </if>" +
            "<if test=\"yhqTicket.type != null and yhqTicket.type !=0 \" >and t.type = #{yhqTicket.type} </if>" +
            "<if test=\"yhqTicket.planid != null and yhqTicket.planid !=0 \">and t.planid = #{yhqTicket.planid} </if>" +
            "<if test=\"yhqTicket.ticketsetid != null and yhqTicket.ticketsetid !=0 \">and t.ticketsetid = #{yhqTicket.ticketsetid} </if>" +
            "<if test=\"yhqTicket.status != null and yhqTicket.status !=0 \">and t.status = #{yhqTicket.status} </if>" +
            "</where> order by t.id desc" +
            "</script>")
    List<Map<String,Object>> selectByObject(@Param("yhqTicket") YhqTicket yhqTicket);


    @Select("<script>select  t.* " +
            "from yhq_ticket t " +
            "<where> 1=1 " +
            "<if test=\"yhqTicket.name != null and yhqTicket.name !=0 \">and t.name LIKE '%${yhqTicket.name}%'</if>" +
            "<if test=\"yhqTicket.type != null and yhqTicket.type !=0 \" >and t.type = #{yhqTicket.type} </if>" +
            "<if test=\"yhqTicket.planid != null and yhqTicket.planid !=0 \">and t.planid = #{yhqTicket.planid} </if>" +
            "<if test=\"yhqTicket.ticketsetid != null and yhqTicket.ticketsetid !=0 \">and t.ticketsetid = #{yhqTicket.ticketsetid} </if>" +
            "<if test=\"yhqTicket.status != null and yhqTicket.status !=0 \">and t.status = #{yhqTicket.status} </if>" +
            "<if test=\"yhqTicket.projectid != null  \">and t.projectid = #{yhqTicket.projectid} </if>" +
            "</where> order by t.id desc " +
            "</script>")
    List<Map<String,Object>> selectByObject1(@Param("yhqTicket") YhqTicket yhqTicket);



    @Select("<script>select  t.*,m.username,o.orderno,o.paymenttime,p.type as projecttype " +
            "from yhq_ticket t " +
            "left join yhq_project p on p.id = t.projectid  " +
            "left join member m on m.id = t.memberid  " +
            "left join orders o on o.ticketno= t.no  " +
            "<where> 1=1 " +
            "<if test=\"id != null and id!=''\">and t.id = #{id} </if>" +
            "</where>order by t.id desc " +
            "</script>")
    List<Map<String,Object>> getListYhqTicketInfoById(@Param("id") Long id);

    @Select("<script>select  t.* " +
            "from yhq_ticket t " +
            "<where> 1=1 " +
            "<if test=\"ticketid1 != null  \">and t.ticketsetid = #{ticketid1} </if>" +
            "<if test=\"status != null  \">and t.status = #{status} </if>" +
            "</where>order by t.id desc " +
            "</script>")
    List<Map<String,Object>> selectByTicketSetIdAndStatus(@Param("ticketid1") long ticketid1, @Param("status") int status);

    @Update("<script>update  yhq_ticket t set  t.status = 2,t.memberid = #{memberid},t.gettime = #{createtime}" +
            "<where> 1=1 " +
            "<if test=\"no != null  \">and t.no = #{no} </if>" +
            "</where>order by t.id desc " +
            "</script>")
    YhqTicket updateByYhqTicket(@Param("no") String no, @Param("createtime") Date createtime, @Param("memberid") long memberid);

    @Select("<script>select  t.* " +
            "from yhq_ticket t " +
            "<where> 1=1 " +
            "<if test=\"memberid != null  \">and t.memberid = #{memberid} </if>" +
            "<if test=\"nowdate != null \">and #{nowdate} BETWEEN t.validitystarttime and t.validityendtime and t.status = 2  </if>" +
            "</where>order by t.id desc " +
            "</script>")
    List<YhqTicket> getNotUseYhqTicket(@Param("memberid") long memberid, @Param("nowdate") Date nowdate);


    @Select("<script>select  t.* " +
            "from yhq_ticket t " +
            "<where> 1=1 " +
            "<if test=\"memberid != null  \">and t.memberid = #{memberid} </if>" +
            "<if test=\"status != null \">and t.status = #{status} </if>" +
            "</where>order by t.id desc " +
            "</script>")
    List<YhqTicket> getUseYhqTicket(@Param("memberid") long memberid,@Param("status")int status);

    @Select("<script>select  t.* " +
            "from yhq_ticket t " +
            "<where> 1=1 " +
            "<if test=\"memberid != null  \">and t.memberid = #{memberid} </if>" +
            "<if test=\"nowdate != null \">and #{nowdate} &gt; t.validityendtime  and status =2  and status !=99 and status !=97 </if>" +
            "</where>order by t.id desc " +
            "</script>")
    List<YhqTicket> getExpiretYhqTicket(@Param("memberid") long memberid, @Param("nowdate") Date nowdate);


    @Select("<script> select  * from yhq_ticket where memberid=#{dto.memberid} and status=2 and (validitystarttime &lt; #{dto.checkTime} and  validityendtime &gt; #{dto.checkTime} )" +
            "<if test=\"dto.type!=null\"> and type=#{dto.type} </if>"+
            "</script>")
    List<YhqTicket>  getYhqTicketByDto(@Param("dto")YhqCheckParamDto yhqCheckParamDto);

    @Select("select * from yhq_ticket where no=#{ticketno} limit 1")
    YhqTicket  getYhqTicketByNo(@Param("ticketno") String ticketno);

    @Update("update yhq_ticket set status=#{view.status} , usertime=#{view.usertime} where no=#{view.no}")
    int updateByOrders(@Param("view") YhqTicket yhqTicket);


}