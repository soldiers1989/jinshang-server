package project.jinshang.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import project.jinshang.common.utils.GsonUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_product.bean.*;
import project.jinshang.mod_product.bean.dto.AttributetblDto1;
import project.jinshang.mod_product.service.*;
import project.jinshang.scheduled.mapper.AppTaskMapper;

import java.util.List;

@Component
public class AppTask {

    @Autowired
    private OrderProductServices orderProductServices;

    @Autowired
    private OrderProductLogService orderProductLogService;

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductStoreService productStoreService;

    @Autowired
    private ProductAttrService productAttrService;


    @Autowired
    private AppTaskMapper appTaskMapper;

    @Autowired
    private  AttributetblService attributetblService;

    @Autowired
    private  ProductsService productsService;


    //@Scheduled(cron = "0 4 10 * * ?")
    public void run(){
        List<OrderProduct> list = orderProductServices.selectByExample(null);

        for(OrderProduct orderProduct : list){
            OrderProductLog orderProductLog = orderProductLogService.getByOrderproductid(orderProduct.getId());
            if(orderProductLog != null) continue;

            orderProductLog = new OrderProductLog();

            ProductInfo productInfo = productInfoService.getById(orderProduct.getPdid());
            orderProductLog.setProductinfojson(GsonUtils.toJson(productInfo));

            ProductStore productStore = productStoreService.getByPdidAndPdno(orderProduct.getPdid(),orderProduct.getPdno());
            orderProductLog.setProductstorejson(GsonUtils.toJson(productStore));

            List<ProductAttr> attrs = productAttrService.getListByPidAndPdno(orderProduct.getPdid(),orderProduct.getPdno());
            orderProductLog.setProductattrjson(GsonUtils.toJson(attrs));

            orderProductLog.setOrderproductid(orderProduct.getId());

            orderProductLogService.add(orderProductLog);

        }
    }



    //@Scheduled(cron = "0 41 10 * * ?")
    public void runSetProductsConnect(){

        List<ProductName> productNameList = appTaskMapper.getProductnameByLevel3("DIN912");


        for(ProductName productName : productNameList) {

            List<AttributetblDto1> attributetblList = attributetblService.getAttributeByProdnameid(productName.getId());


            for (AttributetblDto1 at : attributetblList) {

                if(StringUtils.hasText(at.getConnector())) continue;
                Attributetbl attributetbl = new Attributetbl();
                attributetbl.setId(at.getId());
                attributetbl.setConnector("*");
                attributetblService.updateById(attributetbl);
            }

            attributetblList = attributetblService.getAttributeByProdnameid(productName.getId());

            ProductsExample example = new ProductsExample();
            ProductsExample.Criteria criteria = example.createCriteria();
            criteria.andProductnameidEqualTo(productName.getId());

            List<Products> list = productsService.selectByExample(example);


            for (Products products : list) {
                StringBuilder standBuilder = new StringBuilder();

                List<Attribute> attributeList = GsonUtils.toList(products.getAttribute(), Attribute.class);


                int size = attributetblList.size();
                int i = 1;
                for (AttributetblDto1 attributetblDto1 : attributetblList) {
                    for (Attribute attribute1 : attributeList) {
                        if (attributetblDto1.getId().equals(attribute1.getAttributeid())) {
                            if (attribute1.getValue() != null && !attribute1.getValue().equals("")) {
                                standBuilder.append(attribute1.getValue());
                            }

                            if (i != attributeList.size()) {
                                standBuilder.append(attributetblDto1.getConnector());
                            }

                            i++;
                        }
                    }
                }

                String stand = standBuilder.toString();

                //紧固件|内六角圆柱头螺钉|DIN912|M5*0.8*17|不锈钢304|奥展|A2-70|本色|1.2千/盒、6盒/箱
                products.setProdstr(products.getLevel1() + "|" + products.getLevel2() + "|" + products.getLevel3() + "|" + products.getProductname() + "|" + products.getStandard()
                        + "|" + products.getMaterial() + products.getCardnum() + "|" + products.getMark() + "|" + products.getSurfacetreatment() + "|" + products.getPackagetype());


                Products p = new Products();
                p.setStandard(stand);
                p.setProdstr(products.getProdstr());
                p.setId(products.getId());

                productsService.updateByPrimaryKeySelective(p);
            }
        }
    }

}
