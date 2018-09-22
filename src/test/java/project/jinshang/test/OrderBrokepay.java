package project.jinshang.test;

import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.OrderedMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.jinshang.common.constant.Quantity;
import project.jinshang.mod_member.bean.SellerCategory;
import project.jinshang.mod_product.OrderProductMapper;
import project.jinshang.mod_product.OrdersMapper;
import project.jinshang.mod_product.bean.*;
import project.jinshang.mod_product.service.CategoriesService;
import project.jinshang.mod_product.service.OrdersService;

import java.math.BigDecimal;
import java.util.List;

/**
 * create : wyh
 * date : 2018/6/25
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderBrokepay {

    @Autowired
    private OrderProductMapper orderProductMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private CategoriesService categoriesService;


    @Test
    public void reBuild(){

        OrdersExample example = new OrdersExample();
        example.setOrderByClause("id desc");
        OrdersExample.Criteria criteria = example.createCriteria();
        criteria.andPaymentmethodEqualTo( (short)0);
        criteria.andOrderstatusNotEqualTo((short)7);
//        criteria.andSaleidNotEqualTo((long)6700);
        criteria.andOrdernoEqualTo("D201807031229039802");

        List<Orders> list = ordersMapper.selectByExample(example);

        for(Orders orders : list){
            if(orders.getOrderno().equals("D201807031229039802")){
                System.out.println("aaa");
            }

            BigDecimal totalBrokepay = Quantity.BIG_DECIMAL_0;

            List<OrderProduct> orderProductList = orderProductMapper.getByOrderNo(orders.getOrderno());
            for(OrderProduct orderProduct : orderProductList){
                if(orderProduct.getBrokepay().compareTo(Quantity.BIG_DECIMAL_0) == 0){
                    //计算佣金
                    Long classifyid = orderProduct.getClassifyid();
                    BigDecimal rate = BigDecimal.valueOf(0);


                    //商家分类
                    SellerCategory sellerCategory = ordersService.getSellerCategory(classifyid, orderProduct.getSellerid());
                    if (sellerCategory != null) {
                        if (sellerCategory.getBrokeragerate().compareTo(new BigDecimal(-1)) == 0) {
                            SellerCategory sellerCategory1 = ordersService.getSellerCategory(sellerCategory.getParentid(), orderProduct.getSellerid());
                            if (sellerCategory1 != null) {
                                if (sellerCategory1.getBrokeragerate().compareTo(new BigDecimal(-1)) == 0) {
                                    SellerCategory sellerCategory2 = ordersService.getSellerCategory(sellerCategory1.getParentid(), orderProduct.getSellerid());
                                    if (sellerCategory2 != null) {
                                        rate = sellerCategory2.getBrokeragerate();
                                    }
                                } else {
                                    rate = sellerCategory1.getBrokeragerate();
                                }
                            }
                        } else {
                            rate = sellerCategory.getBrokeragerate();
                        }
                    }

                    Categories categories = ordersService.getCategories(classifyid);
                    if (categories != null) {
                        //佣金比率



                        BigDecimal brolerRate =rate.multiply(BigDecimal.valueOf(0.01));
                        if(brolerRate.compareTo(BigDecimal.valueOf(0))<=0){
                            brolerRate = categoriesService.getBrokerRate(classifyid).multiply(BigDecimal.valueOf(0.01));
                        }

                        //服务费比率 用的是category中设置的
                        BigDecimal serverRate = categoriesService.getServerRate(classifyid).multiply(BigDecimal.valueOf(0.01));

                        //商品单位佣金=销售单价*商品佣金比例
                        BigDecimal singlebrokepay = orderProduct.getPrice().multiply(brolerRate);

                        //商品佣金=商品单位佣金*商品数量
                        //BigDecimal broker = (orderProduct.getActualpayment().subtract(orderProduct.getFreight())).multiply(brolerRate);
                        BigDecimal broker = singlebrokepay.multiply(orderProduct.getNum());

                        OrderProduct updateOrderProduct = new OrderProduct();
                        updateOrderProduct.setId(orderProduct.getId());
                        updateOrderProduct.setSinglebrokepay(singlebrokepay);
                        updateOrderProduct.setBrokepay(broker);
                        ordersService.updateOrderProduct(updateOrderProduct);

                        totalBrokepay = totalBrokepay.add(broker);
                    }
                }else{
                    totalBrokepay = totalBrokepay.add(orderProduct.getBrokepay());
                }
            }


            Orders updateOrder = new Orders();
            updateOrder.setId(orders.getId());
            updateOrder.setBrokepay(totalBrokepay);
            ordersMapper.updateByPrimaryKeySelective(updateOrder);
        }


    }




}
