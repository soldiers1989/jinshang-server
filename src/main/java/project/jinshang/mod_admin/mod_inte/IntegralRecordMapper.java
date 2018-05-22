package project.jinshang.mod_admin.mod_inte;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_admin.mod_inte.bean.IntegralPageModle;
import project.jinshang.mod_admin.mod_inte.bean.IntegralQueryParam;
import project.jinshang.mod_admin.mod_inte.bean.IntegralRecord;
import project.jinshang.mod_admin.mod_inte.bean.IntegralRecordExample;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IntegralRecordMapper {
    int countByExample(IntegralRecordExample example);

    int deleteByExample(IntegralRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(IntegralRecord record);

    int insertSelective(IntegralRecord record);

    List<IntegralRecord> selectByExample(IntegralRecordExample example);

    IntegralRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") IntegralRecord record, @Param("example") IntegralRecordExample example);

    int updateByExample(@Param("record") IntegralRecord record, @Param("example") IntegralRecordExample example);

    int updateByPrimaryKeySelective(IntegralRecord record);

    int updateByPrimaryKey(IntegralRecord record);


    /**
     * 导出excel积分信息
     * @param param
     * @return
     */
    @SelectProvider(type = IntegralProvider.class,method = "queryIntegral")
    public List<Map<String,Object>> getIntegralExcel(IntegralQueryParam param);

    /**
     * 查询用户积分信息
     * @param param
     * @return
     */
    @SelectProvider(type = IntegralProvider.class,method = "queryIntegral")
    public List<IntegralPageModle> getIntegralPageModle(IntegralQueryParam param);

    public class IntegralProvider{

        private final String TBL_MEMBER = "member mem";
        private final String TBL_MEMBER_GRADE = "membergrade grade ON mem.gradleid = grade.id";

        public String queryIntegral(IntegralQueryParam param){
            SQL sql = new SQL().SELECT("mem.id,mem.username,mem.availableIntegral,grade.gradename,mem.integrals,mem.createdate").FROM(TBL_MEMBER);
            sql.LEFT_OUTER_JOIN(TBL_MEMBER_GRADE);

            if(param.getMemberId()!=null){
                sql.WHERE("mem.id=#{memberId}");
            }

            if(StringUtils.hasText(param.getMemberName())){
                param.setMemberName("%"+param.getMemberName()+"%");
                sql.WHERE("mem.username like #{memberName}");
            }

            //开始时间
            Date startTime = param.getStartTime();
            if(startTime!=null){
                sql.WHERE("mem.createdate >=#{startTime}");
            }
            //结束时间
            Date endTime = param.getEndTime();
            if(endTime!=null){
                Calendar c = Calendar.getInstance();
                c.setTime(endTime);
                c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
                Date tomorrow = c.getTime();
                param.setEndTime(tomorrow);
                sql.WHERE("mem.createdate <=#{endTime}");
            }
            sql.ORDER_BY(" mem.id desc ");
            return  sql.toString();
        }
    }
}