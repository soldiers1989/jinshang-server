package project.jinshang.test;


import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import project.jinshang.common.utils.GsonUtils;
import project.jinshang.mod_product.bean.*;
import project.jinshang.mod_product.bean.dto.AttributetblDto1;
import project.jinshang.mod_product.service.*;
import project.jinshang.scheduled.mapper.AppTaskMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional(rollbackFor = Exception.class)
public class CategoryTest {

    @Autowired
    private ProductsService productsService;

    @Autowired
    private AttributetblService attributetblService;

    @Autowired
    private AttvalueService attvalueService;

    @Autowired
    private AppTaskMapper appTaskMapper;

    @Test
    public void repairData(){
        int pageNo = 1;
        int pageSize = 20;
        PageInfo pageInfo = productsService.list(null,null,null,null,null,-1,-1,0,0,null,pageNo,pageSize);
        int totalPage = pageInfo.getPages();

        List<Long> ids = new ArrayList<>();
        try {
        while (pageNo<=totalPage)
            {
                pageInfo = productsService.list(null,null,null,null,null,-1,-1,0,0,null,pageNo,pageSize);

                List<Products> list = pageInfo.getList();

                for(Products products : list){

                    List<Attribute> attributeList = GsonUtils.toList(products.getAttribute(),Attribute.class);
                    for(Attribute attribute : attributeList){

                        String attv = attribute.getValue();

                        if(attribute.getAttribute().equals("公称直径")) {
                            if (attribute.getValue().startsWith("#")) {
                                System.out.println(products.getId() + "---" + attv);
                                attv = attv.substring(1, attv.length()) + "#";
                                System.out.println(products.getId() + "===" + attv);
                                ids.add(products.getId());
                                attribute.setValue(attv);
                            }else if(attv.endsWith("\"") || attv.endsWith("″")){
                                System.out.println(products.getId() + "---" + attv);
                                attv = attv.substring(0, attv.length()-1);
                                System.out.println(products.getId() + "===" + attv);
                                ids.add(products.getId());
                                attribute.setValue(attv);
                            }else if(attv.startsWith("φ") || attv.startsWith("∮")){
                                System.out.println(products.getId() + "---" + attv);
                                attv = "Ф"+attv.substring(1, attv.length());
                                System.out.println(products.getId() + "===" + attv);
                                ids.add(products.getId());
                                attribute.setValue(attv);
                            }
                        }else if(attribute.getAttribute().equals("长度")){
                            if(attv.endsWith("\"") || attv.endsWith("″")) {
                                System.out.println(products.getId() + "---" + attv);
                                attv = attv.substring(0, attv.length() - 1);
                                System.out.println(products.getId() + "===" + attv);
                                ids.add(products.getId());
                                attribute.setValue(attv);
                            }
                        }
                    }




                    List<AttributetblDto1> attributetblList = attributetblService.getAttributeByProdnameid(products.getProductnameid());
                    int size= attributeList.size();
                    int i=1;

                    List<Attribute> resList = new ArrayList<>();

                    StringBuilder standBuilder = new StringBuilder();
                    for(AttributetblDto1 attributetblDto1 : attributetblList){
                        for(Attribute attribute1 : attributeList){
                            if(attributetblDto1.getId().equals(attribute1.getAttributeid())){
                                if (attribute1.getValue() != null && !attribute1.getValue().equals("")){
                                    standBuilder.append(attribute1.getValue());
                                }

                                if(i != attributeList.size()){
                                    standBuilder.append(attributetblDto1.getConnector());
                                }

                                i++;

                                resList.add(attribute1);
                            }
                        }
                    }

                    System.out.println(standBuilder);

                    Products updateP = new Products();
                    updateP.setId(products.getId());
                    updateP.setStandard(standBuilder.toString());
                    updateP.setAttribute(GsonUtils.toJson(resList));

                    //        紧固件|内六角圆柱头螺钉|DIN912|M5*0.8*17|不锈钢304|奥展|A2-70|本色|1.2千/盒、6盒/箱
                    updateP.setProdstr(products.getLevel1()+"|"+products.getLevel2()+"|"+products.getLevel3()+"|"+products.getProductname()+"|"+updateP.getStandard()
                            +"|"+products.getMaterial()+products.getCardnum()+"|"+products.getMark()+"|"+products.getSurfacetreatment()+"|"+products.getPackagetype());

                    productsService.updateByPrimaryKeySelective(updateP);
                }

                System.out.println( pageNo++);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        StringBuilder sb = new StringBuilder();
        ids.forEach(id->{
            sb.append(id).append(",");
        });

        System.out.println(sb);
    }



    @Test
    public void repairAttvalue(){
        List<Map> list = appTaskMapper.getAttrValue();

        for(Map map : list){
            String attv = map.get("paramvalue").toString();
            if(map.get("name").equals("公称直径")) {
                if (attv.startsWith("#")) {
                    attv = attv.substring(1, attv.length()) + "#";
                }else if(attv.endsWith("\"") || attv.endsWith("″")){
                    attv = attv.substring(0, attv.length()-1);
                }else if(attv.startsWith("φ") || attv.startsWith("∮")){
                    attv = "Ф"+attv.substring(1, attv.length());
                }
            }else if(map.get("name").equals("长度")){
                if(attv.endsWith("\"") || attv.endsWith("″") || attv.endsWith("\"")) {
                    attv = attv.substring(0, attv.length() - 1);
                }
            }

            Attvalue attvalue = new Attvalue();
            attvalue.setId((Long) map.get("id"));
            attvalue.setParamvalue(attv);
            attvalueService.updateById(attvalue);
            System.out.println(attv);
        }
    }


    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductAttrService productAttrService;

    @Test
    public void reapirProduct(){
        ProductInfoExample example = new ProductInfoExample();
        example.setOrderByClause("id desc");
        ProductInfoExample.Criteria criteria = example.createCriteria();
        criteria.andProducttypeEqualTo("紧固件");
        int pageNo = 1;
        int pageSize = 200;
        PageInfo pageInfo = productInfoService.getByPage(example,pageNo,pageSize);
        int total = pageInfo.getPages();

        while (pageNo < total){
            pageInfo = productInfoService.getByPage(example,pageNo,pageSize);
            List<ProductInfo> list = pageInfo.getList();
            pageNo++;

            for(ProductInfo info : list){
                String stand = info.getStand();
                String[] attrArr = stand.split("\\*");
                StringBuilder stringBuilder = new StringBuilder();
                for(String attv : attrArr){
                    if (attv.startsWith("#")) {
                        attv = attv.substring(1, attv.length()) + "#";
                    }else if(attv.endsWith("\"") || attv.endsWith("″")){
                        attv = attv.substring(0, attv.length()-1);
                    }else if(attv.startsWith("φ") || attv.startsWith("∮")){
                        attv = "Ф"+attv.substring(1, attv.length());
                    }else if(attv.endsWith("\"") || attv.endsWith("″")) {
                        attv = attv.substring(0, attv.length() - 1);
                    }
                    stringBuilder.append(attv).append("*");
                }


                stand =  stringBuilder.deleteCharAt(stringBuilder.length()-1).toString();

                if(attrArr.length == 1){
                    Products products = productsService.getById(info.getProductid());
                    if(products != null && products.getStandard() != null && products.getStandard().replaceAll("\\*","").equalsIgnoreCase(stand)){
                        stand = products.getStandard();
                    }
                }

                ProductInfo updateP = new ProductInfo();
                updateP.setId(info.getId());
                updateP.setStand(stand);
                productInfoService.updateByPrimaryKeySelective(updateP);
            }

        }
    }



    @Test
    public void setSort(){
        List<String> list = appTaskMapper.getGroupByStand();

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();

        for(String stand : list){
            String[] attrArr = stand.split("\\*");
            if(attrArr.length == 1){
                list1.add(stand);
            }else if (attrArr.length == 2){
                list2.add(stand);
            }else if (attrArr.length == 3){
                list3.add(stand);
            }
        }


        for(String stand : list3){
            System.out.println(stand);
        }

    }

}
