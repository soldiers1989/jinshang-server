package project.jinshang.mod_member.provider;

import org.apache.ibatis.annotations.Param;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_member.bean.dto.MemberAdminQueryDto;

/**
 * create : wyh
 * date : 2017/12/9
 */
public class MemberProvider {

    public  String adminList(@Param("dto") MemberAdminQueryDto dto){

        StringBuilder sql = new StringBuilder("select M.id,M.username,M.realname,M.disabled,M.createdate,M.lastlogindate,M.labelid,M.clerkname,G.gradename,bc.companyname from member M left join membergrade G on M.gradleid=G.id left join buyercompanyinfo bc on M.id=bc.memberid where parentid=0 and flag=true ");

        if(dto.getIsbuy()!=null && dto.getIsbuy() !=0){
            sql.append(" and M.isbuy=#{dto.isbuy} ");
        }

        if(dto.getId() != null &&  dto.getId() >0){
            sql.append(" and M.id=#{dto.id} ");
        }

        if(StringUtils.hasText(dto.getUsername())){
            sql.append(" and (M.username like #{dto.username} or bc.companyname like #{dto.username} or M.realname like #{dto.username} )");
        }


        if(StringUtils.hasText(dto.getClerkname())){
            sql.append(" and M.clerkname like #{dto.clerkname} ");
        }

        if(StringUtils.hasText(dto.getMobile())){
            sql.append(" and M.mobile like #{dto.mobile} ");
        }

        if(dto.getRegistDateStart() != null){
            sql.append(" and M.createdate >= #{dto.registDateStart} ");
        }

        if(dto.getRegistDateEnd() != null){
            sql.append(" and M.createdate <= #{dto.registDateEnd} ");
        }


        if(dto.getLoginDateStart() != null){
            sql.append(" and M.lastlogindate >= #{dto.loginDateStart} ");
        }

        if(dto.getLoginDateEnd() != null){
            sql.append(" and M.lastlogindate <= #{dto.loginDateEnd} ");
        }


        if(dto.getLabelid() != null){
            sql.append(" and (M.labelid like '%,"+dto.getLabelid()+",%' or M.labelid like '"+dto.getLabelid()+",%') ");
        }


        if(dto.getGradleid() != null){
            sql.append(" and M.gradleid=#{dto.gradleid} ");
        }

        if(dto.getCompanyType() >0){
            if(dto.getCompanyType() == Quantity.STATE_1){
                sql.append(" and M.company=true ");
            }else if(dto.getCompanyType() == Quantity.STATE_2){
                sql.append(" and M.company=false ");
            }
        }

        sql.append(" order by M.id desc ");

        return  sql.toString();
    }


    /**
     * 后台导出excel Sql
     * @param dto
     * @return
     */
    public  String adminListForExcelExport(@Param("dto") MemberAdminQueryDto dto){

        StringBuilder sql = new StringBuilder("select M.*,G.gradename,C.id as companyid,C.shortname,C.companyname," +
                "C.address as companyaddress,C.province as companyprovince," +
                "C.city as companycity,C.citysmall as companycitysmall,C.worktelephone,C.bankname,C.bankaccount," +
                "C.mobile as companymobile,C.taxregistrationcertificate,C.bankdeposit from member M left join membergrade G on M.gradleid=G.id  " +
                "left join buyercompanyinfo C on M.id=C.memberid   where parentid=0 and flag=true ");

        if(dto.getIsbuy()!=null && dto.getIsbuy() !=0){
            sql.append(" and M.isbuy=#{dto.isbuy} ");
        }

        if(dto.getId() != null &&  dto.getId() >0){
            sql.append(" and M.id=#{dto.id} ");
        }

        if(StringUtils.hasText(dto.getUsername())){
            sql.append(" and (M.username like #{dto.username} or bc.companyname like #{dto.username} or M.realname like #{dto.username} )");
        }


        if(StringUtils.hasText(dto.getClerkname())){
            sql.append(" and M.clerkname like #{dto.clerkname} ");
        }

        if(StringUtils.hasText(dto.getMobile())){
            sql.append(" and M.mobile like #{dto.mobile} ");
        }

        if(dto.getRegistDateStart() != null){
            sql.append(" and M.createdate >= #{dto.registDateStart} ");
        }

        if(dto.getRegistDateEnd() != null){
            sql.append(" and M.createdate <= #{dto.registDateEnd} ");
        }


        if(dto.getLoginDateStart() != null){
            sql.append(" and M.lastlogindate >= #{dto.loginDateStart} ");
        }

        if(dto.getLoginDateEnd() != null){
            sql.append(" and M.lastlogindate <= #{dto.loginDateEnd} ");
        }


        if(dto.getLabelid() != null){
            sql.append(" and (M.labelid like '%,"+dto.getLabelid()+",%' or M.labelid like '"+dto.getLabelid()+",%') ");
        }


        if(dto.getGradleid() != null){
            sql.append(" and M.gradleid=#{dto.gradleid} ");
        }

        if(dto.getCompanyType() >0){
            if(dto.getCompanyType() == Quantity.STATE_1){
                sql.append(" and M.company=true ");
            }else if(dto.getCompanyType() == Quantity.STATE_2){
                sql.append(" and M.company=false ");
            }
        }

        sql.append(" order by M.id desc ");

        return  sql.toString();
    }


}
