package project.jinshang.mod_product.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Orders {

    private Long id;

    private Long memberid;

    @ApiModelProperty(notes = "卖家id")
    private Long saleid;

    @ApiModelProperty(notes = "订单编号")
    private String orderno;

    @ApiModelProperty(notes = "创建时间")
    private Date createtime;

    @ApiModelProperty(notes = "省")
    private String province;

    @ApiModelProperty(notes = "市")
    private String city;

    @ApiModelProperty(notes = "区")
    private String area;

    @ApiModelProperty(notes = "收货人地址")
    private String receivingaddress;

    @ApiModelProperty(notes = "运费")
    private BigDecimal freight;

    @ApiModelProperty(notes = "是否包邮")
    private Boolean mailornot;

    @ApiModelProperty(notes = "远期定金")
    private BigDecimal deposit;

    @ApiModelProperty(notes = "远期余款")
    private BigDecimal balance;

    @ApiModelProperty(notes = "远期发货时间")
    private Date futuretime;

    @ApiModelProperty(notes = "订单总价")
    private BigDecimal totalprice;

    @ApiModelProperty(notes = "积分")
    private Integer integral;

    @ApiModelProperty(notes = "合同号")
    private String code;

    @ApiModelProperty(notes = "提货方式 0=自提 1=包邮")
    private Short deliverymode;

    @ApiModelProperty(notes = "订单类型0=立即发货1=远期全款2=远期定金3=远期余款")
    private Short ordertype;

    @ApiModelProperty(notes = "仓库名称")
    private String storename;

    @ApiModelProperty(notes = "仓库id")
    private Long storeid;

    @ApiModelProperty(notes = "交易号")
    private String transactionnumber;

    @ApiModelProperty(notes = "是否已结算货款0=未结算1=已结算")
    private Short paymentmethod;

    @ApiModelProperty(notes = "付款时间")
    private Date paymenttime;

    @ApiModelProperty(notes = "发货时间")
    private Date sellerdeliverytime;

    @ApiModelProperty(notes = "发货号")
    private String deliveryno;

    @ApiModelProperty(notes = "买家确认收货时间")
    private Date buyerdeliverytime;

    @ApiModelProperty(notes = "买家确认验货时间")
    private Date buyerinspectiontime;

    @ApiModelProperty(notes = "订单状态0=待付款1=待发货3=待收货4=待验货5=已完成7=已关闭8=备货中9=备货完成")
    private Short orderstatus;

    @ApiModelProperty(notes = "实付款")
    private BigDecimal actualpayment;

    @ApiModelProperty(notes = "快递单号")
    private String couriernumber;

    @ApiModelProperty(notes = "物流公司")
    private String logisticscompany;

    @ApiModelProperty(notes = "是否需要开票0=不开1=开")
    private Short isbilling;

    @ApiModelProperty(notes = "开票类型0=纸质1=电子")
    private Short billingtype;

    @ApiModelProperty(notes = "总订单标识")
    private String uuid;

    @ApiModelProperty(notes = "卖家公司名称")
    private String membercompany;

    @ApiModelProperty(notes = "订单类型0=普通1=线下2=限时购")
    private Short isonline;

    @ApiModelProperty(notes = "全款")
    private BigDecimal allpay;

    @ApiModelProperty(notes = "收货人电话")
    private String phone;

    @ApiModelProperty(notes = "收货人姓名")
    private String shipto;

    @ApiModelProperty(notes = "授信是否已还款0=未还1=已还")
    private Short isbackcredit;

    @ApiModelProperty(notes = "支付方式0=支付宝1=微信2=银行卡3=余额4=授信")
    private Short paytype;

    @ApiModelProperty(notes = "买家名称")
    private String membername;

    @ApiModelProperty(notes = "卖家名称")
    private String sellername;

    @ApiModelProperty(notes = "店铺名称")
    private String shopname;

    @ApiModelProperty(notes = "佣金")
    private BigDecimal brokepay;

    @ApiModelProperty(notes = "冻结金额")
    private BigDecimal frozepay;

    @ApiModelProperty(notes = "服务费")
    private BigDecimal serverpay;

    @ApiModelProperty(notes = "余款uuid")
    private String yuuuid;

    @ApiModelProperty(notes = "延期天数")
    private Integer delaydays;

    @ApiModelProperty(notes = "延期状态0=不延期1=申请延期2=买家同意延期3=买家不同意延期")
    private Short ifdelay;

    @ApiModelProperty(notes = "延期发货违约金")
    private BigDecimal delaypenalty;

    @ApiModelProperty(notes = "订单时间，微信，支付宝，银行卡用")
    private Long ordertime;

    @ApiModelProperty(notes = "订单时间，微信，支付宝，银行卡用(余款)")
    private Long yuordertime;

    @ApiModelProperty(notes = "是否需要发货单，1-需要，0-不需要")
    private Short deliverybill;

    @ApiModelProperty(notes = "第三方支付交易号")
    private  String transactionid;

    @ApiModelProperty(notes = "原因，如订单关闭原因")
    private String reason;


    @ApiModelProperty(notes = "发货方式 0-卖家直发，1-平台代发")
    private  Short deliverytype;


    @ApiModelProperty(notes = "卖家是否已向平台开票 0-未开，1-已开")
    private Short billtoserver;

    //---

    //---wms 新增字段
    private String invoiceName;//购货单位名称

    private String buyercompanyname; //买家公司名称

    private String buyerRealname; //买家真实姓名

    private List<OrderProduct> orderProducts;

    //添加业务员
    @ApiModelProperty(notes = "业务员")
    private String clerkname;
    ///业务员联系方式
    @ApiModelProperty(notes = "业务员")
    private String clerknamephone;

    public Long getYuordertime() {
        return yuordertime;
    }

    public void setYuordertime(Long yuordertime) {
        this.yuordertime = yuordertime;
    }

    public Long getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Long ordertime) {
        this.ordertime = ordertime;
    }

    public BigDecimal getDelaypenalty() {
        return delaypenalty;
    }

    public void setDelaypenalty(BigDecimal delaypenalty) {
        this.delaypenalty = delaypenalty;
    }

    public Integer getDelaydays() {
        return delaydays;
    }

    public void setDelaydays(Integer delaydays) {
        this.delaydays = delaydays;
    }

    public Short getIfdelay() {
        return ifdelay;
    }

    public void setIfdelay(Short ifdelay) {
        this.ifdelay = ifdelay;
    }

    public String getYuuuid() {
        return yuuuid;
    }

    public void setYuuuid(String yuuuid) {
        this.yuuuid = yuuuid;
    }

    public BigDecimal getBrokepay() {
        return brokepay;
    }

    public void setBrokepay(BigDecimal brokepay) {
        this.brokepay = brokepay;
    }

    public BigDecimal getFrozepay() {
        return frozepay;
    }

    public void setFrozepay(BigDecimal frozepay) {
        this.frozepay = frozepay;
    }

    public BigDecimal getServerpay() {
        return serverpay;
    }

    public void setServerpay(BigDecimal serverpay) {
        this.serverpay = serverpay;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername;
    }

    public Short getIsbackcredit() {
        return isbackcredit;
    }

    public void setIsbackcredit(Short isbackcredit) {
        this.isbackcredit = isbackcredit;
    }

    public Short getPaytype() {
        return paytype;
    }

    public void setPaytype(Short paytype) {
        this.paytype = paytype;
    }

    public Short getIsonline() {
        return isonline;
    }

    public void setIsonline(Short isonline) {
        this.isonline = isonline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public Long getSaleid() {
        return saleid;
    }

    public void setSaleid(Long saleid) {
        this.saleid = saleid;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getReceivingaddress() {
        return receivingaddress;
    }

    public void setReceivingaddress(String receivingaddress) {
        this.receivingaddress = receivingaddress == null ? null : receivingaddress.trim();
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public Boolean getMailornot() {
        return mailornot;
    }

    public void setMailornot(Boolean mailornot) {
        this.mailornot = mailornot;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getFuturetime() {
        return futuretime;
    }

    public void setFuturetime(Date futuretime) {
        this.futuretime = futuretime;
    }

    public BigDecimal getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Short getDeliverymode() {
        return deliverymode;
    }

    public void setDeliverymode(Short deliverymode) {
        this.deliverymode = deliverymode;
    }

    public Short getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(Short ordertype) {
        this.ordertype = ordertype;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename == null ? null : storename.trim();
    }

    public Long getStoreid() {
        return storeid;
    }

    public void setStoreid(Long storeid) {
        this.storeid = storeid;
    }

    public String getTransactionnumber() {
        return transactionnumber;
    }

    public void setTransactionnumber(String transactionnumber) {
        this.transactionnumber = transactionnumber == null ? null : transactionnumber.trim();
    }

    public Short getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(Short paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    public Date getPaymenttime() {
        return paymenttime;
    }

    public void setPaymenttime(Date paymenttime) {
        this.paymenttime = paymenttime;
    }

    public Date getSellerdeliverytime() {
        return sellerdeliverytime;
    }

    public void setSellerdeliverytime(Date sellerdeliverytime) {
        this.sellerdeliverytime = sellerdeliverytime;
    }

    public String getDeliveryno() {
        return deliveryno;
    }

    public void setDeliveryno(String deliveryno) {
        this.deliveryno = deliveryno == null ? null : deliveryno.trim();
    }

    public Date getBuyerdeliverytime() {
        return buyerdeliverytime;
    }

    public void setBuyerdeliverytime(Date buyerdeliverytime) {
        this.buyerdeliverytime = buyerdeliverytime;
    }

    public Date getBuyerinspectiontime() {
        return buyerinspectiontime;
    }

    public void setBuyerinspectiontime(Date buyerinspectiontime) {
        this.buyerinspectiontime = buyerinspectiontime;
    }

    public Short getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(Short orderstatus) {
        this.orderstatus = orderstatus;
    }

    public BigDecimal getActualpayment() {
        return actualpayment;
    }

    public void setActualpayment(BigDecimal actualpayment) {
        this.actualpayment = actualpayment;
    }

    public String getCouriernumber() {
        return couriernumber;
    }

    public void setCouriernumber(String couriernumber) {
        this.couriernumber = couriernumber;
    }

    public String getLogisticscompany() {
        return logisticscompany;
    }

    public void setLogisticscompany(String logisticscompany) {
        this.logisticscompany = logisticscompany == null ? null : logisticscompany.trim();
    }

    public Short getIsbilling() {
        return isbilling;
    }

    public void setIsbilling(Short isbilling) {
        this.isbilling = isbilling;
    }

    public Short getBillingtype() {
        return billingtype;
    }

    public void setBillingtype(Short billingtype) {
        this.billingtype = billingtype;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getMembercompany() {
        return membercompany;
    }

    public void setMembercompany(String membercompany) {
        this.membercompany = membercompany == null ? null : membercompany.trim();
    }

    public BigDecimal getAllpay() {
        return allpay;
    }

    public void setAllpay(BigDecimal allpay) {
        this.allpay = allpay;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShipto() {
        return shipto;
    }

    public void setShipto(String shipto) {
        this.shipto = shipto;
    }

    public Short getDeliverybill() {
        return deliverybill;
    }

    public void setDeliverybill(Short deliverybill) {
        this.deliverybill = deliverybill;
    }

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public String getReason() {
        return reason;
    }

    public Orders setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public Short getDeliverytype() {
        return deliverytype;
    }

    public void setDeliverytype(Short deliverytype) {
        this.deliverytype = deliverytype;
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName;
    }

    public Short getBilltoserver() {
        return billtoserver;
    }

    public void setBilltoserver(Short billtoserver) {
        this.billtoserver = billtoserver;
    }

    public String getBuyercompanyname() {
        return buyercompanyname;
    }

    public void setBuyercompanyname(String buyercompanyname) {
        this.buyercompanyname = buyercompanyname;
    }

    public String getBuyerRealname() {
        return buyerRealname;
    }

    public void setBuyerRealname(String buyerRealname) {
        this.buyerRealname = buyerRealname;
    }

    public String getClerkname() {
        return clerkname;
    }

    public void setClerkname(String clerkname) {
        this.clerkname = clerkname;
    }

    public String getClerknamephone() {
        return clerknamephone;
    }

    public void setClerknamephone(String clerknamephone) {
        this.clerknamephone = clerknamephone;
    }
}