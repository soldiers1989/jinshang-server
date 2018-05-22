package project.jinshang.mod_admin.mod_paymode;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_admin.mod_paymode.bean.PayMode;
import project.jinshang.mod_admin.mod_paymode.bean.PayModeExample;

public interface PayModeMapper {
    int countByExample(PayModeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PayMode record);

    int insertSelective(PayMode record);

    List<PayMode> selectByExample(PayModeExample example);

    PayMode selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PayMode record, @Param("example") PayModeExample example);

    int updateByExample(@Param("record") PayMode record, @Param("example") PayModeExample example);

    int updateByPrimaryKeySelective(PayMode record);

    int updateByPrimaryKey(PayMode record);
}