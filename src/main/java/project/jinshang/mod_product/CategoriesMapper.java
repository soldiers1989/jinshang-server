package project.jinshang.mod_product;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import project.jinshang.mod_product.bean.Categories;
import project.jinshang.mod_product.bean.CategoriesExample;


public interface CategoriesMapper {

    int countByExample(CategoriesExample example);

    @CacheEvict(value = "CategoriesCache",allEntries=true)
    int deleteByExample(CategoriesExample example);

    @CacheEvict(value = "CategoriesCache",allEntries=true)
    int deleteByPrimaryKey(Long id);

    @CacheEvict(value = "CategoriesCache",allEntries=true)
    int insert(Categories record);

    @CacheEvict(value = "CategoriesCache",allEntries=true)
    int insertSelective(Categories record);

    List<Categories> selectByExample(CategoriesExample example);

    @Cacheable(value = "CategoriesCache",key = "'CategoriesMapper.selectByPrimaryKey:' + #p0")
    Categories selectByPrimaryKey(Long id);

    @CacheEvict(value = "CategoriesCache",allEntries=true)
    int updateByExampleSelective(@Param("record") Categories record, @Param("example") CategoriesExample example);

    @CacheEvict(value = "CategoriesCache",allEntries=true)
    int updateByExample(@Param("record") Categories record, @Param("example") CategoriesExample example);

    @CacheEvict(value = "CategoriesCache",allEntries=true)
    int updateByPrimaryKeySelective(Categories record);

    @CacheEvict(value = "CategoriesCache",allEntries=true)
    int updateByPrimaryKey(Categories record);

    @Select("select * from categories where name=#{name}")
    Categories getCategoryByName(@Param("name") String name);

    @Select("select * from categories where name=#{name} and parentid=#{parentid}")
    Categories getCategoryByNameAndParentid(@Param("name") String name,@Param("parentid") long parentid);

    @Select("select count(id) from categories where parentid=#{parentid}")
    int hasSonCategoryCount(@Param("parentid") long parentid);

    @Select("select * from categories order by sort asc,id asc")
    @Cacheable(value = "CategoriesCache",key = "'project.jinshang.mod_product.CategoriesMapper.getAll'")
    List<Categories> getAll();


    @Select("select * from categories where catetype='紧固件' order by sort asc,id asc")
    @Cacheable(value = "CategoriesCache",key = "'project.jinshang.mod_product.CategoriesMapper.getAllFastener'")
    List<Categories> getAllFastener();


    @Select("select id,name,parentid,uprate,goldmemberrate,serverrate,thirdrate,secondrate,firstrate,freerate,businessrate,payrate,servernetrate from categories where parentid=#{parentid} order by sort asc")
    List<Categories> listFinanceRateSet(@Param("parentid") long parentid);


    @Select("select id,name,parentid,uprate,goldmemberrate,serverrate,thirdrate,secondrate,firstrate from categories where parentid=#{parentid} order by sort asc")
    List<Categories> listBusinessRate(@Param("parentid") long parentid);

}