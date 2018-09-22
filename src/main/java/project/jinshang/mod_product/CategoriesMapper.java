package project.jinshang.mod_product;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
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
    List<Categories> getAll();


    @Select("select * from categories where catetype='紧固件' order by sort asc,id asc")
    @Cacheable(value = "CategoriesCache",key = "'project.jinshang.mod_product.CategoriesMapper.getAllFastener'")
    List<Categories> getAllFastener();


    @Select("select id,name,parentid,uprate,goldmemberrate,serverrate,thirdrate,secondrate,firstrate,freerate,businessrate,payrate,servernetrate from categories where parentid=#{parentid} order by sort asc")
    List<Categories> listFinanceRateSet(@Param("parentid") long parentid);


    @Select("select id,name,parentid,uprate,goldmemberrate,serverrate,thirdrate,secondrate,firstrate from categories where parentid=#{parentid} order by sort asc")
    List<Categories> listBusinessRate(@Param("parentid") long parentid);
    @Select("with recursive cte as(" +
            "select * from categories  " +
            "UNION ALL " +
            "select k.* from categories k inner join cte c on c.id = k.parentid " +
            ") " +
            "select DISTINCT cte.id,cte.*,f.ratio ,f.cid  ,f.id from cte  left join fx_categories f on cte.id = f.cid")
    List<Map<String,Object>> findCategories();

    @CacheEvict(value = "CategoriesCache",allEntries=true)
    @UpdateProvider(type=CategoriesMapper.CategoriesProvider.class,method = "updateAll")
    void updateAll(List<Categories> list);


    public class CategoriesProvider {
        public String updateAll(Map map){
            List<Categories> list = (List<Categories>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("update categories set sort=tmp.info from (values ");
            MessageFormat mf = new MessageFormat("(#'{'list[{0}].id},#'{'list[{0}].sort})");
            for (int i = 0; i < list.size(); i++) {
//                sb.append(mf.format(new Object[]{i}));
//                if (i < list.size() - 1) {
//                    sb.append(",");
//                }
                sb.append("("+list.get(i).getId()+","+list.get(i).getSort()+")");
                if (i < list.size() - 1) {
                    sb.append(",");
                }
            }
            sb.append(" )as tmp (id,info) where categories.id=tmp.id");

            System.out.println(sb.toString());
            return sb.toString();
        }
    }

    @Cacheable(value = "CategoriesCache",key = "'CategoriesMapper.getSort:' + #p0")
    @Select("select coalesce(sort, 0) from categories where id=#{param1}")
    Integer getSort(Long id);
}