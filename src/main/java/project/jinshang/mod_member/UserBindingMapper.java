package project.jinshang.mod_member;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_member.bean.UserBinding;
import project.jinshang.mod_member.bean.UserBindingExample;

public interface UserBindingMapper {
    int countByExample(UserBindingExample example);

    int deleteByExample(UserBindingExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserBinding record);

    int insertSelective(UserBinding record);

    List<UserBinding> selectByExample(UserBindingExample example);

    UserBinding selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserBinding record, @Param("example") UserBindingExample example);

    int updateByExample(@Param("record") UserBinding record, @Param("example") UserBindingExample example);

    int updateByPrimaryKeySelective(UserBinding record);

    int updateByPrimaryKey(UserBinding record);


    @Select("select * from userbinding where openid=#{openid} and type=#{type} and state=1 limit 1")
    UserBinding getByOpenid(@Param("openid") String openid,@Param("type") Short type);

    @Select("select count(id) from userbinding where openid=#{openid} and type=#{type} and state=1")
    int getBindingCount(@Param("openid") String openid,@Param("type") Short type);

    @Select("select * from userbinding where memberid=#{memberid} and type=#{type} and state=1 limit 1")
    UserBinding getByMemberid(@Param("memberid") long memberid,@Param("type") Short type);
}