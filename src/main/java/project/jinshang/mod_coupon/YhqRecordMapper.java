package project.jinshang.mod_coupon;

import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_coupon.bean.YhqRecord;
import project.jinshang.mod_coupon.bean.YhqRecordExample;

import java.util.List;

public interface YhqRecordMapper {
    int countByExample(YhqRecordExample example);

    int deleteByExample(YhqRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(YhqRecord record);

    int insertSelective(YhqRecord record);

    List<YhqRecord> selectByExample(YhqRecordExample example);

    YhqRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") YhqRecord record, @Param("example") YhqRecordExample example);

    int updateByExample(@Param("record") YhqRecord record, @Param("example") YhqRecordExample example);

    int updateByPrimaryKeySelective(YhqRecord record);

    int updateByPrimaryKey(YhqRecord record);
}