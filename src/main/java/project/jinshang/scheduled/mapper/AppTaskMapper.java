package project.jinshang.scheduled.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_product.bean.ProductName;

import java.util.List;
import java.util.Map;

@Mapper
public interface AppTaskMapper {

    @Select("select p.* from productname p,categories c where p.typeid=c.id and  c.name=#{level3} ")
    public List<ProductName> getProductnameByLevel3(@Param("level3") String level3);

    @Select("select * from member")
    List<Member> getAllMember();

    @Select("select ps.id psid,ps.pdid,ps.storeid,ps.intervalprice,pi.id piid,pi.memberid,pi.packagetype from productstore ps LEFT JOIN productinfo pi on ps.pdid=pi.id \n" +
            "where ps.storeid= 88 and pi.producttype='紧固件' and pi.packagetype is not null and pi.packagetype != ''")
    List<Map<String,Object>> getProductinfoList();

    @Update("update productstore set intervalprice = #{jsonList} where id = #{psid}")
    void updateProductStore(@Param("psid") long psid,@Param("jsonList") String jsonList);


    @Select("select av.*,atl.name from  attvalue av ,attributetbl atl where atl.id=av.attid and (atl.name='公称直径' or atl.name='长度')")
    List<Map> getAttrValue();

    @Select("select stand from productinfo where producttype='紧固件' group by stand order by stand asc")
    List<String> getGroupByStand();

}
