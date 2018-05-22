package project.jinshang.mod_admin.mod_transet;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import project.jinshang.mod_admin.mod_transet.bean.TransactionSetting;
import project.jinshang.mod_admin.mod_transet.bean.TransactionSettingExample;

public interface TransactionSettingMapper {

    @Cacheable(value = "project.jinshang.mod_admin.mod_transet.TransactionSettingMapper",key = "'project.jinshang.mod_admin.mod_transet.TransactionSettingMapper.countByExample'")
    int countByExample(TransactionSettingExample example);

    @CacheEvict(value = "project.jinshang.mod_admin.mod_transet.TransactionSettingMapper",allEntries=true)
    int insert(TransactionSetting record);

    @CacheEvict(value = "project.jinshang.mod_admin.mod_transet.TransactionSettingMapper",allEntries=true)
    int insertSelective(TransactionSetting record);

    @Cacheable(value = "project.jinshang.mod_admin.mod_transet.TransactionSettingMapper",key = "'project.jinshang.mod_admin.mod_transet.TransactionSettingMapper.selectByExample'")
    List<TransactionSetting> selectByExample(TransactionSettingExample example);

    @CacheEvict(value = "project.jinshang.mod_admin.mod_transet.TransactionSettingMapper",allEntries=true)
    int updateByExampleSelective(@Param("record") TransactionSetting record, @Param("example") TransactionSettingExample example);

    @CacheEvict(value = "project.jinshang.mod_admin.mod_transet.TransactionSettingMapper",allEntries=true)
    int updateByExample(@Param("record") TransactionSetting record, @Param("example") TransactionSettingExample example);


    @Select("select * from TransactionSetting limit 1")
    @Cacheable(value = "project.jinshang.mod_admin.mod_transet.TransactionSettingMapper",key = "'project.jinshang.mod_admin.mod_transet.TransactionSettingMapper.getTransactionSetting'")
    TransactionSetting getTransactionSetting();


}