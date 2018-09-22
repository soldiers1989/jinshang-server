package project.jinshang.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.GsonUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_company.bean.BuyerCompanyInfo;
import project.jinshang.mod_company.service.BuyerCompanyService;
import project.jinshang.mod_invoice.bean.InvoiceInfo;
import project.jinshang.mod_invoice.service.InvoiceService;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_product.bean.*;
import project.jinshang.mod_product.bean.dto.AttributetblDto1;
import project.jinshang.mod_product.service.*;
import project.jinshang.scheduled.mapper.AppTaskMapper;
import project.jinshang.service.AppTaskService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    private BuyerCompanyService buyerCompanyService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private AppTaskService appTaskService;


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



   // @Scheduled(cron = "*/5 * * * * ?")
    public void autoAddInvoiceInfo() {
        //BasicRet basicRet = new BasicRet();
        //Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        List<Member> members = memberService.getAllMember();
        for (Member member : members) {

            List<InvoiceInfo> invoiceInfos = invoiceService.getInvoiceInfoListByMemberId(member.getId());
            BuyerCompanyInfo buyerCompanyInfo = buyerCompanyService.getBuyerCompanyInfoByMemberId(member.getId());
            InvoiceInfo invoiceInfo = new InvoiceInfo();
            if (buyerCompanyInfo !=null && member.getCompany() && (invoiceInfos == null || invoiceInfos.size() == 0)) {
                invoiceInfo.setDefaultbill((short) 1);
                invoiceInfo.setMemberid(member.getId());
                invoiceInfo.setCreatedate(new Date());
                invoiceInfo.setUpdatedate(new Date());
                invoiceInfo.setAvailable(Quantity.STATE_0);
                invoiceInfo.setPhone(member.getMobile());
                invoiceInfo.setInvoiceheadup(buyerCompanyInfo.getCompanyname());
                invoiceInfo.setBankofaccounts(buyerCompanyInfo.getBankname());
                invoiceInfo.setTexno(buyerCompanyInfo.getTaxregistrationcertificate());
                invoiceInfo.setAccount(buyerCompanyInfo.getBankaccount());
                invoiceInfo.setAddress(buyerCompanyInfo.getProvince() + "" + buyerCompanyInfo.getCity() + "" + buyerCompanyInfo.getCitysmall() + "" + buyerCompanyInfo.getAddress());
                invoiceInfo.setReceiveaddress(buyerCompanyInfo.getProvince() + "" + buyerCompanyInfo.getCity() + "" + buyerCompanyInfo.getCitysmall() + "" + buyerCompanyInfo.getAddress());
                invoiceInfo.setLinkman(buyerCompanyInfo.getBankuser());

                invoiceService.addInvoiceInfo(invoiceInfo);
                System.out.println("定时任务已执行…………自动为没有发票信息的公司用户添加发票");
            }
        }
    }

    /**
     * 根据productinfo表中packagetype（包装方式）字段更新productstore表中intervalprice（区间价）字段
     */
    //@Scheduled(cron = "0 */5 * * * ?")
    @Scheduled(cron = "0 05 18 14 6 ?")
    public void updateProductStore(){
        List<Map<String,Object>> list = appTaskService.getProductinfoList();
        for(Map<String,Object> map:list){
                String packagetype = map.get("packagetype").toString().replace(" ","");
                //根据“|”截取字符串:0.450千/盒
                String[] typechar = packagetype.split("\\|");
                if(typechar.length==2) {//只有当包装方式这个字段的数据完整的情况下才更新区间价
                    String typechar1 = "";
                    String typechar2 = "" + typechar[1].charAt(0);
                    for (int i = 0; i < typechar[0].length(); i++) {
                        if ((typechar[0].charAt(i) >= 48 && typechar[0].charAt(i) <= 57) || typechar[0].charAt(i) == 46) {//只有字符是数字和小数点时添加
                            typechar1 += typechar[0].charAt(i);
                        }
                    }
                    BigDecimal bd1 = new BigDecimal(typechar1);
                    BigDecimal bd2 = new BigDecimal(typechar2);
                    BigDecimal b3 = bd1.multiply(bd2);
                    //[{"start":"1","end":"5","rate":"98"},{"start":"5","end":"10","rate":"97"},{"start":"10","end":"0","rate":"95"}]
                    List<String> jsonList = new ArrayList<String>();
                    for (int i = 0; i < 3; i++) {
                        //Map<String,Object> jsonMap = new HashMap<String,Object>();
                        String jsonStr = "";
                        if (i == 0) {
                            /*jsonMap.put("start", "0");
                            jsonMap.put("end", typechar1);
                            jsonMap.put("rate", "120");*/
                            jsonStr = "{\"start\": \"0\",\"end\": \"" + typechar1 + "\",\"rate\": \"110\"}";
                            jsonList.add(jsonStr);
                        } else if (i == 1) {
                            /*jsonMap.put("start", typechar1);
                            jsonMap.put("end", b3);
                            jsonMap.put("rate", "110");*/
                            jsonStr = "{\"start\": \"" + typechar1 + "\",\"end\": \"" + b3 + "\",\"rate\": \"103\"}";
                            jsonList.add(jsonStr);
                        } else {
                            /*jsonMap.put("start", b3);
                            jsonMap.put("end", 0);
                            jsonMap.put("rate", "100");*/
                            jsonStr = "{\"start\": \"" + b3 + "\",\"end\": \"0\",\"rate\": \"100\"}";
                            jsonList.add(jsonStr);
                        }

                    }
                    System.out.println(jsonList);
                    if (map.get("psid") != null) {
                        long psid = Long.parseLong(map.get("psid").toString());
                        appTaskService.updateProductStore(psid, jsonList.toString());//更新字段
                    }

                }
        }
    }

}
