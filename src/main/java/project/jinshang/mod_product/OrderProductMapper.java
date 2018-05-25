package project.jinshang.mod_product;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import project.jinshang.mod_product.bean.OrderProduct;
import project.jinshang.mod_product.bean.OrderProductExample;
import project.jinshang.mod_product.bean.Orders;
import project.jinshang.mod_product.bean.ProductEvaModel;
import project.jinshang.mod_sale_rank.bean.SaleRankModel;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public interface OrderProductMapper {
    int countByExample(OrderProductExample example);

    int deleteByExample(OrderProductExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderProduct record);

    int insertSelective(OrderProduct record);

    List<OrderProduct> selectByExample(OrderProductExample example);

    OrderProduct selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderProduct record, @Param("example") OrderProductExample example);

    int updateByExample(@Param("record") OrderProduct record, @Param("example") OrderProductExample example);

    int updateByPrimaryKeySelective(OrderProduct record);

    int updateByPrimaryKey(OrderProduct record);

    @Select("select * from orderproduct where orderno=#{param1}")
    List<OrderProduct> listByOrderNo(String no);

    @Select("select * from orderproduct where orderno=#{param1} and pdno=#{param2}")
    OrderProduct findByOrderNoAndPdno(String no,String pdno);

    @Update("update orderproduct set backstate=#{param2} where id=#{param1}")
    void updateBackStatusById(long id,int status);


    @Select("select sum(actualpayment) from orderproduct where orderno=#{orderno} and pdid=#{pdid} and pdno=#{pdno}")
    BigDecimal getAllpayByOrderNoAndPdidAndPdNo(@Param("orderno") String orderno,@Param("pdid") Long pdid,@Param("pdno") String pdno);


    @Select("select * from orderproduct where orderno=#{orderno} and pdid=#{pdid} and pdno=#{pdno}")
    List<OrderProduct> getOrderProdByOrderNoAndPdidAndPdNo(@Param("orderno") String orderno,@Param("pdid") Long pdid,@Param("pdno") String pdno);


    /**
     * 批量插入
     * @param list
     */
    @InsertProvider(type = OrderProductProvider.class, method = "insertAll")
    void insertAll(List<OrderProduct> list);
    @Select("select * from orderproduct where orderno = #{orderno}")
    List<OrderProduct> getByOrderNo(@Param("orderno") String orderno);




    public class OrderProductProvider {
        public String insertAll(Map map) {
            List<OrderProduct> list = (List<OrderProduct>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO orderproduct ");
            sb.append("(orderno,pdid,pdno,pdname,pddesc,price,unit,num,storename,storeid,mailornot,ismailornot,standard,mark,brand,material,gradeno,classify,freight,orderid,actualpayment,pdpic,pic,sellerid,producttype,protype,attrjson,deliverytime,allpay,partpay,yupay,classifyid,limitid) ");
            sb.append("VALUES ");
            MessageFormat mf = new MessageFormat("(#'{'list[{0}].orderno},#'{'list[{0}].pdid},#'{'list[{0}].pdno},#'{'list[{0}].pdname},#'{'list[{0}].pddesc},#'{'list[{0}].price},#'{'list[{0}].unit},#'{'list[{0}].num},#'{'list[{0}].storename},#'{'list[{0}].storeid},#'{'list[{0}].mailornot},#'{'list[{0}].ismailornot},#'{'list[{0}].standard},#'{'list[{0}].mark},#'{'list[{0}].brand},#'{'list[{0}].material},#'{'list[{0}].gradeno},#'{'list[{0}].classify},#'{'list[{0}].freight},#'{'list[{0}].orderid},#'{'list[{0}].actualpayment},#'{'list[{0}].pdpic},#'{'list[{0}].pic},#'{'list[{0}].sellerid},#'{'list[{0}].producttype},#'{'list[{0}].protype},#'{'list[{0}].attrjson},#'{'list[{0}].deliverytime},#'{'list[{0}].allpay},#'{'list[{0}].partpay},#'{'list[{0}].yupay},#'{'list[{0}].classifyid},#'{'list[{0}].limitid})");
            for (int i = 0; i < list.size(); i++) {
                sb.append(mf.format(new Object[]{i}));
                if (i < list.size() - 1) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }
    }

    /**
     * 根据订单id数组查询订单商品
     * @param orderids
     * @return
     */
    @Select("SELECT id,orderno,price,num,freight,protype,allpay,partpay,yupay,paystate,actualpayment from orderproduct where orderid in (${orderids})")
    public List<OrderProduct> getOrderProductByInOrderids(@Param("orderids") String orderids);

    @Select("select * from orderproduct where orderid=#{param1}")
    List<OrderProduct> listOrderProductByOrderId(long orderid);

    @Select("SELECT op.pdname,op.attrjson,op.price,count(*) as salenum from orderproduct op LEFT JOIN categories ca on op.classifyid=ca.id " +
            "LEFT JOIN categories cas on cas.id=ca.parentid " +
            "where cas.name='螺栓' " +
            "GROUP BY op.pdname,op.attrjson,op.price " +
            "ORDER BY salenum desc LIMIT 4")
    public List<SaleRankModel> getLuoSuanRank();


    @Select("SELECT op.pdname,op.attrjson,op.price,count(*) as salenum from orderproduct op LEFT JOIN categories ca on op.classifyid=ca.id " +
            "LEFT JOIN categories cas on cas.id=ca.parentid " +
            "where cas.name='螺母' " +
            "GROUP BY op.pdname,op.attrjson,op.price " +
            "ORDER BY salenum desc LIMIT 4")
    public List<SaleRankModel> getLuoMuRank();

    @Select("SELECT op.pdname,op.attrjson,op.price,count(*) as salenum from orderproduct op LEFT JOIN categories ca on op.classifyid=ca.id " +
            "LEFT JOIN categories cas on cas.id=ca.parentid " +
            "where cas.name='螺钉' " +
            "GROUP BY op.pdname,op.attrjson,op.price " +
            "ORDER BY salenum desc LIMIT 4")
    public List<SaleRankModel> getLuoDingRank();


    @Select("SELECT op.pdname,op.attrjson,op.price,count(*) as salenum from orderproduct op LEFT JOIN categories ca on op.classifyid=ca.id " +
            "LEFT JOIN categories cas on cas.id=ca.parentid " +
            "where cas.name='挡圈' " +
            "GROUP BY op.pdname,op.attrjson,op.price " +
            "ORDER BY salenum desc LIMIT 4")
    public List<SaleRankModel> getDangQuanRank();


    @Select("SELECT op.eva1,op.eva2,op.eva3,op.evatime,member.username,member.favicon,op.isanonymous,op.buyersexperience " +
            "from orderproduct op LEFT JOIN Member member on op.memberid=member.id " +
            "where op.evaluatestate=1 and op.pdid=#{pdid}")
    public List<ProductEvaModel> getProductEvaListByPdId(@Param("pdid") Long pdid);


    @Select("select sum(num) from orders o,orderproduct op where o.id=op.orderid and o.memberid=#{buyerid} and op.limitid=#{limitid} and op.backstate<>3 and o.orderstatus<>7")
    BigDecimal getTotalNumByLimitid(@Param("buyerid") Long buyerid,@Param("limitid") Long limitid);

    /**
     * 根据订单id查询
     * @param orderid
     * @return
     */
    @Select("select * from orderproduct where orderid=#{orderid}")
    List<OrderProduct> getByOrderid(@Param("orderid") Long orderid);

    @Select("select * from orderproduct where orderno = #{orderno} and backstate = 0 ")
    List<OrderProduct> getByOrderNoAndBackStatus(@Param("orderno") String orderno);

/*    @SelectProvider(type = OrderProductMapper.OrdersProvider.class, method = "queryOrderByParam")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="pdname",property="pdname"),
            @Result(column="price",property="price"),
            @Result(column="unit",property="unit"),
            @Result(column="num",property="num"),
            @Result(column="brand",property="brand"),
            @Result(column="mark",property="mark"),
            @Result(column="standard",property="standard"),
            @Result(column="pddesc",property="pddesc"),
            @Result(column="evaluatestate",property="evaluatestate")
    })
    public List<OrderProduct> selectByOrderNo(String orderno);


    public class OrdersProvider {

        private final String TBL_ORDER_PRODUCT = "orderproduct op";

        public String queryOrderByParam(String orderno) {
            SQL sql = new SQL().SELECT("op.id,op.pdname,op.price,op.unit,op.num,op.brand,op.mark,op.standard,op.pddesc,op.evaluatestate").FROM(TBL_ORDER_PRODUCT);
            sql.WHERE("op.orderno=#{orderno}");
*//*            //商品名称
            String pdName = param.getPdName();
            if (StringUtils.hasText(pdName)) {
                pdName = "%"+pdName+"%";
                param.setPdName(pdName);
                sql.WHERE("op.pdname LIKE #{pdName}");
            }
            //品牌
            String brand = param.getBrand();
            if (StringUtils.hasText(brand)) {
                brand = "%"+brand+"%";
                param.setPdName(brand);
                sql.WHERE("op.brand LIKE #{brand}");
            }
            //印记
            String mark = param.getMark();
            if (StringUtils.hasText(mark)) {
                mark = "%"+mark+"%";
                param.setPdName(mark);
                sql.WHERE("op.mark LIKE #{mark}");
            }
            //规格
            String stand = param.getStand();
            if(StringUtils.hasText(stand)){
                stand = "%"+stand+"%";
                param.setPdName(stand);
                sql.WHERE("op.standard LIKE #{stand}");
            }
            //评价状态
            short evaState = param.getEvaState();
            if(evaState!=-1){
                sql.WHERE("op.evaluatestate=#{evaState}");
            }*//*
            return sql.toString();
        }
    }*/

/*    public static void main(String[] args) {
        OrderProduct orderProduct = new OrderProduct();
        List<OrderProduct> list = new ArrayList<OrderProduct>();

        orderProduct.setOrderid(1l);
        orderProduct.setPdid(2l);
        orderProduct.setOrderno("111111111");
        orderProduct.setPddesc("aaaaaaaaa");
        orderProduct.setPdname("aaaaaaaa");
        orderProduct.setPrice(new BigDecimal(22));
        orderProduct.setOrdernum(12);
        orderProduct.setOrderid(2l);
        orderProduct.setStorename("bb");

        OrderProduct orderProduct2 = new OrderProduct();


        orderProduct2.setOrderid(1l);
        orderProduct2.setPdid(2l);
        orderProduct2.setOrderno("111111111");
        orderProduct2.setPddesc("aaaaaaaaa");
        orderProduct2.setPdname("aaaaaaaa");
        orderProduct2.setPrice(new BigDecimal(22));
        orderProduct2.setOrdernum(12);
        orderProduct2.setOrderid(2l);
        orderProduct2.setStorename("bb");
        list.add(orderProduct);
        list.add(orderProduct2);

        Map<String,List<OrderProduct>> map = new HashMap<String,List<OrderProduct>>();

        map.put("list",list);

        OrderProductProvider orderProductProvider = new OrderProductProvider();
        String sql = orderProductProvider.insertAll(map);

        System.out.println(sql);
    }*/
}