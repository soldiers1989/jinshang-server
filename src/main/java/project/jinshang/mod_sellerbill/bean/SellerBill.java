package project.jinshang.mod_sellerbill.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class SellerBill {
    private Long id;

    private String no;

    private Short state;

    private BigDecimal money;

    private Long sellerid;

    private String adduser;

    private Date addtime;

    private Object billjson;

    private String billtype;

    private Date billcreatetime;

    private String sellerusername;

    private String sellercompanyname;

    private Integer ordernum;

    private BigDecimal totalorders;

    private BigDecimal totalbrokepay;

    @ApiModelProperty(notes = "1= 订单发票   2=违约金发票")
    private Short type;

    @ApiModelProperty(notes = "违约金数量")
    private Integer breaknum;

    @ApiModelProperty(notes = "违约金总金额")
    private BigDecimal totalbreak;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Long getSellerid() {
        return sellerid;
    }

    public void setSellerid(Long sellerid) {
        this.sellerid = sellerid;
    }

    public String getAdduser() {
        return adduser;
    }

    public void setAdduser(String adduser) {
        this.adduser = adduser == null ? null : adduser.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Object getBilljson() {
        return billjson;
    }

    public void setBilljson(Object billjson) {
        this.billjson = billjson;
    }

    public String getBilltype() {
        return billtype;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype == null ? null : billtype.trim();
    }

    public Date getBillcreatetime() {
        return billcreatetime;
    }

    public void setBillcreatetime(Date billcreatetime) {
        this.billcreatetime = billcreatetime;
    }

    public String getSellerusername() {
        return sellerusername;
    }

    public void setSellerusername(String sellerusername) {
        this.sellerusername = sellerusername == null ? null : sellerusername.trim();
    }

    public String getSellercompanyname() {
        return sellercompanyname;
    }

    public void setSellercompanyname(String sellercompanyname) {
        this.sellercompanyname = sellercompanyname == null ? null : sellercompanyname.trim();
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public BigDecimal getTotalorders() {
        return totalorders;
    }

    public void setTotalorders(BigDecimal totalorders) {
        this.totalorders = totalorders;
    }

    public BigDecimal getTotalbrokepay() {
        return totalbrokepay;
    }

    public void setTotalbrokepay(BigDecimal totalbrokepay) {
        this.totalbrokepay = totalbrokepay;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Integer getBreaknum() {
        return breaknum;
    }

    public void setBreaknum(Integer breaknum) {
        this.breaknum = breaknum;
    }

    public BigDecimal getTotalbreak() {
        return totalbreak;
    }

    public void setTotalbreak(BigDecimal totalbreak) {
        this.totalbreak = totalbreak;
    }
}