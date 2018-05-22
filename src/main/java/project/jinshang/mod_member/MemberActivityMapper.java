package project.jinshang.mod_member;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_company.bean.BuyerCompanyInfo;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.bean.MemberActivity;

/**
 * Created by Administrator on 2018/5/10.
 */
public interface MemberActivityMapper {
    @Select("select distinct mobile,id,username,realname from member where mobile =#{mobile} ")
    Member getMemberByMobile(@Param("mobile") String mobile);

    @Select("select * from buyercompanyinfo where memberid =#{id}")
    BuyerCompanyInfo getMemberCompanyByMemberid(@Param("id") long id);

    @Select("select * from memberactivity where mobile =#{mobile} ")
    MemberActivity getMemberActivityByMobile(@Param("mobile") String mobile);

    @Insert("insert into memberactivity (mobile,jointime,memberid,username,companyname) values (#{memberActivity.mobile},#{memberActivity.jointime},#{memberActivity.memberid},#{memberActivity.username},#{memberActivity.companyname})")
    int addmemberActivity(@Param("memberActivity")MemberActivity memberActivity);
}
