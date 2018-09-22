package project.jinshang.mod_product;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_product.bean.LogisticsInfo;
import project.jinshang.mod_product.bean.LogisticsInfoExample;
import project.jinshang.mod_product.bean.dto.LogisticsInfoDto;

public interface LogisticsInfoMapper {
    int countByExample(LogisticsInfoExample example);

    int deleteByExample(LogisticsInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LogisticsInfo record);

    int insertSelective(LogisticsInfo record);

    List<LogisticsInfo> selectByExample(LogisticsInfoExample example);

    LogisticsInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LogisticsInfo record, @Param("example") LogisticsInfoExample example);

    int updateByExample(@Param("record") LogisticsInfo record, @Param("example") LogisticsInfoExample example);

    int updateByPrimaryKeySelective(LogisticsInfo record);

    int updateByPrimaryKey(LogisticsInfo record);


    @Select("<script>select li.* " +
            "from logisticsinfo li " +
            "<where> 1=1 " +
            "<if test=\"orderno != null \">and li.orderno = #{orderno} </if>" +
            "</where> order by li.id desc" +
            "</script>")
    List<LogisticsInfoDto> selectByOrderNo(@Param("orderno") String orderno);
    @Select("<script>select li.* " +
            "from logisticsinfo li " +
            "<where> 1=1 " +
            "<if test=\"orderno != null \">and li.orderno = #{orderno} </if>" +
            "</where> order by li.id desc" +
            "</script>")
    LogisticsInfo selectLogisticsInfoByOrderNo(@Param("orderno") String orderno);

    @Select("select li.* from logisticsinfo li where li.orderno =#{orderno} order by li.time desc limit 1 ")
    LogisticsInfo selectLogisticsInfoByOrderNoAndTime(@Param("orderno") String orderno);

}