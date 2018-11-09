package project.jinshang.mod_product.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_product.OrderProductMapper;
import project.jinshang.mod_product.bean.OrderProduct;
import project.jinshang.mod_product.bean.OrderProductBack;
import project.jinshang.mod_product.bean.OrderProductBackExample;
import project.jinshang.mod_product.bean.OrderProductExample;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.*;

@Service
public class OrderProductServices {

    @Autowired
    private OrderProductMapper orderProductMapper;

    /**
     * 查询买家已经购买该限时购的数量
     * @param buyerid
     * @param limitid
     * @return
     */
    public BigDecimal getTotalNumByLimitid(Long buyerid,Long limitid){
        return  orderProductMapper.getTotalNumByLimitid(buyerid,limitid);
    }


    /**
     * 根据订单id查询
     * @param orderid
     * @return
     */
    public List<OrderProduct> getByOrderid(Long orderid){
       return  orderProductMapper.getByOrderid(orderid);
    }

    public List<OrderProduct> selectByExample(OrderProductExample example){
        return  orderProductMapper.selectByExample(example);
    }


    public OrderProduct getOrderProductById(long id) {
        return orderProductMapper.selectByPrimaryKey(id);
    }

    public List<OrderProduct> getOrderProductByOrderId(long orderid) {
        return  this.getByOrderid(orderid);
    }

    /**
     *
     * @param orderid
     * @param backstate
     * @return
     */
    public List<OrderProduct> getOrderProductByOrderId(long orderid, short backstate) {
        OrderProductExample orderProductExample = new OrderProductExample();
        orderProductExample.createCriteria().andOrderidEqualTo(orderid).andBackstateEqualTo(backstate);
        return orderProductMapper.selectByExample(orderProductExample);
    }


    public List<OrderProduct> getOrderProductByOrderId(long orderid,Short[] backstates) {
        OrderProductExample orderProductExample = new OrderProductExample();
        orderProductExample.createCriteria().andOrderidEqualTo(orderid).andBackstateIn(Arrays.asList(backstates));
        return orderProductMapper.selectByExample(orderProductExample);
    }



    public int getOrderProductCountByOrderId(long orderid, short backstate){
        OrderProductExample orderProductExample = new OrderProductExample();
        orderProductExample.createCriteria().andOrderidEqualTo(orderid).andBackstateEqualTo(backstate);
        return orderProductMapper.countByExample(orderProductExample);
    }

    public List<OrderProduct> getOrderProductByOrderId(long orderid, Long pdid, String pdno) {
        OrderProductExample orderProductExample = new OrderProductExample();
        orderProductExample.createCriteria().andOrderidEqualTo(orderid).andPdidEqualTo(pdid).andPdnoEqualTo(pdno);
        return orderProductMapper.selectByExample(orderProductExample);
    }

    public List<OrderProduct> getOrderProductByOrderId(long orderid, long orderproductid) {
        OrderProductExample orderProductExample = new OrderProductExample();
        orderProductExample.createCriteria().andOrderidEqualTo(orderid).andIdNotEqualTo(orderproductid);
        return orderProductMapper.selectByExample(orderProductExample);
    }

    public List<OrderProduct> getByOrderNo(String orderno) {

        return orderProductMapper.getByOrderNo(orderno);
    }

    public List<OrderProduct> getByOrderNoAndBackStatus(String orderno) {
        return orderProductMapper.getByOrderNoAndBackStatus(orderno);
    }

    public void updateByPrimaryKeySelective(OrderProduct orderProduct){
        orderProductMapper.updateByPrimaryKeySelective(orderProduct);
    }

    public int sendSplitOutGoodsUpdateOrderProduct(OrderProduct updateOrderProduct, OrderProductExample updateOrderProductExample) {
        return orderProductMapper.updateByExampleSelective(updateOrderProduct,updateOrderProductExample);
    }

    public List<OrderProduct> getByOrderNoAndDeliveryidIsNull(String orderno) {
        OrderProductExample orderProductExample = new OrderProductExample();
        orderProductExample.createCriteria().andOrdernoEqualTo(orderno).andDeliveryidIsNull();
        return orderProductMapper.selectByExample(orderProductExample);
    }

    public List<OrderProduct> getByOrderNoAndDeliveryidIsNotNull(String orderno) {
        OrderProductExample orderProductExample = new OrderProductExample();
        orderProductExample.createCriteria().andOrdernoEqualTo(orderno).andDeliveryidIsNotNull();
        return orderProductMapper.selectByExample(orderProductExample);
    }

    public OrderProduct selectById(Long orderproductid) {
        return  orderProductMapper.selectByPrimaryKey(orderproductid);
    }

    public PageInfo selectByObject(int pageNo, int pageSize, OrderProduct orderProduct) {
        if(pageNo != -1){
            PageHelper.startPage(pageNo, pageSize);
        }
        List<Map<String,Object>> list = orderProductMapper.selectByObject(orderProduct);

        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }


    /**
     * 对多个订单商品处于不同状态的进行合并
     * @param list
     * @return
     */
    public List<OrderProduct> margeOrderProduct(List<OrderProduct> list){
        List<OrderProduct> resultList = new ArrayList<>();
        Set<String> pdIdSet = new HashSet<>();

        for(OrderProduct op : list){
            if(pdIdSet.contains(op.getPdid()+op.getPdno())){
                for(OrderProduct resOP : resultList){
                    if((resOP.getPdid()+op.getPdno()).equals(op.getPdid()+op.getPdno())){
                        resOP.setNum(resOP.getNum().add(op.getNum()));
                        resOP.setActualpayment(resOP.getActualpayment().add(op.getActualpayment()));
                        resOP.setDiscountpay(resOP.getDiscountpay().add(op.getDiscountpay()));
                    }
                }
            }else{
                resultList.add(op);
                pdIdSet.add(op.getPdid()+op.getPdno());
            }
        }

        return resultList;
    }


    public List<OrderProduct> selectOrderProductByMultiId(String orderPoductIds){
        return orderProductMapper.selectOrderProductByMultiId(orderPoductIds);
    }

}
