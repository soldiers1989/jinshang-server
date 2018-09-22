package project.jinshang.mod_server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_member.MemberMapper;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_product.bean.Categories;
import project.jinshang.mod_product.bean.OrderProduct;
import project.jinshang.mod_product.service.OrdersService;
import project.jinshang.mod_server.ServerSetMapper;
import project.jinshang.mod_server.bean.*;

import java.math.BigDecimal;
import java.util.*;

@Service
public class MemberServerService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private ServerSetMapper serverSetMapper;

    @Autowired
    private OrdersService ordersService;


    public void updateMember(Member member) {
        memberMapper.updateByPrimaryKeySelective(member);
    }

    public Member getMemberById(Long id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取服务商列表
     *
     * @param param
     * @return
     */
    public PageInfo getMemberServerList(ServerQueryParam param) {
        PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<ServerPageModel> list = memberMapper.getMemberServerList(param);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }


    /**
     * 获取单个服务商信息
     *
     * @param id
     * @return
     */
    public ServerPageModel getSingleMemberById(Long id) {

        ServerSetExample serverSetExample = new ServerSetExample();

        serverSetExample.createCriteria().andMemberidEqualTo(id);

        List<ServerSet> list = serverSetMapper.selectByExample(serverSetExample);

        ServerPageModel serverPageModel = memberMapper.getSingleMemberById(id);
        serverPageModel.setList(list);

        return serverPageModel;
    }

    public void saveServerSet(ServerSet serverSet) {
        serverSetMapper.insertSelective(serverSet);
    }


    public void updateServerSet(ServerSet serverSet) {
        serverSetMapper.updateByPrimaryKeySelective(serverSet);
    }

    public void deleteServerSet(Long id) {
        serverSetMapper.deleteByPrimaryKey(id);
    }

    /**
     * 获取服务商结算列表
     *
     * @param param
     * @return
     */
    public PageInfo getServerPayList(ServerPayQueryParam param) {
        PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<ServerPayPageModel> list = serverSetMapper.getServerPayList(param);
        for(ServerPayPageModel sppm :list){
            if(sppm.getCompanyname()!=null){
                sppm.setServername(sppm.getCompanyname());
            }
        }
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 服务商结算excel
     *
     * @param param
     * @return
     */
    public List<Map<String, Object>> getExcelExportServerPay(ServerPayQueryParam param) {
        List<ServerPayPageModel> list = serverSetMapper.getServerPayList(param);
        List<Map<String, Object>> list1 = new ArrayList<>();

        for (ServerPayPageModel serverPayPageModel : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("服务商", serverPayPageModel.getServername());
            if(StringUtils.hasText(serverPayPageModel.getUsername())){
                map.put("服务商",serverPayPageModel.getUsername());
            }
            if(StringUtils.hasText(serverPayPageModel.getRealname())){
                map.put("服务商",serverPayPageModel.getRealname());
            }
            if(StringUtils.hasText(serverPayPageModel.getCompanyname())){
                map.put("服务商",serverPayPageModel.getCompanyname());
            }
            map.put("地区", serverPayPageModel.getArea());
            map.put("省市", serverPayPageModel.getProvince() + " " + serverPayPageModel.getCity() + " " + serverPayPageModel.getArea());
            map.put("服务费占比%", serverPayPageModel.getRate());
            map.put("服务费", serverPayPageModel.getSum());
            list1.add(map);
        }
        return list1;
    }


    /**
     * 获取结算列表总额
     *
     * @param param
     * @return
     */
    public BigDecimal getTotalSum(ServerPayQueryParam param) {
        BigDecimal totalSum = new BigDecimal(0);
        List<ServerPayPageModel> list = serverSetMapper.getServerPayList(param);
        for (ServerPayPageModel serverPayPageModel : list) {
            totalSum = totalSum.add(serverPayPageModel.getSum());
        }
        return totalSum;
    }

    /**
     * 获取结算详情列表
     *
     * @param param
     * @return
     */
    public PageInfo getServerOrderModelList(SettleQueryParam param) {
        ServerSet serverSet = serverSetMapper.selectByPrimaryKey(param.getId());
        if (serverSet != null) {
            param.setArea(serverSet.getArea());
            param.setCity(serverSet.getCity());
            param.setProvince(serverSet.getProvince());
            param.setRate(serverSet.getRate());
        }
        PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<ServerOrderModel> list = serverSetMapper.getServerOrderModelList(param);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 获取结算详情excel
     *
     * @param param
     * @return
     */
    public List<Map<String, Object>> getExcelExportServerOrder(SettleQueryParam param) {

        ServerSet serverSet = serverSetMapper.selectByPrimaryKey(param.getId());
        if (serverSet != null) {
            param.setArea(serverSet.getArea());
            param.setCity(serverSet.getCity());
            param.setProvince(serverSet.getProvince());
            param.setRate(serverSet.getRate());
        }

        BigDecimal rate = serverSet.getRate().multiply(new BigDecimal(0.01));
        List<ServerOrderModel> list = serverSetMapper.getServerOrderModelList(param);

        List<Map<String, Object>> list1 = new ArrayList<>();

        for (ServerOrderModel serverOrderModel : list) {
            Long orderid = serverOrderModel.getId();
            Date createTime = serverOrderModel.getCreatetime();
            Date finishTime = serverOrderModel.getBuyerinspectiontime();
            String orderno = serverOrderModel.getOrderno();
            String area = serverOrderModel.getArea();
            String memmbername = serverOrderModel.getMembername();
            Long memberid = serverOrderModel.getMemberid();
            BigDecimal orderpay = serverOrderModel.getActualpayment().subtract(serverOrderModel.getFreight());
            List<OrderProduct> orderProducts = ordersService.getOrderProductByOrderId(orderid);
            for (OrderProduct orderProduct : orderProducts) {
                if (orderProduct.getBackstate() == Quantity.STATE_0) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("日期", createTime);
                    map.put("地区", area);
                    map.put("买家", memmbername);
                    map.put("会员号", memberid);
                    map.put("完成时间", finishTime);

                    Categories categories = ordersService.getCategories(orderProduct.getClassifyid());
                    //服务费率
                    BigDecimal servicesrate = categories.getServicesrate().multiply(new BigDecimal(0.01));

                    BigDecimal serverpay = (orderProduct.getActualpayment().subtract(orderProduct.getFreight())).multiply(servicesrate).multiply(rate);

                    map.put("服务费", serverpay);
                    map.put("订单号", orderno);
                    map.put("订单金额", orderpay);
                    map.put("商品金额", orderProduct.getActualpayment().subtract(orderProduct.getFreight()));
                    map.put("商品", orderProduct.getPdname());
                    map.put("服务分类", orderProduct.getClassify());
                    map.put("服务费利率", categories.getServicesrate() + "%");
                    map.put("服务费占比", serverSet.getRate() + "%");
                    list1.add(map);
                }
            }
        }

        return list1;
    }

    /**
     * 获取结算详情总额
     *
     * @param param
     * @return
     */
    public BigDecimal getTotalPay(SettleQueryParam param) {

        ServerSet serverSet = serverSetMapper.selectByPrimaryKey(param.getId());
        if (serverSet != null) {
            param.setArea(serverSet.getArea());
            param.setCity(serverSet.getCity());
            param.setProvince(serverSet.getProvince());
            param.setRate(serverSet.getRate());
        }
        List<ServerOrderModel> list = serverSetMapper.getServerOrderModelList(param);
        BigDecimal totalPay = new BigDecimal(0);
        for (ServerOrderModel serverOrderModel : list) {
            totalPay = totalPay.add(serverOrderModel.getServerpay());
        }
        return totalPay;
    }

    public ServerSet getServerSer(Long id) {
        return serverSetMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据地区获取地区服务费设置
     *
     * @param area
     * @param city
     * @param province
     * @return
     */
    public ServerSet getSingelServerSetByArea(String area, String city, String province, Long memberid) {
        return serverSetMapper.getSingelServerSetByArea(area, city, province, memberid);
    }

    /**
     * 根据用户id删除地区服务费设置
     *
     * @param memberid
     */
    public void deleteServerSetByMemberId(Long memberid) {
        ServerSetExample serverSetExample = new ServerSetExample();
        serverSetExample.createCriteria().andMemberidEqualTo(memberid);
        serverSetMapper.deleteByExample(serverSetExample);
    }

    /**
     * 获取服务商下拉列表
     *
     * @return
     */
    public List<ServerSet> getServerSetList() {
        List<ServerSet> list = serverSetMapper.getServerSetList();
        for(ServerSet ss:list){
            if(ss.getCompanyname()!=null){
                ss.setServername(ss.getCompanyname());
            }
        }
        return list;
    }

}
