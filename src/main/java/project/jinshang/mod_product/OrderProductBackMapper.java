package project.jinshang.mod_product;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import project.jinshang.mod_product.bean.BackQueryParam;
import project.jinshang.mod_product.bean.OrderProductBack;
import project.jinshang.mod_product.bean.OrderProductBackExample;
import project.jinshang.mod_product.bean.OrderProductBackView;
import project.jinshang.mod_product.provider.OrderProductBackProvider;

public interface OrderProductBackMapper {
    int countByExample(OrderProductBackExample example);

    int deleteByExample(OrderProductBackExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderProductBack record);

    int insertSelective(OrderProductBack record);

    List<OrderProductBack> selectByExample(OrderProductBackExample example);

    OrderProductBack selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderProductBack record, @Param("example") OrderProductBackExample example);

    int updateByExample(@Param("record") OrderProductBack record, @Param("example") OrderProductBackExample example);

    int updateByPrimaryKeySelective(OrderProductBack record);

    int updateByPrimaryKey(OrderProductBack record);


    @Select("select ob.*,p.level3 from orderProductBack ob left join productinfo p on ob.pdid=p.id where ob.orderpdid=#{id} order by ob.id desc limit 1 ")
    OrderProductBack getOrderProductBackByOrderProductID(Long id);


    @SelectProvider(type = OrderProductBackProvider.class,method = "getOrderProductBackList")
    List<OrderProductBackView> getOrderProductBackList(BackQueryParam backQueryParam);

    @Select("select * from orderProductBack where orderno=#{orderno} order by id desc")
    List<OrderProductBack> getByOrderNo(@Param("orderno") String orderno );

}