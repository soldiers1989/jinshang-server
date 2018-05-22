package project.jinshang.mod_admin.mod_showcategory;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import project.jinshang.mod_admin.mod_showcategory.bean.ShowCatedetail;
import project.jinshang.mod_admin.mod_showcategory.bean.ShowCatedetailExample;

public interface ShowCatedetailMapper {
    int countByExample(ShowCatedetailExample example);

    @CacheEvict(value = "jinshang-listShowCate",allEntries = true)
    int deleteByExample(ShowCatedetailExample example);

    @CacheEvict(value = "jinshang-listShowCate",allEntries = true)
    int deleteByPrimaryKey(Long id);

    @CacheEvict(value = "jinshang-listShowCate",allEntries = true)
    int insert(ShowCatedetail record);

    @CacheEvict(value = "jinshang-listShowCate",allEntries = true)
    int insertSelective(ShowCatedetail record);

    List<ShowCatedetail> selectByExample(ShowCatedetailExample example);

    ShowCatedetail selectByPrimaryKey(Long id);

    @CacheEvict(value = "jinshang-listShowCate",allEntries = true)
    int updateByExampleSelective(@Param("record") ShowCatedetail record, @Param("example") ShowCatedetailExample example);

    @CacheEvict(value = "jinshang-listShowCate",allEntries = true)
    int updateByExample(@Param("record") ShowCatedetail record, @Param("example") ShowCatedetailExample example);

    @CacheEvict(value = "jinshang-listShowCate",allEntries = true)
    int updateByPrimaryKeySelective(ShowCatedetail record);

    @CacheEvict(value = "jinshang-listShowCate",allEntries = true)
    int updateByPrimaryKey(ShowCatedetail record);
}