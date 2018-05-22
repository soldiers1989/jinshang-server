package project.jinshang.mod_admin.mod_showcategory;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheEvict;
import project.jinshang.mod_admin.mod_showcategory.bean.ShowCate;
import project.jinshang.mod_admin.mod_showcategory.bean.ShowCateExample;

public interface ShowCateMapper {
    int countByExample(ShowCateExample example);

    @CacheEvict(value = "jinshang-listShowCate",allEntries = true)
    int deleteByExample(ShowCateExample example);

    @CacheEvict(value = "jinshang-listShowCate",allEntries = true)
    int deleteByPrimaryKey(Long id);

    @CacheEvict(value = "jinshang-listShowCate",allEntries = true)
    int insert(ShowCate record);

    @CacheEvict(value = "jinshang-listShowCate",allEntries = true)
    int insertSelective(ShowCate record);

    List<ShowCate> selectByExample(ShowCateExample example);

    ShowCate selectByPrimaryKey(Long id);

    @CacheEvict(value = "jinshang-listShowCate",allEntries = true)
    int updateByExampleSelective(@Param("record") ShowCate record, @Param("example") ShowCateExample example);

    @CacheEvict(value = "jinshang-listShowCate",allEntries = true)
    int updateByExample(@Param("record") ShowCate record, @Param("example") ShowCateExample example);

    @CacheEvict(value = "jinshang-listShowCate",allEntries = true)
    int updateByPrimaryKeySelective(ShowCate record);

    @CacheEvict(value = "jinshang-listShowCate",allEntries = true)
    int updateByPrimaryKey(ShowCate record);

    @Select("select * from showcate order  by  sort asc ")
    List<ShowCate> listAll();
}