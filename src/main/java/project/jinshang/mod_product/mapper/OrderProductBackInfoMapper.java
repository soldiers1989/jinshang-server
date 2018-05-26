package project.jinshang.mod_product.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_product.bean.OrderProductBackInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/5/24.
 */
public interface OrderProductBackInfoMapper {
    @Insert("insert into orderproductbackinfo (orderno,pdid,backno,backnum,backtype,backstate,backtime) values (#{orderProductBackInfo.orderno},#{orderProductBackInfo.pdid},#{orderProductBackInfo.backno},#{orderProductBackInfo.backnum},#{orderProductBackInfo.backtype},#{orderProductBackInfo.backstate},#{orderProductBackInfo.backtime})")
    void addOrderProductBackInfo(@Param("orderProductBackInfo") OrderProductBackInfo orderProductBackInfo);

    @Select("select * from orderproductbackinfo where orderno = #{orderno} ORDER BY backtime desc")
    List<OrderProductBackInfo> getOrderProductBackByOrderNo(@Param("orderno") String orderno);
}
