package project.jinshang.scheduled;


import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.jinshang.common.exception.CashException;

import project.jinshang.common.utils.DateUtils;
import project.jinshang.mod_fx.bean.*;
import project.jinshang.mod_fx.service.*;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.service.MemberService;
import project.jinshang.mod_product.bean.Categories;
import project.jinshang.mod_product.bean.OrderProduct;
import project.jinshang.mod_product.bean.OrderQueryParam;
import project.jinshang.mod_product.bean.Orders;
import project.jinshang.mod_product.bean.dto.OrdersView;
import project.jinshang.mod_product.service.CategoriesService;
import project.jinshang.mod_product.service.OrderProductServices;
import project.jinshang.mod_product.service.OrdersService;

import java.math.BigDecimal;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;


/**
 * create : zzy
 * date : 2018/05/07
 */
@Component
@Transactional(rollbackFor = Exception.class)
@Profile({"test","pro"})
public class OrderTask {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private MemberService memberService;
    @Autowired
    private OrderProductServices orderProductServices;
    @Autowired
    private FxCategoriesService fxCategoriesService;
    @Autowired
    private FxCommisionService fxCommisionService;
    @Autowired
    private FxCirculationService fxCirculationService;
    @Autowired
    private FxStationService fxStationService;
    @Autowired
    private FxMoneyListService fxMoneyListService;
    @Autowired
    private FxMoneyService fxMoneyService;
    @Autowired
    private CategoriesService categoriesService;


    /**
     * 每天半夜4点执行一次 每天只能执行一次否则会有重复记录
     *
     * @throws CashException
     */

    //@Scheduled(cron = "0 16 15 * * ?")
    // @Scheduled(cron = "0 0/3 * * * ?")
    @Scheduled(cron = "0 0 4 * * ?")
    public void Order() throws CashException {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date starttime = cal.getTime();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date endtime = calendar.getTime();


      /*  Date date = new Date();//取时间
        Calendar calendar1 = new GregorianCalendar();
        calendar1.setTime(date);
        calendar1.add(calendar1.DATE, 1);//把日期往前减少一天，若想把日期向后推一天则将负数改为正数
        calendar1.set(Calendar.HOUR_OF_DAY, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND, 0);
        Date endtime = calendar1.getTime();*/


        //查询出昨天0点到24点之间付款，且状态正常的订单
        Fxstation fxstation1 =fxStationService.getStationInfo();
        if(fxstation1.getDisable()==false) {
            System.out.println("系统默认开启三级分销");
            List<Orders> orderlist = ordersService.getRegularOrders(starttime, endtime);

            //判断下单人是否有介绍人，即其“inviterid”是否为空，若不为空则继续，若为空则跳过该订单；
            for (Orders orders : orderlist) {
                Member member = memberService.getMemberById(orders.getMemberid());
                if (null == member.getInviterid()) {
                    System.out.println("介绍人为空则跳过该订单");
                    continue;
                }
                System.out.println("进入遍历订单关联商品");
                //不为空则 订单遍历该订单关联商品
                List<OrderProduct> orderProductList = orderProductServices.getByOrderNo(orders.getOrderno());

                BigDecimal commisionprice1 = new BigDecimal(0);
                BigDecimal commisionprice2 = new BigDecimal(0);
                //生成type=1 的记录
                Fxcommision fxcommision1 = new Fxcommision();
                //生成type=2 的记录
                Fxcommision fxcommision2 = new Fxcommision();

                // 返佣帐号ID 根据订单买家ID，读取其“inviterid”，查询该邀请人ID
                Member ordermember = memberService.getInviterIdByMemberId(orders.getMemberid());
                //一级返佣账号
                Member member1 = memberService.getInviterIdByMemberId(ordermember.getInviterid());
                //再查询是否有 根据一级返佣帐号判断并读取二级返佣帐号
                Member member2 = memberService.getInviterIdByMemberId(member1.getInviterid());

                for (OrderProduct orderProduct : orderProductList) {
                    //部分退货的时候 Classifyid有可能为空
                    if (orderProduct.getClassifyid() == null) {
                        continue;
                    }

                    Fxcategories fxcategories = fxCategoriesService.getCategoriesInfoById(orderProduct.getClassifyid());
                    commisionprice1 = commisionprice1.add(getCommisionPrice(orderProduct, fxcategories));
                    if (null != member2) {
                        Fxstation fxstation = fxStationService.getStationInfo();
                        commisionprice2 = commisionprice1.multiply(fxstation.getRatio2().divide(new BigDecimal(100)));
                    }
                }

                fxcommision1.setCommisionprice(commisionprice1);
                fxcommision1.setOrderid(orders.getId());
                fxcommision1.setOrderno(orders.getOrderno());
                fxcommision1.setMemberid(orders.getMemberid());
                fxcommision1.setSaleid(orders.getSaleid());
                fxcommision1.setCmemberid(member1.getId());
                fxcommision1.setType(1l);
                fxcommision1.setOrdercreatetime(orders.getCreatetime());
                fxcommision1.setTotalprice(orders.getTotalprice());
                fxcommision1.setCommisionprice(commisionprice1);
                fxcommision1.setProgressnum(1l);
                fxcommision1.setCreatetime(new Date());
                fxCommisionService.insertFxcommision(fxcommision1);
                //佣金流转表 流转记录
                Fxcirculation fxcirculation = new Fxcirculation();
                fxcirculation.setCommisionid(fxcommision1.getId());
                fxcirculation.setOrderid(orders.getId());
                fxcirculation.setOrderno(orders.getOrderno());
                fxcirculation.setMemberid(orders.getMemberid());
                fxcirculation.setSaleid(orders.getSaleid());
                fxcirculation.setCirculationtext(orders.getOrderno() + "一级佣金核算中");
                fxcirculation.setCirculationold(0l);
                fxcirculation.setCirculationnew(1l);
                fxcirculation.setCreatetime(new Date());
                fxCirculationService.insertFxcirculation(fxcirculation);

                if (null != member2) {
                    fxcommision2.setCommisionprice(commisionprice2);
                    fxcommision2.setOrderid(orders.getId());
                    fxcommision2.setOrderno(orders.getOrderno());
                    fxcommision2.setMemberid(orders.getMemberid());
                    fxcommision2.setSaleid(orders.getSaleid());
                    fxcommision2.setCmemberid(member2.getId());
                    fxcommision2.setType(2l);
                    fxcommision2.setOrdercreatetime(orders.getCreatetime());
                    fxcommision2.setTotalprice(orders.getTotalprice());
                    fxcommision2.setProgressnum(1l);
                    fxcommision2.setCreatetime(new Date());
                    fxCommisionService.insertFxcommision(fxcommision2);
                    //佣金流转表 流转记录
                    Fxcirculation fxcirculation_two = new Fxcirculation();
                    fxcirculation_two.setCommisionid(fxcommision2.getId());
                    fxcirculation_two.setOrderid(orders.getId());
                    fxcirculation_two.setOrderno(orders.getOrderno());
                    fxcirculation_two.setMemberid(orders.getMemberid());
                    fxcirculation_two.setSaleid(orders.getSaleid());
                    fxcirculation_two.setCirculationtext(orders.getOrderno() + "二级佣金核算中");
                    fxcirculation_two.setCirculationold(0l);
                    fxcirculation_two.setCirculationnew(1l);
                    fxcirculation_two.setCreatetime(new Date());
                    fxCirculationService.insertFxcirculation(fxcirculation_two);
                }
            }
        }

    }


    private BigDecimal getCommisionPrice(OrderProduct orderProduct, Fxcategories fxcategories) {
        BigDecimal commisionprice = new BigDecimal(0);
        if (null == fxcategories) {
            //如果查询到为空则查找上一级parentid
            Categories categories = categoriesService.getById(orderProduct.getClassifyid());
            Fxcategories fxcategories_two = fxCategoriesService.getCategoriesInfoById(categories.getParentid());
            if (null == fxcategories_two) {
                //如果查询到还是为空则继续查找上一级parentid
                Categories categories1 = categoriesService.getById(categories.getParentid());
                Fxcategories fxcategories_three = fxCategoriesService.getCategoriesInfoById(categories1.getParentid());
                commisionprice = commisionprice.add(orderProduct.getNum().multiply(orderProduct.getPrice()).multiply(fxcategories_three.getRatio().divide(new BigDecimal(100))));
            } else {
                commisionprice = commisionprice.add(orderProduct.getNum().multiply(orderProduct.getPrice()).multiply(fxcategories_two.getRatio().divide(new BigDecimal(100))));
            }

        } else {
            //不为空则为当前查出的佣金比例
            commisionprice = commisionprice.add(orderProduct.getNum().multiply(orderProduct.getPrice()).multiply(fxcategories.getRatio().divide(new BigDecimal(100))));
        }
        return commisionprice;
    }


    /**
     * 每天早晨5点执行一次  这个定时任务要在order之后
     *
     * @throws CashException
     */
    //@Scheduled(cron = "0 40 16 * * ? ")
    //@Scheduled(cron = "0 00 18 * * ? ")
    //@Scheduled(cron = "0 0/3 * * * ?")
    @Scheduled(cron = "0 0 5 * * ?")
    public void executeReturn() throws CashException {
        System.out.println("执行返佣");
        //查询每天遍历progressNum为1和2的返佣记录
        Fxstation fxstation1 = fxStationService.getStationInfo();
        if(fxstation1.getDisable()==false) {
            System.out.println("系统默认开启三级分销");
            List<Fxcommision> fxcommisionList = fxCommisionService.getFxcommisionList();

            //查询佣金返现周期
            Fxstation fxstation = fxStationService.getStationInfo();
            int cycle = fxstation.getCycle().intValue();
            for (Fxcommision fxcommision : fxcommisionList) {

                //查询是否订单为5.订单已完成
                Orders orders = ordersService.getOrdersById(fxcommision.getOrderid());
                if (1 == fxcommision.getProgressnum()) {
                    if (5 == orders.getOrderstatus()) {
                        recount1(fxstation, cycle, fxcommision, orders);

                        //佣金流转表 流转记录
                        Fxcirculation fxcirculation_two = new Fxcirculation();
                        fxcirculation_two.setCommisionid(fxcommision.getId());
                        fxcirculation_two.setOrderid(orders.getId());
                        fxcirculation_two.setOrderno(orders.getOrderno());
                        fxcirculation_two.setMemberid(orders.getMemberid());
                        fxcirculation_two.setSaleid(orders.getSaleid());
                        fxcirculation_two.setCirculationtext(orders.getOrderno() + "订单完成_执行返佣");
                        fxcirculation_two.setCirculationold(1l);
                        fxcirculation_two.setCirculationnew(2l);
                        fxcirculation_two.setCreatetime(new Date());
                        fxCirculationService.insertFxcirculation(fxcirculation_two);

                    } else if (7 == orders.getOrderstatus()) {
                        //订单被关闭，关闭相应返佣记录
                        Fxcommision updatefxcommision = new Fxcommision();
                        updatefxcommision.setId(fxcommision.getId());
                        updatefxcommision.setProgressnum(97l);
                        BigDecimal zero = new BigDecimal(0);
                        updatefxcommision.setCommisionprice(zero);
                        fxCommisionService.updateByFxcommision(updatefxcommision);

                        Fxcirculation fxcirculation_two = new Fxcirculation();
                        fxcirculation_two.setCommisionid(fxcommision.getId());
                        fxcirculation_two.setOrderid(orders.getId());
                        fxcirculation_two.setOrderno(orders.getOrderno());
                        fxcirculation_two.setMemberid(orders.getMemberid());
                        fxcirculation_two.setSaleid(orders.getSaleid());
                        fxcirculation_two.setCirculationtext(orders.getOrderno() + "订单关闭_不执行返佣");
                        fxcirculation_two.setCirculationold(1l);
                        fxcirculation_two.setCirculationnew(97l);
                        fxcirculation_two.setCreatetime(new Date());
                        fxCirculationService.insertFxcirculation(fxcirculation_two);


                    } else {
                        //如果没有已完成则
                        System.out.println("没有已完成或者关闭的订单，不需要执行任何操作");
                        continue;
                    }

                } else if (2 == fxcommision.getProgressnum()) {
                    //返佣入账
                    //当前时间减去买家确认验货时间  是否大于返佣周期天数
                    System.out.println("当前时间减去买家确认验货时间  是否大于返佣周期天数");
                    int days = DateUtils.diffDays(new Date(), fxcommision.getBuyerinspectiontime());
                    if (days - cycle > 0) {


                        Fxcommision updatefxcommision = new Fxcommision();
                        updatefxcommision.setId(fxcommision.getId());
                        updatefxcommision.setProgressnum(99l);
                        fxCommisionService.updateByFxcommision(updatefxcommision);

                        //流转记录
                        Fxcirculation fxcirculation_two = new Fxcirculation();
                        fxcirculation_two.setCommisionid(fxcommision.getId());
                        fxcirculation_two.setOrderid(orders.getId());
                        fxcirculation_two.setOrderno(orders.getOrderno());
                        fxcirculation_two.setMemberid(orders.getMemberid());
                        fxcirculation_two.setSaleid(orders.getSaleid());
                        fxcirculation_two.setCirculationtext(orders.getOrderno() + "超过返佣周期天数_更新为已完成,进行返佣");
                        fxcirculation_two.setCirculationold(2l);
                        fxcirculation_two.setCirculationnew(97l);
                        fxcirculation_two.setCreatetime(new Date());
                        fxCirculationService.insertFxcirculation(fxcirculation_two);


                        Fxmoneylist fxmoneylist = new Fxmoneylist();
                        fxmoneylist.setMemberid(fxcommision.getCmemberid());//返佣人ID
                        fxmoneylist.setListtype(1l);
                        fxmoneylist.setListtext(orders.getOrderno() + "佣金入账");
                        fxmoneylist.setMoneynum(fxcommision.getCommisionprice());
                        fxmoneylist.setMoneytotal(fxcommision.getCommisionprice());
                        fxmoneylist.setCreatetime(new Date());
                        fxMoneyListService.insertFxmoneylist(fxmoneylist);


                        //判断在佣金余额表 是否已经存入过返佣人id
                        Fxmoney oldfxmoney = fxMoneyService.getByCMemberId(fxmoneylist.getMemberid());
                        Fxmoney fxmoney = new Fxmoney();
                        if (oldfxmoney != null) {
                            fxmoney.setId(oldfxmoney.getId());
                            BigDecimal moneytotal = new BigDecimal(0);
                            moneytotal = oldfxmoney.getMoneytotal().add(fxmoneylist.getMoneytotal());
                            fxmoney.setMoneytotal(moneytotal);
                            fxMoneyService.updateByFxmoney(fxmoney);
                        } else {
                            fxmoney.setMemberid(fxcommision.getCmemberid());
                            BigDecimal moneytotal = new BigDecimal(0);
                            moneytotal = moneytotal.add(fxmoneylist.getMoneytotal());
                            fxmoney.setMoneytotal(moneytotal);
                            fxMoneyService.insertFxmoney(fxmoney);
                        }

                    }

                } else {
                    //如果没有
                    System.out.println("没有订单进行中和佣金核算中，不需要执行任何操作");
                    continue;
                }

            }
        }

    }

    private void recount(Fxstation fxstation, int cycle, Fxcommision fxcommision, Orders orders) {
        //Progressnum 为2：佣金核算中
        Fxcommision updatefxcommision = new Fxcommision();
        updatefxcommision.setId(fxcommision.getId());
        //重新商品核算
        List<OrderProduct> orderProductList = orderProductServices.getByOrderNo(orders.getOrderno());
        BigDecimal commisionprice = new BigDecimal(0);
        for (OrderProduct orderProduct : orderProductList) {

            //根据商品分类佣金比例，核算出佣金，并插入数据库表 佣金记录表 fx_commision；

            // 返佣帐号ID 根据订单买家ID，读取其“inviterid”，查询该邀请人ID
            Member ordermember = memberService.getInviterIdByMemberId(orders.getMemberid());
            //一级返佣账号
            Member member1 = memberService.getInviterIdByMemberId(ordermember.getInviterid());
            //再查询是否有 根据一级返佣帐号判断并读取二级返佣帐号
            Member member2 = memberService.getInviterIdByMemberId(member1.getInviterid());

            BigDecimal zero = new BigDecimal(0);
            //部分退货 重新核算佣金 （一定要在一级二级 重新核算前）
            if (orderProduct.getBackstate() == 3) {
                //部分退货的不做核算，也不能直接关闭
                System.out.println("部分退货不做任何处理");
                continue;
            }
            if (orderProduct.getBackstate() != 3) {
                Fxcategories fxcategories1 = fxCategoriesService.getCategoriesInfoById(orderProduct.getClassifyid());
                //需要做分类取上一级
                if (fxcommision.getType() == 1) {

                        commisionprice = getCommisionPrice(orderProduct, fxcategories1);
                        Fxcommision fxcommision1 = new Fxcommision();
                        fxcommision1.setId(fxcommision.getId());
                        fxcommision1.setCommisionprice(commisionprice);
                        fxCommisionService.updateByFxcommision(fxcommision1);
                        updateTimeAndCheck(cycle, orders, updatefxcommision, zero);


                }
                if (fxcommision.getType() == 2) {
                    //二级分佣重新核算

                        BigDecimal commisionprice_two = new BigDecimal(0);
                        commisionprice = getCommisionPrice(orderProduct, fxcategories1);
                        commisionprice_two = commisionprice.multiply((fxstation.getRatio2().divide(new BigDecimal(100))));
                        Fxcommision fxcommision2 = new Fxcommision();
                        fxcommision2.setId(fxcommision.getId());
                        fxcommision2.setCommisionprice(commisionprice_two);
                        fxCommisionService.updateByFxcommision(fxcommision2);
                        updateTimeAndCheck(cycle, orders, updatefxcommision, zero);


                }
            }

        }
    }



    private void recount1(Fxstation fxstation, int cycle, Fxcommision fxcommision, Orders orders) {
        //Progressnum 为2：佣金核算中
        Fxcommision updatefxcommision = new Fxcommision();
        updatefxcommision.setId(fxcommision.getId());
        //重新商品核算
        // 返佣帐号ID 根据订单买家ID，读取其“inviterid”，查询该邀请人ID
        Member ordermember = memberService.getInviterIdByMemberId(orders.getMemberid());
        //一级返佣账号
        Member member1 = memberService.getInviterIdByMemberId(ordermember.getInviterid());
        //再查询是否有 根据一级返佣帐号判断并读取二级返佣帐号
        Member member2 = memberService.getInviterIdByMemberId(member1.getInviterid());

        BigDecimal commisionprice1 = new BigDecimal(0);
        BigDecimal commisionprice2 = new BigDecimal(0);
        //生成type=1 的记录
        Fxcommision fxcommision1 = new Fxcommision();
        //生成type=2 的记录
        Fxcommision fxcommision2 = new Fxcommision();


        List<OrderProduct> orderProductList = orderProductServices.getByOrderNo(orders.getOrderno());
        BigDecimal commisionprice = new BigDecimal(0);
       for (OrderProduct orderProduct : orderProductList) {
            BigDecimal zero = new BigDecimal(0);
            //部分退货 重新核算佣金 （一定要在一级二级 重新核算前）
            if (orderProduct.getBackstate() == 3) {
                //部分退货的不做核算，也不能直接关闭
                System.out.println("部分退货不做任何处理");
                continue;
            }
            if (orderProduct.getBackstate() != 3) {
                Fxcategories fxcategories1 = fxCategoriesService.getCategoriesInfoById(orderProduct.getClassifyid());
                //需要做分类取上一级
                if (fxcommision.getType() == 1) {
                    commisionprice1 = commisionprice1.add(getCommisionPrice(orderProduct, fxcategories1));
                    fxcommision1.setId(fxcommision.getId());
                    fxcommision1.setCommisionprice(commisionprice1);
                    fxCommisionService.updateByFxcommision(fxcommision1);
                    updateTimeAndCheck(cycle, orders, updatefxcommision, zero);
                }
                if (fxcommision.getType() == 2) {
                    //二级分佣重新核算
                    BigDecimal commisionprice_two = new BigDecimal(0);
                    commisionprice1 = commisionprice1.add(getCommisionPrice(orderProduct, fxcategories1));
                    commisionprice_two = commisionprice1.multiply((fxstation.getRatio2().divide(new BigDecimal(100))));
                    fxcommision2.setId(fxcommision.getId());
                    fxcommision2.setCommisionprice(commisionprice_two);
                    fxCommisionService.updateByFxcommision(fxcommision2);
                    updateTimeAndCheck(cycle, orders, updatefxcommision, zero);
                }
            }
        }
    }



    private void updateTimeAndCheck(int cycle, Orders orders, Fxcommision updatefxcommision, BigDecimal commisionprice) {

        Date buyerinspectiontime = orders.getBuyerinspectiontime();
        updatefxcommision.setBuyerinspectiontime(orders.getBuyerinspectiontime());
        updatefxcommision.setProgressnum(2l);
        BigDecimal zero = new BigDecimal(0);
        if (commisionprice.compareTo(zero) > 0) {
            updatefxcommision.setCommisionprice(commisionprice);
        }
        if (buyerinspectiontime != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(orders.getBuyerinspectiontime());
            c.add(Calendar.DAY_OF_MONTH, cycle);// 加上返佣的天数
            Date time = c.getTime();
            updatefxcommision.setCommisiontime(time);
        }
        fxCommisionService.updateByFxcommision(updatefxcommision);
        System.out.println("更新 返佣时间和完成时间");
    }

    private void find(Orders orders) {
        List<OrderProduct> orderProductList = orderProductServices.getByOrderNo(orders.getOrderno());

        for (OrderProduct orderProduct : orderProductList) {
            System.out.println("查找该商品分类下 佣金比例 ");
            //查询出商品所属分类(用于商品所属分类返佣比例)  去fx_categories表查询这个分类设置的佣金比例
            Fxcategories fxcategories = fxCategoriesService.getCategoriesInfoById(orderProduct.getClassifyid());

            //根据商品分类佣金比例，核算出佣金，并插入数据库表 佣金记录表 fx_commision；
            Fxcommision fxcommision = new Fxcommision();
            // 返佣帐号ID 根据订单买家ID，读取其“inviterid”，查询该邀请人ID
            Member ordermember = memberService.getInviterIdByMemberId(orders.getMemberid());
            //一级返佣账号
            Member member1 = memberService.getInviterIdByMemberId(ordermember.getInviterid());
            //再查询是否有 根据一级返佣帐号判断并读取二级返佣帐号
            Member member2 = memberService.getInviterIdByMemberId(member1.getInviterid());
            System.out.println("一级二级佣金账号查找完毕");
        }
    }

    /**
     * 每天上午8点执行一次
     *
     */
    @Scheduled(cron = "0 0 8 * * ?")
    public void futureOrderPrepareRemind(){
        LocalDate localDate = LocalDate.now();
        OrderQueryParam queryFutureParam = new OrderQueryParam();
        queryFutureParam.setPresellconfim((short)1);
        PageInfo allMemberFutureOrdersList = ordersService.getAllMemberOrdersList(queryFutureParam);
        List<OrdersView> list = allMemberFutureOrdersList.getList();
        for (OrdersView futureOrder:list){
            Date prestocktime = futureOrder.getPrestocktime();
            Instant instant = prestocktime.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();

            // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
            LocalDate newPrestocktime = instant.atZone(zoneId).toLocalDate();
            String forwardnoticephone = futureOrder.getForwardnoticephone();
            if (prestocktime.equals(localDate) || (localDate.plus(3,ChronoUnit.DAYS)).compareTo(newPrestocktime) == 0){
                List<Orders> orderViewList = new ArrayList<>();
                for (OrdersView ordersView:list) {
                    Orders orders = new Orders();
                    //属性转换
                    orderViewList.add(orders);
                }
                ordersService.smsNotifySellerToFutureOrders(orderViewList);
            }

        }
    }

}
