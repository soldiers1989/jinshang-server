package project.jinshang.mod_server;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_server.bean.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface ServerSetMapper {
    int countByExample(ServerSetExample example);

    int deleteByExample(ServerSetExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ServerSet record);

    int insertSelective(ServerSet record);

    List<ServerSet> selectByExample(ServerSetExample example);

    ServerSet selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ServerSet record, @Param("example") ServerSetExample example);

    int updateByExample(@Param("record") ServerSet record, @Param("example") ServerSetExample example);

    int updateByPrimaryKeySelective(ServerSet record);

    int updateByPrimaryKey(ServerSet record);

    @SelectProvider(type = ServerSetMapper.ServerPayProvider.class,method = "serverPayQuery")
    List<ServerPayPageModel> getServerPayList(ServerPayQueryParam param);

    public class ServerPayProvider{

        private static final String TBL_MEMBER = "Member mb";
        private static final String TBL_SERVER_SET = "serverset ss on mb.id=ss.memberid";
        private static final String TBL_ORDERS = "orders od on ss.area=od.area and ss.city=od.city and ss.province=od.province";
        private static final String TBL_BUYER_COMPANY_INFO = "buyercompanyinfo byi on mb.id=byi.memberid";

        public String serverPayQuery(ServerPayQueryParam param){

            StringBuffer sb = new StringBuffer();

            sb.append("byi.companyname,ss.servername,mb.realname,mb.username,ss.area,ss.city,ss.province,ss.rate,ss.id,");
            sb.append("case when sum(od.serverpay) is null then 0 ELSE sum(od.serverpay*ss.rate*0.01) END");

            SQL sql = new SQL().SELECT(sb.toString()).FROM(TBL_MEMBER);
            sql.LEFT_OUTER_JOIN(TBL_SERVER_SET);
            sql.LEFT_OUTER_JOIN(TBL_ORDERS);
            sql.LEFT_OUTER_JOIN(TBL_BUYER_COMPANY_INFO);

            sql.WHERE("mb.services=true");
            sql.WHERE("od.orderstatus=5");

            if(param.getMemberid()!=null){
                sql.WHERE("ss.memberid=#{memberid}");
            }

            if(param.getStartTime()!=null){
                sql.WHERE("od.buyerinspectiontime >=#{startTime}");
                sql.WHERE("ss.starttime<=#{startTime}");
            }
            if(param.getEndTime()!=null){
                Calendar c = Calendar.getInstance();
                c.setTime(param.getEndTime());
                c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
                Date tomorrow = c.getTime();
                param.setEndTime(tomorrow);
                sql.WHERE("od.buyerinspectiontime <=#{endTime}");
                sql.WHERE("ss.endtime>=#{endTime}");
            }

            sql.GROUP_BY("byi.companyname,mb.realname,mb.username,ss.servername,ss.area,ss.city,ss.province,ss.rate,ss.id");
            return sql.toString();
        }
    }

    @SelectProvider(type = ServerSetMapper.SettleProvider.class,method = "querySettle")
    List<ServerOrderModel> getServerOrderModelList(SettleQueryParam param);


    public class SettleProvider{

        private static final String TBL_ORDERS = "orders od";

        public String querySettle(SettleQueryParam param){

            StringBuffer sb = new StringBuffer();
            sb.append("od.createtime,od.membername,od.memberid,od.transactionnumber,od.id,od.orderno,od.area,od.actualpayment,od.freight,");
            sb.append("od.buyerinspectiontime,od.serverpay*#{rate}*0.01 as serverpay");

            SQL sql = new SQL().SELECT(sb.toString()).FROM(TBL_ORDERS);

            sql.WHERE("od.orderstatus=5");


            if(StringUtils.hasText(param.getArea())){
                sql.WHERE("od.area=#{area}");
            }
            if(StringUtils.hasText(param.getCity())){
                sql.WHERE("od.city=#{city}");
            }
            if(StringUtils.hasText(param.getProvince())){
                sql.WHERE("od.province=#{province}");
            }
            if(param.getStartTime()!=null){
                sql.WHERE("od.buyerinspectiontime >=#{startTime}");
            }
            if(param.getEndTime()!=null){
                Calendar c = Calendar.getInstance();
                c.setTime(param.getEndTime());
                c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
                Date tomorrow = c.getTime();
                param.setEndTime(tomorrow);
                sql.WHERE("od.buyerinspectiontime <=#{endTime}");
            }

            return sql.toString();
        }
    }

    @Select("SELECT sset.memberid,servername,companyname from serverset sset left join member mb on sset.memberid=mb.id left join buyercompanyinfo byi \n" +
            "on sset.memberid=byi.memberid where mb.services=TRUE GROUP BY sset.memberid,servername,companyname")
    List<ServerSet> getServerSetList();

    @Select("select id from serverset where area=#{area} and city=#{city} and province=#{province} and memberid=#{memberid}")
    ServerSet getSingelServerSetByArea(@Param("area") String area,@Param("city") String city,@Param("province") String province,@Param("memberid") Long memberid);

}
