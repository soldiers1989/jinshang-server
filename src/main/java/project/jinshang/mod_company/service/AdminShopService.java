package project.jinshang.mod_company.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_company.SellerCompanyInfoMapper;
import project.jinshang.mod_company.bean.SellerCompanyInfo;
import project.jinshang.mod_product.OrdersMapper;
import project.jinshang.mod_product.bean.Categories;
import project.jinshang.mod_product.bean.Orders;
import project.jinshang.mod_product.bean.OrdersExample;
import project.jinshang.mod_product.service.CategoriesService;
import project.jinshang.mod_shop.service.ShopGradeService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminShopService {
    @Autowired
    private SellerCompanyInfoMapper sellerCompanyInfoMapper;
    @Autowired
    private ShopGradeService shopGradeService;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private OrdersMapper ordersMapper;

    /**
     * 商铺信息列表
     * @param companyname
     * @param username
     * @param shopgradeid
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo listShop( String companyname,String username,int shopgradeid,int validate,int pageNo,int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        SellerCompanyInfo sellerCompanyInfo=new SellerCompanyInfo();
        if (shopgradeid!=0){
          String gradename=shopGradeService.selectByPrimaryKey(shopgradeid).getGradename();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("companyname",companyname);
        map.put("username",username);
        map.put((String) "shopgradeid",shopgradeid);
        map.put("validate",validate);
        List<Map>list =sellerCompanyInfoMapper.selectShop(map);
        list.stream().forEach(map1 -> {
            BigDecimal broker=Quantity.BIG_DECIMAL_0;
            List<Orders> ordersList=new ArrayList<>();
            long saleid= (long) map1.get("memberid");
            OrdersExample example=new OrdersExample();
            OrdersExample.Criteria criteria=example.createCriteria();
            criteria.andSaleidEqualTo(saleid);
            criteria.andOrderstatusEqualTo(Quantity.STATE_5);
            ordersList=ordersMapper.selectByExample(example);
            if (ordersList.size()>0){
                for (Orders orders:ordersList) {
                    broker=broker.add(orders.getBrokepay());
                }
            }
            map1.put("broker",broker);
        });
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 获取商家佣金
     * @param sellerid
     * @return
     */
    public BigDecimal getSellerSumBokerBySellerId(Long sellerid){
        return sellerCompanyInfoMapper.getSellerSumBokerBySellerId(sellerid);
    }


    /**
     * 后台导出商家Excel
     * @param companyname
     * @param username
     * @param shopgradeid
     * @param validate
     * @return
     */
    public List<Map<String,Object>> listShopForAdminExcel( String companyname,String username,int shopgradeid,int validate){
        SellerCompanyInfo sellerCompanyInfo=new SellerCompanyInfo();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("companyname",companyname);
        map.put("username",username);
        map.put((String) "shopgradeid",shopgradeid);
        map.put("validate",validate);
        List<Map<String,Object>>list =sellerCompanyInfoMapper.selectShopForAdminExcel(map);


        List<Map<String,Object>> data = new ArrayList<>();

        for(Map<String,Object> m : list){
            HashMap<String,Object> resMap =  new HashMap<>();
            resMap.put("商家编号",m.get("id"));
            resMap.put("店铺名称",m.get("companyname"));

//            StringBuilder prodtype =  new StringBuilder();
//            Long[] businesscategoryArr = (Long[]) m.get("businesscategory");
//            if(businesscategoryArr != null){
//                for(Long catid : businesscategoryArr){
//                    Categories categories =  categoriesService.getById(catid);
//                    if(categories != null){
//                        prodtype.append(categories.getName()).append("  ");
//                    }
//                }
//            }

 //           resMap.put("产品类型",prodtype.toString());
            resMap.put("商家账号",m.get("username"));
            resMap.put("商家所在地", StringUtils.nvl(m.get("province"))+
                    StringUtils.nvl(m.get("city"))+
                    StringUtils.nvl(m.get("citysmall")));
            resMap.put("详细地址",m.get("address"));
            resMap.put("商家等级",m.get("gradename"));
            resMap.put("创店时间",m.get("createdate"));
            resMap.put("状态",(Integer)m.get("shopstate")==0? "开启":"关闭" );
            resMap.put("公司名称",m.get("companyname"));
            resMap.put("公司电话",m.get("companytel"));
            resMap.put("联系电话",m.get("linkmantel"));
            resMap.put("员工数",m.get("employeenum"));
            resMap.put("注册资金",m.get("regfound"));
            resMap.put("营业执照号",m.get("businesslicencenumber"));

            resMap.put("执照有效期",m.get("businesslicencestart")+"-"+m.get("businesslicenceend"));
            resMap.put("法定经营范围",m.get("businessscope"));
            resMap.put("银行账号",m.get("bankaccount"));
            resMap.put("开户银行",m.get("bankname"));
            resMap.put("支付宝账号",m.get("alipayno"));
            resMap.put("微信号",m.get("wxno"));
            resMap.put("纳税登记号",m.get("taxRegistrationno"));
            resMap.put("纳税人识别号",m.get("taxregistrationcertificate"));

            data.add(resMap);
        }

        return  data;
    }






    /**
     * 根据id查询商家店铺的详细信息
     * @param id
     * @return
     */
    public SellerCompanyInfo  selectShopInfoByid(long id){
        SellerCompanyInfo sellerCompanyInfo=sellerCompanyInfoMapper.selectByPrimaryKey(id);
        return  sellerCompanyInfo;
    }

    /**
     * 编辑商铺
     */
    public  void updateSellerCompanyInfo (SellerCompanyInfo sellerCompanyInfo){
        sellerCompanyInfoMapper.updateByPrimaryKeySelective(sellerCompanyInfo);
    }
}
