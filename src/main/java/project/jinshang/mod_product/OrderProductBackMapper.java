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


    @Select("<script> select ob.*,op.classifyid,op.pdpic as \"pdPic\",op.standard,op.unit from orderProductBack ob inner join orderproduct op  on ob.orderpdid=op.id " +
            " where 1=1 "+
            "<if test=\"param.memberId!=null and param.memberId!=''\"> and ob.memberid=#{param.memberId} </if>"+
            "<if test=\"param.sellerid!=null and param.sellerid!=''\"> and ob.sellerid=#{param.sellerid} </if>"+
            "<if test=\"param.sellerName!=null and param.sellerName!=''\"> and ob.sellername like CONCAT(\"%\",#{param.sellerName},\"%\") </if>"+
            "<if test=\"param.memberName!=null and param.memberName!=''\"> and ob.membername like CONCAT(\"%\",#{param.memberName},\"%\") </if>"+
            "<if test=\"param.pdName!=null and param.pdName!=''\"> and ob.pdname LIKE CONCAT(\"%\",#{param.pdName},\"%\") </if>"+
            "<if test=\"param.orderNo!=null and param.orderNo!=''\"> and ob.orderno LIKE CONCAT(\"%\",#{param.orderNo},\"%\") </if>"+
            "<if test=\"param.code!=null and param.code!=''\"> and ob.code LIKE CONCAT(\"%\",#{param.code},\"%\") </if>"+
            "<if test=\"param.backNo!=null and param.backNo!=''\"> and ob.backNo LIKE CONCAT(\"%\",#{param.backNo},\"%\") </if>"+
            "<if test=\"param.startTime!=null and param.startTime!=''\"> and ob.createtime &gt; #{param.startTime} </if>"+
            "<if test=\"param.endTime!=null and param.endTime!=''\"> and ob.createtime &lt; #{param.endTime} </if>"+
            "<if test=\"param.multiBackStates!=null and param.multiBackStates!=''\"> and ob.state in (${param.multiBackStates}) </if>"+
            "<if test=\"param.prodNamAndOrderNo!=null and param.prodNamAndOrderNo!=''\"> and (ob.orderno like '%${param.prodNamAndOrderNo}%' or ob.pdname LIKE '%${param.prodNamAndOrderNo}%' ) </if>"+
            " order by orders.id desc "+
            "</script>")
    List<OrderProductBackView> getOrderProductBackListForMp(@Param("param") BackQueryParam backQueryParam);

}