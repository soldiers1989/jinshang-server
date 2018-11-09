package project.jinshang.mod_admin.mod_count;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_admin.mod_count.bean.SearchKeyQueryParam;
import project.jinshang.mod_admin.mod_count.bean.SearchKeyRecord;
import project.jinshang.mod_admin.mod_count.bean.SearchKeyRecordExample;
import project.jinshang.mod_admin.mod_count.bean.SearchKeyStatistcModel;

public interface SearchKeyRecordMapper {
    int countByExample(SearchKeyRecordExample example);

    int deleteByExample(SearchKeyRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SearchKeyRecord record);

    int insertSelective(SearchKeyRecord record);

    List<SearchKeyRecord> selectByExample(SearchKeyRecordExample example);

    SearchKeyRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SearchKeyRecord record, @Param("example") SearchKeyRecordExample example);

    int updateByExample(@Param("record") SearchKeyRecord record, @Param("example") SearchKeyRecordExample example);

    int updateByPrimaryKeySelective(SearchKeyRecord record);

    int updateByPrimaryKey(SearchKeyRecord record);

    /**
     * 统计相同关键词 搜索次数
     * @param searchKeyQueryParam
     * @return
     */

    @Select("<script>select count(0) as countnum,s.searchkey " +
            "from searchkeyrecord s " +
            "<where> 1=1 " +
            "<if test=\"searchKeyQueryParam.startTime != null \">and s.createtime &gt;= #{searchKeyQueryParam.startTime} </if>" +
            "<if test=\"searchKeyQueryParam.endTime != null \">and s.createtime &lt;= #{searchKeyQueryParam.endTime} </if>" +
            "</where> group by s.searchkey order by count(0) desc " +
            "</script>")
    List<SearchKeyStatistcModel> searchcountStatistic(@Param("searchKeyQueryParam") SearchKeyQueryParam searchKeyQueryParam);

    @Select("<script>select count(0) as countnum,s.searchkey " +
            "from searchkeyrecord s " +
            "<where> 1=1 " +
            "<if test=\"searchKeyQueryParam.startTime != null \">and s.createtime &gt;= #{searchKeyQueryParam.startTime} </if>" +
            "<if test=\"searchKeyQueryParam.endTime != null \">and s.createtime &lt;= #{searchKeyQueryParam.endTime} </if>" +
            "</where> group by s.searchkey order by count(0) desc " +
            "</script>")
    List<Map<String,Object>> selectByObject(@Param("searchKeyQueryParam") SearchKeyQueryParam searchKeyQueryParam);
}