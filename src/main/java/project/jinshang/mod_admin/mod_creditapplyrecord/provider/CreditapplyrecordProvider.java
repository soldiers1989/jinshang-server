package project.jinshang.mod_admin.mod_creditapplyrecord.provider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import project.jinshang.common.utils.StringUtils;

import java.util.Map;

/**
 * create : wyh
 * date : 2017/12/1
 */
public class CreditapplyrecordProvider {




    public  String creditUser(@Param("memberid") long memberid){
        SQL sql =  new SQL();
        sql.SELECT(" M.id,M.username,M.company,G.gradename,M.realname,M.mobile,M.email,C.companyname,C.province,C.city,C.citysmall,C.address,M.creditlimit,M.usedlimit," +
                "(select count(id) from billcreate where buyerid=M.id) as billcount,(select count(id) from billcreate where illegal=true and buyerid=M.id) as illegalcount,(select sum(illegalmoney) from billcreate where illegal=true and buyerid=M.id) as totalillegalmoney," +
                "(select max(illegaldays) from billcreate where illegal=true and buyerid=M.id) as maxillegaldays");
        sql.FROM(" member M ");
        sql.LEFT_OUTER_JOIN(" buyercompanyinfo C on M.id=C.memberid ");
        sql.LEFT_OUTER_JOIN(" membergrade G on M.gradleid=G.id ");
        sql.WHERE(" M.id=#{memberid} ");

        return  sql.toString();
    }


    public  String listUserBillCreate(@Param("memberid") long memberid){
        SQL sql = new SQL();

        sql.FROM(" billcreate bc ");
        sql.SELECT(" bc.* ");
        if(memberid >0) sql.WHERE(" bc.buyerid=#{memberid} ");
        sql.ORDER_BY(" bc.id desc ");

        return  sql.toString();
    }




    public String listCreditUser(@Param("map") Map<String,Object> map){
        SQL sql =  new SQL();
        sql.SELECT(" M.id,M.username,M.mobile,M.creditlimit,M.usedlimit,M.creditstate,C.companyname,(select count(id) from billcreate where buyerid=M.id) as billcount ");
        sql.FROM(" member M ");
        sql.LEFT_OUTER_JOIN(" buyercompanyinfo C  on M.id=C.memberid ");

        sql.WHERE(" M.flag=true ");

        if(map.containsKey("memberid") && ((long) map.get("memberid"))>0){
            sql.WHERE(" M.id=#{memberid} ");
        }

        if(map.containsKey("username") && StringUtils.hasText(map.get("username").toString())){
            sql.WHERE(" M.username=#{username} ");
        }

        if(map.containsKey("realname") && StringUtils.hasText(map.get("realname").toString()) ){
            sql.WHERE(" M.realname=#{realname} ");
        }

        if(map.containsKey("companyname") && StringUtils.hasText(map.get("companyname").toString())){
            sql.WHERE(" C.companyname=#{companyname} ");
        }

        if(map.containsKey("mobile") && StringUtils.hasText(map.get("mobile").toString())){
            sql.WHERE(" M.mobile=#{mobile} ");
        }

        if(map.containsKey("creditstate") && ((short)map.get("creditstate")) != -1){
            sql.WHERE(" M.creditstate=#{creditstate} ");
        }




        sql.ORDER_BY("  M.id desc ");

        return  sql.toString();
    }


    /**
     * 后台授信会员导出Excel
     * @param map
     * @return  billcount 累计帐单数,totalillegalcount 累计违约数,maxillagedays 最长违约天数,totalillegalmoney 累计违约金额
     */
    public String listCreditUserForAdminExportExcle(@Param("map") Map<String,Object> map){

        SQL sql =  new SQL();
        sql.SELECT(" M.id,M.username,M.mobile,M.realname,M.email,M.company,M.creditlimit,M.usedlimit,M.availablelimit,M.creditstate," +
                "C.companyname,C.address as companyaddress,C.province as companyprovince,C.city as companycity,C.citysmall as companycitysmall," +
                "MG.gradename,(select count(id) from billcreate where buyerid=M.id) as billcount," +
                "(select count(id) from billcreate where buyerid=M.id and illegal=true) as totalillegalcount," +
                "(select max(illegaldays) from billcreate where buyerid=M.id ) as maxillagedays," +
                "(select sum(illegalmoney) from billcreate where buyerid=M.id ) as totalillegalmoney ");
        sql.FROM(" member M ");
        sql.LEFT_OUTER_JOIN(" buyercompanyinfo C  on M.id=C.memberid ");
        sql.LEFT_OUTER_JOIN(" membergrade MG on M.gradleid=MG.id ");

        sql.WHERE(" M.flag=true ");

        if(map.containsKey("memberid") && ((long) map.get("memberid"))>0){
            sql.WHERE(" M.id=#{memberid} ");
        }

        if(map.containsKey("username") && StringUtils.hasText(map.get("username").toString())){
            sql.WHERE(" M.username=#{username} ");
        }

        if(map.containsKey("realname") && StringUtils.hasText(map.get("realname").toString()) ){
            sql.WHERE(" M.realname=#{realname} ");
        }

        if(map.containsKey("companyname") && StringUtils.hasText(map.get("companyname").toString())){
            sql.WHERE(" C.companyname=#{companyname} ");
        }

        if(map.containsKey("mobile") && StringUtils.hasText(map.get("mobile").toString())){
            sql.WHERE(" M.mobile=#{mobile} ");
        }

        if(map.containsKey("creditstate") && ((short)map.get("creditstate")) != -1){
            sql.WHERE(" M.creditstate=#{creditstate} ");
        }



        sql.ORDER_BY("  M.id desc ");

        return  sql.toString();
    }





    public  String listForAdmin(@Param("map")Map<String,Object> map){
        SQL sql =  new SQL(){{
            SELECT("R.*,M.username");
            FROM(" creditapplyrecord R ");
            LEFT_OUTER_JOIN(" member M on R.memberid=M.id ");
            if(map.containsKey("state") && ((short)map.get("state") != -1)) {
                WHERE("R.state=#{state}");
            }

            if(map.containsKey("memberid") && (long)map.get("memberid") != -1 ){
                WHERE(" R.memberid=#{memberid} ");
            }

            if(map.containsKey("contract") && StringUtils.hasText((String) map.get("contract"))){
                WHERE(" R.contract=#{contract} ");
            }
            if(map.containsKey("phone") && StringUtils.hasText((String) map.get("phone"))){
                WHERE(" R.phone=#{phone} ");
            }
            if(map.containsKey("company") && StringUtils.hasText((String) map.get("company"))){
                WHERE(" R.company like '%"+ map.get("company")+"%'" );
            }


            if(map.containsKey("id") && (long)map.get("id") != -1 ){
                WHERE(" R.id=#{id} ");
            }


            if(map.containsKey("startDate") && map.get("startDate") != null ){
                WHERE(" createtime>=#{startDate} ");
            }


            if(map.containsKey("endDate") && map.get("endDate") != null ){
                WHERE(" createtime-interval '1 days' <= #{endDate}");
            }


            ORDER_BY(" id desc ");
        }};
        return  sql.toString();
    }



    public  String listForAdminExcel(@Param("map")Map<String,Object> map){
        SQL sql =  new SQL(){{
            SELECT("R.*,M.username");
            FROM(" creditapplyrecord R ");
            LEFT_OUTER_JOIN(" member M on R.memberid=M.id ");
            if(map.containsKey("state") && ((short)map.get("state") != -1)) {
                WHERE("R.state=#{state}");
            }

            if(map.containsKey("memberid") && (long)map.get("memberid") != -1 ){
                WHERE(" R.memberid=#{memberid} ");
            }

            if(map.containsKey("contract") && StringUtils.hasText((String) map.get("contract"))){
                WHERE(" R.contract=#{contract} ");
            }
            if(map.containsKey("phone") && StringUtils.hasText((String) map.get("phone"))){
                WHERE(" R.phone=#{phone} ");
            }
            if(map.containsKey("company") && StringUtils.hasText((String) map.get("company"))){
                WHERE(" R.company=#{company} ");
            }


            if(map.containsKey("id") && (long)map.get("id") != -1 ){
                WHERE(" R.id=#{id} ");
            }


            if(map.containsKey("startDate") && map.get("startDate") != null ){
                WHERE(" createtime>=#{startDate} ");
            }


            if(map.containsKey("endDate") && map.get("endDate") != null ){
                WHERE(" createtime-interval '1 days' <= #{endDate}");
            }


            ORDER_BY(" id desc ");
        }};
        return  sql.toString();
    }


}
