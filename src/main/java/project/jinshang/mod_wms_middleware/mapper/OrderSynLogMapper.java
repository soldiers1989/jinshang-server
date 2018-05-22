package project.jinshang.mod_wms_middleware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_wms_middleware.bean.OrderSynLog;
import project.jinshang.mod_wms_middleware.bean.OrderSynLogExample;

public interface OrderSynLogMapper {
    int countByExample(OrderSynLogExample example);

    int deleteByExample(OrderSynLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderSynLog record);

    int insertSelective(OrderSynLog record);

    List<OrderSynLog> selectByExample(OrderSynLogExample example);

    OrderSynLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderSynLog record, @Param("example") OrderSynLogExample example);

    int updateByExample(@Param("record") OrderSynLog record, @Param("example") OrderSynLogExample example);

    int updateByPrimaryKeySelective(OrderSynLog record);

    int updateByPrimaryKey(OrderSynLog record);


    @Insert("<script>insert into ordersynlog(orderno,state,operatetime,type) values" +
            "<foreach collection=\"list\" item=\"log\" separator=\",\">" +
            "(#{log.orderno},#{log.state},#{log.operatetime},#{log.type})" +
            "</foreach>" +
            "</script>")
    int  batchAdd(@Param("list") List<OrderSynLog> list);
}