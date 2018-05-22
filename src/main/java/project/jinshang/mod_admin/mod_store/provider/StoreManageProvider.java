package project.jinshang.mod_admin.mod_store.provider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_admin.mod_store.bean.StoreManageQueryDto;

/**
 * create : wyh
 * date : 2018/1/3
 */
public class StoreManageProvider {


    public  String searchManageList(@Param("dto")StoreManageQueryDto dto){
        SQL sql = new SQL();
        sql.SELECT(" S.*,M.username,C.companyname ");
        sql.FROM(" store S ");
        sql.JOIN(" member M on s.memberid=M.id ");
        sql.JOIN(" sellercompanyinfo C on S.memberid=C.memberid ");

        if(dto.getId() != null && dto.getId()>0) sql.WHERE(" s.id=#{id} ");
        if(StringUtils.hasText(dto.getCompanyname())) sql.WHERE(" C.companyname=#{companyname} ");
        if(StringUtils.hasText(dto.getName())) sql.WHERE(" S.name=#{name} ");
        if(dto.getCreatetimeStart() != null) sql.WHERE(" S.createtime>=#{createtimeStart} ");
        if(dto.getCreatetimeEnd() != null) sql.WHERE(" s.createtime < #{createtimeEnd} ");

        return  sql.toString();
    }

}
