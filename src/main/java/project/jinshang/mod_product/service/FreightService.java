package project.jinshang.mod_product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.exception.MyException;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_product.ShippingTemplatesMapper;
import project.jinshang.mod_product.bean.AreaCost;
import project.jinshang.mod_product.bean.ShippingTemplates;
import project.jinshang.mod_product.bean.dto.OrderFrightDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create : wyh
 * date : 2018/7/4
 */
@Service
public class FreightService {

    @Autowired
    private ShopCarService shopCarService;
    @Autowired
    private AreaCostService areaCostService;
    @Autowired
    private ShippingTemplatesMapper shippingTemplatesMapper;

    /**
     * 计算运费
     */
    @Deprecated
    public BigDecimal getFreight(Long freightmode, BigDecimal totalweight, String uProvince, String uCity){
        //计算运费
        if (freightmode != Quantity.STATE_) {
            ShippingTemplates shippingTemplates = shopCarService.getShippingTemp(freightmode);
            //默认运费公斤
            BigDecimal defaultfreight = shippingTemplates.getDefaultfreight();
            //默认运费
            BigDecimal defaultcost = shippingTemplates.getDefaultcost();
            //每增加公斤
            BigDecimal perkilogramadded = shippingTemplates.getPerkilogramadded();
            //每增加运费
            BigDecimal perkilogramcost = shippingTemplates.getPerkilogramcost();

            //匹配地区运费
            List<AreaCost> areaCostList = areaCostService.getAreaCostByTemid(freightmode);
            //如果用户默认收货地址为空，则用商品默认运费
            if (uProvince == null || uCity == null) {
                if (shippingTemplates != null) {
                    //计算默认运费
                    return getTotalCostByWeight(defaultfreight, defaultcost, perkilogramadded, perkilogramcost, totalweight);
                }
            } else {
                //如果有地区运费模板，就用地区运费模板，否则用默认运费模板
                if (areaCostList != null && areaCostList.size()>0) {
                    AreaCost areaCost = null;
                    for(AreaCost areaCost1 : areaCostList){
                        String[] provinceArr =  areaCost1.getProvince().split(",");
                        List<String> provinceList = new ArrayList<>();
                        if(provinceArr != null){
                            for(String p : provinceArr){
                                provinceList.add(p.trim());
                            }
                        }

                        String[] cityArr = areaCost1.getCity().split(",");
                        List<String> cityList = new ArrayList<>();
                        if(cityArr != null && cityArr.length>0){
                            for(String c : cityArr){
                                cityList.add(c.trim());
                            }
                        }

                        if((provinceList.contains(uProvince) || provinceList.contains(uProvince.replace("省",""))) &&
                                (cityList.contains(uCity) || cityList.contains(uCity.replace("市","")))){
                            areaCost = areaCost1;
                            break;
                        }
                    }

                    if (areaCost != null) {
                        BigDecimal udefaultfreight = areaCost.getDefaultfreight();
                        //默认运费
                        BigDecimal udefaultcost = areaCost.getDefaultcost();
                        //每增加公斤
                        BigDecimal uperkilogramadded = areaCost.getPerkilogramadded();
                        //每增加运费
                        BigDecimal uperkilogramcost = areaCost.getPerkilogramcost();
                        //计算地区运费
                        return getTotalCostByWeight(udefaultfreight, udefaultcost, uperkilogramadded, uperkilogramcost, totalweight);

                    } else {
                        //计算默认运费
                        return getTotalCostByWeight(defaultfreight, defaultcost, perkilogramadded, perkilogramcost, totalweight);
                    }

                } else {
                    //计算默认运费
                    return getTotalCostByWeight(defaultfreight, defaultcost, perkilogramadded, perkilogramcost, totalweight);
                }
            }
        }

        //包邮
        return new BigDecimal(0);
    }




    /**
     * 计算运费- 商家合集模式
     * @param freightmode  运费模板id
     * @param totalProductMoney  集合内订单商品总金额（不包含运费）
     * @param totalweight  集合内总商品重量
     * @param uProvince  收货地址
     * @param uCity  收货地址
     * @return
     * @throws Exception
     */
    public Map<String,Object> getFreight(Long freightmode, BigDecimal totalProductMoney , BigDecimal totalweight, String uProvince, String uCity) throws Exception {
        //计算运费
        if(freightmode==null || freightmode<0) {
            throw new MyException("freightmode不可为空"); //return BigDecimal.valueOf(0);
        }
        if(totalweight.compareTo(Quantity.BIG_DECIMAL_0) <= 0){
            throw new MyException("货物重量不可小于等于0");
        }
        if(!StringUtils.hasText(uProvince) || !StringUtils.hasText(uCity)){
            throw new MyException("收货地址不合法");
        }

        ShippingTemplates shippingTemplates = shippingTemplatesMapper.selectByPrimaryKey(freightmode);
        if(shippingTemplates==null){
            throw new MyException("运费模板不存在");
        }

        Map<String,Object> resMap = new HashMap<>();
        BigDecimal freight;
        OrderFrightDto orderFrightDto = new OrderFrightDto();
        orderFrightDto.setCity(uCity);
        orderFrightDto.setProvince(uProvince);
        orderFrightDto.setCounttype(shippingTemplates.getCounttype());

        //运费方式有2种  counttype 计价方式 1=按重量，2=按金额
        //匹配地区运费
        List<AreaCost> areaCostList = areaCostService.getAreaCostByTemid(shippingTemplates.getId());
        AreaCost areaCost = matchAreaCost(areaCostList,uProvince,uCity);
        if(shippingTemplates.getCounttype() == Quantity.STATE_1){  //按照重量计算
            //默认运费公斤
            BigDecimal defaultfreight = shippingTemplates.getDefaultfreight();
            //默认运费
            BigDecimal defaultcost = shippingTemplates.getDefaultcost();
            //每增加公斤
            BigDecimal perkilogramadded = shippingTemplates.getPerkilogramadded();
            //每增加运费
            BigDecimal perkilogramcost = shippingTemplates.getPerkilogramcost();

            if(areaCost != null){
                defaultfreight = areaCost.getDefaultfreight();
                //默认运费
                defaultcost = areaCost.getDefaultcost();
                //每增加公斤
                perkilogramadded = areaCost.getPerkilogramadded();
                //每增加运费
                perkilogramcost = areaCost.getPerkilogramcost();
            }
            freight = getTotalCostByWeight(defaultfreight,defaultcost,perkilogramadded,perkilogramcost,totalweight);
            orderFrightDto.setDefaultfreight(defaultfreight);
            orderFrightDto.setDefaultcost(defaultcost);
            orderFrightDto.setPerkilogramadded(perkilogramadded);
            orderFrightDto.setPerkilogramcost(perkilogramcost);

            resMap.put("freight",freight);
            resMap.put("orderFrightDto",orderFrightDto);
        }else if(shippingTemplates.getCounttype() == Quantity.STATE_2){
            //满免金额
            BigDecimal defaultfreeprice = shippingTemplates.getDefaultfreeprice();
            //默认运费元
            BigDecimal defaultcost = shippingTemplates.getDefaultcost();
            if(areaCost != null){
                defaultfreeprice = areaCost.getDefaultfreeprice();
                defaultcost = areaCost.getDefaultcost();
            }
            freight = getTotalCostByTotalMoney(defaultfreeprice,defaultcost,totalProductMoney);
            orderFrightDto.setDefaultfreeprice(defaultfreeprice);
            orderFrightDto.setDefaultcost(defaultcost);
            resMap.put("freight",freight);
            resMap.put("orderFrightDto",orderFrightDto);
        }else{
            throw new MyException("运费模板的计价方式不合法，请联系网站管理员");
        }

        return resMap;
    }



    public BigDecimal getFreightByOrderFrightDto(OrderFrightDto dto, BigDecimal totalProductMoney , BigDecimal totalweight,String province,String city) throws MyException {
        BigDecimal freight = Quantity.BIG_DECIMAL_0;

        //默认运费公斤
        BigDecimal defaultfreight = dto.getDefaultfreight();
        //默认运费
        BigDecimal defaultcost = dto.getDefaultcost();
        //每增加公斤
        BigDecimal perkilogramadded = dto.getPerkilogramadded();
        //每增加运费
        BigDecimal perkilogramcost = dto.getPerkilogramcost();
        //满免金额
        BigDecimal defaultfreeprice = dto.getDefaultfreeprice();

        if(dto.getCounttype() == Quantity.STATE_1){
            freight = this.getTotalCostByWeight(defaultfreight,defaultcost,perkilogramadded,perkilogramcost,totalweight);
        }else if(dto.getCounttype() == Quantity.STATE_2){
            freight = this.getTotalCostByTotalMoney(defaultfreeprice,defaultcost,totalProductMoney);
        }else{
            throw new MyException("运费模板的计价方式不合法，请联系网站管理员");
        }

        return freight;
    }


    /**
     * 按金额计算运费
     * @param defaultfreeprice  满免金额
     * @param defaultcost  默认运费元
     * @param totalProductMoney  订单商品总金额（不包含运费）
     * @return
     */
    private BigDecimal getTotalCostByTotalMoney(BigDecimal defaultfreeprice,BigDecimal defaultcost,BigDecimal totalProductMoney){
        if(defaultfreeprice.compareTo(totalProductMoney) <= 0){  //大于减免金额
            return Quantity.BIG_DECIMAL_0;
        }
        return  defaultcost;
    }


    /**
     * 按重量计算运费
     *
     * @param defaultfreight   默认运费公斤
     * @param defaultcost      默认运费
     * @param perkilogramadded 每增加公斤
     * @param perkilogramcost  每增加运费
     * @param totalweight      商品总重量
     * @return 每个商品的运费
     */
    private BigDecimal getTotalCostByWeight(BigDecimal defaultfreight, BigDecimal defaultcost, BigDecimal perkilogramadded, BigDecimal perkilogramcost, BigDecimal totalweight) {

        BigDecimal totalCost = new BigDecimal(0);
        //判断重量计算运费
        //如果小于或等于默认重量
        if (totalweight.compareTo(defaultfreight)<= 0) {
            totalCost = defaultcost;
        } else {
            //剩余的重量
            BigDecimal substractWeight = totalweight.subtract(defaultfreight);

            if(perkilogramcost.compareTo(Quantity.BIG_DECIMAL_0) <=0){  //每增加运费 小于等于0，说明运费最高收取defaultcost
                totalCost = defaultcost;
            }else{
                //倍数和余数
                BigDecimal[] results = substractWeight.divideAndRemainder(perkilogramadded);
                //如果剩下的重量小于每增加公斤数
                if (results[0].compareTo(new BigDecimal(0)) == Quantity.INT_0) {
                    totalCost = defaultcost.add(perkilogramcost);
                } else {
                    BigDecimal multiplycost = results[0].multiply(perkilogramcost);
                    //如果没有余数
                    if (results[1].compareTo(new BigDecimal(0)) == Quantity.INT_0) {
                        totalCost = multiplycost.add(defaultcost);
                    } else {
                        //如果有余数
                        totalCost = multiplycost.add(perkilogramcost).add(defaultcost);
                    }
                }
            }
        }
        return totalCost;
    }


    /**
     * 匹配运费地区规则
     * @param areaCostList
     * @param uProvince
     * @param uCity
     * @return
     */
    private AreaCost matchAreaCost(List<AreaCost> areaCostList,String uProvince,String uCity) {
        AreaCost areaCost = null;
        if (areaCostList != null && areaCostList.size() > 0) {
            for (AreaCost areaCost1 : areaCostList) {
                String[] provinceArr = areaCost1.getProvince().split(",");
                List<String> provinceList = new ArrayList<>();
                for (String p : provinceArr) {
                    provinceList.add(p.trim());
                }

                String[] cityArr = areaCost1.getCity().split(",");
                List<String> cityList = new ArrayList<>();
                if (cityArr.length > 0) {
                    for (String c : cityArr) {
                        cityList.add(c.trim());
                    }
                }
                if ((provinceList.contains(uProvince) || provinceList.contains(uProvince.replace("省", ""))) &&
                        (cityList.contains(uCity) || cityList.contains(uCity.replace("市", "")))) {
                    areaCost = areaCost1;
                    break;
                }
            }
        }
        return areaCost;
    }



    public Short getOrderfright(String method){
        if("包邮".equals(method)){
            return Quantity.STATE_1;
        }else if("自提".equals(method)){
            return Quantity.STATE_2;
        }else if("顺丰到付".equals(method)){
            return Quantity.STATE_3;
        }else if("物流自提".equals(method)){
            return Quantity.STATE_4;
        }else if("物流到户".equals(method)){
            return Quantity.STATE_5;
        }else if("快递".equals(method)){
            return Quantity.STATE_6;
        }

        return null;
    }






}
