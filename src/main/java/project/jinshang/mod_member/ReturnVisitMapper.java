package project.jinshang.mod_member;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_member.bean.ReturnVisit;
import project.jinshang.mod_member.bean.ReturnVisitExample;

public interface ReturnVisitMapper {
    int countByExample(ReturnVisitExample example);

    int deleteByExample(ReturnVisitExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ReturnVisit record);

    int insertSelective(ReturnVisit record);

    List<ReturnVisit> selectByExample(ReturnVisitExample example);

    ReturnVisit selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ReturnVisit record, @Param("example") ReturnVisitExample example);

    int updateByExample(@Param("record") ReturnVisit record, @Param("example") ReturnVisitExample example);

    int updateByPrimaryKeySelective(ReturnVisit record);

    int updateByPrimaryKey(ReturnVisit record);

    @Select("<script>select r.*,m.id,m.realname,m.username,m.labelname,m.mobile,m.waysalesman,a.realname as adminrealname  " +
            "from returnvisit r " +
            "left join member m on m.id = r.memberid " +
            "left join admin a on a.id = r.adminid " +
            "<where> 1=1 " +
            "<if test=\"username != null  and username != ''\">AND m.username LIKE '%${username}%' </if>" +
            "<if test=\"labelname != null and labelname !=''\">AND m.labelname = #{labelname} </if>" +
            "<if test=\"mobile != null and mobile !=''\">AND m.mobile LIKE  '%${mobile}%' </if>" +
            "<if test=\"waysalesman != null and waysalesman !=''\">AND m.waysalesman LIKE  '%${waysalesman}%' </if>" +
            "<if test=\"returnVisit.memberid != null \">AND r.memberid = #{returnVisit.memberid} </if>" +
            "<if test=\"returnVisit.type != null and returnVisit.type != '' \">AND r.type LIKE '%${returnVisit.type}%' </if>" +
            "<if test=\"startime != null and endtime != null \">and r.returntime between #{startime} and #{endtime} </if>" +
            "<if test=\"returnVisit.result != null and returnVisit.result != ''\">AND r.result LIKE '%${returnVisit.result}%' </if>" +
            "<if test=\"returnVisit.content != null and returnVisit.content != '' \">AND r.content LIKE '%${returnVisit.content}%' </if>" +
            "</where> order by r.id desc" +
            "</script>")
    List<Map<String,Object>> selectObject(@Param("mobile") String mobile,@Param("waysalesman") String waysalesman,@Param("username") String username,@Param("labelname") String labelname,@Param("startime") Date startime,@Param("endtime") Date endtime,@Param("returnVisit") ReturnVisit returnVisit);


    @Select("<script>select r.*,m.id,m.username,m.labelname  " +
            "from returnvisit r " +
            "left join member m on m.id = r.memberid " +
            "<where> 1=1 " +
            "<if test=\"memberid != null and memberid!=''\">AND r.memberid = #{memberid} </if>" +
            "</where> order by r.id desc" +
            "</script>")
    List<Map<String,Object>> getListByMemeberid(@Param("memberid") long memberid);
}