package project.jinshang.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_member.service.AdminService;
import project.jinshang.mod_product.bean.Orders;
import project.jinshang.mod_product.service.OrdersService;

import java.util.List;

public class ThreadForUpdateOrders {
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private AdminService adminService;

    private long id;

    private long adminid;

     public long getId() { return id; }
     public void setId(long id) { this.id = id; }
    public long getAdminid() {
        return adminid;
    }
    public void setAdminid(long adminid) {
        this.adminid = adminid;
    }

    public ThreadForUpdateOrders(long id, long adminid) {
        this.id = id;
        this.adminid = adminid;
    }

    public void run() {
        // 查询所有会员的订单
        List<Orders> ordersList=ordersService.findOrdersByuserid(id);

        Admin admininfo=adminService.getById(adminid);

        //更新所有订单的业务员和手机号
         for (Orders orders:ordersList){
         orders.setClerkname(admininfo.getRealname());
         orders.setClerknamephone(admininfo.getMobile());
        ordersService.updateOrders(orders);
         //更新失敗的 跳过 继续循环 记录错误的id(订单)
        }

    }
}
