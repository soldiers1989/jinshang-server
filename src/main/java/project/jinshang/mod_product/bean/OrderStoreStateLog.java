package project.jinshang.mod_product.bean;

import project.jinshang.mod_product.bean.dto.StoreState;

import java.util.List;

public class OrderStoreStateLog {
    private Long id;

    private Long orderid;

    private String orderno;

    private Short laststate;

    private Object statejson;


    private List<StoreState> list;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public Short getLaststate() {
        return laststate;
    }

    public void setLaststate(Short laststate) {
        this.laststate = laststate;
    }

    public Object getStatejson() {
        return statejson;
    }

    public void setStatejson(Object statejson) {
        this.statejson = statejson;
    }

    public List<StoreState> getList() {
        return list;
    }

    public void setList(List<StoreState> list) {
        this.list = list;
    }
}