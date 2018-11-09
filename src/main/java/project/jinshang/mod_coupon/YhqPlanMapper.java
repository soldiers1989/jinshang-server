package project.jinshang.mod_coupon;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_coupon.bean.YhqPlan;
import project.jinshang.mod_coupon.bean.YhqPlanExample;

import java.util.List;
import java.util.Map;

public interface YhqPlanMapper {
    int countByExample(YhqPlanExample example);

    int deleteByExample(YhqPlanExample example);

    int deleteByPrimaryKey(Long id);

    int insert(YhqPlan record);

    int insertSelective(YhqPlan record);

    List<YhqPlan> selectByExample(YhqPlanExample example);

    YhqPlan selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") YhqPlan record, @Param("example") YhqPlanExample example);

    int updateByExample(@Param("record") YhqPlan record, @Param("example") YhqPlanExample example);

    int updateByPrimaryKeySelective(YhqPlan record);

    int updateByPrimaryKey(YhqPlan record);

    @Select("<script>select p.id,p.name,sum(t.ticketcount) as ticketcount,sum(t.surpluscount) as  surpluscount  " +
            "from yhq_plan p " +
            "left join yhq_ticketset t on t.planid = p.id " +
            "<where> 1=1 " +
            "<if test=\"yhqPlan.name != null \">and p.name LIKE '%${yhqPlan.name}%' </if>" +
            "</where>group by p.id order by p.id desc" +
            "</script>")
    List<Map<String,Object>> selectObject(@Param("yhqPlan") YhqPlan yhqPlan);



}