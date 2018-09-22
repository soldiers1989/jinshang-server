package project.jinshang.mod_product.service;


import mizuki.project.core.restserver.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import project.jinshang.common.utils.*;
import project.jinshang.mod_front.ProductFrontAction;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_product.bean.OrderStoreStateLog;
import project.jinshang.mod_product.bean.Orders;
import project.jinshang.mod_product.bean.dto.StoreState;
import project.jinshang.mod_product.mapper.OrderStoreStateLogMapper;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderStoreStateLogService {

    @Autowired
    private OrderStoreStateLogMapper orderStoreStateLogMapper;


    @Autowired
    private  OrdersService ordersService;


    @Value("${shop.self-support.id}")
    private  String self_support_id;

    @Value("${shop.aozhan-syn.id}")
    private String aozhansynid;

    @Value("${shop.jinshang-store-url}")
    private  String jinshangStoreUrl;


    @Value("${shop.aozhan-store-url}")
    private String aozhanStoreUrl;


    public OrderStoreStateLog getByOrderid(Long orderid){
        return  orderStoreStateLogMapper.getByOrderid(orderid);
    }


    public OrderStoreStateLog getByOrderno(String orderno){
        return  orderStoreStateLogMapper.getByOrderno(orderno);
    }


    public void add(OrderStoreStateLog log){
        orderStoreStateLogMapper.insertSelective(log);
    }


    public  void  updateByPrimaryKeySelective(OrderStoreStateLog log){
        orderStoreStateLogMapper.updateByPrimaryKeySelective(log);
    }



    public OrderStoreStateLog getStoreState(Orders orders) throws IOException {

        List<StoreState> list= new ArrayList<>();

        OrderStoreStateLog log = this.getByOrderid(orders.getId());
        if(log != null && log.getLaststate() != null && log.getLaststate() == 99){
            list = GsonUtils.toList(log.getStatejson().toString(),StoreState.class);
            list.sort(new StoreCompore());
            log.setList(list);
            return  log;
        }

        if(Arrays.asList(self_support_id.split("\\|")).contains(orders.getSaleid().toString())){
            String url = jinshangStoreUrl.replace("#orderno#",orders.getOrderno());

            String content =  HttpClientUtils.get(url);

            if(content != null && content.contains("body")){
               list = enJinshangWms(content);
            }
        }else if(Arrays.asList(aozhansynid.split("\\|")).contains(orders.getSaleid().toString())){
//            orders.setOrderno("D201805071021545608");
            String url = aozhanStoreUrl.replace("#orderno#",orders.getOrderno());
            System.out.println(url);
            String content = HttpClientUtils.get(url);
            //System.out.println(content);
            if(content != null){
                list = enAoZhanErp(content);
            }
        }

        if(list != null && list.size()>0) {
            list.sort(new StoreCompore());
        }

        if(log != null  && list.size()>0){
            log.setStatejson(GsonUtils.toJson(list));
            log.setLaststate(list.get(0).getState());
            orderStoreStateLogMapper.updateByPrimaryKeySelective(log);
            log.setList(list);
        }else if(log == null && list.size()>0){
            log = new OrderStoreStateLog();
            log.setStatejson(GsonUtils.toJson(list));
            log.setOrderid(orders.getId());
            log.setOrderno(orders.getOrderno());
            log.setLaststate(list.get(0).getState());
            orderStoreStateLogMapper.insertSelective(log);
            log.setList(list);
        }

        return  log;
    }



    private List<StoreState> enAoZhanErp(String content){
        List<StoreState> list = new ArrayList<>();

        if(CommonUtils.isGoodJson(content)){
            Map<String,Object> map = GsonUtils.toMap(content);
            if((Double)map.getOrDefault("retCode",-1) == 1){
                List<Map> jsonMap = (List<Map>) map.get("data");

                for(Map m : jsonMap){
                    StoreState ss = new StoreState();
//                    ss.setState(Short.parseShort(m.get("state").toString()));

                    ss.setState(((Double)m.get("state")).shortValue());

                    ss.setOperateUser(m.get("operateUser").toString());
                    ss.setCreateTime(m.get("createTime").toString());
                    ss.setPointName(m.get("pointName").toString());
                    list.add(ss);

                }
            }
        }
        return  list;
    }



    private  List<StoreState> enJinshangWms(String content){
        List<StoreState> list = new ArrayList<>();

        if(CommonUtils.isGoodJson(content)){
            Map<String,Object> map = GsonUtils.toMap(content);

            String bodyStr =  map.get("body").toString();

            List<Map> bodyList = GsonUtils.toList(bodyStr,Map.class);

            for(Map stateMap : bodyList){
                StoreState storeState = new StoreState();
                storeState.setPointName(stateMap.get("pointName").toString().trim());
                storeState.setCreateTime(DateUtils.format(DateUtils.StrToDate(stateMap.get("createTime").toString()),"yyyy-MM-dd HH:mm:ss"));
                storeState.setOperateUser(stateMap.get("createUserName").toString());
                storeState.setState(this.getState(storeState));
                list.add(storeState);
            }
        }


        return  list;
    }



    private Short getState(StoreState storeState){
         if(storeState.getPointName().equals("待配货")) {
             return 1;
         }else if(storeState.getPointName().equals("已拣货")) {
             return 2;
         }else if (storeState.getPointName().equals("已打物流单")) {
             return 3;
         }else if(storeState.getPointName().equals("已出库")){
             return 99;
        }

        return  -1;
    }



    private  class  StoreCompore implements  Comparator<StoreState>{
        @Override
        public int compare(StoreState o1, StoreState o2) {
            if(o1.getState()>o2.getState()) {
                return -1;
            }else{
                return  1;
            }
        }
    }

}


