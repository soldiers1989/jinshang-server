package project.jinshang.mod_admin.mod_returnreason;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_admin.mod_returnreason.bean.ReturnReason;
import project.jinshang.mod_admin.mod_returnreason.bean.ReturnReasonExample;

public interface ReturnReasonMapper {
    int countByExample(ReturnReasonExample example);

    int deleteByExample(ReturnReasonExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ReturnReason record);

    int insertSelective(ReturnReason record);

    List<ReturnReason> selectByExample(ReturnReasonExample example);

    ReturnReason selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ReturnReason record, @Param("example") ReturnReasonExample example);

    int updateByExample(@Param("record") ReturnReason record, @Param("example") ReturnReasonExample example);

    int updateByPrimaryKeySelective(ReturnReason record);

    int updateByPrimaryKey(ReturnReason record);
    @Select("<script>select r.id,r.returnreason,r.responsibility,r.penalty,r.createtime,r.updatetime,a.realname,a.username " +
            "from returnreason r " +
            "left join admin a on a.id = r.adminid " +
            "<where> 1=1 " +
            "<if test=\"returnReason.returnreason != null \">and r.returnreason like '%${returnReason.returnreason}%' </if>" +
            "<if test=\"returnReason.adminid != null \">and r.adminid = #{returnReason.adminid} </if>" +
            "<if test=\"returnReason.remark != null \">and r.remark like '%${returnReason.remark}%' </if>" +
            "</where> order by r.id desc" +
            "</script>")
    List<Map<String,Object>> selectByObject(@Param("returnReason") ReturnReason returnReason);

    @Select("<script>select r.id,r.returnreason,r.responsibility,r.penalty " +
            "from returnreason r " +
            "<where> 1=1 " +
            "<if test=\"returnReason.returnreason != null \">and r.returnreason like '%${returnReason.returnreason}%' </if>" +
            "<if test=\"returnReason.adminid != null \">and r.adminid = #{returnReason.adminid} </if>" +
            "<if test=\"returnReason.remark != null \">and r.remark like '%${returnReason.remark}%' </if>" +
            "</where> order by r.id desc" +
            "</script>")
    List<Map<String,Object>> selectByObject1(@Param("returnReason") ReturnReason returnReason);

    @Select("<script>select r.id,r.returnreason,r.responsibility,r.penalty,r.createtime,r.updatetime " +
            "from returnreason r  where returnreason = #{returnbackreason} limit 1 " +
            "</script>")
    ReturnReason selectByReturnReason(@Param("returnbackreason") String returnbackreason);


}