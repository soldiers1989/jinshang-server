package project.jinshang.mod_fx;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_fx.bean.Fxcommision;
import project.jinshang.mod_fx.bean.FxcommisionExample;
import project.jinshang.mod_fx.dto.FxcommisionDto;
import project.jinshang.mod_member.bean.Member;


public interface FxcommisionMapper {
    int countByExample(FxcommisionExample example);

    int deleteByExample(FxcommisionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Fxcommision record);

    int insertSelective(Fxcommision record);

    List<Fxcommision> selectByExample(FxcommisionExample example);

    Fxcommision selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Fxcommision record, @Param("example") FxcommisionExample example);

    int updateByExample(@Param("record") Fxcommision record, @Param("example") FxcommisionExample example);

    int updateByPrimaryKeySelective(Fxcommision record);

    int updateByPrimaryKey(Fxcommision record);

    @Select("select sum(commisionprice) as countcommisionprice from fx_commision f where progressNum=99 and cmemberid = #{cmemberid} ")
    FxcommisionDto getCountCommisionPriceBycMemberId(@Param("cmemberid") Long cmemberid);

    @Select("select sum(commisionprice)   as allcountcommisionprice from fx_commision f where progressNum=99 ")
    FxcommisionDto getAllCountCommisionPrice();

    @Select("select sum(commisionprice)  as waitcommisionprice from fx_commision f where progressNum in (1,2) and cmemberid = #{cmemberid} ")
    FxcommisionDto getWaitCommisionPriceBycMemberId(@Param("cmemberid") Long cmemberid);

    @Select("select sum(commisionprice)  as allwaitcommisionprice from fx_commision f where progressNum in (1,2) ")
    FxcommisionDto getAllWaitCommisionPrice();

    @Select("<script>select f.*,m1.username buyname ,m2.username salename,m3.username  returnname " +
            "from fx_commision f " +
            "left join member m1 on m1.id = f.memberid " +
            "left join member m2 on m2.id = f.saleid " +
            "left join member m3 on m3.id = f.cmemberid " +
            "<where> 1=1 " +
            "<if test=\"cmemberid != null \">and f.cmemberid = #{cmemberid} </if>" +
            "<if test=\"startime != null \">and f.createtime &gt;= #{startime} </if>" +
            "<if test=\"endtime != null \">and f.createtime &lt;= #{endtime} </if>" +
            "<if test=\"fxcommision.orderno != null \">and f.orderno = #{fxcommision.orderno} </if>" +
            "<if test=\"fxcommision.cmemberid != null \">and f.cmemberid = #{fxcommision.cmemberid} </if>" +
            "<if test=\"fxcommision.memberid != null \">and f.memberid = #{fxcommision.memberid} </if>" +
            "<if test=\"fxcommision.progressnum != null  \">and f.progressnum = #{fxcommision.progressnum} </if>" +
            "</where> order by f.id desc " +
            "</script>")
    List<Map<String,Object>> selectObject(@Param("startime")Date startime, @Param("endtime") Date endtime, @Param("fxcommision") Fxcommision fxcommision,@Param("cmemberid")long cmemberid );


    @Select("<script>select f.*,m1.username buyname  ,m2.username salename,m3.username returnname " +
            "from fx_commision f " +
            "left join member m1 on m1.id = f.memberid " +
            "left join member m2 on m2.id = f.saleid " +
            "left join member m3 on m3.id = f.cmemberid " +
            "<where> 1=1 " +
            "<if test=\"startime != null \">and f.createtime &gt;= #{startime} </if>" +
            "<if test=\"endtime != null \">and f.createtime &lt;= #{endtime} </if>" +
            "<if test=\"fxcommision.orderno != null \">and f.orderno = #{fxcommision.orderno} </if>" +
            "<if test=\"fxcommision.cmemberid != null \">and f.cmemberid = #{fxcommision.cmemberid} </if>" +
            "<if test=\"fxcommision.memberid != null \">and f.memberid = #{fxcommision.memberid} </if>" +
            "<if test=\"fxcommision.progressnum != null  \">and f.progressnum = #{fxcommision.progressnum} </if>" +
            "</where> order by f.id desc " +
            "</script>")
    List<Map<String,Object>> selectObject1(@Param("startime")Date startime, @Param("endtime") Date endtime, @Param("fxcommision") Fxcommision fxcommision,@Param("member")Member member );

    @Select("select f.id,f.orderid,f.orderno,f.ordercreatetime,f.buyerinspectiontime,f.progressnum,f.commisionprice,f.cmemberid,f.memberid,f.saleid,f.type  from fx_commision f  where progressNum in(1,2)")
    List<Fxcommision> getFxcommisionList();
}