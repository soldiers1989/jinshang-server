package project.jinshang.mod_admin.mod_count;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.cache.annotation.Cacheable;
import project.jinshang.mod_admin.mod_count.bean.ProductView;
import project.jinshang.mod_admin.mod_count.bean.ProductViewExample;

public interface ProductViewMapper {
    int countByExample(ProductViewExample example);

    int deleteByExample(ProductViewExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProductView record);

    int insertSelective(ProductView record);

    List<ProductView> selectByExample(ProductViewExample example);

    ProductView selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProductView record, @Param("example") ProductViewExample example);

    int updateByExample(@Param("record") ProductView record, @Param("example") ProductViewExample example);

    int updateByPrimaryKeySelective(ProductView record);

    int updateByPrimaryKey(ProductView record);


    @Select("select * from productview where pdid=#{pdid} and yyyymmdd=#{yyyymmdd}  order by id desc limit 1")
    ProductView selectByPdidAndYyyymmdd(@Param("pdid") long pdid,@Param("yyyymmdd") int yyyymmdd);

    //添加访问次数
    @Update("update productview set count = count + #{count} where id=#{id}")
    int addCount(@Param("id") Long id,@Param("count") int count);

//    @Cacheable(value = "jinshang-productview",key = "'getMaxCountByyyyymmdd-yyyyymmdd:'+#p0+'-count:'+#p1")
    @Select("select pw.pdid,pw.count,pw.yyyymmdd,pi.productname from productview pw,productinfo pi  where pw.pdid=pi.id and  pw.yyyymmdd=#{yyyymmdd} ORDER BY  count desc limit #{count}")
    List<Map<String,Object>> getMaxCountByyyyymmdd(@Param("yyyymmdd") int yyyymmdd,@Param("count") int count);


//    @Cacheable(value = "jinshang-productview",key = "'getViewList-yyyymmdd_start:'+#p0+'-yyyymmdd_end:'+#p1+'-ids:'+#p2")
    @Select("select pw.pdid,pw.count,pw.yyyymmdd,pi.productname from productview pw,productinfo pi  where pw.pdid=pi.id and  pw.yyyymmdd >= #{yyyymmdd_start} and pw.yyyymmdd <= #{yyyymmdd_end} and pw.pdid in (${ids}) order by pw.id desc ")
    List<Map<String,Object>> getViewList(@Param("yyyymmdd_start") int yyyymmdd_start,@Param("yyyymmdd_end") int yyyymmdd_end,@Param("ids") String ids);




}