package project.jinshang.mod_product.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_product.bean.SearchAttrSortValue;
import project.jinshang.mod_product.bean.SearchAttrSortValueExample;

public interface SearchAttrSortValueMapper {
    int countByExample(SearchAttrSortValueExample example);

    int deleteByExample(SearchAttrSortValueExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SearchAttrSortValue record);

    int insertSelective(SearchAttrSortValue record);

    List<SearchAttrSortValue> selectByExample(SearchAttrSortValueExample example);

    SearchAttrSortValue selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SearchAttrSortValue record, @Param("example") SearchAttrSortValueExample example);

    int updateByExample(@Param("record") SearchAttrSortValue record, @Param("example") SearchAttrSortValueExample example);

    int updateByPrimaryKeySelective(SearchAttrSortValue record);

    int updateByPrimaryKey(SearchAttrSortValue record);
}