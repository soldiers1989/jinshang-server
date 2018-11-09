package project.jinshang.mod_product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.GsonUtils;
import project.jinshang.mod_coupon.YhqRecordMapper;
import project.jinshang.mod_coupon.YhqTicketMapper;
import project.jinshang.mod_coupon.bean.YhqRecord;
import project.jinshang.mod_coupon.bean.YhqTicket;
import project.jinshang.mod_coupon.dto.YhqCheckParamDto;
import project.jinshang.mod_coupon.dto.YhqRule;
import project.jinshang.mod_product.bean.OrderProduct;
import project.jinshang.mod_product.bean.dto.ShopCarProdView;
import project.jinshang.mod_product.bean.dto.ShopCarView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 优惠券使用service
 *
 * @author xiazy
 * @create 2018-08-16 21:46
 **/
@Service
public class YhqUseService {

    @Autowired
    private YhqTicketMapper yhqTicketMapper;
    @Autowired
    private YhqRecordMapper yhqRecordMapper;

    public List<YhqTicket>   getAvaliableTicketList(YhqCheckParamDto yhqCheckParamDto){

        List<YhqTicket>  yhqTicketList=new ArrayList<>();
//        yhqCheckParamDto.setMemberid(11682L);
//        yhqCheckParamDto.setCheckTime(new Date());
//        yhqCheckParamDto.setOrderAmount(new BigDecimal(133.34));
        BigDecimal orderAmount=yhqCheckParamDto.getOrderAmount();
        List<YhqTicket> current=yhqTicketMapper.getYhqTicketByDto(yhqCheckParamDto);
        current.stream().forEach(yhqTicket -> {
            YhqRule rule=GsonUtils.toBean(yhqTicket.getRule(), YhqRule.class);
            BigDecimal rule1=rule.getRule1();
            if (orderAmount.compareTo(rule1)!=-1){
                yhqTicketList.add(yhqTicket);
            }

        });

        //other check..


        return yhqTicketList;

    }



    /**
     *校验优惠券是否可以使用
     * @author xiazy
     * @date  2018/8/20 14:43
     * @param productTotalPrice
     * @param ticketno
     * @return boolean
     */
    public boolean checkCoupon(BigDecimal productTotalPrice,String ticketno){
        YhqTicket yhqTicket=this.getYhqTicketByNo(ticketno);
        YhqRule rule=GsonUtils.toBean(yhqTicket.getRule(), YhqRule.class);
        BigDecimal rule1=rule.getRule1();
        if (productTotalPrice.compareTo(rule1)!=-1){
            return true;
        }
        return false;
    }
    /**
     *购物车提交，优惠券金额分配
     * @author xiazy
     * @date  2018/8/19 9:35
     * @param shopCarViewList
     * @param ticketno
     * @return java.util.List<project.jinshang.mod_product.bean.dto.ShopCarView>
     */
    public List<ShopCarView>  useCouponForCart(List<ShopCarView> shopCarViewList,String ticketno){
        //所有商品的价格的总和
        BigDecimal allProductTotalPrice= Quantity.BIG_DECIMAL_0;
        //根据算法平摊到每个商品的优惠金额的总和(不包含最后的平差)
        BigDecimal totalDiscountPrice= Quantity.BIG_DECIMAL_0;
        //所有订单的商品的集合
        List<ShopCarProdView>   allShopCarProdViewList=new ArrayList<>();
        //根据算法，每个商品的平摊金额的集合(不包含最后的平差)
        List<BigDecimal>  discountList=new ArrayList<>();
        //需要进行平差计算的商品集合
        List<ShopCarProdView> adjustmentList=new ArrayList<>();
        for (ShopCarView shopCarView:shopCarViewList){
            for (ShopCarProdView prodView : shopCarView.getShopCarProdViewList()) {
                allProductTotalPrice = allProductTotalPrice.add(prodView.getAllpay());
            }
            allShopCarProdViewList.addAll(shopCarView.getShopCarProdViewList());
        }
        allProductTotalPrice=new BigDecimal(allProductTotalPrice.toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
        YhqTicket yhqTicket=yhqTicketMapper.getYhqTicketByNo(ticketno);
        YhqRule rule=GsonUtils.toBean(yhqTicket.getRule(), YhqRule.class);
        //优惠券金额，具体的字段待定
        BigDecimal capital=rule.getRule2();
        //每个商品优惠的价格(不包含最后的平差)
        BigDecimal perDiscountPrice=Quantity.BIG_DECIMAL_0;
        //统计需要进行平差额商品的个数
        int i=0;
        for (ShopCarProdView shopCarProdView:allShopCarProdViewList){
            perDiscountPrice=new BigDecimal(shopCarProdView.getAllpay().multiply(capital).divide(allProductTotalPrice,5,BigDecimal.ROUND_HALF_UP).toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
            totalDiscountPrice=totalDiscountPrice.add(perDiscountPrice);
            discountList.add(perDiscountPrice);
            shopCarProdView.setDiscountprice(perDiscountPrice);
            //优惠后的商品总价
            shopCarProdView.setAllpay(shopCarProdView.getAllpay().subtract(perDiscountPrice));
        }

        BigDecimal maxDiscountPrice= Collections.max(discountList);
        BigDecimal adjustmentAmount=capital.subtract(totalDiscountPrice);
        /*进行金额的最后的平差计算*/
        if (adjustmentAmount.compareTo(Quantity.BIG_DECIMAL_0)!=0){
            for (ShopCarProdView shopCarProdView:allShopCarProdViewList){
                if (shopCarProdView.getDiscountprice().compareTo(maxDiscountPrice)==0){
                    adjustmentList.add(shopCarProdView);
                    i++;
                }
            }
            ShopCarProdView lastAdjustmentProd=adjustmentList.get(0);
            lastAdjustmentProd.setDiscountprice(lastAdjustmentProd.getDiscountprice().add(adjustmentAmount));
            lastAdjustmentProd.setAllpay(lastAdjustmentProd.getAllpay().subtract(adjustmentAmount));
//            BigDecimal perAdjustment=adjustmentAmount.divide(new BigDecimal(i),2,BigDecimal.ROUND_HALF_UP);
//            for (ShopCarProdView shopCarProdView:adjustmentList){
//                shopCarProdView.setDiscountprice(shopCarProdView.getDiscountprice().add(perAdjustment));
//                shopCarProdView.setAllpay(shopCarProdView.getAllpay().subtract(perAdjustment));
//            }
            //这个步骤有待商榷
//            BigDecimal lastDiff=perAdjustment.multiply(new BigDecimal(i));
//            if(lastDiff.compareTo(adjustmentAmount)!=0){
//                BigDecimal lastDiffAmount=adjustmentAmount.subtract(lastDiff);
//                //随机去一个，平差到第一个上面
//                adjustmentList.get(0).setAllpay(adjustmentList.get(0).getAllpay().subtract(lastDiffAmount));
//                adjustmentList.get(0).setDiscountprice(adjustmentList.get(0).getDiscountprice().add(lastDiffAmount));
//            }
        }
        return shopCarViewList;
    }



    /**
     *订单修改，优惠券金额分配
     * @author xiazy
     * @date  2018/8/22 15:44
     * @param orderProductList
     * @param discountprice
     * @return java.util.List<project.jinshang.mod_product.bean.OrderProduct>
     */
    public List<OrderProduct>  useCouponForUpdate(List<OrderProduct> orderProductList,BigDecimal discountprice){
        //所有商品的价格的总和
        BigDecimal allProductTotalPrice= Quantity.BIG_DECIMAL_0;
        //根据算法平摊到每个商品的优惠金额的总和(不包含最后的平差)
        BigDecimal totalDiscountPrice= Quantity.BIG_DECIMAL_0;
        //根据算法，每个商品的平摊金额的集合(不包含最后的平差)
        List<BigDecimal>  discountList=new ArrayList<>();
        //需要进行平差计算的商品集合
        List<OrderProduct> adjustmentList=new ArrayList<>();
        for (OrderProduct orderProduct:orderProductList){
            allProductTotalPrice = allProductTotalPrice.add(orderProduct.getActualpayment());
        }
        allProductTotalPrice=new BigDecimal(allProductTotalPrice.toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
        //优惠券金额，具体的字段待定
        BigDecimal capital=discountprice;
        //每个商品优惠的价格(不包含最后的平差)
        BigDecimal perDiscountPrice=Quantity.BIG_DECIMAL_0;
        //统计需要进行平差额商品的个数
        int i=0;
        for (OrderProduct orderProduct:orderProductList){
            perDiscountPrice=new BigDecimal(orderProduct.getActualpayment().multiply(capital).divide(allProductTotalPrice,5,BigDecimal.ROUND_HALF_UP).toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
            totalDiscountPrice=totalDiscountPrice.add(perDiscountPrice);
            discountList.add(perDiscountPrice);
            orderProduct.setDiscountpay(perDiscountPrice);
            //优惠后的商品总价
            orderProduct.setActualpayment(orderProduct.getActualpayment().subtract(perDiscountPrice));
//            shopCarProdView.setAllpay(shopCarProdView.getAllpay().subtract(perDiscountPrice));
        }

        BigDecimal maxDiscountPrice= Collections.max(discountList);
        BigDecimal adjustmentAmount=capital.subtract(totalDiscountPrice);
        /*进行金额的最后的平差计算*/
        if (adjustmentAmount.compareTo(Quantity.BIG_DECIMAL_0)!=0){
            for (OrderProduct orderProduct:orderProductList){
                if (orderProduct.getDiscountpay().compareTo(maxDiscountPrice)==0){
                    adjustmentList.add(orderProduct);
                    i++;
                }
            }
            OrderProduct lastAdjustmentProd=adjustmentList.get(0);
            lastAdjustmentProd.setDiscountpay(lastAdjustmentProd.getDiscountpay().add(adjustmentAmount));
            lastAdjustmentProd.setActualpayment(lastAdjustmentProd.getActualpayment().subtract(adjustmentAmount));
//            BigDecimal perAdjustment=adjustmentAmount.divide(new BigDecimal(i),2,BigDecimal.ROUND_HALF_UP);
//            for (ShopCarProdView shopCarProdView:adjustmentList){
//                shopCarProdView.setDiscountprice(shopCarProdView.getDiscountprice().add(perAdjustment));
//                shopCarProdView.setAllpay(shopCarProdView.getAllpay().subtract(perAdjustment));
//            }
            //这个步骤有待商榷
//            BigDecimal lastDiff=perAdjustment.multiply(new BigDecimal(i));
//            if(lastDiff.compareTo(adjustmentAmount)!=0){
//                BigDecimal lastDiffAmount=adjustmentAmount.subtract(lastDiff);
//                //随机去一个，平差到第一个上面
//                adjustmentList.get(0).setAllpay(adjustmentList.get(0).getAllpay().subtract(lastDiffAmount));
//                adjustmentList.get(0).setDiscountprice(adjustmentList.get(0).getDiscountprice().add(lastDiffAmount));
//            }
        }
        return orderProductList;
    }




    public YhqTicket getYhqTicketByNo(String ticketno){
        return yhqTicketMapper.getYhqTicketByNo(ticketno);
    }

    public int insertYhRecord(YhqRecord yhqRecord){
        return yhqRecordMapper.insertSelective(yhqRecord);
    }

    public BigDecimal getYhqAmount(String ticketno){
        YhqTicket yhqTicket=yhqTicketMapper.getYhqTicketByNo(ticketno);
        YhqRule yhqRule=GsonUtils.toBean(yhqTicket.getRule(),YhqRule.class);
        return yhqRule.getRule2();
    }

}
