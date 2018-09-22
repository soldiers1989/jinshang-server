package project.jinshang.mod_member.provider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_member.bean.dto.MemberLabelQueryDto;

/**
 * create : wyh
 * date : 2017/12/30
 */
public class MemberLabelProvider {

    public  String getLabelList(@Param("queryDto") MemberLabelQueryDto queryDto){
//        SQL sql =  new SQL();
//        sql.FROM(" memberlabel ml ");
//        sql.SELECT(" ml.* ");
//        if(StringUtils.hasText(queryDto.getLabelname())){
//            sql.WHERE(" ml.labelname like '%${queryDto.labelname}%' ");
//        }

        StringBuilder sql = new StringBuilder(" select * from memberlabel ml where 1=1 ");

        if(StringUtils.hasText(queryDto.getLabelname())){
            sql.append(" and  ml.labelname like '%"+queryDto.getLabelname()+"%'");
        }

        if(StringUtils.hasText(queryDto.getLabelname())){
            sql.append(" and  ml.remarks like '%"+queryDto.getRemarks()+"%'");
        }

        return  sql.toString();
    }


    public  String getMemberLabelCount(@Param("id") long id){
        String sql = "select count(id) from member where labelid like '%"+id+",%'";
        return  sql;
    }

}
