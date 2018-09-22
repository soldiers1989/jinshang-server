package project.jinshang.mod_fx;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_fx.bean.Fxcategories;
import project.jinshang.mod_fx.bean.FxcategoriesExample;


public interface FxcategoriesMapper {
    int countByExample(FxcategoriesExample example);

    int deleteByExample(FxcategoriesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Fxcategories record);

    int insertSelective(Fxcategories record);

    List<Fxcategories> selectByExample(FxcategoriesExample example);

    Fxcategories selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Fxcategories record, @Param("example") FxcategoriesExample example);

    int updateByExample(@Param("record") Fxcategories record, @Param("example") FxcategoriesExample example);

    int updateByPrimaryKeySelective(Fxcategories record);

    int updateByPrimaryKey(Fxcategories record);

    @Select("<script>select f.* " +
            "from fx_categories f " +
            "<where> 1=1 " +
            "<if test=\"cid != null \">and f.cid = #{cid} </if>" +
            "</where> order by f.id desc" +
            "</script>")
    Fxcategories getCategoriesInfoById(@Param("cid") Long cid);

    @Select("update fx_categories f set ratio = ${ratio}   where f.cid = #{cid} ")
    void updateFxCategories(@Param("cid") Long cid, @Param("ratio") BigDecimal ratio);
}