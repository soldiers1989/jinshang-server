package project.jinshang.scheduled.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_product.bean.ProductName;

import java.util.List;

@Mapper
public interface AppTaskMapper {

    @Select("select p.* from productname p,categories c where p.typeid=c.id and  c.name=#{level3} ")
    public List<ProductName> getProductnameByLevel3(@Param("level3") String level3);

    @Select("select * from member")
    List<Member> getAllMember();

}
