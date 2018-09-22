package project.jinshang.mod_product.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_product.bean.SearchAttrSort;
import project.jinshang.mod_product.bean.SearchAttrSortExample;

public interface SearchAttrSortMapper {
    int countByExample(SearchAttrSortExample example);

    int deleteByExample(SearchAttrSortExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SearchAttrSort record);

    int insertSelective(SearchAttrSort record);

    List<SearchAttrSort> selectByExample(SearchAttrSortExample example);

    SearchAttrSort selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SearchAttrSort record, @Param("example") SearchAttrSortExample example);

    int updateByExample(@Param("record") SearchAttrSort record, @Param("example") SearchAttrSortExample example);

    int updateByPrimaryKeySelective(SearchAttrSort record);

    int updateByPrimaryKey(SearchAttrSort record);
}