package project.jinshang.mod_activity.provider;

import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_activity.bean.dto.LimitTimeProdQuery;

/**
 * create : wyh
 * date : 2018/1/11
 */
public class LimitTimeActivityProvider {

    /**
     * 卖家端列表查询
      * @param query
     * @return
     */
    public  String  listBuyPageForSeller(LimitTimeProdQuery query){
        StringBuilder sql = new StringBuilder("select  p.level1,p.level2,p.level3,p.productname,p.brand,p.cardnum,p.mark,p.material,p.stand,p.producttype,p.unit,l.* from productinfo p,limittimeprod l WHERE p.id =  l.pdid and l.state<>7 ");

        if(query.getMemberid() != null){
            sql.append(" and l.memberid=#{memberid} ");
        }

        if(query.getState() != null){
            sql.append(" and l.state=#{state} ");
        }

        if(query.getBegintime() != null){
            sql.append(" and l.begintime >= #{begintime} ");
        }

        if(query.getEndtime() != null){
            sql.append(" and l.endtime < #{endtime} ");
        }

        if(StringUtils.hasText(query.getActivitytitle())){
            sql.append(" and l.activitytitle like #{activitytitle} ");
        }
        sql.append(" order by l.id desc ");
        return sql.toString();
    }


    /**
     * 后台列表查询
     * @param query
     * @return
     */
    public  String  listBuyPageForAdmin(LimitTimeProdQuery query){
        StringBuilder sql = new StringBuilder("select  p.level1,p.level2,p.level3,p.productname,p.brand,p.cardnum,p.mark,p.material,p.unit,p.stand,p.producttype,l.*," +
                "(select sum(actualpayment) from orderproduct where limitid=l.id and paystate=1) as sumsalsmoney" +
                " from productinfo p,limittimeprod l WHERE p.id =  l.pdid and l.state<>7 ");

        if(query.getMemberid() != null){
            sql.append(" and l.memberid=#{memberid} ");
        }

        if(query.getState() != null){
            sql.append(" and l.state=#{state} ");
        }


        if(query.getBegintime() != null){
            sql.append(" and l.begintime >= #{begintime} ");
        }

        if(query.getEndtime() != null){
            sql.append(" and l.endtime < #{endtime} ");
        }


        if(StringUtils.hasText(query.getActivitytitle())){
//            sql.append(" and l.activitytitle like '%"+query.getActivitytitle()+"%' ");
            sql.append(" and l.activitytitle like #{activitytitle} ");
        }

        if(query.getActivitycateid() != null && query.getActivitycateid() >0){
            sql.append(" and l.categoryid=#{activitycateid} ");
        }
        sql.append(" order by l.id desc ");
        return sql.toString();
    }





    public  String  listBuyPageForAdminExportExcel(LimitTimeProdQuery query){
        StringBuilder sql = new StringBuilder("select  p.level1,p.level2,p.level3,p.productname,p.brand,p.cardnum,p.mark,p.material,p.unit,p.stand,p.producttype,l.*," +
                "(select sum(actualpayment) from orderproduct where limitid=l.id and paystate=1) as sumsalsmoney" +
                " from productinfo p,limittimeprod l WHERE p.id =  l.pdid and l.state<>7 ");

        if(query.getMemberid() != null){
            sql.append(" and l.memberid=#{memberid} ");
        }

        if(query.getState() != null){
            sql.append(" and l.state=#{state} ");
        }


        if(query.getBegintime() != null){
            sql.append(" and l.begintime >= #{begintime} ");
        }

        if(query.getEndtime() != null){
            sql.append(" and l.endtime < #{endtime} ");
        }


        if(StringUtils.hasText(query.getActivitytitle())){
//            sql.append(" and l.activitytitle like '%"+query.getActivitytitle()+"%' ");
            sql.append(" and l.activitytitle like #{activitytitle}) ");
        }

        if(query.getActivitycateid() != null && query.getActivitycateid() >0){
            sql.append(" and l.categoryid=#{activitycateid} ");
        }
        sql.append(" order by l.id desc ");
        return sql.toString();
    }



    public  String  listBuyPageForFront(@Param("query") LimitTimeProdQuery query, @Param("befoBuytime") int befoBuytime){
        StringBuilder sql = new StringBuilder("select  p.level1,p.level2,p.level3,p.productname,p.brand,p.cardnum,p.mark,p.material,p.stand,p.producttype,p.unit,p.pdpicture,l.* from productinfo p,limittimeprod l WHERE p.id =  l.pdid  ");

        if(query.getState() == Quantity.STATE_0) {  //所有， 进行中的和预热中的
            sql.append(" and ((l.state=1 ");
            if (befoBuytime > 0) {
                sql.append(" and  begintime - now() <= '" + befoBuytime + " hour') ");
            }
            sql.append(" or l.state=4) ");
        }else if(query.getState() == Quantity.STATE_4){
            sql.append(" and l.state=4 ");
        }else if(query.getState() == Quantity.STATE_9){
            sql.append(" and l.state=1 and   begintime -now() <= '" + befoBuytime + " hour' ");
        }

        if(StringUtils.hasText(query.getActivitytitle())){
            sql.append(" and l.activitytitle=#{query.activitytitle} ");
        }

        if(query.getActivitycateid() != null && query.getActivitycateid() >0){
            sql.append(" and l.categoryid=#{query.activitycateid} ");
        }

        return sql.toString();
    }


    /**
     * 卖家端导出限时购活动excel
     * @param query
     * @return
     */
    public  String  listBuyPageForSellerExportExcel(LimitTimeProdQuery query){
        StringBuilder sql = new StringBuilder("select p.productname,p.brand,p.cardnum,p.mark,p.material,p.stand,p.producttype,p.unit,l.* from productinfo p,limittimeprod l WHERE p.id =  l.pdid and l.state<>7 ");

        if(query.getMemberid() != null){
            sql.append(" and l.memberid=#{memberid} ");
        }

        if(query.getState() != null){
            sql.append(" and l.state=#{state} ");
        }

        if(query.getBegintime() != null){
            sql.append(" and l.begintime >= #{begintime} ");
        }

        if(query.getEndtime() != null){
            sql.append(" and l.endtime < #{endtime} ");
        }

        if(StringUtils.hasText(query.getActivitytitle())){
            sql.append(" and l.activitytitle like #{activitytitle} ");
        }

        sql.append(" order by l.id desc ");
        return sql.toString();
    }



}
