package project.jinshang.mod_admin.mod_floor;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import project.jinshang.mod_admin.mod_floor.bean.Floor;
import project.jinshang.mod_admin.mod_floor.bean.FloorExample;

public interface FloorMapper {
    int countByExample(FloorExample example);

    @CacheEvict(value = "jinshang-floor",allEntries = true)
    int deleteByExample(FloorExample example);

    @CacheEvict(value = "jinshang-floor",allEntries = true)
    int deleteByPrimaryKey(Long id);

    @CacheEvict(value = "jinshang-floor",allEntries = true)
    int insert(Floor record);

    @CacheEvict(value = "jinshang-floor",allEntries = true)
    int insertSelective(Floor record);

    List<Floor> selectByExample(FloorExample example);

    Floor selectByPrimaryKey(Long id);

    @CacheEvict(value = "jinshang-floor",allEntries = true)
    int updateByExampleSelective(@Param("record") Floor record, @Param("example") FloorExample example);

    @CacheEvict(value = "jinshang-floor",allEntries = true)
    int updateByExample(@Param("record") Floor record, @Param("example") FloorExample example);

    @CacheEvict(value = "jinshang-floor",allEntries = true)
    int updateByPrimaryKeySelective(Floor record);

    @CacheEvict(value = "jinshang-floor",allEntries = true)
    int updateByPrimaryKey(Floor record);

    @Cacheable(value = "jinshang-floor",key = "'jinshang-floor-getFloorproducts-ids:'+#p0")
    @Select("select p.id,p.productname,p.subtitle,p.brand,p.stand,p.unit,p.pdpicture,p.salesnum,p.producttype,(select min(prodprice) from productstore pst where p.id=pst.pdid) as price from productinfo p where p.id in (${ids}) and p.pdstate=4 ")
    List<Map<String,Object>> getFloorproducts(@Param("ids") String ids);


    @Cacheable(value = "jinshang-floor",key = "'jinshang-floor-getRankingprod-id:'+#p0")
    @Select("select p.id,p.productname,p.subtitle,p.brand,p.stand,p.pdpicture,p.unit,p.pdpicture,p.producttype,p.level3,(select min(prodprice) from productstore pst where p.id=pst.pdid) as prodprice from productinfo p where p.id = #{id} and p.pdstate=4 ")
    Map<String,Object> getRankingprod(@Param("id") Long id);


    //查询真实楼层排行榜数据
    @Cacheable(value = "jinshang-floor",key = "'jinshang-floor-getRealRankingprodList-level2id:'+#p0+'-count:'+#p1")
    @Select("select p.id,p.productname,p.productalias,p.subtitle,p.brand,p.stand,p.pdpicture,p.material,p.salesnum as salenum,p.unit,p.producttype,p.level3,(select min(prodprice) from productstore where p.id=pdid ) as price from productinfo p where level2id=#{level2id} and  pdstate<>6  order by salesnum desc limit #{count}")
    List<Map<String,Object>> getRealRankingprodList(@Param("level2id") long level2id,@Param("count") int count);


    @Cacheable(value = "jinshang-floor",key="'getShowFloor'")
    @Select("select * from floor where isshow=1 order by sort asc")
    List<Floor> getShowFloor();

}