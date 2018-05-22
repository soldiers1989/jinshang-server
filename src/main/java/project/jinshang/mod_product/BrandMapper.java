package project.jinshang.mod_product;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import project.jinshang.mod_product.bean.Brand;
import project.jinshang.mod_product.bean.BrandExample;

public interface BrandMapper {
    int countByExample(BrandExample example);

    int deleteByExample(BrandExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Brand record);

    int insertSelective(Brand record);

    List<Brand> selectByExample(BrandExample example);

    Brand selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Brand record, @Param("example") BrandExample example);

    int updateByExample(@Param("record") Brand record, @Param("example") BrandExample example);

    int updateByPrimaryKeySelective(Brand record);

    int updateByPrimaryKey(Brand record);

    @Select("select * from brand where name=#{name} limit 1")
    Brand getByName(@Param("name") String name);


    @Select("select * from brand where name=#{name} and (categoryid=#{cateid} or categoryid=-1) and auditstate=1")
    Brand getByNameAndCateid(@Param("name") String name,@Param("cateid") Long cateid);


    @Select("select * from brand where name=#{name} and categoryid in (${ids}) limit 1")
    Brand getBrandByIdsAndName(@Param("ids") String ids,@Param("name") String name);

    @Select("<script>select * from brand where (categoryid=#{cateid} or categoryid=-1) " +
            "<if test=\"auditstate != null and auditstate != -1\"> and auditstate=#{auditstate} </if>" +
            "</script>")
    List<Brand> getByCateid(@Param("cateid") Long cateid,@Param("auditstate") Short auditstate);
}