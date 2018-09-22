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

    public  String adminList(@Param("adminrealname")String adminrealname,@Param("dto") MemberAdminQueryDto dto){

        StringBuilder sql = new StringBuilder("select M.id,M.username,M.realname,M.disabled,M.createdate,M.lastlogindate,M.labelid,M.clerkname,M.waysalesman,M.registesource,M.registersourcelabel,M.registertypelabel,M.registerchannellabel,M.labelname,G.gradename,bc.companyname from member M left join membergrade G on M.gradleid=G.id left join buyercompanyinfo bc on M.id=bc.memberid   where parentid=0 and flag=true ");

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

        if(StringUtils.hasText(dto.getWaysalesman())){
            sql.append(" and M.waysalesman like #{dto.waysalesman} ");
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

        if(StringUtils.hasText(dto.getLabelname())){
            sql.append(" and M.labelname =#{dto.labelname} ");
        }

        if(dto.getGradleid() != null){
            sql.append(" and M.gradleid=#{dto.gradleid} ");
        }
        /*if (dto.getRegistesource()!=null&&dto.getRegistesource()!=(short)-1){
            sql.append(" and M.registesource=#{dto.registesource}");
        }*/
        if(StringUtils.hasText(dto.getRegistersourcelabel())){
            sql.append(" and M.registersourcelabel like #{dto.registersourcelabel} ");
        }

        if(StringUtils.hasText(dto.getRegistertypelabel())){
            sql.append(" and M.registertypelabel like #{dto.registertypelabel} ");
        }

        if(StringUtils.hasText(dto.getRegisterchannellabel())){
            sql.append(" and M.registerchannellabel like #{dto.registerchannellabel} ");
        }

        if(dto.getCompanyType() >0){
            if(dto.getCompanyType() == Quantity.STATE_1){
                sql.append(" and M.company=true ");
            }else if(dto.getCompanyType() == Quantity.STATE_2){
                sql.append(" and M.company=false ");
            }
        }
        if(StringUtils.hasText(adminrealname)){
            sql.append("and ( M.clerkname = #{adminrealname} or  M.waysalesman =#{adminrealname}) ");
        }

        sql.append(" order by M.id desc  ");

        return  sql.toString();
    }


    /**
     * 后台导出excel Sql
     * @param dto
     * @return
     */
    public  String adminListForExcelExport(@Param("adminrealname")String adminrealname,@Param("dto") MemberAdminQueryDto dto){

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
            sql.append(" and (M.username like #{dto.username} or C.companyname like #{dto.username} or M.realname like #{dto.username} )");
        }


        if(StringUtils.hasText(dto.getClerkname())){
            sql.append(" and M.clerkname like #{dto.clerkname} ");
        }

        if(StringUtils.hasText(dto.getMobile())){
            sql.append(" and M.mobile like #{dto.mobile} ");
        }

        if(StringUtils.hasText(dto.getWaysalesman())){
            sql.append(" and M.waysalesman like #{dto.waysalesman} ");
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

        if(dto.getLabelname() != null){
            sql.append(" and M.labelname =#{dto.labelname} ");

        }


        if(dto.getGradleid() != null){
            sql.append(" and M.gradleid=#{dto.gradleid} ");
        }


        if(StringUtils.hasText(dto.getRegistersourcelabel())){
            sql.append(" and M.registersourcelabel like #{dto.registersourcelabel} ");
        }

        if(StringUtils.hasText(dto.getRegistertypelabel())){
            sql.append(" and M.registertypelabel like #{dto.registertypelabel} ");
        }

        if(StringUtils.hasText(dto.getRegisterchannellabel())){
            sql.append(" and M.registerchannellabel like #{dto.registerchannellabel} ");
        }


        if(dto.getCompanyType() >0){
            if(dto.getCompanyType() == Quantity.STATE_1){
                sql.append(" and M.company=true ");
            }else if(dto.getCompanyType() == Quantity.STATE_2){
                sql.append(" and M.company=false ");
            }
        }

        if(StringUtils.hasText(adminrealname)){
            sql.append("and ( M.clerkname = #{adminrealname} or  M.waysalesman =#{adminrealname}) ");
        }

        sql.append(" order by M.id desc ");

        return  sql.toString();
    }


    /**
     * 后台获取用户信息的总记录数
     * @param dto
     * @return
     */
    public String getAdminCount(@Param("dto") MemberAdminQueryDto dto){

        StringBuilder sql = new StringBuilder("select count(1) from member M left join membergrade G on M.gradleid=G.id  " +
                "left join buyercompanyinfo C on M.id=C.memberid where parentid=0 and flag=true ");

        if(dto.getIsbuy()!=null && dto.getIsbuy() !=0){
            sql.append(" and M.isbuy=#{dto.isbuy} ");
        }

        if(dto.getId() != null &&  dto.getId() >0){
            sql.append(" and M.id=#{dto.id} ");
        }

        if(StringUtils.hasText(dto.getUsername())){
            sql.append(" and (M.username like #{dto.username} or C.companyname like #{dto.username} or M.realname like #{dto.username} )");
        }


        if(StringUtils.hasText(dto.getClerkname())){
            sql.append(" and M.clerkname like #{dto.clerkname} ");
        }

        if(StringUtils.hasText(dto.getMobile())){
            sql.append(" and M.mobile like #{dto.mobile} ");
        }

        if(StringUtils.hasText(dto.getWaysalesman())){
            sql.append(" and M.waysalesman like #{dto.waysalesman} ");
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

        //sql.append(" order by M.id desc ");

        return  sql.toString();
    }

}
