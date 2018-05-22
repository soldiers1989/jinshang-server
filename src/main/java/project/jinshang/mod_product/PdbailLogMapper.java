package project.jinshang.mod_product;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_product.bean.PdbailLog;
import project.jinshang.mod_product.bean.PdbailLogExample;

public interface PdbailLogMapper {
    int countByExample(PdbailLogExample example);

    int deleteByExample(PdbailLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PdbailLog record);

    int insertSelective(PdbailLog record);

    List<PdbailLog> selectByExample(PdbailLogExample example);

    PdbailLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PdbailLog record, @Param("example") PdbailLogExample example);

    int updateByExample(@Param("record") PdbailLog record, @Param("example") PdbailLogExample example);

    int updateByPrimaryKeySelective(PdbailLog record);

    int updateByPrimaryKey(PdbailLog record);


    @Select("select * from pdbaillog where pdid=#{pdid} and type=#{type} and sellerid=#{sellerid} order by id desc limit 1")
    PdbailLog getLastLogByPdidType(@Param("pdid") long pdid,@Param("type") short type,@Param("sellerid") long sellerid);

}