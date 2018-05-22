package project.jinshang.mod_member;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_member.bean.Favorite;
import project.jinshang.mod_member.bean.FavoriteExample;
import project.jinshang.mod_member.bean.dto.FavoriteProductDto;

public interface FavoriteMapper {
    int countByExample(FavoriteExample example);

    int deleteByExample(FavoriteExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Favorite record);

    int insertSelective(Favorite record);

    List<Favorite> selectByExample(FavoriteExample example);

    Favorite selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Favorite record, @Param("example") FavoriteExample example);

    int updateByExample(@Param("record") Favorite record, @Param("example") FavoriteExample example);

    int updateByPrimaryKeySelective(Favorite record);

    int updateByPrimaryKey(Favorite record);


    @Delete("delete from favorite where pid=#{pid} and memberid=#{memberid}")
    int delByIdAndMemberid(@Param("pid") long pid, @Param("memberid") long memberid);


    @Select("select F.id,F.createtime,P.id as pid,P.productname,P.pdpicture as pic from favorite F ,productinfo P where F.pid=P.id and F.memberid=#{memberid} order by F.id desc")
    List<FavoriteProductDto> list(@Param("memberid") long memberid);

    @Select("SELECT DISTINCT ON(F.id)F.id,F.createtime,F.pid,ps.storename,ps.pdstorenum,ps.startnum,pi.productname,pi.producttype,\n" +
            "ps.prodprice,pi.material,pi.cardnum,pi.brand,pi.packagetype,pi.level3,pi.tag,pi.pdpicture,pi.stand,br.pic ,pi.selfsupport\n" +
            "FROM favorite F \n" +
            "LEFT JOIN productstore ps on F.pid=ps.pdid\n" +
            "LEFT JOIN productinfo pi on pi.id=F.pid \n" +
            "LEFT JOIN brand br on pi.brandid=br.id \n" +
            "where F.memberid=#{memberId} order by F.id desc")
    List<FavoriteProductDto> listByMemberId(@Param("memberId") Long memberId);
}